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
public class Results {

    @SerializedName("channel")
    @Expose
    private Channel channel;

    /**
     * @return The channel
     */
    public Channel getChannel() {
        return channel;
    }

    /**
     * @param channel The channel
     */
    public void setChannel(Channel channel) {
        this.channel = channel;
    }

}