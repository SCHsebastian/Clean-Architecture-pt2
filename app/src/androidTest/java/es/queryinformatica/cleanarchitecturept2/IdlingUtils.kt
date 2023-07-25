package es.queryinformatica.cleanarchitecturept2

import es.queryinformatica.cleanarchitecturept2.idling.ComposeCountingIdlingResource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart


fun <T> Flow<T>.attachIdling(countingIdlingResource: ComposeCountingIdlingResource): Flow<T> {
    return onStart {
        countingIdlingResource.increment()
    }.onEach {
        countingIdlingResource.decrement()
    }
}

