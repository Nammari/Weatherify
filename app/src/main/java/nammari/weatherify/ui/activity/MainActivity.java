package nammari.weatherify.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import nammari.weatherify.model.WeatherResponse;
import nammari.weatherify.ui.fragment.WeatherForecastDataFragment;
import nammari.weatherify.ui.fragment.WeatherForecastViewFragment;
import retrofit2.Response;

/**
 * Created by nammari on 8/21/16.
 * email : nammariahmad@gmail.com
 * phone : +962798939560
 */

public class MainActivity extends AppCompatActivity implements WeatherForecastDataFragment.DataFetchResultInteractionListener, WeatherForecastViewFragment.ForeCastDataTaskListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(android.R.id.content, new WeatherForecastViewFragment(), WeatherForecastViewFragment.TAG)
                    .commit();
        }
    }


    @Override
    public void onStartTask() {

        WeatherForecastViewFragment fragment = (WeatherForecastViewFragment) getSupportFragmentManager().findFragmentByTag(WeatherForecastViewFragment.TAG);
        if (fragment != null) {
            fragment.showLoading();
        }
    }

    @Override
    public void onResponse(Response<WeatherResponse> response) {
        WeatherForecastViewFragment fragment = (WeatherForecastViewFragment) getSupportFragmentManager().findFragmentByTag(WeatherForecastViewFragment.TAG);
        if (fragment != null) {
            fragment.handleResponse(response);
        }
    }

    @Override
    public void onFailure(Throwable t) {
        WeatherForecastViewFragment fragment = (WeatherForecastViewFragment) getSupportFragmentManager().findFragmentByTag(WeatherForecastViewFragment.TAG);
        if (fragment != null) {
            fragment.showError(t);
        }
    }

    @Override
    public void requestData() {
        WeatherForecastDataFragment fragment = (WeatherForecastDataFragment) getSupportFragmentManager().findFragmentByTag(WeatherForecastDataFragment.TAG);
        if (fragment == null) {
            fragment = new WeatherForecastDataFragment();
            getSupportFragmentManager().beginTransaction().add(fragment, WeatherForecastDataFragment.TAG).commit();
        } else {
            fragment.fetch();
        }

    }
}
