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

import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.view.View;

import com.afollestad.materialdialogs.MaterialDialog;
import com.ivianuu.rxmaterialdialogs.base.DialogEvent;

/**
 * Single choice list dialog event
 */
public class SingleChoiceListDialogEvent extends DialogEvent {

    private View itemView;
    private int which;
    private CharSequence text;

    @RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
    public SingleChoiceListDialogEvent(@NonNull MaterialDialog dialog,
                          @NonNull View itemView,
                          int which,
                          @NonNull CharSequence text) {
        super(dialog);
        this.itemView = itemView;
        this.which = which;
        this.text = text;
    }

    /**
     * Returns the item view of this event
     */
    @NonNull
    public View getItemView() {
        return itemView;
    }

    /**
     * Returns the which of this event
     */
    public int getWhich() {
        return which;
    }

    /**
     * Returns the text of this event
     */
    @NonNull
    public CharSequence getText() {
        return text;
    }

}
