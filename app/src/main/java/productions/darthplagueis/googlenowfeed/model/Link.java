package productions.darthplagueis.googlenowfeed.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by c4q on 12/18/17.
 */

public class Link {

        @SerializedName("type")
        @Expose
        private String type;
        @SerializedName("url")
        @Expose
        private String url;
        @SerializedName("suggested_link_text")
        @Expose
        private String suggestedLinkText;

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

        public String getSuggestedLinkText() {
            return suggestedLinkText;
        }

        public void setSuggestedLinkText(String suggestedLinkText) {
            this.suggestedLinkText = suggestedLinkText;
        }

    }
