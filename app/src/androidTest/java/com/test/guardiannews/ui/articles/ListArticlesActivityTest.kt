package com.test.guardiannews.ui.articles


import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.test.guardiannews.R
import org.hamcrest.Matchers.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class ListArticlesActivityTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(ListArticlesActivity::class.java)

    @Test
    fun listArticlesActivityTest() {
        val viewGroup = onView(
            allOf(
                withId(R.id.sw_to_r_list_news),
                withParent(
                    allOf(
                        withId(R.id.cl_root),
                        withParent(withId(android.R.id.content))
                    )
                ),
                isDisplayed()
            )
        )
        viewGroup.check(matches(isDisplayed()))
    }
}
