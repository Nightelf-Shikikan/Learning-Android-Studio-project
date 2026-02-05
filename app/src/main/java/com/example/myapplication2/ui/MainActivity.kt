package com.example.myapplication2.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

import com.example.myapplication2.R

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.myapplication2.navigation.AppNavGraph
import com.example.myapplication2.ui.theme.TasksAppTheme

import javax.inject.Inject
import com.example.myapplication2.viewmodel.MainViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TasksAppTheme {
                AppNavGraph()
            }
        }
    }
}