package com.test.guardiannews.extentions

import java.text.SimpleDateFormat
import java.util.*


/**
 *  Get format date from standard to app
 */
fun String.getDateFromBasicFormat(): String {
    val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
    val formatter = SimpleDateFormat("dd/MM/yyyy")
    return formatter.format(parser.parse(this))
}

/**
 *  Get Time from Date
 */
fun String.getTimeFromBasicFormat(): String {
    val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
    val formatter = SimpleDateFormat("hh:mm aa")
    return formatter.format(parser.parse(this))
}