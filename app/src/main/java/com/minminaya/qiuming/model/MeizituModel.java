package com.minminaya.qiuming.model;
import java.io.Serializable;
import java.util.List;

public class MeizituModel implements Serializable {

    /**
     * picInfos : [{"picUrl":"http://mm.howkuai.com/wp-content/uploads/2017a/06/02/01.jpg","picTitle":null},{"picUrl":"http://mm.howkuai.com/wp-content/uploads/2017a/06/02/02.jpg","picTitle":null},{"picUrl":"http://mm.howkuai.com/wp-content/uploads/2017a/06/02/03.jpg","picTitle":null},{"picUrl":"http://mm.howkuai.com/wp-content/uploads/2017a/06/02/04.jpg","picTitle":null},{"picUrl":"http://mm.howkuai.com/wp-content/uploads/2017a/06/02/05.jpg","picTitle":null},{"picUrl":"http://mm.howkuai.com/wp-content/uploads/2017a/06/02/06.jpg","picTitle":null},{"picUrl":"http://mm.howkuai.com/wp-content/uploads/2017a/06/02/07.jpg","picTitle":null},{"picUrl":"http://mm.howkuai.com/wp-content/uploads/2017a/06/02/08.jpg","picTitle":null},{"picUrl":"http://mm.howkuai.com/wp-content/uploads/2017a/06/02/09.jpg","picTitle":null}]
     * webTitle : 我肯定在这样的健美教室，我可以待一天
     */
    private static final long serialVersionUID = -7620435178023928252L;

    private String webTitle;
    private List<PicInfosBean> picInfos;

    public String getWebTitle() {
        return webTitle;
    }

    public void setWebTitle(String webTitle) {
        this.webTitle = webTitle;
    }

    public List<PicInfosBean> getPicInfos() {
        return picInfos;
    }

    public void setPicInfos(List<PicInfosBean> picInfos) {
        this.picInfos = picInfos;
    }

    public class PicInfosBean implements Serializable{
        /**
         * picUrl : http://mm.howkuai.com/wp-content/uploads/2017a/06/02/01.jpg
         * picTitle : null
         */

        private static final long serialVersionUID = -762043517802392L;

        private String picUrl;
        private Object picTitle;

        public String getPicUrl() {
            return picUrl;
        }

        public void setPicUrl(String picUrl) {
            this.picUrl = picUrl;
        }

        public Object getPicTitle() {
            return picTitle;
        }

        public void setPicTitle(Object picTitle) {
            this.picTitle = picTitle;
        }
    }
}
