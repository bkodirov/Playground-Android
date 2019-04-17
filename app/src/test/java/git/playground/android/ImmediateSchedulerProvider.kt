package git.playground.android

import git.playground.android.domain.SchedulerProvider
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

object ImmediateSchedulerProvider : SchedulerProvider {
    override fun single(): Scheduler = Schedulers.trampoline()

    override fun computation(): Scheduler = Schedulers.trampoline()

    override fun io(): Scheduler = Schedulers.trampoline()

    override fun ui(): Scheduler = Schedulers.trampoline()
}