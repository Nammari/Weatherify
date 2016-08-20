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
public class Atmosphere {

    @SerializedName("humidity")
    @Expose
    private String humidity;
    @SerializedName("pressure")
    @Expose
    private String pressure;
    @SerializedName("rising")
    @Expose
    private String rising;
    @SerializedName("visibility")
    @Expose
    private String visibility;

    /**
     * @return The humidity
     */
    public String getHumidity() {
        return humidity;
    }

    /**
     * @param humidity The humidity
     */
    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    /**
     * @return The pressure
     */
    public String getPressure() {
        return pressure;
    }

    /**
     * @param pressure The pressure
     */
    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    /**
     * @return The rising
     */
    public String getRising() {
        return rising;
    }

    /**
     * @param rising The rising
     */
    public void setRising(String rising) {
        this.rising = rising;
    }

    /**
     * @return The visibility
     */
    public String getVisibility() {
        return visibility;
    }

    /**
     * @param visibility The visibility
     */
    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

}