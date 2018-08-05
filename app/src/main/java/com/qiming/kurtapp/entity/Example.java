package com.qiming.kurtapp.entity;

import com.bluelinelabs.logansquare.annotation.JsonObject;

import java.util.List;

/**
 * Created by kurtg on 18/2/3.
 */
@JsonObject(fieldDetectionPolicy = JsonObject.FieldDetectionPolicy.NONPRIVATE_FIELDS_AND_ACCESSORS)
public class Example {

    private int resultCode;
    private int total;
    private String signatureServer;
    private List<ResultBean> searchResultList;

    public int getResultCode() { return resultCode;}

    public void setResultCode(int resultCode) { this.resultCode = resultCode;}

    public int getTotal() { return total;}

    public void setTotal(int total) { this.total = total;}

    public String getSignatureServer() { return signatureServer;}

    public void setSignatureServer(String signatureServer) { this.signatureServer = signatureServer;}

    public List<ResultBean> getSearchResultList() { return searchResultList;}

    public void setSearchResultList(List<ResultBean> searchResultList) {
        this.searchResultList = searchResultList;
    }

    @JsonObject(fieldDetectionPolicy = JsonObject.FieldDetectionPolicy.NONPRIVATE_FIELDS_AND_ACCESSORS)
    public static class ResultBean {

        /**
         * mediaId : 11013531699
         * mediaTitle : 奇门遁甲
         * appType : 1
         * categoryId : 1004
         * mediaSubTitle :
         * rate : 8.0
         * doubanRate :
         * total : 1
         * isFee : 1
         * venderId : 1002
         * timeLength :
         * poster : http://pic6.qiyipic.com/image/20180129/42/9e/v_114654279_m_601_m2_480_270.jpg
         * mark : http://picture-basemedia.hismarttv.com/console/uploaded_images/20171207105454482479vender.png
         * isBuy : 0
         * jumpParams : {"startupType":4,"startupUrl":[{"key":"startupType","value":1,"type":"int"},{"key":"packageName","value":"com.jamdeo.tv.vod","type":"String"},{"key":"className","value":"com.jamdeo.tv.vod.detail.ContentDetailActivity","type":"String"},{"key":"uri","value":"content://com.jamdeo.data.vod/content_detail?source=JAMDEO_CLOUD&programSeriesId=11013531699","type":"String"},{"key":"com.jamdeo.UiInterpreter.EXTRA_DETAIL_VIEW_TRIGGER","value":0,"type":"int"},{"key":"srcPackageName","value":"com.hisense.hicloud.edca","type":"String"},{"key":"realClass","value":"com.jamdeo.tv.vod.detail.ContentDetailActivity","type":"String"},{"key":"typecode","value":1002,"type":"int"},{"key":"resourcePackageName","value":"com.ju.unifiedsearch","type":"String"},{"key":"superAppParam","value":{"productCode":"1","id":"11013531699","name":"","typeCode":"1001","subTypeCode":"","packageVipId":"","albumType":""},"type":"String"},{"key":"resourceType","value":9005,"type":"String"}]}
         * current : 1
         * thirdDownloadUrl : {"package_name":"com.ktcp.video","app_name":"中国互联网电视","url":"tenvideo2://?action=1&stay_flag=1","cover_id":"jzhtr2cgy35ejz0"}
         */

        private long mediaId;
        private String mediaTitle;
        private int appType;
        private int categoryId;
        private String mediaSubTitle;
        private String rate;
        private String doubanRate;
        private String total;
        private String isFee;
        private String venderId;
        private String timeLength;
        private String poster;
        private String mark;
        private int isBuy;
        private String jumpParams;
        private String current;
        private ThirdDownloadUrlBean thirdDownloadUrl;

        public long getMediaId() { return mediaId;}

        public void setMediaId(long mediaId) { this.mediaId = mediaId;}

        public String getMediaTitle() { return mediaTitle;}

        public void setMediaTitle(String mediaTitle) { this.mediaTitle = mediaTitle;}

        public int getAppType() { return appType;}

        public void setAppType(int appType) { this.appType = appType;}

        public int getCategoryId() { return categoryId;}

        public void setCategoryId(int categoryId) { this.categoryId = categoryId;}

        public String getMediaSubTitle() { return mediaSubTitle;}

        public void setMediaSubTitle(String mediaSubTitle) { this.mediaSubTitle = mediaSubTitle;}

