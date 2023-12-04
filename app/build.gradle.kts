plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id ("kotlin-kapt")
    id ("androidx.navigation.safeargs")
    id("com.google.dagger.hilt.android")
    id ("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
    id("com.google.gms.google-services")

}

android {
    namespace = "com.example.manshatsoultancommunity"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.manshatsoultancommunity"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        dataBinding = true
    }
}
kapt {
    correctErrorTypes = true
}
dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("com.google.firebase:firebase-database:20.3.0")
    implementation("com.google.firebase:firebase-firestore:24.9.1")
    implementation("com.google.firebase:firebase-storage:20.3.0")
    implementation("com.google.firebase:firebase-messaging:23.3.1")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    //dimens
    implementation ("com.intuit.sdp:sdp-android:1.1.0")
    //font dimens
    implementation ("com.intuit.ssp:ssp-android:1.1.0")
    //Glide
    implementation ("com.github.bumptech.glide:glide:4.16.0")

    // Navigation component
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.5")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.5")

    // Retrofit
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation ("com.squareup.okhttp3:okhttp:4.9.1")

    // Coroutines
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.1")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.1")

    // Coroutine Lifecycle Scopes
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")

    //Hilt
    implementation("com.google.dagger:hilt-android:2.49")
    kapt("com.google.dagger:hilt-android-compiler:2.49")

    //firebase
    //implementation (platform("com.google.firebase:firebase-bom:32.3.1"))
    //implementation ("com.google.firebase:firebase-database-ktx:20.3.0")

    // lottie
    val lottieVersion = "3.4.0"
    //noinspection GradleDependency
    implementation("com.airbnb.android:lottie:$lottieVersion")

    // Room database
    implementation ("androidx.room:room-runtime:2.6.0")
    annotationProcessor ("androidx.room:room-compiler:2.6.0")
    implementation ("androidx.room:room-ktx:2.6.0")


}