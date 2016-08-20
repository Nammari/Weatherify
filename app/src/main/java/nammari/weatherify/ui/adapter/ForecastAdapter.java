package nammari.weatherify.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import nammari.weatherify.R;
import nammari.weatherify.model.Forecast;

/**
 * Created by nammari on 8/21/16.
 * email : nammariahmad@gmail.com
 * phone : +962798939560
 */

public class ForecastAdapter extends RecyclerView.Adapter<ForecastAdapter.ViewHolder> {


    private final LayoutInflater inflater;
    private List<Forecast> data;
    private final StringBuilder builder = new StringBuilder();

    public ForecastAdapter(LayoutInflater inflater, List<Forecast> data) {
        this.inflater = inflater;
        this.data = data;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.adapter_forecast_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Forecast item = data.get(position);
        //date
        holder.date.setText(item.getDay());
        //low
        builder.setLength(0);
        builder.append(item.getLow()).append("°");
        holder.lowTemperature.setText(builder.toString());
        //high
        builder.setLength(0);
        builder.append(item.getHigh()).append("°");
        holder.highTemperature.setText(builder.toString());
        //description
        holder.codeStatus.setText(item.getText());
    }


    public void setData(List<Forecast> data) {
        this.data = data;
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView date;

        TextView highTemperature;

        TextView lowTemperature;

        TextView codeStatus;


        public ViewHolder(View itemView) {
            super(itemView);
            date = (TextView) itemView.findViewById(R.id.date);
            highTemperature = (TextView) itemView.findViewById(R.id.high_temperature);
            lowTemperature = (TextView) itemView.findViewById(R.id.low_temperature);
            codeStatus = (TextView) itemView.findViewById(R.id.code_status);
        }
    }
}
