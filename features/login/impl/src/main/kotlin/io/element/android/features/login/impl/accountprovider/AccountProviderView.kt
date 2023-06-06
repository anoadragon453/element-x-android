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

package io.element.android.features.login.impl.accountprovider

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import io.element.android.features.login.impl.R
import io.element.android.libraries.designsystem.atomic.molecules.ButtonColumnMolecule
import io.element.android.libraries.designsystem.atomic.molecules.IconTitleSubtitleMolecule
import io.element.android.libraries.designsystem.atomic.pages.HeaderFooterPage
import io.element.android.libraries.designsystem.preview.ElementPreviewDark
import io.element.android.libraries.designsystem.preview.ElementPreviewLight
import io.element.android.libraries.designsystem.theme.components.Button
import io.element.android.libraries.designsystem.theme.components.Text
import io.element.android.libraries.designsystem.theme.components.TextButton

@Composable
fun AccountProviderView(
    state: AccountProviderState,
    modifier: Modifier = Modifier,
    onContinue: () -> Unit = {},
    onChange: () -> Unit = {},
) {
    HeaderFooterPage(
        modifier = modifier,
        header = {
            IconTitleSubtitleMolecule(
                modifier = Modifier.padding(top = 60.dp),
                iconImageVector = Icons.Filled.AccountCircle,
                title = stringResource(
                    id = if (state.isAccountCreation) {
                        R.string.screen_account_provider_signup_title
                    } else {
                        R.string.screen_account_provider_signin_title
                    },
                    state.homeserver
                ),
                subTitle = stringResource(
                    id = if (state.isAccountCreation) {
                        R.string.screen_account_provider_signup_subtitle
                    } else {
                        // Use same value for now.
                        R.string.screen_account_provider_signup_subtitle
                    },
                )
            )
        },
        footer = {
            ButtonColumnMolecule {
                Button(
                    onClick = {
                        onContinue()
                    },
                    enabled = true,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(text = stringResource(id = R.string.screen_account_provider_continue))
                }
                TextButton(
                    onClick = {
                        onChange()
                    },
                    enabled = true,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(text = stringResource(id = R.string.screen_account_provider_change))
                }
            }
        }
    ) {
        // No content
    }
}

@Preview
@Composable
fun AccountProviderViewLightPreview(@PreviewParameter(AccountProviderStateProvider::class) state: AccountProviderState) =
    ElementPreviewLight { ContentToPreview(state) }

@Preview
@Composable
fun AccountProviderViewDarkPreview(@PreviewParameter(AccountProviderStateProvider::class) state: AccountProviderState) =
    ElementPreviewDark { ContentToPreview(state) }

@Composable
private fun ContentToPreview(state: AccountProviderState) {
    AccountProviderView(
        state = state,
    )
}
