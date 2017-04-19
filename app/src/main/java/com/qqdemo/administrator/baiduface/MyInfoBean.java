package com.qqdemo.administrator.baiduface;

import java.util.List;

/**
 * Created by Administrator on 2017/4/18.
 */

public class MyInfoBean {


    /**
     * result_num : 1
     * result : [{"location":{"left":270,"top":268,"width":370,"height":352},"face_probability":1,"rotation_angle":4,"yaw":-2.8324255943298,"pitch":5.0544576644897,"roll":3.8639903068542,"age":25.157718658447,"beauty":51.851783752441,"expression":0,"expression_probablity":1,"faceshape":[{"type":"square","probability":0.29369184374809},{"type":"triangle","probability":0.09810022264719},{"type":"oval","probability":0.18417382240295},{"type":"heart","probability":0.016066417098045},{"type":"round","probability":0.40796768665314}],"gender":"female","gender_probability":0.9999942779541,"glasses":0,"glasses_probability":0.99999308586121,"race":"yellow","race_probability":0.99976056814194}]
     * log_id : 2394502737
     */

    private int result_num;
    private long log_id;
    /**
     * location : {"left":270,"top":268,"width":370,"height":352}
     * face_probability : 1
     * rotation_angle : 4
     * yaw : -2.8324255943298
     * pitch : 5.0544576644897
     * roll : 3.8639903068542
     * age : 25.157718658447
     * beauty : 51.851783752441
     * expression : 0
     * expression_probablity : 1
     * faceshape : [{"type":"square","probability":0.29369184374809},{"type":"triangle","probability":0.09810022264719},{"type":"oval","probability":0.18417382240295},{"type":"heart","probability":0.016066417098045},{"type":"round","probability":0.40796768665314}]
     * gender : female
     * gender_probability : 0.9999942779541
     * glasses : 0
     * glasses_probability : 0.99999308586121
     * race : yellow
     * race_probability : 0.99976056814194
     */

    private List<ResultBean> result;
    /**
     * error_code : SDK108
     * error_msg : connection or read data time out
     */

    private String error_code;
    private String error_msg;

    public int getResult_num() {
        return result_num;
    }

    public void setResult_num(int result_num) {
        this.result_num = result_num;
    }

    public long getLog_id() {
        return log_id;
    }

    public void setLog_id(long log_id) {
        this.log_id = log_id;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public String getError_code() {
        return error_code;
    }

    public void setError_code(String error_code) {
        this.error_code = error_code;
    }

    public String getError_msg() {
        return error_msg;
    }

    public void setError_msg(String error_msg) {
        this.error_msg = error_msg;
    }

    public static class ResultBean {
        /**
         * left : 270
         * top : 268
         * width : 370
         * height : 352
         */

        private LocationBean location;
        private double face_probability;
        private int rotation_angle;
        private double yaw;
        private double pitch;
        private double roll;
        private double age;
        private double beauty;
        private int expression;
        private double expression_probablity;
        private String gender;
        private double gender_probability;
        private int glasses;
        private double glasses_probability;
        private String race;
        private double race_probability;
        /**
         * type : square
         * probability : 0.29369184374809
         */

        private List<FaceshapeBean> faceshape;

        public LocationBean getLocation() {
            return location;
        }

        public void setLocation(LocationBean location) {
            this.location = location;
        }

        public double getFace_probability() {
            return face_probability;
        }

        public void setFace_probability(double face_probability) {
            this.face_probability = face_probability;
        }

        public int getRotation_angle() {
            return rotation_angle;
        }

        public void setRotation_angle(int rotation_angle) {
            this.rotation_angle = rotation_angle;
        }

        public double getYaw() {
            return yaw;
        }

        public void setYaw(double yaw) {
            this.yaw = yaw;
        }

        public double getPitch() {
            return pitch;
        }

        public void setPitch(double pitch) {
            this.pitch = pitch;
        }

        public double getRoll() {
            return roll;
        }

        public void setRoll(double roll) {
            this.roll = roll;
        }

        public double getAge() {
            return age;
        }

        public void setAge(double age) {
            this.age = age;
        }

        public double getBeauty() {
            return beauty;
        }

        public void setBeauty(double beauty) {
            this.beauty = beauty;
        }

        public int getExpression() {
            return expression;
        }

        public void setExpression(int expression) {
            this.expression = expression;
        }

        public double getExpression_probablity() {
            return expression_probablity;
        }

        public void setExpression_probablity(double expression_probablity) {
            this.expression_probablity = expression_probablity;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public double getGender_probability() {
            return gender_probability;
        }

        public void setGender_probability(double gender_probability) {
            this.gender_probability = gender_probability;
        }

        public int getGlasses() {
            return glasses;
        }

        public void setGlasses(int glasses) {
            this.glasses = glasses;
        }

        public double getGlasses_probability() {
            return glasses_probability;
        }

        public void setGlasses_probability(double glasses_probability) {
            this.glasses_probability = glasses_probability;
        }

        public String getRace() {
            return race;
        }

        public void setRace(String race) {
            this.race = race;
        }

        public double getRace_probability() {
            return race_probability;
        }

        public void setRace_probability(double race_probability) {
            this.race_probability = race_probability;
        }

        public List<FaceshapeBean> getFaceshape() {
            return faceshape;
        }

        public void setFaceshape(List<FaceshapeBean> faceshape) {
            this.faceshape = faceshape;
        }

        public static class LocationBean {
            private int left;
            private int top;
            private int width;
            private int height;

            public int getLeft() {
                return left;
            }

            public void setLeft(int left) {
                this.left = left;
            }

            public int getTop() {
                return top;
            }

            public void setTop(int top) {
                this.top = top;
            }

            public int getWidth() {
                return width;
            }

            public void setWidth(int width) {
                this.width = width;
            }

            public int getHeight() {
                return height;
            }

            public void setHeight(int height) {
                this.height = height;
            }
        }

        public static class FaceshapeBean {
            private String type;
            private double probability;

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public double getProbability() {
                return probability;
            }

            public void setProbability(double probability) {
                this.probability = probability;
            }
        }
    }
}
