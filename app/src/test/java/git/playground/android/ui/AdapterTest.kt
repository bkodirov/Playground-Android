package git.playground.android.ui

import com.google.common.truth.Truth.assertThat
import org.junit.Test
import java.net.UnknownHostException

class AdapterTest {
    @Test
    fun `when map called with Throwable THEN returns Fail object`() {
        val actual = GitRepoResponseAdapter.map(UnknownHostException("dummy.net doesn't exist"))
        assertThat(actual).isInstanceOf(Fail::class.java)
    }
    @Test
    fun `when map called with list of Repository objects THEN returns Success object`() {
        val actual = GitRepoResponseAdapter.map(listOf())
        assertThat(actual).isInstanceOf(Success::class.java)
    }
}