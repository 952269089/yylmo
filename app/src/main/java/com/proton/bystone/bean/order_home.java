package com.proton.bystone.bean;

import java.util.List;



/**
 * Created by Administrator on 2016/11/30 0030.
 */

public class order_home {


    /**
     * Disbursement : 121
     * createdAt : 2016-11-28 22:40:13
     * name : jT7w6668
     * objectId : 9bb2ab10f2
     * people_number : 11
     * pig :
     * price_unit : 11
     * specific1_descriptio : 技术
     * specific_description : 路上
     * updatedAt : 2016-11-28 22:40:13
     * working_hours : 147
     */

    private List<ResultsBean> results;

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public static class ResultsBean {
        private String Disbursement;
        private String createdAt;
        private String name;
        private String objectId;
        private String people_number;
        private String pig;
        private String price_unit;
        private String specific1_descriptio;
        private String specific_description;
        private String updatedAt;
        private String working_hours;
        private String xinyu;
        private String dianji;
        String city;



        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getDianji() {
            return dianji;
        }

        public void setDianji(String dianji) {
            this.dianji = dianji;
        }

        public String getXinyu() {
            return xinyu;
        }

        public void setXinyu(String xinyu) {
            this.xinyu = xinyu;
        }

        public String getDisbursement() {
            return Disbursement;
        }

        public void setDisbursement(String Disbursement) {
            this.Disbursement = Disbursement;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getObjectId() {
            return objectId;
        }

        public void setObjectId(String objectId) {
            this.objectId = objectId;
        }

        public String getPeople_number() {
            return people_number;
        }

        public void setPeople_number(String people_number) {
            this.people_number = people_number;
        }

        public String getPig() {
            return pig;
        }

        public void setPig(String pig) {
            this.pig = pig;
        }

        public String getPrice_unit() {
            return price_unit;
        }

        public void setPrice_unit(String price_unit) {
            this.price_unit = price_unit;
        }

        public String getSpecific1_descriptio() {
            return specific1_descriptio;
        }

        public void setSpecific1_descriptio(String specific1_descriptio) {
            this.specific1_descriptio = specific1_descriptio;
        }

        public String getSpecific_description() {
            return specific_description;
        }

        public void setSpecific_description(String specific_description) {
            this.specific_description = specific_description;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

        public String getWorking_hours() {
            return working_hours;
        }

        public void setWorking_hours(String working_hours) {
            this.working_hours = working_hours;
        }
    }
}
