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
public class Wind {

    @SerializedName("chill")
    @Expose
    private String chill;
    @SerializedName("direction")
    @Expose
    private String direction;
    @SerializedName("speed")
    @Expose
    private String speed;

    /**
     * @return The chill
     */
    public String getChill() {
        return chill;
    }

    /**
     * @param chill The chill
     */
    public void setChill(String chill) {
        this.chill = chill;
    }

    /**
     * @return The direction
     */
    public String getDirection() {
        return direction;
    }

    /**
     * @param direction The direction
     */
    public void setDirection(String direction) {
        this.direction = direction;
    }

    /**
     * @return The speed
     */
    public String getSpeed() {
        return speed;
    }

    /**
     * @param speed The speed
     */
    public void setSpeed(String speed) {
        this.speed = speed;
    }

}