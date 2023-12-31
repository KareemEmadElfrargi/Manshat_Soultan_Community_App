
# Manshat Soultan Community

Socail Media App presents the village news in terms of deaths, sports, health and education news on its own, where specialized officials publish these news on the app.

## Authors

- [@KareemEmadElfrargi](https://github.com/KareemEmadElfrargi)
- [@Mohamed Samir](https://github.com/Mohamed-samir03) 

## Screenshots
- Some Screenshots of this app
![Manshat_Soultan_Community_App](https://github.com/KareemEmadElfrargi/Manshat_Soultan_Community_App/assets/148908216/3762c1eb-8d3f-4f48-ab05-6f87e6a94205)

## Language of this Application

It hold single language now and is adaptable to other languages

`Arabic Language`

## Libraries and technologies used

• Navigation component : one activity contains multiple fragments  instead of creating multiple activites
```bash
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.5")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.5")
```
 • Room : Save meals in local database.
```bash
    implementation ("androidx.room:room-runtime:2.6.1")
    implementation ("androidx.room:room-ktx:2.6.1")
    kapt ("androidx.room:room-compiler:2.6.1")
```   
 • MVVM & LiveData : Saperate logic code from views and save the state in case the screen configuration changes
```bash
    implementation ("android.arch.lifecycle:extensions:1.1.0")
    annotationProcessor ("android.arch.lifecycle:compiler:1.1.0")
    testImplementation ("android.arch.core:core-testing:1.1.0")
```   
 • Coroutines : do some code in the background.
```bash
    // Coroutines
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.1")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.1")

    // Coroutine Lifecycle Scopes
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")

```   
 • Glide : Catch images and load them in imageView.
```bash
implementation ("com.github.bumptech.glide:glide:4.16.0")
```   
 • Dependency injection with Hilt : Hilt is a dependency injection library for Android that reduces the boilerplate of doing manual dependency injection in your project
```bash
implementation("com.google.dagger:hilt-android:2.49")
kapt("com.google.dagger:hilt-android-compiler:2.49")
```
## Others like 

```bash
//circular image
implementation ("de.hdodenhof:circleimageview:3.1.0"))
//Import the StringEscapeUtils
implementation ("org.apache.commons:commons-text:1.9")
// photoView
implementation ("com.github.chrisbanes:PhotoView:2.3.0")
//circular image
implementation ("de.hdodenhof:circleimageview:3.1.0")
//stepView
implementation ("com.github.shuhart:stepview:1.5.1")
//loading button
implementation("com.github.leandroborgesferreira:loading-button-android:2.3.0")
//dimens
implementation ("com.intuit.sdp:sdp-android:1.1.0")
//font dimens
implementation ("com.intuit.ssp:ssp-android:1.1.0")
```

