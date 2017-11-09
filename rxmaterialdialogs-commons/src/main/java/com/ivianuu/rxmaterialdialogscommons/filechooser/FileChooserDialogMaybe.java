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

package com.ivianuu.rxmaterialdialogscommons.filechooser;

import android.support.annotation.NonNull;

import com.ivianuu.rxmaterialdialogs.base.DialogCancellable;

import io.reactivex.Maybe;
import io.reactivex.MaybeEmitter;
import io.reactivex.MaybeOnSubscribe;

/**
 * File chooser dialog maybe
 */
final class FileChooserDialogMaybe implements MaybeOnSubscribe<FileChooserDialogEvent> {

    private final FileChooserDialogBuilder builder;

    private FileChooserDialogMaybe(FileChooserDialogBuilder builder) {
        this.builder = builder;
    }

    static Maybe<FileChooserDialogEvent> create(@NonNull FileChooserDialogBuilder builder) {
        return Maybe.create(new FileChooserDialogMaybe(builder));
    }

    @Override
    public void subscribe(final MaybeEmitter<FileChooserDialogEvent> e) throws Exception {
        // set file callback
        builder.callback((dialog, file) -> {
            e.onSuccess(new FileChooserDialogEvent(dialog.materialDialog, file));
            e.onComplete();
        });

        FileChooserDialog dialog = builder.create();

        // set cancellable
        e.setCancellable(new DialogCancellable(dialog.materialDialog));

        // show
        dialog.show();
    }
}
