
# Manshat Soultan Community

Socail Media App presents the village news in terms of deaths, sports, health and education news on its own, where specialized officials publish these news on the app.

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
