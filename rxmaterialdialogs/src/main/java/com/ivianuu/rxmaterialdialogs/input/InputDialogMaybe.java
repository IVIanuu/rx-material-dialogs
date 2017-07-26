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

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.ivianuu.rxmaterialdialogs.base.DialogMaybe;

import io.reactivex.Maybe;
import io.reactivex.MaybeEmitter;

/**
 * Input single
 */
class InputDialogMaybe extends DialogMaybe<InputDialogEvent> {

    private CharSequence hint;
    private CharSequence prefill;
    private boolean allowEmptyInput;

    private InputDialogMaybe(MaterialDialog.Builder dialogBuilder,
                             CharSequence hint,
                             CharSequence prefill,
                             boolean allowEmptyInput) {
        super(dialogBuilder);
        this.hint = hint;
        this.prefill = prefill;
        this.allowEmptyInput = allowEmptyInput;
    }

    static Maybe<InputDialogEvent> create(MaterialDialog.Builder dialogBuilder,
                                          CharSequence hint,
                                          CharSequence prefill,
                                          boolean allowEmptyInput) {
        return Maybe.create(new InputDialogMaybe(dialogBuilder, hint, prefill, allowEmptyInput));
    }

    @Override
    protected void onPreBuild(@NonNull final MaybeEmitter<InputDialogEvent> e, @NonNull MaterialDialog.Builder dialogBuilder) {
        dialogBuilder
                .input(hint, prefill, allowEmptyInput, new MaterialDialog.InputCallback() {
                    @Override
                    public void onInput(@NonNull MaterialDialog dialog, CharSequence input) {
                        if (!e.isDisposed()) {
                            e.onSuccess(new InputDialogEvent(dialog, input));
                            e.onComplete();
                        }
                    }
                })
                .onAny(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        if (which != DialogAction.POSITIVE && !e.isDisposed())  {
                            e.onComplete();
                        }
                    }
                });
    }
}
