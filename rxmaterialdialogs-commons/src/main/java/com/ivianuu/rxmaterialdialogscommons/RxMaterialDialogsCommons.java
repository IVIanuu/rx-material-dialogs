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

package com.ivianuu.rxmaterialdialogscommons;

import android.content.Context;
import android.support.annotation.NonNull;

import com.ivianuu.rxmaterialdialogscommons.color.ColorChooserDialogBuilder;
import com.ivianuu.rxmaterialdialogscommons.filechooser.FileChooserDialogBuilder;
import com.ivianuu.rxmaterialdialogscommons.listmaterialsimple.MaterialSimpleListDialogBuilder;

import static com.ivianuu.rxmaterialdialogs.Preconditions.checkNotNull;

// TODO: 26.07.2017  Add folder chooser

/**
 * Material dialog commons
 */
public final class RxMaterialDialogsCommons {

    private RxMaterialDialogsCommons() {
        // no instances
    }

    /**
     * Returns a new color chooser dialog
     */
    @NonNull
    public static ColorChooserDialogBuilder colorChooserDialog(@NonNull Context context) {
        checkNotNull(context, "context == null");
        return new ColorChooserDialogBuilder(context);
    }

    /**
     * Returns a new file chooser dialog
     */
    @NonNull
    public static FileChooserDialogBuilder fileChooserDialogBuilder(@NonNull Context context) {
        checkNotNull(context, "context == null");
        return new FileChooserDialogBuilder(context);
    }

    /**
     * Returns a new material simple list dialog
     */
    @NonNull
    public static MaterialSimpleListDialogBuilder materialSimpleListDialog(@NonNull Context context) {
        checkNotNull(context, "context == null");
        return new MaterialSimpleListDialogBuilder(context);
    }

}
