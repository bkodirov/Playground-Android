package git.playground.android

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import git.playground.android.datalayer.api.TestApiModule
import git.playground.android.di.DaggerMainComponent
import git.playground.android.di.DepGraph
import git.playground.android.di.MainModule
import git.playground.android.ui.MainActivity
import git.playground.android.ui.list.RepositoryViewHolder
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class RepositoryListScreenTest {
    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java, true, false)

    @Before
    fun setUp() {
        val context = InstrumentationRegistry.getInstrumentation().context
        DepGraph.mainComponent = DaggerMainComponent.builder().mainModule(MainModule(context)).apiModule(TestApiModule(context)).build()
        val i = Intent(InstrumentationRegistry.getInstrumentation().targetContext, MainActivity::class.java)
        activityRule.launchActivity(i)
    }

    @Test
    fun given_repository_returning_valid_data_set_WHEN_user_searches_for_valid_git_repo_THEN_lift_of_data_rendered_on_the_recycler() {
        onView(withId(androidx.appcompat.R.id.search_src_text)).perform(typeText("fetch fixture"))
        onView(withId(androidx.appcompat.R.id.search_src_text)).perform(pressImeActionButton())
        onView(withId(R.id.repositoryRecyclerView)).check(matches(isDisplayed()))
        Thread.sleep(1000)
        onView(withId(R.id.repositoryRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition<RepositoryViewHolder>(0, click()))
    }
}