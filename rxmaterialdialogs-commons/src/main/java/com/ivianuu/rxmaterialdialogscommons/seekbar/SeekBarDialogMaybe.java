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

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.ivianuu.rxmaterialdialogs.base.DialogMaybe;
import com.ivianuu.rxmaterialdialogscommons.R;

import io.reactivex.Maybe;
import io.reactivex.MaybeEmitter;

/**
 * Seek bar dialog maybe
 */
final class SeekBarDialogMaybe extends DialogMaybe<SeekBarDialogEvent> {

    private final View customView;
    private final int minProgress;
    private final int maxProgress;
    private final int currentProgress;

    private SeekBarDialogMaybe(MaterialDialog.Builder dialogBuilder,
                               View customView,
                               int minProgress,
                               int maxProgress,
                               int currentProgress) {
        super(dialogBuilder);
        this.customView = customView;
        this.minProgress = minProgress;
        this.maxProgress = maxProgress;
        this.currentProgress = currentProgress;
    }

    static Maybe<SeekBarDialogEvent> create(@NonNull MaterialDialog.Builder dialogBuilder,
                                            @NonNull View customView,
                                            int minProgress,
                                            int maxProgress,
                                            int currentProgress) {
        return Maybe.create(new SeekBarDialogMaybe(
                dialogBuilder, customView, minProgress, maxProgress, currentProgress));
    }

    @Override
    protected void onPreBuild(@NonNull MaybeEmitter<SeekBarDialogEvent> e, @NonNull MaterialDialog.Builder dialogBuilder) {
        SeekBar seekBar = customView.findViewById(R.id.seek_bar);
        TextView progressText = customView.findViewById(R.id.progress_text);

        // set text on changes
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (progress > maxProgress) {
                    seekBar.setProgress(maxProgress);
                } else if (progress < minProgress) {
                    seekBar.setProgress(minProgress);
                } else {
                    progressText.setText("Progress " + progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seekBar.setProgress(currentProgress);

        dialogBuilder.customView(customView, false);

        dialogBuilder.onAny((dialog, which) -> {
            if (which == DialogAction.POSITIVE) {
                e.onSuccess(new SeekBarDialogEvent(dialog, seekBar.getProgress()));
            }
            e.onComplete();
        });
    }
}
