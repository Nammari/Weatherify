package nammari.weatherify.api;

import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Created by nammari on 8/20/16.
 * email : nammariahmad@gmail.com
 * phone : +962798939560
 */

public class Constants {

    public static String BASE_URL = "https://query.yahooapis.com/v1/public/";

    public static final SimpleDateFormat SERVER_DATE_FORMAT = new SimpleDateFormat("dd MMM yyyy", Locale.US);


    public static final String QUERY_PARAM_KEY = "q";
    public static final String QUERY_PARAM_VALUE = "select%20*%20from%20weather.forecast%20where%20woeid%20in%20%28select%20woeid%20from%20geo.places%281%29%20where%20text%3D%22amsterdam%22%29";
    public static final String FORMAT_PARAM_KEY = "format";
    public static final String FORMAT_PARAM_VALUE = "json";
    public static final String ENV_PARAM_KEY = "env";
    public static final String ENV_PARAM_VALUE = "store%3A%2F%2Fdatatables.org%2Falltableswithkeys";

}
