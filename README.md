# MovieePlot

What I use to build this project :
- Android Studio 4.1
- Retrofit 2
- Kotlin Coroutines
- OKHTTP ( For Logging In Debug Mode Purpose )
- Kotlin , Kotlin-X
- Android Jetpack + Android Navigation Component ( Use Single Activity )
- Dagger2 for Depedency Injection ( Multi Module )

# Note: This App is single type activity application.

A simple app to hit the Movie DB Popular Movie API, Upcoming Movie API, that shows details when items on the list are tapped (a typical master/detail app), also user able to this App is implements MVVM architecture using Dagger2, Retrofit, Coroutines, LiveData, DataBinding and Navigation Component.

# The app has following packages: 
1. data: It contains all the data accessing and manipulating components.
2. di: Dependency providing classes using Dagger2.
3. domain: It contains dto classes and repositories.
4. presentation: View classes ( Main And Base ).
5. utils: Utility classes.

# Library reference resources:
1. Coroutines: https://codelabs.developers.google.com/codelabs/kotlin-coroutines/
2. Dagger2: https://github.com/MindorksOpenSource/android-dagger2-example
3. Retrofit: https://square.github.io/retrofit/
4. DataBinding: https://developer.android.com/topic/libraries/data-binding
5. Navigation Component: https://developer.android.com/guide/navigation/navigation-getting-started
