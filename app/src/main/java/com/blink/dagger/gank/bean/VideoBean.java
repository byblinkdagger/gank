package com.blink.dagger.gank.bean;

import java.util.List;

/**
 * Created by blink_dagger on 17-1-20.
 */
public class VideoBean {

    /**
     * error : false
     * results : [{"_id":"586a5dd7421aa94db821c27b","createdAt":"2017-01-02T22:04:07.793Z","desc":"【科普】钢铁侠的10个经典装甲（上篇）","publishedAt":"2017-01-20T11:50:52.750Z","source":"chrome","type":"休息视频","url":"http://www.bilibili.com/video/av7746083/","used":true,"who":"LHF"},{"_id":"587f8aa2421aa91194ca0e3e","createdAt":"2017-01-18T23:32:50.542Z","desc":"【歪果仁研究邪会】第五会：致前任的一句话","publishedAt":"2017-01-19T11:40:09.118Z","source":"chrome","type":"休息视频","url":"http://www.bilibili.com/video/av7156916/","used":true,"who":"lxxself"},{"_id":"587b9cd9421aa9119a6ca67f","createdAt":"2017-01-16T00:01:29.768Z","desc":"泰国悲喜参半催泪爱情短片《男朋友死了》","publishedAt":"2017-01-16T14:12:18.71Z","source":"chrome","type":"休息视频","url":"http://www.vmovier.com/50577?from=search_post","used":true,"who":"lxxself"},{"_id":"587756ad421aa9315bfbe869","createdAt":"2017-01-12T18:13:01.888Z","desc":"8年前：大卫·鲍伊神准预测互联网的未来！","publishedAt":"2017-01-13T11:58:16.991Z","source":"chrome","type":"休息视频","url":"http://weibo.com/tv/v/ea8548b2ccf328d8f9daf15308e5cf8c?fid=1034:ea8548b2ccf328d8f9daf15308e5cf8c","used":true,"who":"lxxself"},{"_id":"5870fc6b421aa9315ea79917","createdAt":"2017-01-07T22:34:19.708Z","desc":"红色警戒2 10部队不造建筑打死7个冷酷电脑","publishedAt":"2017-01-12T11:30:59.369Z","source":"chrome","type":"休息视频","url":"http://www.bilibili.com/video/av7851064/","used":true,"who":"LHF"},{"_id":"5870fcd9421aa93161103dc2","createdAt":"2017-01-07T22:36:09.5Z","desc":"【战略PK学/干货巨大】中美关系历史恩怨（全程高能/超清）","publishedAt":"2017-01-11T12:05:20.787Z","source":"chrome","type":"休息视频","url":"http://www.bilibili.com/video/av7813988/","used":true,"who":"LHF"},{"_id":"58735b3a421aa9315bfbe848","createdAt":"2017-01-09T17:43:22.895Z","desc":"【请回答1988】请回答1988泪点盘点(这剧有毒）","publishedAt":"2017-01-10T11:33:19.525Z","source":"web","type":"休息视频","url":"http://www.bilibili.com/video/av7795685/","used":true,"who":"yolo.cc"},{"_id":"5870fc53421aa9316407fb7b","createdAt":"2017-01-07T22:33:55.739Z","desc":"【守望先锋】十个你绝不知道的隐藏技巧","publishedAt":"2017-01-09T11:46:59.821Z","source":"chrome","type":"休息视频","url":"http://www.bilibili.com/video/av7862259/","used":true,"who":"LHF"},{"_id":"586ef725421aa9315ea79907","createdAt":"2017-01-06T09:47:17.539Z","desc":"首富马云小品首秀，搭档宋小宝却当面把首富们都损了一遍","publishedAt":"2017-01-06T13:20:19.591Z","source":"chrome","type":"休息视频","url":"http://www.toutiao.com/i6372124630777332226/?tt_from=weixin&utm_campaign=client_share&app=news_article&utm_source=weixin&iid=7132594338&utm_medium=toutiao_android&wxshare_count=1","used":true,"who":"daimajia"},{"_id":"586da2cb421aa9315bfbe81d","createdAt":"2017-01-05T09:35:07.60Z","desc":"你不看足球，你就不会懂他们回家的意义。","publishedAt":"2017-01-05T13:18:10.185Z","source":"chrome","type":"休息视频","url":"http://weibo.com/tv/v/EiC91EKPB?fid=1034:2eb5141a5364676aba17ce36c13259df","used":true,"who":"lxxself"}]
     */

    private boolean error;
    /**
     * _id : 586a5dd7421aa94db821c27b
     * createdAt : 2017-01-02T22:04:07.793Z
     * desc : 【科普】钢铁侠的10个经典装甲（上篇）
     * publishedAt : 2017-01-20T11:50:52.750Z
     * source : chrome
     * type : 休息视频
     * url : http://www.bilibili.com/video/av7746083/
     * used : true
     * who : LHF
     */

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
        private String _id;
        private String createdAt;
        private String desc;
        private String publishedAt;
        private String source;
        private String type;
        private String url;
        private boolean used;
        private String who;

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

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
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
    }
}
