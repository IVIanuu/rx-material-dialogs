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

package com.ivianuu.rxmaterialdialogscommons.listcustom;

import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;

import com.afollestad.materialdialogs.MaterialDialog;
import com.ivianuu.rxmaterialdialogs.base.DialogEvent;

/**
 * Custom list dialog event
 */
public final class CustomListDialogEvent<Item extends CustomListItem> extends DialogEvent {

    private final int index;
    private final Item item;

    @RestrictTo(RestrictTo.Scope.LIBRARY)
    public CustomListDialogEvent(@NonNull MaterialDialog dialog,
                                 int index,
                                 @NonNull Item item) {
        super(dialog);
        this.index = index;
        this.item = item;
    }

    /**
     * Returns the index of this event
     */
    public int getIndex() {
        return index;
    }

    /**
     * Returns the item of this event
     */
    @NonNull
    public Item getItem() {
        return item;
    }
}
