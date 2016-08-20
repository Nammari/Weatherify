package nammari.weatherify.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import nammari.weatherify.R;
import nammari.weatherify.model.WeatherResponse;
import nammari.weatherify.ui.adapter.ForecastAdapter;
import nammari.weatherify.ui.controls.SimpleVerticalSpaceItemDecoration;
import retrofit2.Response;

/**
 * Created by nammari on 8/21/16.
 * email : nammariahmad@gmail.com
 * phone : +962798939560
 */

public class WeatherForecastViewFragment extends Fragment implements View.OnClickListener {


    public static final String TAG = WeatherForecastViewFragment.class.getSimpleName();


    @IntDef({STATE_MAIN_VIEW, STATE_ERROR_VIEW, STATE_LOADING_VIEW, STATE_EMPTY_VIEW})
    @Retention(RetentionPolicy.SOURCE)
    public @interface ViewState {
    }

    public static final int STATE_MAIN_VIEW = 0;
    public static final int STATE_ERROR_VIEW = 1;
    public static final int STATE_LOADING_VIEW = 2;
    public static final int STATE_EMPTY_VIEW = 3;

    public WeatherForecastViewFragment() {
    }


    public interface ForeCastDataTaskListener {
        void requestData();
    }

    ForeCastDataTaskListener mForeCastDataTaskListener;
    View loadingView;
    View errorView;
    View emptyView;
    RecyclerView recyclerView;
    ForecastAdapter adapter;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ForeCastDataTaskListener) {
            mForeCastDataTaskListener = (ForeCastDataTaskListener) context;
        }
        assert mForeCastDataTaskListener != null;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (container == null) {
            return null;
        }
        View view = inflater.inflate(R.layout.fragment_weather_list, container, false);
        view.findViewById(R.id.retry).setOnClickListener(this);
        loadingView = view.findViewById(R.id.loading_container);
        errorView = view.findViewById(R.id.error_container);
        emptyView = view.findViewById(R.id.empty);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerivew);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(
                new SimpleVerticalSpaceItemDecoration(
                        (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8.0f, getResources().getDisplayMetrics())
                )
        );
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        adapter = new ForecastAdapter(LayoutInflater.from(getActivity()), null);
        recyclerView.setAdapter(adapter);
        mForeCastDataTaskListener.requestData();

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        loadingView = errorView = emptyView = recyclerView = null;
    }


    public void showLoading() {
        setCurrentViewState(STATE_LOADING_VIEW);
    }


    public void showError(Throwable t) {
        setCurrentViewState(STATE_ERROR_VIEW);
    }


    public void handleResponse(Response<WeatherResponse> response) {
        if (response.isSuccessful()) {
            if (response.body() != null
                    && response.body().getQuery() != null
                    && response.body().getQuery().getResults() != null
                    && response.body().getQuery().getResults().getChannel() != null
                    && response.body().getQuery().getResults().getChannel().getItem() != null
                    && response.body().getQuery().getResults().getChannel().getItem().getForecast() != null
                    && !response.body().getQuery().getResults().getChannel().getItem().getForecast().isEmpty()) {
                adapter.setData(response.body().getQuery().getResults().getChannel().getItem().getForecast());
                adapter.notifyDataSetChanged();
                setCurrentViewState(STATE_MAIN_VIEW);
            } else {
                setCurrentViewState(STATE_EMPTY_VIEW);
            }
        } else {

            setCurrentViewState(STATE_ERROR_VIEW);
        }
    }


    private void setCurrentViewState(@ViewState int state) {
        switch (state) {
            case STATE_ERROR_VIEW:
                errorView.setVisibility(View.VISIBLE);
                loadingView.setVisibility(View.GONE);
                recyclerView.setVisibility(View.GONE);
                emptyView.setVisibility(View.GONE);
                break;
            case STATE_LOADING_VIEW:
                loadingView.setVisibility(View.VISIBLE);
                errorView.setVisibility(View.GONE);
                recyclerView.setVisibility(View.GONE);
                emptyView.setVisibility(View.GONE);
                break;
            case STATE_MAIN_VIEW:
                recyclerView.setVisibility(View.VISIBLE);
                errorView.setVisibility(View.GONE);
                loadingView.setVisibility(View.GONE);
                emptyView.setVisibility(View.GONE);
                break;
            case STATE_EMPTY_VIEW:
                emptyView.setVisibility(View.VISIBLE);
                errorView.setVisibility(View.GONE);
                loadingView.setVisibility(View.GONE);
                recyclerView.setVisibility(View.GONE);
                break;
        }
    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.retry) {
            mForeCastDataTaskListener.requestData();
        }
    }
}
