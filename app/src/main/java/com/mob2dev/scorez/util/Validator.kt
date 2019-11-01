package com.mob2dev.scorez.util

import android.text.TextUtils
import java.util.regex.Pattern
import androidx.core.util.PatternsCompat as Patterns

object Validator {
    private val emailPattern = Patterns.EMAIL_ADDRESS
    private const val validGhanaRegex = "^0(24|54|55|56|26|27|57|23|28|20|50)\\d{7}$"

    fun isRequired(str: String): Boolean {
        return !TextUtils.isEmpty(str)
    }

    fun passMinLength(str: String, minimumLength: Int): Boolean {
        return isRequired(str) && str.length >= minimumLength
    }

    fun passMaxLength(str: String, maximumLength: Int): Boolean {
        return isRequired(str) && str.length <= maximumLength
    }

    fun passRange(str: String, minimumRange: Int, maximumRange: Int): Boolean {
        return isRequired(str) && passMinLength(str, minimumRange) && passMaxLength(str, maximumRange)
    }

    fun isValidEmail(str: String): Boolean {
        return emailPattern.matcher(str).matches()
    }


    fun isValidNumber(str: String): Boolean {
        return isRequired(str) && TextUtils.isDigitsOnly(str) && isValidNumberFormat(str)
    }

    private fun isValidNumberFormat(str: String): Boolean {
        return Pattern.matches(validGhanaRegex, str)

    }

}
