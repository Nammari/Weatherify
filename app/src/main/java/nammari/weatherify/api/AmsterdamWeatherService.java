package nammari.weatherify.api;

import nammari.weatherify.model.WeatherResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by nammari on 8/20/16.
 * email : nammariahmad@gmail.com
 * phone : +962798939560
 */

public interface AmsterdamWeatherService {

    @GET("yql")
    Call<WeatherResponse> getForecastForAmsterdam(@Query(value = Constants.QUERY_PARAM_KEY, encoded = true) String queryValue, @Query(value = Constants.FORMAT_PARAM_KEY, encoded = true) String format, @Query(value = Constants.ENV_PARAM_KEY, encoded = true) String env);
}
