package git.playground.android

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.pressImeActionButton
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.SmallTest
import androidx.test.rule.ActivityTestRule
import git.playground.android.ui.MainActivity
import org.junit.Rule
import org.junit.Test

@SmallTest
class SearchScreenTest {
    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java)
    @Test
    fun when_MainActivity_launched_THEN_Keyboard_is_opened_and_SearchView_is_focused() {
        onView(withId(androidx.appcompat.R.id.search_src_text)).perform(typeText("fubotv"))
        onView(withId(androidx.appcompat.R.id.search_src_text)).perform(pressImeActionButton())
        onView(withId(R.id.progressParent)).check(matches(isDisplayed()))
    }
}