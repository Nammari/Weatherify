package nammari.weatherify.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;
/**
 * Created by nammari on 8/21/16.
 * email : nammariahmad@gmail.com
 * phone : +962798939560
 */

@Generated("org.jsonschema2pojo")
public class Guid {

    @SerializedName("isPermaLink")
    @Expose
    private String isPermaLink;

    /**
     *
     * @return
     * The isPermaLink
     */
    public String getIsPermaLink() {
        return isPermaLink;
    }

    /**
     *
     * @param isPermaLink
     * The isPermaLink
     */
    public void setIsPermaLink(String isPermaLink) {
        this.isPermaLink = isPermaLink;
    }

}