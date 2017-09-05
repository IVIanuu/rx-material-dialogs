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

import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.ivianuu.rxmaterialdialogs.base.DialogEvent;

/**
 * Represents a single button event
 */
public class SingleButtonDialogEvent extends DialogEvent {

    private final DialogAction which;

    @RestrictTo(RestrictTo.Scope.LIBRARY)
    public SingleButtonDialogEvent(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
        super(dialog);
        this.which = which;
    }

    /**
     * Returns the clicked button type this event
     */
    @NonNull
    public DialogAction getWhich() {
        return which;
    }

    /**
     * Returns if the event action equals the passed one
     */
    public boolean isButton(@NonNull DialogAction action) {
        return which == action;
    }
}
