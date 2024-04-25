package com.mine.design.navigation

import androidx.annotation.StringRes
import com.mine.design.R
import com.mine.utils.Constants.DETAILS
import com.mine.utils.Constants.HOME

sealed class Screen(val route: String, @StringRes val resourceId: Int) {
    object Home : Screen(HOME, R.string.text_home)
    object Details : Screen(DETAILS, R.string.text_details)
}