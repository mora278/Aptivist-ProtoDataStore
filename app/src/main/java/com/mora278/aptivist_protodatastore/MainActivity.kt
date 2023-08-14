package com.mora278.aptivist_protodatastore

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.mora278.aptivist_protodatastore.presentation.screens.AdvancedScreen
import com.mora278.aptivist_protodatastore.presentation.screens.BasicScreen
import com.mora278.aptivist_protodatastore.presentation.screens.TestingScreen
import com.mora278.aptivist_protodatastore.presentation.theme.AptivistProtoDataStoreTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AptivistProtoDataStoreTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Select Screen
                    BasicScreen()
                    // AdvancedScreen()
                    // TestingScreen()
                }
            }
        }
    }
}