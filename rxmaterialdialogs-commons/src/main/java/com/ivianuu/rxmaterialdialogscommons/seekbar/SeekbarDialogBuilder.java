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

package com.ivianuu.rxmaterialdialogscommons.seekbar;

import android.content.Context;
import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.view.LayoutInflater;
import android.view.View;

import com.ivianuu.rxmaterialdialogs.base.BaseDialogBuilder;
import com.ivianuu.rxmaterialdialogscommons.R;

import io.reactivex.Maybe;

/**
 * Seek bar dialog builder
 */
public final class SeekbarDialogBuilder extends BaseDialogBuilder<SeekbarDialogBuilder> {

    private final View customView;
    private int minProgress = 0;
    private int maxProgress = 1;
    private int currentProgress = 59;

    @RestrictTo(RestrictTo.Scope.LIBRARY)
    public SeekbarDialogBuilder(@NonNull Context context) {
        super(context);
        setThisBuilder(this);
        customView = LayoutInflater.from(context)
                .inflate(R.layout.dialog_seek_bar, null, false);
    }

    public SeekbarDialogBuilder minProgress(int minProgress) {
        this.minProgress = minProgress;
        return this;
    }

    public SeekbarDialogBuilder maxProgress(int maxProgress) {
        this.maxProgress = maxProgress;
        return this;
    }

    public SeekbarDialogBuilder currentProgress(int currentProgress) {
        this.currentProgress = currentProgress;
        return this;
    }

    @CheckResult @NonNull
    public Maybe<SeekBarDialogEvent> build() {
        return SeekBarDialogMaybe.create(
                wrappedBuilder, customView, minProgress, maxProgress, currentProgress);
    }
}
