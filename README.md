# iTunes Discover

This is an application created for Appetiser Apps Code Challenge. It is written in Java and developed using Android MVVM framework.

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
  
  a. Country Code picker - implemented using [CountryCodePicker](https://github.com/hbb20/CountryCodePickerProject) library. I added a function to detect the country based on user device and add that to the SegmentedControl preferred Country along with the default country code.
  
  b. Media type filter - implemented using [SegmentedControl](https://github.com/RobertApikyan/SegmentedControl) library.
  
  c. Search Field - add IME_ACTION_SEARCH action listener and function to auto hide softkeyboard.
  
4. Data Persistence

  a. Last date of search - displayed above the result list. Since this is a searching application, I think it is more important to display the information about searching instead of visiting the application.
  
  b. Last visited screen - application remembers the last activity visited and its content. This is important so the user can still retrieved the last data displayed even if they accidentally kill the app.
  
  c. Last term searched - displayed in the search field. Same with the last date of search, I think it is important to display the information about searching.
  
  d. Last country code searched - auto selected in the CountryCodePicker. Same reasoning above.
  
  e. Last media type searched - auto selected in the SegmentedControl. Same reasoning above.
  
5. 
  
  
