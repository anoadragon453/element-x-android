/*
 * Copyright (c) 2022 New Vector Ltd
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

package io.element.android.x.features.rageshake.detection

import androidx.compose.runtime.Stable
import io.element.android.x.features.rageshake.preferences.RageshakePreferencesState

@Stable
data class RageshakeDetectionState(
    val takeScreenshot: Boolean = false,
    val showDialog: Boolean = false,
    val isStarted: Boolean = false,
    val preferenceState: RageshakePreferencesState = RageshakePreferencesState(),
    val eventSink: (RageshakeDetectionEvents) -> Unit = {}
)
