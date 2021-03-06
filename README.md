# VReddit

The project has Reddit's theme and use its API https://www.reddit.com/dev/api/

## Table of Contents
* [ScreenShots](#screenshots)
* [Tools and Libs](#tools)
* [TODOs](#todos)
* [Known issues](#knownIssues)

<a name="screenshots"></a>
## Screen Shots

<p align="center">
  <img src="screenshots/splash.png" align="center" width=200>
  <img src="screenshots/screen_listing.png" align="center" width=200>
  <img src="screenshots/listing_filter.png" align="center" width=200>
  <img src="screenshots/listing_empty.png" align="center" width=200>
  <img src="screenshots/listing_custom_google.png" align="center" width=200>
  <img src="screenshots/comment_empty.png" align="center" width=200>
  <img src="screenshots/comment.png" align="center" width=200>
  <img src="screenshots/comment_land.png" align="center" width=200>
  <br>
  <img src="screenshots/tablet1.png" align="center" width=400>
  <img src="screenshots/tablet2.png" align="center" width=400>
  <br>
  <img src="screenshots/tablet2land.png" align="center" width=800>
</p>

<a name="tools"></a>
## Tools and Libs
* [Kotlin](https://kotlinlang.org/)
* [Kotlin Android Extensions](https://kotlinlang.org/docs/tutorials/android-plugin.html)
  * Description: I avoid findViewById using this plugin I have used the syntax separated with underline, because inside the code I have identied easily for example:
```kotlin
   private fun setUI(redditNewsData: RedditNewsData) {
        author_default_detail.text = redditNewsData.author.plus(" - ").plus(redditNewsData.created.getTimeString())
        title_default_detail.text = redditNewsData.title
        score_default_detail.text = redditNewsData.score.toString()
        comment_default_detail.text = redditNewsData.numComments.toString()
        share_default_detail.setOnClickListener { context.sharedLink(redditNewsData.url) }
        description_default_detail.text = redditNewsData.selftext
        btn_original_post.setOnClickListener { showCustomTab(newsData) }
        commentsAdapter = CommentsAdapter()
        recyclew_comments.adapter = commentsAdapter
        recyclew_comments.setHasFixedSize(true)
    }
```
* [Timber](https://github.com/JakeWharton/timber)
* [RxJava2](https://github.com/ReactiveX/RxJava)
* [Retrofit2](http://square.github.io/retrofit/)
* [Picasso](http://square.github.io/picasso/)
* [Gson](https://github.com/google/gson)
* [Android Support Libs](https://developer.android.com/topic/libraries/support-library/index.html):
  * RecyclerView
  * CardView
  * Design Support Library
  * ConstraintLayout
  * [Custom Labs](https://developer.android.com/topic/libraries/support-library/packages.html#custom-tabs)
* Android Testing Support Libs
  * [Espresso](https://developer.android.com/training/testing/espresso/index.html)
  * [jUnit](http://junit.org/junit4/)
* [Room Components](https://developer.android.com/topic/libraries/architecture/room.html)
* [Stetho](http://facebook.github.io/stetho/)
* [Fabric](https://get.fabric.io/?utm_campaign=discover&utm_medium=natural)
* [Jacoco](https://github.com/arturdm/jacoco-android-gradle-plugin)

<a name="todos"/></a>
## ToDos
* To improve layout of the list.
* To do test with Roboeletric
* More more more test much more
* AndroidTest is working in emulators with API 26,25,24,21,19, but It have to make androidTest work in emulator with API 23

<a name="knownIssues"/></a>
## Known issues
* In moto G4 the androidTest is flaky



License
-------

    Copyright 2017 Rodrigo Vianna

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
