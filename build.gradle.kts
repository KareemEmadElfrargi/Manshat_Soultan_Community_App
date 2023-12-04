// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    repositories {
        google()
    }
    dependencies {
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.7.5")
        classpath("com.google.gms:google-services:4.4.0")
    }
}

plugins {
    id("com.android.application") version "8.1.3" apply false
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false
    //id ("com.google.gms.google-services") version "4.4.0" apply false
    id("com.google.dagger.hilt.android") version "2.44" apply false
    id ("com.google.android.libraries.mapsplatform.secrets-gradle-plugin") version "2.0.1" apply false
}