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

package com.ivianuu.rxmaterialdialogs.listsimple;

import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.view.View;

import com.afollestad.materialdialogs.MaterialDialog;
import com.ivianuu.rxmaterialdialogs.base.DialogEvent;

/**
 * Simple list dialog event
 */
public class SimpleListDialogEvent extends DialogEvent {

    @IntDef(value = {
            EventType.CLICK,
            EventType.LONG_CLICK
    })
    public @interface EventType {
        int CLICK = 0;
        int LONG_CLICK = 1;
    }

    private final View itemView;
    private final int position;
    private final CharSequence text;
    private final int eventType;

    @RestrictTo(RestrictTo.Scope.LIBRARY)
    public SimpleListDialogEvent(@NonNull MaterialDialog dialog,
                          @NonNull View itemView,
                          int position,
                          @NonNull CharSequence text,
                          @EventType int eventType) {
        super(dialog);
        this.itemView = itemView;
        this.position = position;
        this.text = text;
        this.eventType = eventType;
    }

    /**
     * Returns the item view of this event
     */
    @NonNull
    public View getItemView() {
        return itemView;
    }

    /**
     * Returns the position of this event
     */
    public int getPosition() {
        return position;
    }

    /**
     * Returns the text of this event
     */
    @NonNull
    public CharSequence getText() {
        return text;
    }

    @EventType
    public int getEventType() {
        return eventType;
    }
}