        public String getRate() { return rate;}

        public void setRate(String rate) { this.rate = rate;}

        public String getDoubanRate() { return doubanRate;}

        public void setDoubanRate(String doubanRate) { this.doubanRate = doubanRate;}

        public String getTotal() { return total;}

        public void setTotal(String total) { this.total = total;}

        public String getIsFee() { return isFee;}

        public void setIsFee(String isFee) { this.isFee = isFee;}

        public String getVenderId() { return venderId;}

        public void setVenderId(String venderId) { this.venderId = venderId;}

        public String getTimeLength() { return timeLength;}

        public void setTimeLength(String timeLength) { this.timeLength = timeLength;}

        public String getPoster() { return poster;}

        public void setPoster(String poster) { this.poster = poster;}

        public String getMark() { return mark;}

        public void setMark(String mark) { this.mark = mark;}

        public int getIsBuy() { return isBuy;}

        public void setIsBuy(int isBuy) { this.isBuy = isBuy;}

        public String getJumpParams() { return jumpParams;}

        public void setJumpParams(String jumpParams) { this.jumpParams = jumpParams;}

        public String getCurrent() { return current;}

        public void setCurrent(String current) { this.current = current;}

        public ThirdDownloadUrlBean getThirdDownloadUrl() { return thirdDownloadUrl;}

        public void setThirdDownloadUrl(ThirdDownloadUrlBean thirdDownloadUrl) {
            this.thirdDownloadUrl = thirdDownloadUrl;
        }

        @JsonObject(fieldDetectionPolicy = JsonObject.FieldDetectionPolicy.NONPRIVATE_FIELDS_AND_ACCESSORS)
        public static class ThirdDownloadUrlBean {

            /**
             * package_name : com.ktcp.video
             * app_name : 中国互联网电视
             * url : tenvideo2://?action=1&stay_flag=1
             * cover_id : jzhtr2cgy35ejz0
             */

            private String package_name;
            private String app_name;
            private String url;
            private String cover_id;

            public String getPackage_name() { return package_name;}

            public void setPackage_name(String package_name) { this.package_name = package_name;}

            public String getApp_name() { return app_name;}

            public void setApp_name(String app_name) { this.app_name = app_name;}

            public String getUrl() { return url;}

            public void setUrl(String url) { this.url = url;}

            public String getCover_id() { return cover_id;}

            public void setCover_id(String cover_id) { this.cover_id = cover_id;}

            @Override
            public String toString() {
                final StringBuffer sb = new StringBuffer("ThirdDownloadUrlBean{");
                sb.append("package_name:'").append(package_name).append('\'');
                sb.append(", app_name:'").append(app_name).append('\'');
                sb.append(", url:'").append(url).append('\'');
                sb.append(", cover_id:'").append(cover_id).append('\'');
                sb.append('}');
                return sb.toString();
            }
        }

        @Override
        public String toString() {
            final StringBuffer sb = new StringBuffer("ResultBean{");
            sb.append("mediaId:").append(mediaId);
            sb.append(", mediaTitle:'").append(mediaTitle).append('\'');
            sb.append(", appType:").append(appType);
            sb.append(", categoryId:").append(categoryId);
            sb.append(", mediaSubTitle:'").append(mediaSubTitle).append('\'');
            sb.append(", rate:'").append(rate).append('\'');
            sb.append(", doubanRate:'").append(doubanRate).append('\'');
            sb.append(", total:'").append(total).append('\'');
            sb.append(", isFee:'").append(isFee).append('\'');
            sb.append(", venderId:'").append(venderId).append('\'');
            sb.append(", timeLength:'").append(timeLength).append('\'');
            sb.append(", poster:'").append(poster).append('\'');
            sb.append(", mark:'").append(mark).append('\'');
            sb.append(", isBuy:").append(isBuy);
            sb.append(", jumpParams:'").append(jumpParams).append('\'');
            sb.append(", current:'").append(current).append('\'');
            sb.append(", thirdDownloadUrl:").append(thirdDownloadUrl);
            sb.append('}');
            return sb.toString();
        }
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Example{");
        sb.append("resultCode:").append(resultCode);
        sb.append(", total:").append(total);
        sb.append(", signatureServer:'").append(signatureServer).append('\'');
        sb.append(", searchResultList:").append(searchResultList);
        sb.append('}');
        return sb.toString();
    }
}
