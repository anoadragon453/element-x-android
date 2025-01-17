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

package io.element.android.x.features.preferences.root

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import io.element.android.x.architecture.Async
import io.element.android.x.designsystem.components.preferences.PreferenceView
import io.element.android.x.features.logout.LogoutPreferenceState
import io.element.android.x.features.logout.LogoutPreferenceView
import io.element.android.x.features.preferences.user.UserPreferences
import io.element.android.x.features.rageshake.preferences.RageshakePreferencesState
import io.element.android.x.features.rageshake.preferences.RageshakePreferencesView
import io.element.android.x.ui.strings.R as StringR

@Composable
fun PreferencesRootView(
    state: PreferencesRootState,
    modifier: Modifier = Modifier,
    onBackPressed: () -> Unit = {},
    onOpenRageShake: () -> Unit = {},
) {
    // TODO Hierarchy!
    // Include pref from other modules
    PreferenceView(
        modifier = modifier,
        onBackPressed = onBackPressed,
        title = stringResource(id = StringR.string.settings)
    ) {
        UserPreferences(state.myUser)
        RageshakePreferencesView(
            state = state.rageshakeState,
            onOpenRageshake = onOpenRageShake,
        )
        LogoutPreferenceView(
            state = state.logoutState,
        )
    }
}

@Preview
@Composable
fun PreferencesContentPreview() {
    val state = PreferencesRootState(
        logoutState = LogoutPreferenceState(),
        rageshakeState = RageshakePreferencesState(),
        myUser = Async.Uninitialized
    )
    PreferencesRootView(state)
}
