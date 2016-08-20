package nammari.weatherify.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by nammari on 8/20/16.
 * email : nammariahmad@gmail.com
 * phone : +962798939560
 */

public class Services {
    public  static Retrofit RETROFIT = new Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    public static AmsterdamWeatherService AMSTERDAM_WEATHER_SERVICES ;


    public static void initializeAmsterdamWeatherService(AmsterdamWeatherService service) {
        AMSTERDAM_WEATHER_SERVICES = service;
    }

}
