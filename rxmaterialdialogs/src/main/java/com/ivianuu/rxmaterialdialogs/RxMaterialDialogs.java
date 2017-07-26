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

package com.ivianuu.rxmaterialdialogs;

import android.content.Context;
import android.support.annotation.NonNull;

import com.ivianuu.rxmaterialdialogs.input.InputDialogBuilder;
import com.ivianuu.rxmaterialdialogs.listmultichoice.MultiChoiceListDialogBuilder;
import com.ivianuu.rxmaterialdialogs.listsimple.SimpleListDialogBuilder;
import com.ivianuu.rxmaterialdialogs.listsinglechoice.SingleChoiceListDialogBuilder;
import com.ivianuu.rxmaterialdialogs.singlebutton.SingleButtonDialogBuilder;

// TODO: 26.07.2017  Add checkbox
// TODO: 26.07.2017  Add progressbar
// TODO: 26.07.2017  Add custom view

/**
 * Class to access the builders
 */
public final class RxMaterialDialogs {

    private RxMaterialDialogs() {
        // no instances
    }

    /**
     * Returns a builder for a input dialog
     */
    @NonNull
    public static InputDialogBuilder inputDialog(@NonNull Context context) {
        return new InputDialogBuilder(context);
    }

    /**
     * Returns a builder for a single button dialog
     */
    @NonNull
    public static SingleButtonDialogBuilder singleButtonDialog(@NonNull Context context) {
        return new SingleButtonDialogBuilder(context);
    }

    /**
     * Returns a builder for a simple list dialog
     */
    @NonNull
    public static SimpleListDialogBuilder simpleListDialog(@NonNull Context context) {
        return new SimpleListDialogBuilder(context);
    }

    /**
     * Returns a builder for a single choice list dialog
     */
    @NonNull
    public static SingleChoiceListDialogBuilder singleChoiceListDialog(@NonNull Context context) {
        return new SingleChoiceListDialogBuilder(context);
    }

    /**
     * Returns a builder for a multi choice list dialog
     */
    @NonNull
    public static MultiChoiceListDialogBuilder multiChoiceListDialog(@NonNull Context context) {
        return new MultiChoiceListDialogBuilder(context);
    }

}
