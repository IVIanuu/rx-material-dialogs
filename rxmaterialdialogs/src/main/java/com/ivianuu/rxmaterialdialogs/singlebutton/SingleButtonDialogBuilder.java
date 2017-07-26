/*
 * Copyright 2017 Manuel Wrage
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ivianuu.rxmaterialdialogs.singlebutton;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;

import com.ivianuu.rxmaterialdialogs.base.BaseDialogBuilder;

import io.reactivex.Maybe;

/**
 * @author Manuel Wrage (IVIanuu)
 */
public class SingleButtonDialogBuilder extends BaseDialogBuilder<SingleButtonDialogBuilder> {

    @RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
    public SingleButtonDialogBuilder(@NonNull Context context) {
        super(context);
        setThisBuilder(this);
    }

    @NonNull
    public Maybe<SingleButtonDialogEvent> build() {
        return SingleButtonDialogMaybe.create(wrappedBuilder);
    }
}
