[![CodeStyle](https://img.shields.io/badge/code%20style-%E2%9D%A4-FF4081.svg?style=for-the-badge)](https://ktlint.github.io/)
[![Compose](https://img.shields.io/badge/compose-1.4.3-red.svg?style=for-the-badge)](https://developer.android.com/jetpack/compose)
[![Kotlin](https://img.shields.io/badge/kotlin-1.8.10-blueviolet.svg?style=for-the-badge)](https://kotlinlang.org/)
[![Dagger Hilt](https://img.shields.io/badge/dagger-hilt-2.44-blue.svg?style=for-the-badge)](https://dagger.dev/hilt/)
[![Android API](https://img.shields.io/badge/api-23%2B-brightgreen.svg?style=for-the-badge)](https://android-arsenal.com/api?level=23)
[![License Apache 2.0](https://img.shields.io/badge/License-Apache%202.0-orange.svg?style=for-the-badge)](https://opensource.org/licenses/Apache-2.0)

<p align="center"> 
   <img height="250" src="https://user-images.githubusercontent.com/80427734/147891822-5cd34c80-8dca-4d34-8278-2aa3bf36913f.png"/> 
</p>

<h1 align="center"> Jetpack Compose Music Player </h1>

banner

A simple music player app built using Android Jetpack Compose. It features a list screen, and upon clicking an item, it starts playing the music, showing options at the bottom of the screen including play/pause, previous, and next. Clicking on the bottom bar opens a ModalBottomSheetLayout with all the track details and progress.

Demo

Light [Image] [Image]
Dark [Image] [Image]

Note: Replace the [Image] placeholders with actual images or gifs of your application running in both light and dark modes.

Tech Stack

This project uses many of the popular libraries, plugins, and tools of the Android ecosystem, including:

Jetpack Compose - For building the UI following Material 3 design
Media ExoPlayer - For the music player functionality
MVVM - Architecture pattern for structuring the app
Dagger Hilt - For dependency injection
Kotlin DSL, Kotlin KSP, and Kotlin Gradle - For managing build scripts and dependencies
Glide Compose - For image loading
Lottie Compose - For loading animated JSON
BuildConfig - For securely managing the server URL for the music files stored in local.properties
Additional details of the implementation include:

The music is played from a server URL, which is stored in local.properties for security purposes.
Jetpack custom themes are used for the UI design.
All composable states are centralized in ViewModel.
All ExoPlayer operations are centralized in a separate class.
The project follows SOLID principles and uses design patterns such as Singleton, Factory, and Builder.
Kotlin extension functions are used extensively.
Gradle dependencies are centralized in buildSrc.
All these practices align with the best MAD (Modern Android Development) practices.

Plugins and Tools

Kotlin Gradle - For managing build scripts
Kotlin DSL - For managing project and module scripts
Kotlin KSP - A fast, lightweight compiler plugin API for Kotlin
Feel free to add more tools and plugins you used in your project here.

How to Use

Clone this repository to your machine.
Open Android Studio and import the project.
Navigate to local.properties file and add the BASE_URL key with your server URL for the music files.
Add your tracks in repositories>TrackRepositoryImpl under createTracks() method.
Build and run the project.
Contributing

Contributions are what make the open source community such an amazing place to learn, inspire, and create. Any contributions you make are greatly appreciated:

Open an issue first to discuss what you would like to change.
Fork the Project
Create your feature branch (git checkout -b feature/amazing-feature)
Commit your changes (git commit -m 'Add some amazing feature')
Push to the branch (git push origin feature/amazing-feature)
Open a pull request
Please make sure to update tests as appropriate.

Authors

Your Name or Alias - @YourTwitterHandle - LinkedIn

Add more authors if applicable.

License

Copyright © 2023 - Your Name