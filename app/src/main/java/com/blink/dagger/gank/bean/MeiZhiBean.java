package com.blink.dagger.gank.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lucky on 17-1-10.
 */
public class MeiZhiBean implements Parcelable {

    /**
     * error : false
     * results : [{"_id":"56cc6d1d421aa95caa7078ad","createdAt":"2015-09-15T08:47:12.714Z","desc":"9.16","publishedAt":"2015-09-16T03:34:05.100Z","type":"福利","url":"http://ww3.sinaimg.cn/large/7a8aed7bgw1ew38eojcpzj20p010kwjr.jpg","used":true,"who":"张涵宇"},{"_id":"5760a606421aa940f1b54acf","createdAt":"2016-06-15T08:49:10.942Z","desc":"本田翼","publishedAt":"2016-06-17T12:04:39.386Z","source":"chrome","type":"福利","url":"http://ww4.sinaimg.cn/large/610dc034jw1f4vmdn2f5nj20kq0rm755.jpg","used":true,"who":"代码家"},{"_id":"5840bd8a421aa939bb4637e9","createdAt":"2016-12-02T08:17:14.322Z","desc":"12-2","publishedAt":"2016-12-02T12:13:34.224Z","source":"chrome","type":"福利","url":"http://ww4.sinaimg.cn/large/610dc034gw1fac4t2zhwsj20sg0izahf.jpg","used":true,"who":"代码家"},{"_id":"56cc6d22421aa95caa707914","createdAt":"2015-10-12T01:24:51.340Z","desc":"10.12","publishedAt":"2015-10-14T03:19:21.608Z","type":"福利","url":"http://ww3.sinaimg.cn/large/7a8aed7bgw1ewy3cst6rzj20lx0v4wj5.jpg","used":true,"who":"张涵宇"}]
     */

    private boolean error;
    private List<ResultsBean> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public static class ResultsBean {
        /**
         * _id : 56cc6d1d421aa95caa7078ad
         * createdAt : 2015-09-15T08:47:12.714Z
         * desc : 9.16
         * publishedAt : 2015-09-16T03:34:05.100Z
         * type : 福利
         * url : http://ww3.sinaimg.cn/large/7a8aed7bgw1ew38eojcpzj20p010kwjr.jpg
         * used : true
         * who : 张涵宇
         * source : chrome
         */

        private String _id;
        private String createdAt;
        private String desc;
        private String publishedAt;
        private String type;
        private String url;
        private boolean used;
        private String who;
        private String source;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getPublishedAt() {
            return publishedAt;
        }

        public void setPublishedAt(String publishedAt) {
            this.publishedAt = publishedAt;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public boolean isUsed() {
            return used;
        }

        public void setUsed(boolean used) {
            this.used = used;
        }

        public String getWho() {
            return who;
        }

        public void setWho(String who) {
            this.who = who;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(this.error ? (byte) 1 : (byte) 0);
        dest.writeList(this.results);
    }

    public MeiZhiBean() {
    }

    protected MeiZhiBean(Parcel in) {
        this.error = in.readByte() != 0;
        this.results = new ArrayList<ResultsBean>();
        in.readList(this.results, ResultsBean.class.getClassLoader());
    }

    public static final Parcelable.Creator<MeiZhiBean> CREATOR = new Parcelable.Creator<MeiZhiBean>() {
        @Override
        public MeiZhiBean createFromParcel(Parcel source) {
            return new MeiZhiBean(source);
        }

        @Override
        public MeiZhiBean[] newArray(int size) {
            return new MeiZhiBean[size];
        }
    };
}
