package com.darleyleal.exitto.domain.utils

import java.time.LocalTime

fun welcomeMessage(): String {
    val currentMoment = LocalTime.now().hour
    return when (currentMoment) {
        in (0..4) -> "Sleep well :)"
        in (5..11) -> "Good morning"
        in (12..16) -> "Good afternoon"
        in (17..23) -> "Good evening"
        else -> ""
    }
}