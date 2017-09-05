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

package com.ivianuu.rxmaterialdialogs.base;

import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;

import com.afollestad.materialdialogs.MaterialDialog;

import io.reactivex.MaybeEmitter;
import io.reactivex.MaybeOnSubscribe;

/**
 * Dialog single
 */
@RestrictTo(RestrictTo.Scope.LIBRARY)
public abstract class DialogMaybe<T> implements MaybeOnSubscribe<T> {

    private MaterialDialog.Builder dialogBuilder;

    public DialogMaybe(@NonNull MaterialDialog.Builder dialogBuilder) {
        this.dialogBuilder = dialogBuilder;
    }

    @Override
    public final void subscribe(final MaybeEmitter<T> e) throws Exception {
        // set cancel listener
        dialogBuilder
                .cancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialogInterface) {
                        e.onComplete();
                    }
                });

        // apply settings to the builder
        onPreBuild(e, dialogBuilder);

        // build
        MaterialDialog dialog = dialogBuilder.build();

        // set disposable
        e.setDisposable(new DialogDisposable(dialog));

        // show
        dialog.show();
    }

    /**
     * This will be called right before the dialog will be build
     */
    protected abstract void onPreBuild(@NonNull MaybeEmitter<T> e, @NonNull MaterialDialog.Builder dialogBuilder);

}
