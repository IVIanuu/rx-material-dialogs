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

import com.afollestad.materialdialogs.MaterialDialog;
import com.ivianuu.rxmaterialdialogs.base.DialogMaybe;

import io.reactivex.Maybe;
import io.reactivex.MaybeEmitter;

/**
 * Single button single
 */
class SingleButtonDialogMaybe extends DialogMaybe<SingleButtonDialogEvent> {

    private SingleButtonDialogMaybe(MaterialDialog.Builder dialogBuilder) {
        super(dialogBuilder);
    }

    /**
     * Returns a new single button single
     */
    static Maybe<SingleButtonDialogEvent> create(@NonNull MaterialDialog.Builder dialogBuilder) {
        return Maybe.create(new SingleButtonDialogMaybe(dialogBuilder));
    }

    @Override
    protected void onPreBuild(@NonNull final MaybeEmitter<SingleButtonDialogEvent> e, @NonNull MaterialDialog.Builder dialogBuilder) {
        dialogBuilder
                .onAny((dialog, which) -> {
                    if (!e.isDisposed()) {
                        e.onSuccess(new SingleButtonDialogEvent(dialog, which));
                        e.onComplete();
                    }
                });
    }

}
