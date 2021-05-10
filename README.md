[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
[![API](https://img.shields.io/badge/API-16%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=16)

# CapitalRotation
A simple app that just do two things:
- check that a string begin with a capital letter
- rotate an image at desired angle

## Tip
You can change rotation after displaying image by tap on device "Back" button


## Download
Apk is available here

## Functionality
* This app is available in english and french
* In order to be accessible to a maximum of users/devices this application is compatible up to android 4.1.1 (API 16)

## Tests
App was tested on Android 4.1.1 emulator(english) and real Pixel3a(french)

Tests available here:
* [Unit tests](/app/src/test/java/eu/pbenayoun/capitalrotation/HomeUtilsTest.kt)
* [Instrumented Tests](/app/src/androidTest/java/eu/pbenayoun/capitalrotation/MainActivityTest.kt)

Libraries Used
--------------
* [Foundation][0] - Components for core system capabilities, Kotlin extensions and support for
  multidex and automated testing.
  * [AppCompat][1] - Degrade gracefully on older versions of Android.
  * [Android KTX][2] - Write more concise, idiomatic Kotlin code.
  * [Test][4] - An Android testing framework for unit and runtime UI tests.
* [Architecture][10] - A collection of libraries that help you design robust, testable, and
  maintainable apps. Start with classes for managing your UI component lifecycle and handling data
  persistence.
  * [Data Binding][11] - Declaratively bind observable data to UI elements.
  * [Lifecycles][12] - Create a UI that automatically responds to lifecycle events.
  * [Navigation][14] - Handle everything needed for in-app navigation.
  * [ViewModel][17] - Store UI-related data that isn't destroyed on app rotations. Easily schedule
     asynchronous tasks for optimal execution.
* [UI][30] - Details on why and how to use UI Components in your apps - together or separate
  * [Animations & Transitions][31] - Move widgets and transition between screens.
  * [Fragment][34] - A basic unit of composable UI.
  * [Layout][35] - Lay out widgets using different algorithms.
 
[0]: https://developer.android.com/jetpack/components
[1]: https://developer.android.com/topic/libraries/support-library/packages#v7-appcompat
[2]: https://developer.android.com/kotlin/ktx
[4]: https://developer.android.com/training/testing/
[10]: https://developer.android.com/jetpack/arch/
[11]: https://developer.android.com/topic/libraries/data-binding/
[12]: https://developer.android.com/topic/libraries/architecture/lifecycle
[14]: https://developer.android.com/topic/libraries/architecture/navigation/
[17]: https://developer.android.com/topic/libraries/architecture/viewmodel
[30]: https://developer.android.com/guide/topics/ui
[31]: https://developer.android.com/training/animation/
[34]: https://developer.android.com/guide/components/fragments
[35]: https://developer.android.com/guide/topics/ui/declaring-layout

(library chapter inspired by [Sunflower](https://github.com/android/sunflower)) github page)

## Technical note
This app doesn't use [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) or [Kotlin Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) but these projects use them heavily:
* [TmDbApp](https://github.com/BenayounP/ThatTMDBApp) (WIP) 
* [ASA](https://github.com/BenayounP/BASA) (WIP) 


## Contributing
If you want to help in any way, just send me an [email](mailto:pierre<àcabnum.fr)

## License
This project is licensed under the Apache License 2.0 - see the [LICENSE.md](LICENSE.md) file for details