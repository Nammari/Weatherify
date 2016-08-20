package nammari.weatherify;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

import nammari.weatherify.api.AmsterdamWeatherService;
import nammari.weatherify.api.Services;

/**
 * Created by nammari on 8/21/16.
 * email : nammariahmad@gmail.com
 * phone : +962798939560
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        LeakCanary.install(this);
        //initialize retrofit service.
        Services.initializeAmsterdamWeatherService(Services.RETROFIT.create(AmsterdamWeatherService.class));
    }
}
