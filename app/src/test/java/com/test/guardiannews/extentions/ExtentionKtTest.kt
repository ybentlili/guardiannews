package com.test.guardiannews.extentions

import junit.framework.TestCase
import org.junit.Test

class ExtentionKtTest : TestCase() {

    private val date : String = "2020-10-10T16:10:56Z"
    private val resultFormatDate :String = "10/10/2020"
    private val hoursFormat : String = "04:10 PM"
    @Test
    fun testGetDateFromBasicFormat() {
        var result = date.getDateFromBasicFormat() ;
        assertEquals(result,resultFormatDate)
    }

    @Test
    fun testGetTimeFromBasicFormat() {
        var result = date.getTimeFromBasicFormat() ;
        assertEquals(result,hoursFormat)
    }
}