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

import android.content.Context;
import android.support.annotation.CheckResult;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.StringRes;

import com.ivianuu.rxmaterialdialogs.base.BaseDialogBuilder;

import io.reactivex.Maybe;

/**
 * Input builder
 */
public class InputDialogBuilder extends BaseDialogBuilder<InputDialogBuilder> {

    private CharSequence hint;
    private CharSequence prefill;
    private boolean allowEmptyInput;

    @RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
    public InputDialogBuilder(@NonNull Context context) {
        super(context);
        setThisBuilder(this);
    }

    public InputDialogBuilder input(
            @Nullable CharSequence hint,
            @Nullable CharSequence prefill,
            boolean allowEmptyInput) {
        this.hint = hint;
        this.prefill = prefill;
        this.allowEmptyInput = allowEmptyInput;
        return this;
    }

    public InputDialogBuilder input(
            @Nullable CharSequence hint,
            @Nullable CharSequence prefill) {
        return input(hint, prefill, true);
    }

    public InputDialogBuilder input(
            @StringRes int hint,
            @StringRes int prefill,
            boolean allowEmptyInput) {
        return input(
                hint == 0 ? null : wrappedBuilder.getContext().getText(hint),
                prefill == 0 ? null : wrappedBuilder.getContext().getText(prefill),
                allowEmptyInput);
    }

    public InputDialogBuilder input(
            @StringRes int hint, @StringRes int prefill) {
        return input(hint, prefill, true);
    }

    public InputDialogBuilder inputType(int type) {
        wrappedBuilder.inputType(type);
        return this;
    }

    public InputDialogBuilder inputRange(
            @IntRange(from = 0, to = Integer.MAX_VALUE) int minLength,
            @IntRange(from = -1, to = Integer.MAX_VALUE) int maxLength) {
        wrappedBuilder.inputRange(minLength, maxLength);
        return this;
    }

    public InputDialogBuilder inputRange(
            @IntRange(from = 0, to = Integer.MAX_VALUE) int minLength,
            @IntRange(from = -1, to = Integer.MAX_VALUE) int maxLength,
            @ColorInt int errorColor) {
        wrappedBuilder.inputRange(minLength, maxLength, errorColor);
        return this;
    }

    public InputDialogBuilder inputRangeRes(
            @IntRange(from = 0, to = Integer.MAX_VALUE) int minLength,
            @IntRange(from = -1, to = Integer.MAX_VALUE) int maxLength,
            @ColorRes int errorColor) {
        wrappedBuilder.inputRangeRes(minLength, maxLength, errorColor);
        return this;
    }

    @CheckResult @NonNull
    public Maybe<InputDialogEvent> build() {
        return InputDialogMaybe.create(wrappedBuilder, hint, prefill, allowEmptyInput);
    }
}
