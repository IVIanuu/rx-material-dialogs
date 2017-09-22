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

package com.ivianuu.rxmaterialdialogscommons.color;

import android.support.annotation.NonNull;

import com.ivianuu.rxmaterialdialogs.base.DialogCancellable;

import io.reactivex.Maybe;
import io.reactivex.MaybeEmitter;
import io.reactivex.MaybeOnSubscribe;

/**
 * Color chooser dialog maybe
 */
final class ColorChooserDialogMaybe implements MaybeOnSubscribe<ColorChooserDialogEvent> {

    private final ColorChooserDialogBuilder builder;

    private ColorChooserDialogMaybe(ColorChooserDialogBuilder builder) {
        this.builder = builder;
    }

    static Maybe<ColorChooserDialogEvent> create(@NonNull ColorChooserDialogBuilder builder) {
        return Maybe.create(new ColorChooserDialogMaybe(builder));
    }

    @Override
    public void subscribe(final MaybeEmitter<ColorChooserDialogEvent> e) throws Exception {
        // set color callback
        builder.callback((dialog, selectedColor) -> {
            if (!e.isDisposed()) {
                e.onSuccess(new ColorChooserDialogEvent(dialog, selectedColor));
                e.onComplete();
            }
        });

        ColorChooserDialog dialog = builder.create();

        // set cancellable
        e.setCancellable(new DialogCancellable(dialog.materialDialog));

        // show
        dialog.show();
    }
}
