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

import android.content.Context;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.StringRes;

import java.io.File;

import io.reactivex.Maybe;

/**
 * File chooser dialog builder
 */
public final class FileChooserDialogBuilder {

    @NonNull final transient Context context;
    @StringRes int cancelButton;
    String initialPath;
    String mimeType;
    String[] extensions;
    String goUpLabel;
    @Nullable String mediumFont;
    @Nullable String regularFont;
    FileChooserDialog.FileCallback fileCallback;

    @RestrictTo(RestrictTo.Scope.LIBRARY)
    public FileChooserDialogBuilder(@NonNull Context context) {
        this.context = context;
        cancelButton = android.R.string.cancel;
        initialPath = Environment.getExternalStorageDirectory().getAbsolutePath();
        mimeType = null;
        goUpLabel = "...";
    }

    public FileChooserDialogBuilder typeface(@Nullable String medium, @Nullable String regular) {
        this.mediumFont = medium;
        this.regularFont = regular;
        return this;
    }

    public FileChooserDialogBuilder cancelButton(@StringRes int text) {
        cancelButton = text;
        return this;
    }

    public FileChooserDialogBuilder initialPath(@Nullable String initialPath) {
        if (initialPath == null) {
            initialPath = File.separator;
        }
        this.initialPath = initialPath;
        return this;
    }

    public FileChooserDialogBuilder mimeType(@Nullable String type) {
        mimeType = type;
        return this;
    }

    public FileChooserDialogBuilder extensionsFilter(@Nullable String... extensions) {
        this.extensions = extensions;
        return this;
    }

    public FileChooserDialogBuilder goUpLabel(String text) {
        goUpLabel = text;
        return this;
    }

    FileChooserDialogBuilder callback(@NonNull FileChooserDialog.FileCallback fileCallback) {
        this.fileCallback = fileCallback;
        return this;
    }

    FileChooserDialog create() {
        return new FileChooserDialog(this);
    }

    @NonNull
    public Maybe<FileChooserDialogEvent> build() {
        return FileChooserDialogMaybe.create(this);
    }

}
