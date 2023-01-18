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

package io.element.android.x.root

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import com.airbnb.android.showkase.models.Showkase
import io.element.android.x.component.ShowkaseButton
import io.element.android.x.features.rageshake.crash.ui.CrashDetectionEvents
import io.element.android.x.features.rageshake.crash.ui.CrashDetectionView
import io.element.android.x.features.rageshake.detection.RageshakeDetectionEvents
import io.element.android.x.features.rageshake.detection.RageshakeDetectionView
import io.element.android.x.getBrowserIntent

@Composable
fun RootView(
    state: RootState,
    modifier: Modifier = Modifier,
    onOpenBugReport: () -> Unit = {},
    children: @Composable BoxScope.() -> Unit,
) {
    Box(
        modifier = modifier
            .fillMaxSize(),
        contentAlignment = Alignment.TopCenter,
    ) {
        children()
        val eventSink = state.eventSink
        val context = LocalContext.current

        fun onOpenBugReport() {
            state.crashDetectionState.eventSink(CrashDetectionEvents.ResetAppHasCrashed)
            state.rageshakeDetectionState.eventSink(RageshakeDetectionEvents.Dismiss)
            onOpenBugReport.invoke()
        }

        ShowkaseButton(
            isVisible = false,
            onCloseClicked = { eventSink(RootEvents.HideShowkaseButton) },
            onClick = { ContextCompat.startActivity(context, Showkase.getBrowserIntent(context), null) }
        )
        RageshakeDetectionView(
            state = state.rageshakeDetectionState,
            onOpenBugReport = ::onOpenBugReport,
        )
        CrashDetectionView(
            state = state.crashDetectionState,
            onOpenBugReport = ::onOpenBugReport,
        )
    }
}
