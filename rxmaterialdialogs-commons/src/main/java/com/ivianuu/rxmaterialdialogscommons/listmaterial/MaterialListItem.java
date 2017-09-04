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

package com.ivianuu.rxmaterialdialogscommons.listmaterial;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.AttrRes;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.DimenRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.IntRange;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.util.TypedValue;

import com.afollestad.materialdialogs.util.DialogUtils;

/**
 * Material list item
 */
public class MaterialListItem {

    private final Builder builder;

    private MaterialListItem(Builder builder) {
        this.builder = builder;
    }

    public Drawable getIcon() {
        return builder.icon;
    }

    public CharSequence getTitle() {
        return builder.title;
    }

    public CharSequence getText() {
        return builder.text;
    }

    public int getIconPadding() {
        return builder.iconPadding;
    }

    @ColorInt
    public int getBackgroundColor() {
        return builder.backgroundColor;
    }

    public long getId() {
        return builder.id;
    }

    @Nullable
    public Object getTag() {
        return builder.tag;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        if (getTitle() != null) {
            builder.append(getTitle());
        }

        if (getText() != null) {
            if (getTitle() != null) {
                builder.append(" ");
            }

            builder.append(getText());
        }

        return builder.toString();
    }

    public static class Builder {

        private final Context context;
        protected Drawable icon;
        protected CharSequence title;
        protected CharSequence text;
        protected long id;

        int iconPadding;
        int backgroundColor;
        Object tag;

        public Builder(Context context) {
            this.context = context;
            backgroundColor = Color.parseColor("#BCBCBC");
        }

        public Builder icon(Drawable icon) {
            this.icon = icon;
            return this;
        }

        public Builder icon(@DrawableRes int iconRes) {
            return icon(ContextCompat.getDrawable(context, iconRes));
        }

        public Builder iconPadding(@IntRange(from = 0, to = Integer.MAX_VALUE) int padding) {
            this.iconPadding = padding;
            return this;
        }

        public Builder iconPaddingDp(@IntRange(from = 0, to = Integer.MAX_VALUE) int paddingDp) {
            this.iconPadding =
                    (int)
                            TypedValue.applyDimension(
                                    TypedValue.COMPLEX_UNIT_DIP,
                                    paddingDp,
                                    context.getResources().getDisplayMetrics());
            return this;
        }

        public Builder iconPaddingRes(@DimenRes int paddingRes) {
            return iconPadding(context.getResources().getDimensionPixelSize(paddingRes));
        }

        public Builder title(CharSequence title) {
            this.title = title;
            return this;
        }

        public Builder title(@StringRes int titleRes) {
            return title(context.getString(titleRes));
        }

        public Builder text(CharSequence text) {
            this.text = text;
            return this;
        }

        public Builder text(@StringRes int textRes) {
            return text(context.getString(textRes));
        }

        public Builder backgroundColor(@ColorInt int color) {
            this.backgroundColor = color;
            return this;
        }

        public Builder backgroundColorRes(@ColorRes int colorRes) {
            return backgroundColor(DialogUtils.getColor(context, colorRes));
        }

        public Builder backgroundColorAttr(@AttrRes int colorAttr) {
            return backgroundColor(DialogUtils.resolveColor(context, colorAttr));
        }

        public Builder id(long id) {
            this.id = id;
            return this;
        }

        public Builder tag(@Nullable Object tag) {
            this.tag = tag;
            return this;
        }

        public MaterialListItem build() {
            return new MaterialListItem(this);
        }
    }
}
