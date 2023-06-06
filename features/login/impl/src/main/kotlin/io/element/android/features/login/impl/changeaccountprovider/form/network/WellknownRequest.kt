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
package io.element.android.features.login.impl.changeaccountprovider.form.network

import io.element.android.libraries.core.bool.orFalse
import io.element.android.libraries.network.RetrofitFactory
import timber.log.Timber
import javax.inject.Inject

class WellknownRequest @Inject constructor(
    private val retrofitFactory: RetrofitFactory,
) {
    /**
     * Return true if the wellknown can be retrieved and is valid
     * @param baseUrl for instance https://matrix.org
     */
    suspend fun execute(baseUrl: String): Boolean {
        val wellknownApi = retrofitFactory.create(baseUrl)
            .create(WellknownAPI::class.java)

        return try {
            val response = wellknownApi.getWellKnown()
            response.isValid()
        } catch (throwable: Throwable) {
            Timber.e(throwable)
            false
        }
    }
}

private fun WellKnown.isValid(): Boolean {
    return homeServer?.baseURL?.isNotBlank().orFalse()
}
