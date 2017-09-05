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

import com.afollestad.materialdialogs.MaterialDialog;
import com.ivianuu.rxmaterialdialogs.base.DialogEvent;

import java.io.File;

/**
 * File chooser dialog event
 */
public final class FileChooserDialogEvent extends DialogEvent {

    private final File selectedFile;

    FileChooserDialogEvent(@NonNull MaterialDialog dialog, @NonNull File selectedFile) {
        super(dialog);
        this.selectedFile = selectedFile;
    }

    /**
     * Returns the selected file of this event
     */
    @NonNull
    public File getSelectedFile() {
        return selectedFile;
    }
}
