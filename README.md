# Weatherify

Weatherify is an android application that display Amsterdam weather forecast.

  - Display List with Low and high temperature for 10 days .

Used [Espresso] to write concise, beautiful, and reliable Android UI tests and [MockWebServer] to mimic http api responses and [LeakCanary] for memory leaks detection.

### Test Senarios :

1- test forecast api returns consice number of forecast items. (9 items) and check the number of displayed items also test the visibility of other ui state views.

```java
    @Test
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
```

2- test forecast api reutruns 0 number of items and check if empty view is displayed on screen also other ui state for other views .

```java
    @Test
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
```

3- test when error occur on api level by returning 404 http status code and check if retry UI is being display. aslo test when user clicks on retry button and return api response with 10 forecast items .

```java
    @Test
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
```
### Tech

Weatherify uses a number of open source projects to work properly:
* [Espresso] -  to write concise, beautiful, and reliable Android UI tests.
* [LeakCanary] - A memory leak detection library for Android and Java.
* [Retrofit] - A type-safe HTTP client for Android and Java.
* [MockWebServer] - A scriptable web server for testing HTTP clients.


   [LeakCanary]: <https://github.com/square/leakcanary>
   [Retrofit]: <http://square.github.io/retrofit/>
   [MockWebServer]: <https://github.com/square/okhttp/tree/master/mockwebserver>
   [Espresso]: <https://google.github.io/android-testing-support-library/docs/espresso/>
