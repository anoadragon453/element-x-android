/*
 * Copyright (c) 2023 New Vector Ltd
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.element.android.libraries.push.providers.firebase

import io.element.android.libraries.core.log.logger.LoggerTag
import io.element.android.libraries.matrix.api.MatrixClient
import io.element.android.libraries.push.providers.api.PushProvider
import io.element.android.libraries.push.providers.api.PusherSubscriber
import timber.log.Timber
import javax.inject.Inject

private val loggerTag = LoggerTag("FirebasePushProvider")

class FirebasePushProvider @Inject constructor(
    private val googleFcmHelper: GoogleFcmHelper,
    private val pusherSubscriber: PusherSubscriber,
) : PushProvider {

    override fun getDistributorNames(): List<String> {
        // Must return an non-empty list for now
        return listOf("unused")
    }

    override suspend fun registerWith(matrixClient: MatrixClient, distributorName: String) {
        val pushKey = googleFcmHelper.getFcmToken() ?: return Unit.also {
            Timber.tag(loggerTag.value).w("Unable to register pusher, Firebase token is not known.")
        }
        pusherSubscriber.registerPusher(matrixClient, pushKey, FirebaseConfig.pusher_http_url)
    }
}
