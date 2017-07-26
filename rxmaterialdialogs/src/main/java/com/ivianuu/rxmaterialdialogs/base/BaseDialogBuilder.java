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

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.annotation.AttrRes;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.DimenRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.StringRes;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.GravityEnum;
import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.StackingBehavior;
import com.afollestad.materialdialogs.Theme;

/**
 * BaseDialogBuilder
 */
@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
public class BaseDialogBuilder<T extends BaseDialogBuilder> {

    protected MaterialDialog.Builder wrappedBuilder;
    protected T thisBuilder;

    public BaseDialogBuilder(@NonNull Context context) {
        wrappedBuilder = new MaterialDialog.Builder(context);
    }

    protected final void setThisBuilder(T thisBuilder) {
        this.thisBuilder = thisBuilder;
    }

    public T title(@StringRes int titleRes) {
        wrappedBuilder.title(titleRes);
        return thisBuilder;
    }

    public T title(@NonNull CharSequence title) {
        wrappedBuilder.title(title);
        return thisBuilder;
    }

    public T titleGravity(@NonNull GravityEnum gravity) {
        wrappedBuilder.titleGravity(gravity);
        return thisBuilder;
    }

    public T buttonRippleColor(@ColorInt int color) {
        wrappedBuilder.buttonRippleColor(color);
        return thisBuilder;
    }

    public T buttonRippleColorRes(@ColorRes int colorRes) {
        wrappedBuilder.buttonRippleColorRes(colorRes);
        return thisBuilder;
    }

    public T buttonRippleColorAttr(@AttrRes int colorAttr) {
        wrappedBuilder.buttonRippleColorAttr(colorAttr);
        return thisBuilder;
    }

    public T titleColor(@ColorInt int color) {
        wrappedBuilder.titleColor(color);
        return thisBuilder;
    }

    public T titleColorRes(@ColorRes int colorRes) {
        wrappedBuilder.titleColorRes(colorRes);
        return thisBuilder;
    }

    public T titleColorAttr(@AttrRes int colorAttr) {
        wrappedBuilder.titleColorAttr(colorAttr);
        return thisBuilder;
    }

    public T typeface(@Nullable Typeface medium, @Nullable Typeface regular) {
        wrappedBuilder.typeface(medium, regular);
        return thisBuilder;
    }

    public T typeface(@Nullable String medium, @Nullable String regular) {
        wrappedBuilder.typeface(medium, regular);
        return thisBuilder;
    }

    public T icon(@NonNull Drawable icon) {
        wrappedBuilder.icon(icon);
        return thisBuilder;
    }

    public T iconRes(@DrawableRes int icon) {
        wrappedBuilder.iconRes(icon);
        return thisBuilder;
    }

    public T iconAttr(@AttrRes int iconAttr) {
        wrappedBuilder.iconAttr(iconAttr);
        return thisBuilder;
    }

    public T content(@StringRes int contentRes) {
        wrappedBuilder.content(contentRes);
        return thisBuilder;
    }

    public T content(@StringRes int contentRes, boolean html) {
        wrappedBuilder.content(contentRes, html);
        return thisBuilder;
    }

    public T content(@NonNull CharSequence content) {
        wrappedBuilder.content(content);
        return thisBuilder;
    }

    public T content(@StringRes int contentRes, Object... formatArgs) {
        wrappedBuilder.content(contentRes, formatArgs);
        return thisBuilder;
    }

    public T contentColor(@ColorInt int color) {
        wrappedBuilder.contentColor(color);
        return thisBuilder;
    }

    public T contentColorRes(@ColorRes int colorRes) {
        wrappedBuilder.contentColorRes(colorRes);
        return thisBuilder;
    }

    public T contentColorAttr(@AttrRes int colorAttr) {
        wrappedBuilder.contentColorAttr(colorAttr);
        return thisBuilder;
    }

    public T contentGravity(@NonNull GravityEnum gravity) {
        wrappedBuilder.contentGravity(gravity);
        return thisBuilder;
    }

