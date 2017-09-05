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

package com.ivianuu.rxmaterialdialogs.listsinglechoice;

import android.content.Context;
import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;

import com.ivianuu.rxmaterialdialogs.list.BaseListDialogBuilder;

import io.reactivex.Maybe;

/**
 * Single choice list dialog builder
 */
public class SingleChoiceListDialogBuilder extends BaseListDialogBuilder<SingleChoiceListDialogBuilder> {

    private int selectedIndex = -1;

    @RestrictTo(RestrictTo.Scope.LIBRARY)
    public SingleChoiceListDialogBuilder(@NonNull Context context) {
        super(context);
        setThisBuilder(this);
    }

    public SingleChoiceListDialogBuilder selectedIndex(int selectedIndex) {
        this.selectedIndex = selectedIndex;
        return this;
    }

    @CheckResult @NonNull
    public Maybe<SingleChoiceListDialogEvent> build() {
        return SingleChoiceListDialogMaybe.create(wrappedBuilder, selectedIndex);
    }
}
