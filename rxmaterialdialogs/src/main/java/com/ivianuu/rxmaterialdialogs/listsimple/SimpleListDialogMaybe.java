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

import android.support.annotation.NonNull;

import com.afollestad.materialdialogs.MaterialDialog;
import com.ivianuu.rxmaterialdialogs.base.DialogMaybe;

import io.reactivex.Maybe;
import io.reactivex.MaybeEmitter;

/**
 * Simple list dialog single
 */
class SimpleListDialogMaybe extends DialogMaybe<SimpleListDialogEvent> {

    private SimpleListDialogMaybe(MaterialDialog.Builder dialogBuilder) {
        super(dialogBuilder);
    }

    static Maybe<SimpleListDialogEvent> create(@NonNull MaterialDialog.Builder dialogBuilder) {
        return Maybe.create(new SimpleListDialogMaybe(dialogBuilder));
    }

    @Override
    protected void onPreBuild(@NonNull final MaybeEmitter<SimpleListDialogEvent> e, @NonNull MaterialDialog.Builder dialogBuilder) {
        dialogBuilder
                .itemsCallback((dialog, itemView, position, text) -> {
                    if (!e.isDisposed()) {
                        e.onSuccess(new SimpleListDialogEvent(
                                dialog, itemView, position, text, SimpleListDialogEvent.EventType.CLICK));
                        e.onComplete();
                    }
                })
                .itemsLongCallback((dialog, itemView, position, text) -> {
                    if (!e.isDisposed()) {
                        e.onSuccess(new SimpleListDialogEvent(
                                dialog, itemView, position, text, SimpleListDialogEvent.EventType.LONG_CLICK));
                        e.onComplete();
                    }
                    return true;
                })
                .onAny((dialog, which) -> {
                    if (!e.isDisposed()) {
                        e.onComplete();
                    }
                });
    }
}