    public T contentLineSpacing(float multiplier) {
        wrappedBuilder.contentLineSpacing(multiplier);
        return thisBuilder;
    }

    public T buttonsGravity(@NonNull GravityEnum gravity) {
        wrappedBuilder.buttonsGravity(gravity);
        return thisBuilder;
    }

    public T positiveText(@StringRes int positiveRes) {
        wrappedBuilder.positiveText(positiveRes);
        return thisBuilder;
    }

    public T positiveText(@NonNull CharSequence message) {
        wrappedBuilder.positiveText(message);
        return thisBuilder;
    }

    public T positiveColor(@ColorInt int color) {
        wrappedBuilder.positiveColor(color);
        return thisBuilder;
    }

    public T positiveColorRes(@ColorRes int colorRes) {
        wrappedBuilder.positiveColorRes(colorRes);
        return thisBuilder;
    }

    public T positiveColorAttr(@AttrRes int colorAttr) {
        wrappedBuilder.positiveColorAttr(colorAttr);
        return thisBuilder;
    }

    public T positiveColor(@NonNull ColorStateList colorStateList) {
        wrappedBuilder.positiveColor(colorStateList);
        return thisBuilder;
    }

    public T positiveFocus(boolean isFocusedDefault) {
        wrappedBuilder.positiveFocus(isFocusedDefault);
        return thisBuilder;
    }

    public T neutralText(@StringRes int neutralRes) {
        wrappedBuilder.neutralText(neutralRes);
        return thisBuilder;
    }

    public T neutralText(@NonNull CharSequence message) {
        wrappedBuilder.neutralText(message);
        return thisBuilder;
    }

    public T negativeColor(@ColorInt int color) {
        wrappedBuilder.negativeColor(color);
        return thisBuilder;
    }

    public T negativeColorRes(@ColorRes int colorRes) {
        wrappedBuilder.negativeColorRes(colorRes);
        return thisBuilder;
    }

    public T negativeColorAttr(@AttrRes int colorAttr) {
        wrappedBuilder.negativeColorAttr(colorAttr);
        return thisBuilder;
    }

    public T negativeColor(@NonNull ColorStateList colorStateList) {
        wrappedBuilder.negativeColor(colorStateList);
        return thisBuilder;
    }

    public T negativeText(@StringRes int negativeRes) {
        wrappedBuilder.neutralText(negativeRes);
        return thisBuilder;
    }

    public T negativeText(@NonNull CharSequence message) {
        wrappedBuilder.negativeText(message);
        return thisBuilder;
    }

    public T negativeFocus(boolean isFocusedDefault) {
        wrappedBuilder.negativeFocus(isFocusedDefault);
        return thisBuilder;
    }

    public T neutralColor(@ColorInt int color) {
        wrappedBuilder.negativeColor(color);
        return thisBuilder;
    }

    public T neutralColorRes(@ColorRes int colorRes) {
        wrappedBuilder.negativeColorRes(colorRes);
        return thisBuilder;
    }

    public T neutralColorAttr(@AttrRes int colorAttr) {
        wrappedBuilder.negativeColorAttr(colorAttr);
        return thisBuilder;
    }

    public T neutralColor(@NonNull ColorStateList colorStateList) {
        wrappedBuilder.negativeColor(colorStateList);
        return thisBuilder;
    }

    public T neutralFocus(boolean isFocusedDefault) {
        wrappedBuilder.negativeFocus(isFocusedDefault);
        return thisBuilder;
    }

    public T linkColor(@ColorInt int color) {
        wrappedBuilder.linkColor(color);
        return thisBuilder;
    }

    public T linkColorRes(@ColorRes int colorRes) {
        wrappedBuilder.linkColorRes(colorRes);
        return thisBuilder;
    }

    public T linkColorAttr(@AttrRes int colorAttr) {
        wrappedBuilder.linkColorAttr(colorAttr);
        return thisBuilder;
    }

