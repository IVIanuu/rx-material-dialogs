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

package com.ivianuu.rxmaterialdialogs.list;

import android.content.Context;
import android.support.annotation.ArrayRes;
import android.support.annotation.AttrRes;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;

import com.afollestad.materialdialogs.GravityEnum;
import com.ivianuu.rxmaterialdialogs.base.BaseDialogBuilder;

import java.util.Collection;

/**
 * Base list dialog
 */
public class BaseListDialogBuilder<T extends BaseListDialogBuilder> extends BaseDialogBuilder<T> {

    @RestrictTo(RestrictTo.Scope.LIBRARY)
    public BaseListDialogBuilder(@NonNull Context context) {
        super(context);
    }

    public T items(@NonNull Collection collection) {
        wrappedBuilder.items(collection);
        return thisBuilder;
    }

    public T items(@ArrayRes int itemsRes) {
        wrappedBuilder.items(itemsRes);
        return thisBuilder;
    }

    public T items(@NonNull CharSequence... items) {
        wrappedBuilder.items(items);
        return thisBuilder;
    }

    public T itemsColor(@ColorInt int color) {
        wrappedBuilder.itemsColor(color);
        return thisBuilder;
    }

    public T itemsColorRes(@ColorRes int colorRes) {
        wrappedBuilder.itemsColorRes(colorRes);
        return thisBuilder;
    }

    public T itemsColorAttr(@AttrRes int colorAttr) {
        wrappedBuilder.itemsColorAttr(colorAttr);
        return thisBuilder;
    }

    public T itemsGravity(@NonNull GravityEnum gravity) {
        wrappedBuilder.itemsGravity(gravity);
        return thisBuilder;
    }

    public T itemsIds(@NonNull int[] idsArray) {
        wrappedBuilder.itemsIds(idsArray);
        return thisBuilder;
    }

    public T itemsIds(@ArrayRes int idsArrayRes) {
        wrappedBuilder.itemsIds(idsArrayRes);
        return thisBuilder;
    }

    public T itemsDisabledIndices(@Nullable Integer... disabledIndices) {
        wrappedBuilder.itemsDisabledIndices(disabledIndices);
        return thisBuilder;
    }

}
