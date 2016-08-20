package nammari.weatherify.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import nammari.weatherify.api.Constants;
import nammari.weatherify.api.Services;
import nammari.weatherify.model.WeatherResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by nammari on 8/21/16.
 * email : nammariahmad@gmail.com
 * phone : +962798939560
 *
 * used to execute api call and provide result, also store (cache) data for activity configuration change situation.
 */

public class WeatherForecastDataFragment extends Fragment {

    public static final String TAG = WeatherForecastDataFragment.class.getSimpleName();
    Call<WeatherResponse> mWeatherResponseCall;
    Response<WeatherResponse> mWeatherResponse;

    public WeatherForecastDataFragment() {
    }


    public interface DataFetchResultInteractionListener {
        void onStartTask();

        void onResponse(Response<WeatherResponse> response);

        void onFailure(Throwable t);
    }

    DataFetchResultInteractionListener mOnDataFetchInteractionListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof DataFetchResultInteractionListener) {
            mOnDataFetchInteractionListener = (DataFetchResultInteractionListener) context;
        }

        assert mOnDataFetchInteractionListener != null;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        fetch();
    }

    public void fetch() {
        mOnDataFetchInteractionListener.onStartTask();
        if (mWeatherResponse != null) {
            mOnDataFetchInteractionListener.onResponse(mWeatherResponse);
            return;
        }

        if (mWeatherResponseCall == null) {
            mWeatherResponseCall = Services.AMSTERDAM_WEATHER_SERVICES.getForecastForAmsterdam(Constants.QUERY_PARAM_VALUE, Constants.FORMAT_PARAM_VALUE, Constants.ENV_PARAM_VALUE);
            mWeatherResponseCall.enqueue(new Callback<WeatherResponse>() {
                @Override
                public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                    mWeatherResponse = response;
                    mOnDataFetchInteractionListener.onResponse(response);
                    mWeatherResponseCall = null;
                }

                @Override
                public void onFailure(Call<WeatherResponse> call, Throwable t) {
                    mOnDataFetchInteractionListener.onFailure(t);
                    mWeatherResponseCall = null;
                }
            });
        }
    }


}
