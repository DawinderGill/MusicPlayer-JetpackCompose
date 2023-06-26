[![CodeStyle](https://img.shields.io/badge/code%20style-%E2%9D%A4-FF4081.svg?style=for-the-badge)](https://ktlint.github.io/)
[![Compose](https://img.shields.io/badge/compose-1.4.3-red.svg?style=for-the-badge)](https://developer.android.com/jetpack/compose)
[![Kotlin](https://img.shields.io/badge/kotlin-1.8.10-blueviolet.svg?style=for-the-badge)](https://kotlinlang.org/)
[![Dagger Hilt](https://img.shields.io/badge/dagger%20hilt-2.44-blue.svg?style=for-the-badge)](https://dagger.dev/hilt/)
[![Android API](https://img.shields.io/badge/api-21%2B-brightgreen.svg?style=for-the-badge)](https://android-arsenal.com/api?level=23)
[![License Apache 2.0](https://img.shields.io/badge/License-Apache%202.0-orange.svg?style=for-the-badge)](https://opensource.org/licenses/Apache-2.0)

<p align="center"> 
   <img height="250" src="https://user-images.githubusercontent.com/80427734/147891822-5cd34c80-8dca-4d34-8278-2aa3bf36913f.png"/> 
</p>

<h1 align="center"> Jetpack Compose Music Player </h1>

Android Clean Architecture in this sample project that presents modern, approach to [Android](https://www.android.com/) application development using [Kotlin](https://kotlinlang.org/) and latest tech-stack.

The goal of the project is to demonstrate best practices, provide a set of guidelines, and present modern Android
application architecture that is modular, scalable, maintainable and testable. This application may look simple, but it
has all of these small details that will set the rock-solid foundation of the larger app suitable for bigger teams and
long application lifecycle management.

A few key concepts and technologies form the cornerstone of this project:

- [__Jetpack Compose__](https://developer.android.com/jetpack/compose): This is the next-generation UI toolkit for Android applications, allowing you to build interfaces through declarative UI patterns, thereby reducing the boilerplate code and helping to bring your app to life more quickly​.
- [__Recomposition__](https://developer.android.com/jetpack/compose/mental-model): In the context of Jetpack Compose, recomposition is the process by which the Compose framework updates your composable functions when the data they read changes, ensuring your UI is always up-to-date​.
- [__Managing States__](https://developer.android.com/jetpack/compose/state): This project demonstrates the appropriate handling of states in a composable function in Jetpack Compose. This is crucial for building dynamic and interactive UIs that can respond to changes in data over time​.
- [__Kotlin DSL and Gradle__](https://docs.gradle.org/current/userguide/kotlin_dsl.html): The Kotlin DSL provides a type-safe and expressive way to model your project using Kotlin. This project uses Kotlin DSL for Gradle, which allows for writing Gradle build scripts in a more expressive and convenient way, leveraging Kotlin's features such as type-safety, null-safety, and IDE support​.
- [__Centralizing Dependencies__](https://proandroiddev.com/better-dependencies-management-using-buildsrc-kotlin-dsl-eda31cdb81bf): To maintain a clean and manageable build system, this project centralizes its dependencies in a single location, making it easy to manage and update the dependencies across modules​ using ``buildSrc + Kotlin DSL``.

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
