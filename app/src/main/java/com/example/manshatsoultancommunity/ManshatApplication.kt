package com.example.manshatsoultancommunity

import android.app.Application
import androidx.room.Room
import com.example.manshatsoultancommunity.database.AppDatabase
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltAndroidApp
class ManshatApplication:Application() {
}