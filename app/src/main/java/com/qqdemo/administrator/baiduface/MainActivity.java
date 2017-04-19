package com.qqdemo.administrator.baiduface;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.aip.face.AipFace;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    //设置APPID/AK/SK
    public static final String APP_ID = "9534369";
    public static final String API_KEY = "YVRXAcxFGRFbYFTSGmp8KPIm";
    public static final String SECRET_KEY = "rpUk9XoNV3xzBV6Hy5IzarLzY5s8H8yO";
    private static final String TAG = "MainActivity";
    private RelativeLayout mRelativeLayout;
    private MyView mImg;
    private Button mBtn, mSelect;
    private AipFace mClient;
    private Gson mGson;
    private Handler mHandler;
    private TextView mTxt;
    private Bitmap mBitmap;
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mGson = new Gson();

        initFaceClient();

        initViews();

        initHandle();


    }

    private void initHandle() {
        mHandler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                hideProgressDialog();
                MyInfoBean myInfoBean = (MyInfoBean) msg.obj;
                if (myInfoBean.getResult_num() != 0) {
                    Log.i(TAG, "run: 结果数量" + myInfoBean.getResult().size());
                    Log.i(TAG, "run: 人数量" + myInfoBean.getResult_num());
                    Log.i(TAG, "run: 年龄" + myInfoBean.getResult().get(0).getAge());
                    Log.i(TAG, "run: 性别" + myInfoBean.getResult().get(0).getGender());
                    Log.i(TAG, "run: 性别度" + myInfoBean.getResult().get(0).getGender_probability());
                    Log.i(TAG, "run: 人种" + myInfoBean.getResult().get(0).getRace());
                    Log.i(TAG, "run:美丑打分，范围0-1，越大表示越美 " + myInfoBean.getResult().get(0).getBeauty());
                    Log.i(TAG, "run:表情，0，不笑；1，微笑；2，大笑  " + myInfoBean.getResult().get(0).getExpression());
                    Log.i(TAG, "run:表情置信度 " + myInfoBean.getResult().get(0).getExpression_probablity());
                    Log.i(TAG, "run:是否带眼镜，0-无眼镜，1-普通眼镜，2-墨镜 " + myInfoBean.getResult().get(0).getGlasses());
                    Log.i(TAG, "run:眼镜置信度， " + myInfoBean.getResult().get(0).getGlasses_probability());
                    StringBuffer stringBuffer = new StringBuffer();
                    int num = 0;
                    stringBuffer.append("图中人数：" + myInfoBean.getResult_num() + "\t\n");
                    List<Rect> rects = new ArrayList<>();
                    for (MyInfoBean.ResultBean people : myInfoBean.getResult()) {
                        MyInfoBean.ResultBean.LocationBean location = people.getLocation();
                        int left = location.getLeft();
                        int top = location.getTop();
                        int width = location.getWidth();
                        int height = location.getHeight();
                        Rect rect = new Rect((int) (left), (int) (top), (int) ((width + left)), (int) ((height + top)));
                        rects.add(rect);

                        stringBuffer.append("==========" + "\t\n");
                        stringBuffer.append("人：" + (++num) + "\t\n");
                        stringBuffer.append("年龄：" + people.getAge() + "\t\n");
                        stringBuffer.append("性别：" + people.getGender() + "\t\n");
                        stringBuffer.append("人种：" + people.getRace() + "\t\n");
                        stringBuffer.append("美丑度：" + people.getBeauty() + "\t\n");
                        stringBuffer.append("表情：" + (people.getExpression() == 0 ? "不笑" : people.getExpression() == 1 ? "微笑" : "大笑") + "\t\n");
                        stringBuffer.append("眼镜：" + (people.getGlasses() == 0 ? "无眼镜" : people.getGlasses() == 1 ? "普通眼镜" : "墨镜") + "\t\n");
                        stringBuffer.append("==========" + "\t\n");
                    }
                    mImg.drawRect(rects);
                    mTxt.setText(stringBuffer.toString());
                } else {
                    Toast.makeText(MainActivity.this, "没有识别到人", Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });

    }

    private void initViews() {
        mImg = (MyView) findViewById(R.id.iv_img);
        mTxt = (TextView) findViewById(R.id.tv_txt);
        mBtn = (Button) findViewById(R.id.btn_shibie);
        mSelect = (Button) findViewById(R.id.btn_select);
        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mBitmap==null){
                    Toast.makeText(MainActivity.this, "还没有选择照片", Toast.LENGTH_SHORT).show();
                    return;
                }
                shibie();
                showProgressDialog("识别中···");
            }
        });
        mSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 10001);
            }
        });
    }

    private void shibie() {
        final HashMap<String, String> paraMap = new HashMap<String, String>();
        paraMap.put("face_fields", "age,beauty,expression,faceshape,gender,glasses,race");
        paraMap.put("max_face_num", "5");
        final byte[] bytes = Bitmap2Bytes(mBitmap);
        new Thread(new Runnable() {
            @Override
            public void run() {
                JSONObject res = mClient.detect(bytes, paraMap);
                Log.i(TAG, "run: " + res);
                final MyInfoBean myInfoBean = mGson.fromJson(res.toString(), MyInfoBean.class);

                if (myInfoBean.getError_code() == null) {
                    Message message = new Message();
                    message.obj = myInfoBean;
                    mHandler.sendMessage(message);
                } else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            hideProgressDialog();
                            Toast.makeText(MainActivity.this, "错误" + myInfoBean.getError_code() + "：" + myInfoBean.getError_msg(), Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            }
        }).start();

    }

    private void initFaceClient() {
        mClient = new AipFace(APP_ID, API_KEY, SECRET_KEY);
        // 可选：设置网络连接参数
        mClient.setConnectionTimeoutInMillis(2000);
        mClient.setSocketTimeoutInMillis(60000);
    }

    public byte[] Bitmap2Bytes(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
        return baos.toByteArray();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {

            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};

            Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();
            mBitmap = BitmapFactory.decodeFile(picturePath);
            mImg.clear();
            mTxt.setText("");
            mImg.setImageBitmap(mBitmap);
        }
    }
    void showProgressDialog(String msg){
        if(mProgressDialog==null){
            mProgressDialog=new ProgressDialog(this);
        }
        mProgressDialog.setMessage(msg);
        mProgressDialog.show();
    }
    void hideProgressDialog(){
        mProgressDialog.hide();
    }

}
