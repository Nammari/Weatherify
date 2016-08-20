package nammari.weatherify;

import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.InstrumentationTestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import nammari.weatherify.api.AmsterdamWeatherService;
import nammari.weatherify.api.Services;
import nammari.weatherify.ui.activity.MainActivity;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.Visibility;
import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static android.support.test.espresso.matcher.ViewMatchers.withId;


/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class AppTest extends InstrumentationTestCase {


    @Rule
    public ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule<>(MainActivity.class);
    private MockWebServer server;


    @Before
    /**
     * assign a mock server implementation to simulate api responses .
     */
    public void setUp() throws Exception {
        super.setUp();
        server = new MockWebServer();
        server.start();
        Retrofit testRetrofit = new Retrofit.Builder()
                .baseUrl(server.url("/").toString())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Services.initializeAmsterdamWeatherService(testRetrofit.create(AmsterdamWeatherService.class));
        injectInstrumentation(InstrumentationRegistry.getInstrumentation());

    }

    @Test
    /**
     * Happy case , i stored inside the forecast 9 items , so list would display 9 items only.
     */
    public void testListWith9ForecastItems() throws Exception {
        String fileName = "responsewith9forecastitems.json";
        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(RestServiceTestHelper.getStringFromFile(getInstrumentation().getContext(), fileName)));
        Intent intent = new Intent();
        mActivityRule.launchActivity(intent);
        Espresso.onView(ViewMatchers.withId(R.id.recyclerivew)).check(new RecyclerViewItemCountAssertion(9));
        checkViewVisibility(R.id.recyclerivew, Visibility.VISIBLE);
        checkViewVisibility(R.id.empty, Visibility.GONE);
        checkViewVisibility(R.id.error_container, Visibility.GONE);
        checkViewVisibility(R.id.loading_container, Visibility.GONE);
    }

    @Test
    /**
     * Error view case, i used Http 404 status code to simulate the error, and check if error view will be displayed.
     * then i perfom a click on retry button and return the 10 forecasts and check visibility for UI.
     */
    public void testErrorIsShown() throws Exception {
        server.enqueue(new MockResponse()
                .setHttp2ErrorCode(404)
        );
        Intent intent = new Intent();
        mActivityRule.launchActivity(intent);
        checkViewVisibility(R.id.error_container, Visibility.VISIBLE);
        checkViewVisibility(R.id.recyclerivew, Visibility.GONE);
        checkViewVisibility(R.id.empty, Visibility.GONE);
        checkViewVisibility(R.id.loading_container, Visibility.GONE);

        //retry button click
        String fileName = "responsewith10forecastitems.json";
        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(RestServiceTestHelper.getStringFromFile(getInstrumentation().getContext(), fileName)));
        onView(withId(R.id.retry)).perform(ViewActions.click());
        onView(ViewMatchers.withId(R.id.recyclerivew)).check(new RecyclerViewItemCountAssertion(10));
        checkViewVisibility(R.id.recyclerivew, Visibility.VISIBLE);
        checkViewVisibility(R.id.empty, Visibility.GONE);
        checkViewVisibility(R.id.error_container, Visibility.GONE);
        checkViewVisibility(R.id.loading_container, Visibility.GONE);
    }


    @Test
    /**
     * When forecast returns 0 items, empty view should appear.
     */
    public void testEmptyState() throws Exception {

        String fileName = "emptyforecast.json";
        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(RestServiceTestHelper.getStringFromFile(getInstrumentation().getContext(), fileName)));
        Intent intent = new Intent();
        mActivityRule.launchActivity(intent);
        onView(ViewMatchers.withId(R.id.recyclerivew)).check(new RecyclerViewItemCountAssertion(0));
        checkViewVisibility(R.id.empty, Visibility.VISIBLE);
        checkViewVisibility(R.id.recyclerivew, Visibility.GONE);
        checkViewVisibility(R.id.error_container, Visibility.GONE);
        checkViewVisibility(R.id.loading_container, Visibility.GONE);

    }

    private void checkViewVisibility(int resourceId, Visibility visibility) {
        onView(withId(resourceId)).check(matches(withEffectiveVisibility(visibility)));
    }

    @After
    public void tearDown() throws Exception {
        server.shutdown();
    }
}
