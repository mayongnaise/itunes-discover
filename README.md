# iTunes Discover

Written in Java and developed using Android MVVM pattern.

## Download

Download APK Installer [here](https://www.dropbox.com/s/0svwineujv7d16h/itunes-discover_1.0.apk?dl=1)

![Master List](https://raw.githubusercontent.com/mayongnaise/itunes-discover/master/itunes-discover-master-list.jpg) ![Detail View](https://raw.githubusercontent.com/mayongnaise/itunes-discover/master/itunes-discover-detail-view2.jpg) ![Detail View Collapsed](https://raw.githubusercontent.com/mayongnaise/itunes-discover/master/itunes-discover-detail-view-collapse.jpg)

## Minimum SDK API 23+


## Features

1. Display result list based on iTunes Search API call. Each item or track on the list displayed the following:

   * Track Name
   * ArtWork
   * Track Price
   * Primary Genre
  
2. Display a detailed view of the track selected by the user from the list. Aside from the above details, the detailed view also list the following:

   * Track Kind
   * Long description / Short description / description (whichever is not empty or null)
   * Artist Name
  
3. Default value of search parameters are provided on first load, but the application caters to <b>dynamic searching</b>.

   - Country Code picker - implemented using [CountryCodePicker](https://github.com/hbb20/CountryCodePickerProject) library. I added a function to detect the country based on user device and add that to the SegmentedControl preferred Country along with the default country code.
  
   - Media type filter - implemented using [SegmentedControl](https://github.com/RobertApikyan/SegmentedControl) library.
  
   - Search Field - add IME_ACTION_SEARCH action listener and function to auto hide softkeyboard.
  
4. Data Persistence

   - Last date of search - displayed above the result list. Since this is a searching application, I think it is more important to display the information about searching instead of information about the user's visits.
  
   - Last visited screen - application remembers the last activity visited and its content. This is important so the user can still retrieved the last data displayed even if they accidentally kill the app.
  
   - Last term searched - displayed in the search field. Same with the last date of search, I think it is important to display the information about searching.
  
   - Last country code searched - auto selected in the CountryCodePicker. Same reasoning above.
  
   - Last media type searched - auto selected in the SegmentedControl. Same reasoning above.
  
5. Offline Support 

   - Even though the network is not available on the user device, result list will be retrieved from a cached repository. If they attempted to perform a new search query, an error message stating that the network is not available will be displayed.

## UI & Design
### General use of Android Material Design

   - Added [Shared Element Transitions](https://developer.android.com/training/transitions/start-activity) from the master list view to the detailed view.
  
   - Added [CollapsingToolbarLayout](https://developer.android.com/reference/android/support/design/widget/CollapsingToolbarLayout) for the detailed view.
  
   - Added [SwipeRefreshLayout](https://developer.android.com/reference/android/support/v4/widget/SwipeRefreshLayout)
  
   - Use of [CardView](https://developer.android.com/reference/android/support/v7/widget/CardView) and [RecyclerView](https://developer.android.com/reference/android/support/v7/widget/RecyclerView) for the master-detail screens.
  
   - Use of custom fonts: [Montserrat](https://www.fontsquirrel.com/fonts/montserrat) and [SF UI Display](https://www.cufonfonts.com/font/sf-ui-text-2)
  
   - Use of [Picasso](https://square.github.io/picasso/) as image loader
  

## References

iTunes Web Service Documentation: https://affiliate.itunes.apple.com/resources/documentation/itunes-store-web-service-search-api/#searching)

## Other Libraries

   - [RoundedImageView](https://github.com/vinc3m1/RoundedImageView)
   - [Retrofit](https://square.github.io/retrofit/)
   - [RxJava](https://github.com/ReactiveX/RxAndroid)
   - [Dagger2](https://github.com/google/dagger)
   - [ButterKnife](https://jakewharton.github.io/butterknife/)
   - [EventBus](https://github.com/greenrobot/EventBus)
   - [SDP](https://github.com/intuit/sdp)
