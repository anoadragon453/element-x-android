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

package io.element.android.x.di

import com.squareup.anvil.annotations.ContributesTo
import com.squareup.anvil.annotations.MergeSubcomponent
import dagger.BindsInstance
import dagger.Subcomponent
import io.element.android.x.architecture.NodeFactoriesBindings
import io.element.android.x.matrix.room.MatrixRoom

@SingleIn(RoomScope::class)
@MergeSubcomponent(RoomScope::class)
interface RoomComponent : NodeFactoriesBindings {

    @Subcomponent.Builder
    interface Builder {
        @BindsInstance
        fun room(room: MatrixRoom): Builder
        fun build(): RoomComponent
    }

    @ContributesTo(SessionScope::class)
    interface ParentBindings {
        fun roomComponentBuilder(): Builder
    }
}
