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

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.ivianuu.rxmaterialdialogs.base.DialogMaybe;

import io.reactivex.Maybe;
import io.reactivex.MaybeEmitter;

/**
 * Multi choice list dialog single
 */
class MultiChoiceListDialogMaybe extends DialogMaybe<MultiChoiceListDialogEvent> {

    private Integer[] selectedIndices;

    private MultiChoiceListDialogMaybe(MaterialDialog.Builder dialogBuilder, Integer[] selectedIndices) {
        super(dialogBuilder);
        this.selectedIndices = selectedIndices;
    }

    static Maybe<MultiChoiceListDialogEvent> create(@NonNull MaterialDialog.Builder dialogBuilder,
                                                           Integer[] selectedIndices) {
        return Maybe.create(new MultiChoiceListDialogMaybe(dialogBuilder, selectedIndices));
    }

    @Override
    protected void onPreBuild(@NonNull final MaybeEmitter<MultiChoiceListDialogEvent> e, @NonNull MaterialDialog.Builder dialogBuilder) {
        dialogBuilder
                .itemsCallbackMultiChoice(selectedIndices, new MaterialDialog.ListCallbackMultiChoice() {
                    @Override
                    public boolean onSelection(MaterialDialog dialog, Integer[] which, CharSequence[] text) {
                        if (!e.isDisposed()) {
                            e.onSuccess(new MultiChoiceListDialogEvent(dialog, which, text));
                            e.onComplete();
                        }
                        return true;
                    }
                })
                .onAny(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        if (which != DialogAction.POSITIVE && !e.isDisposed()) {
                            e.onComplete();
                        }
                    }
                });
    }
}
