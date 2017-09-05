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

package com.ivianuu.rxmaterialdialogs.input;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;

import com.afollestad.materialdialogs.MaterialDialog;
import com.ivianuu.rxmaterialdialogs.base.DialogEvent;

/**
 * Input event
 */
public class InputDialogEvent extends DialogEvent {

    private final CharSequence input;

    @RestrictTo(RestrictTo.Scope.LIBRARY)
    public InputDialogEvent(@NonNull MaterialDialog dialog, @NonNull CharSequence input) {
        super(dialog);
        this.input = input;
    }

    /**
     * Returns the input of this event
     */
    @Nullable
    public CharSequence getInput() {
        return input;
    }
}