    public T linkColor(@NonNull ColorStateList colorStateList) {
        wrappedBuilder.linkColor(colorStateList);
        return thisBuilder;
    }

    public T listSelector(@DrawableRes int selectorRes) {
        wrappedBuilder.listSelector(selectorRes);
        return thisBuilder;
    }

    public T btnSelectorStacked(@DrawableRes int selectorRes) {
        wrappedBuilder.btnSelectorStacked(selectorRes);
        return thisBuilder;
    }

    public T btnSelector(@DrawableRes int selectorRes) {
        wrappedBuilder.btnSelector(selectorRes);
        return thisBuilder;
    }

    public T btnSelector(@DrawableRes int selectorRes, @NonNull DialogAction which) {
        wrappedBuilder.btnSelector(selectorRes, which);
        return thisBuilder;
    }
    
    public T btnStackedGravity(@NonNull GravityEnum gravity) {
        wrappedBuilder.btnStackedGravity(gravity);
        return thisBuilder;
    }

    public T widgetColor(@ColorInt int color) {
        wrappedBuilder.widgetColor(color);
        return thisBuilder;
    }

    public T widgetColorRes(@ColorRes int colorRes) {
        wrappedBuilder.widgetColorRes(colorRes);
        return thisBuilder;
    }

    public T widgetColorAttr(@AttrRes int colorAttr) {
        wrappedBuilder.widgetColorAttr(colorAttr);
        return thisBuilder;
    }

    public T choiceWidgetColor(@Nullable ColorStateList colorStateList) {
        wrappedBuilder.choiceWidgetColor(colorStateList);
        return thisBuilder;
    }

    public T dividerColor(@ColorInt int color) {
        wrappedBuilder.dividerColor(color);
        return thisBuilder;
    }

    public T dividerColorRes(@ColorRes int colorRes) {
        wrappedBuilder.dividerColorRes(colorRes);
        return thisBuilder;
    }

    public T dividerColorAttr(@AttrRes int colorAttr) {
        wrappedBuilder.dividerColorAttr(colorAttr);
        return thisBuilder;
    }

    public T backgroundColor(@ColorInt int color) {
        wrappedBuilder.backgroundColor(color);
        return thisBuilder;
    }

    public T backgroundColorRes(@ColorRes int colorRes) {
        wrappedBuilder.backgroundColorRes(colorRes);
        return thisBuilder;
    }

    public T backgroundColorAttr(@AttrRes int colorAttr) {
        wrappedBuilder.backgroundColorAttr(colorAttr);
        return thisBuilder;
    }

    public T theme(@NonNull Theme theme) {
        wrappedBuilder.theme(theme);
        return thisBuilder;
    }

    public T cancelable(boolean cancelable) {
        wrappedBuilder.cancelable(cancelable);
        return thisBuilder;
    }

    public T canceledOnTouchOutside(boolean canceledOnTouchOutside) {
        wrappedBuilder.canceledOnTouchOutside(canceledOnTouchOutside);
        return thisBuilder;
    }

    public T autoDismiss(boolean dismiss) {
        // TODO: 25.07.2017 maybe remove this
        wrappedBuilder.autoDismiss(dismiss);
        return thisBuilder;
    }

    public T limitIconToDefaultSize() {
        wrappedBuilder.limitIconToDefaultSize();
        return thisBuilder;
    }

    public T maxIconSize(int maxIconSize) {
        wrappedBuilder.maxIconSize(maxIconSize);
        return thisBuilder;
    }

    public T maxIconSizeRes(@DimenRes int maxIconSizeRes) {
        wrappedBuilder.maxIconSizeRes(maxIconSizeRes);
        return thisBuilder;
    }

    public T stackingBehavior(@NonNull StackingBehavior behavior) {
        wrappedBuilder.stackingBehavior(behavior);
        return thisBuilder;
    }

    public T tag(@Nullable Object tag) {
        wrappedBuilder.tag(tag);
        return thisBuilder;
    }
}
