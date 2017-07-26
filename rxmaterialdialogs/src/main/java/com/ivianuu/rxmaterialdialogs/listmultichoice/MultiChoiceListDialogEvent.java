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

package com.ivianuu.rxmaterialdialogs.listmultichoice;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;

import com.afollestad.materialdialogs.MaterialDialog;
import com.ivianuu.rxmaterialdialogs.base.DialogEvent;

/**
 * Multi choice list dialog event
 */
public class MultiChoiceListDialogEvent extends DialogEvent {

    private Integer[] which;
    private CharSequence[] text;

    @RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
    public MultiChoiceListDialogEvent(@NonNull MaterialDialog dialog,
                                @NonNull Integer[] which,
                                @NonNull CharSequence[] text) {
        super(dialog);
        this.which = which;
        this.text = text;
    }

    /**
     * Returns the position of this event
     */
    @Nullable
    public Integer[] getWhich() {
        return which;
    }

    /**
     * Returns the texts of this event
     */
    @Nullable
    public CharSequence[] getText() {
        return text;
    }

}
