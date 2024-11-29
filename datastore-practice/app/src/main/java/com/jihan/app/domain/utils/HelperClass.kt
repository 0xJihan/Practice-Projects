package com.jihan.app.domain.utils

import android.util.Patterns

object HelperClass {

    fun validateUserCredentials(
        userName: String = "Default",
        email: String,
        password: String,
    ): Pair<Boolean, String> {

        val result = Pair(true, "")

        if (userName.isEmpty() || email.isEmpty() || password.isEmpty()) {
            return Pair(false, "Please Provide All Required Information")
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return Pair(false, "Invalid Email Address")
        } else if (password.length < 5) {
            return Pair(false, "Password should be at least 5 characters long")
        }


        return result
    }

}