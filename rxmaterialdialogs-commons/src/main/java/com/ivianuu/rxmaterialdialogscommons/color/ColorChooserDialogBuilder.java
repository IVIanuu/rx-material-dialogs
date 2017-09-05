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

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.ArrayRes;
import android.support.annotation.CheckResult;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.StringRes;

import com.afollestad.materialdialogs.Theme;
import com.afollestad.materialdialogs.util.DialogUtils;
import com.ivianuu.rxmaterialdialogscommons.R;

import io.reactivex.Maybe;

/**
 * Color chooser dialog builder
 */
public class ColorChooserDialogBuilder {

    ColorChooserDialog.ColorCallback colorCallback;

    @NonNull
    final Context context;
    @Nullable
    String mediumFont;
    @Nullable
    String regularFont;
    @Nullable
    String title;
    @Nullable
    String titleSub;
    @ColorInt
    int preselectColor;
    String doneBtn;
    String backBtn;
    String cancelBtn;
    String presetsBtn;
    @Nullable
    int[] colorsTop;
    @Nullable
    int[][] colorsSub;
    @Nullable
    Theme theme;

    boolean accentMode = false;
    String customBtn;
    boolean dynamicButtonColor = true;
    boolean allowUserCustom = true;
    boolean allowUserCustomAlpha = true;
    boolean setPreselectionColor = false;

    @SuppressLint("PrivateResource")
    @RestrictTo(RestrictTo.Scope.LIBRARY)
    public ColorChooserDialogBuilder(@NonNull Context context) {
        this.context = context;

        // default button texts
        doneButton(R.string.md_done_label);
        cancelButton(R.string.md_cancel_label);
        backButton(R.string.md_back_label);
        customButton(R.string.md_custom_label);
        presetsButton(R.string.md_presets_label);
    }
    
    public ColorChooserDialogBuilder title(@StringRes int titleRes) {
        return title(context.getString(titleRes));
    }
    
    public ColorChooserDialogBuilder title(@NonNull String title) {
        this.title = title;
        return this;
    }
    
    public ColorChooserDialogBuilder typeface(@Nullable String medium, @Nullable String regular) {
        this.mediumFont = medium;
        this.regularFont = regular;
        return this;
    }

    public ColorChooserDialogBuilder titleSub(@StringRes int titleSubRes) {
        return titleSub(context.getString(titleSubRes));
    }
    
    public ColorChooserDialogBuilder titleSub(@NonNull String titleSub) {
        this.titleSub = titleSub;
        return this;
    }

    public ColorChooserDialogBuilder theme(@NonNull Theme theme) {
        this.theme = theme;
        return this;
    }

    public ColorChooserDialogBuilder preselect(@ColorInt int preselect) {
        preselectColor = preselect;
        setPreselectionColor = true;
        return this;
    }

    public ColorChooserDialogBuilder accentMode(boolean accentMode) {
        this.accentMode = accentMode;
        return this;
    }

    public ColorChooserDialogBuilder doneButton(@StringRes int textRes) {
        return doneButton(context.getString(textRes));
    }

    public ColorChooserDialogBuilder doneButton(@NonNull String text) {
        doneBtn = text;
        return this;
    }

    public ColorChooserDialogBuilder backButton(@StringRes int textRes) {
        return backButton(context.getString(textRes));
    }

    public ColorChooserDialogBuilder backButton(@NonNull String text) {
        backBtn = text;
        return this;
    }

    public ColorChooserDialogBuilder cancelButton(@StringRes int textRes) {
        return cancelButton(context.getString(textRes));
    }

    public ColorChooserDialogBuilder cancelButton(@NonNull String text) {
        cancelBtn = text;
        return this;
    }
    
    public ColorChooserDialogBuilder customButton(@StringRes int textRes) {
        return customButton(context.getString(textRes));
    }

    public ColorChooserDialogBuilder customButton(@NonNull String text) {
        customBtn = text;
        return this;
    }

    public ColorChooserDialogBuilder presetsButton(@StringRes int textRes) {
        return presetsButton(context.getString(textRes));
    }

    public ColorChooserDialogBuilder presetsButton(@NonNull String text) {
        presetsBtn = text;
        return this;
    }

    public ColorChooserDialogBuilder dynamicButtonColor(boolean enabled) {
        dynamicButtonColor = enabled;
        return this;
    }

    public ColorChooserDialogBuilder customColors(@NonNull int[] topLevel, @Nullable int[][] subLevel) {
        colorsTop = topLevel;
        colorsSub = subLevel;
        return this;
    }

    public ColorChooserDialogBuilder customColors(@ArrayRes int topLevel, @Nullable int[][] subLevel) {
        colorsTop = DialogUtils.getColorArray(context, topLevel);
        colorsSub = subLevel;
        return this;
    }

    public ColorChooserDialogBuilder allowUserColorInput(boolean allow) {
        allowUserCustom = allow;
        return this;
    }

    public ColorChooserDialogBuilder allowUserColorInputAlpha(boolean allow) {
        allowUserCustomAlpha = allow;
        return this;
    }

    ColorChooserDialogBuilder callback(@NonNull ColorChooserDialog.ColorCallback colorCallback) {
        this.colorCallback = colorCallback;
        return this;
    }

    ColorChooserDialog create() {
        return new ColorChooserDialog(this);
    }

    @CheckResult @NonNull
    public Maybe<ColorChooserDialogEvent> build() {
        return ColorChooserDialogMaybe.create(this);
    }

}