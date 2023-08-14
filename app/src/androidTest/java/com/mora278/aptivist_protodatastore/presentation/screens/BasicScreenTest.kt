package com.mora278.aptivist_protodatastore.presentation.screens

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.mora278.aptivist_protodatastore.presentation.theme.AptivistProtoDataStoreTheme
import com.mora278.aptivist_protodatastore.presentation.viewmodels.BasicViewModel
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class BasicScreenTest {
    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
        hiltRule.inject()
        composeTestRule.setContent {
            AptivistProtoDataStoreTheme {

            }
        }
    }

    @Test
    fun test_001() {
        composeTestRule.onNodeWithText("")
    }
}