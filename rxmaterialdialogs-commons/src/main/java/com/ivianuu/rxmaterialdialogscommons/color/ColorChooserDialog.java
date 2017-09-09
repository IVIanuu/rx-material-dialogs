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
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.res.ResourcesCompat;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.color.CircleView;
import com.afollestad.materialdialogs.internal.MDTintHelper;
import com.afollestad.materialdialogs.util.DialogUtils;
import com.ivianuu.rxmaterialdialogscommons.R;

import java.util.Locale;

/**
 * Color chooser dialog
 */
final class ColorChooserDialog implements View.OnClickListener, View.OnLongClickListener {

    private int[] colorsTop;
    @Nullable
    private int[][] colorsSub;
    private final int circleSize;
    private final GridView grid;
    private View colorChooserCustomFrame;
    private EditText customColorHex;
    private View customColorIndicator;
    private TextWatcher customColorTextWatcher;
    private SeekBar customSeekA;
    private TextView customSeekAValue;
    private SeekBar customSeekR;
    private TextView customSeekRValue;
    private SeekBar customSeekG;
    private TextView customSeekGValue;
    private SeekBar customSeekB;
    private TextView customSeekBValue;
    private SeekBar.OnSeekBarChangeListener customColorRgbListener;
    private int selectedCustomColor;

    MaterialDialog materialDialog;

    private int topIndex;
    private int subIndex;
    private boolean inSub;

    private final ColorChooserDialogBuilder builder;

    @SuppressLint("PrivateResource")
    ColorChooserDialog(final ColorChooserDialogBuilder builder) {
        this.builder = builder;

        generateColors();

        int preselectColor;
        boolean foundPreselectColor = false;

        if (builder.setPreselectionColor) {
            preselectColor = builder.preselectColor;
            if (preselectColor != 0) {
                for (int topIndex = 0; topIndex < colorsTop.length; topIndex++) {
                    if (colorsTop[topIndex] == preselectColor) {
                        foundPreselectColor = true;
                        topIndex(topIndex);
                        if (builder.accentMode) {
                            subIndex(2);
                        } else if (colorsSub != null) {
                            findSubIndexForColor(topIndex, preselectColor);
                        } else {
                            subIndex(5);
                        }
                        break;
                    }

                    if (colorsSub != null) {
                        for (int subIndex = 0; subIndex < colorsSub[topIndex].length; subIndex++) {
                            if (colorsSub[topIndex][subIndex] == preselectColor) {
                                foundPreselectColor = true;
                                topIndex(topIndex);
                                subIndex(subIndex);
                                break;
                            }
                        }
                        if (foundPreselectColor) {
                            break;
                        }
                    }
                }
            }
        } else {
            preselectColor = Color.BLACK;
            foundPreselectColor = true;
        }

        circleSize = builder.context.getResources().getDimensionPixelSize(R.dimen.md_colorchooser_circlesize);
        MaterialDialog.Builder bd =
                new MaterialDialog.Builder(builder.context)
                        .title(getTitle())
                        .autoDismiss(false)
                        .customView(R.layout.md_dialog_colorchooser, false)
                        .negativeText(builder.cancelBtn)
                        .positiveText(builder.doneBtn)
                        .neutralText(builder.allowUserCustom ? builder.customBtn : "")
                        .typeface(builder.mediumFont, builder.regularFont)
                        .onPositive(
                                (dialog, which) -> {
                                    builder.colorCallback.onColorSelection(dialog, getSelectedColor());
                                    materialDialog.dismiss();
                                })
                        .onNegative(
                                (dialog, which) -> {
                                    if (isInSub()) {
                                        materialDialog.setActionButton(DialogAction.NEGATIVE, ColorChooserDialog.this.builder.cancelBtn);
                                        isInSub(false);
                                        subIndex(-1); // Do this to avoid ArrayIndexOutOfBoundsException
                                        invalidate();
                                    } else {
                                        materialDialog.cancel();
                                    }
                                })
                        .onNeutral(
                                (dialog, which) -> toggleCustom())
                        .showListener(
                                dialog -> invalidateDynamicButtonColors());

        if (builder.theme != null) {
            bd.theme(builder.theme);
        }

        materialDialog = bd.build();

        final View v = materialDialog.getCustomView();
        grid = v.findViewById(R.id.md_grid);

        if (builder.allowUserCustom) {
            selectedCustomColor = preselectColor;
            colorChooserCustomFrame = v.findViewById(R.id.md_colorChooserCustomFrame);
            customColorHex = v.findViewById(R.id.md_hexInput);
            customColorIndicator = v.findViewById(R.id.md_colorIndicator);
            customSeekA = v.findViewById(R.id.md_colorA);
            customSeekAValue = v.findViewById(R.id.md_colorAValue);
            customSeekR = v.findViewById(R.id.md_colorR);
            customSeekRValue = v.findViewById(R.id.md_colorRValue);
            customSeekG = v.findViewById(R.id.md_colorG);
            customSeekGValue = v.findViewById(R.id.md_colorGValue);
            customSeekB = v.findViewById(R.id.md_colorB);
            customSeekBValue = v.findViewById(R.id.md_colorBValue);

            if (!builder.allowUserCustomAlpha) {
                v.findViewById(R.id.md_colorALabel).setVisibility(View.GONE);
                customSeekA.setVisibility(View.GONE);
                customSeekAValue.setVisibility(View.GONE);
                customColorHex.setHint("2196F3");
                customColorHex.setFilters(new InputFilter[]{new InputFilter.LengthFilter(6)});
            } else {
                customColorHex.setHint("FF2196F3");
                customColorHex.setFilters(new InputFilter[]{new InputFilter.LengthFilter(8)});
            }

            if (!foundPreselectColor) {
                // If color wasn't found in the preset colors, it must be custom
                toggleCustom();
            }
        }

        invalidate();
    }

    private void generateColors() {
        if (builder.colorsTop != null) {
            colorsTop = builder.colorsTop;
            colorsSub = builder.colorsSub;
            return;
        }

        if (builder.accentMode) {
            colorsTop = ColorPalette.ACCENT_COLORS;
            colorsSub = ColorPalette.ACCENT_COLORS_SUB;
        } else {
            colorsTop = ColorPalette.PRIMARY_COLORS;
            colorsSub = ColorPalette.PRIMARY_COLORS_SUB;
        }
    }

    private void invalidateDynamicButtonColors() {
        if (materialDialog == null) {
            return;
        }
        if (builder.dynamicButtonColor) {
            int selectedColor = getSelectedColor();
            if (Color.alpha(selectedColor) < 64
                    || (Color.red(selectedColor) > 247
                    && Color.green(selectedColor) > 247
                    && Color.blue(selectedColor) > 247)) {
                // Once we get close to white or transparent,
                // the action buttons and seekbars will be a very light gray.
                selectedColor = Color.parseColor("#DEDEDE");
            }

            if (builder.dynamicButtonColor) {
                materialDialog.getActionButton(DialogAction.POSITIVE).setTextColor(selectedColor);
                materialDialog.getActionButton(DialogAction.NEGATIVE).setTextColor(selectedColor);
                materialDialog.getActionButton(DialogAction.NEUTRAL).setTextColor(selectedColor);
            }

            if (customSeekR != null) {
                if (customSeekA.getVisibility() == View.VISIBLE) {
                    MDTintHelper.setTint(customSeekA, selectedColor);
                }
                MDTintHelper.setTint(customSeekR, selectedColor);
                MDTintHelper.setTint(customSeekG, selectedColor);
                MDTintHelper.setTint(customSeekB, selectedColor);
            }
        }
    }

    private void toggleCustom() {
        if (grid.getVisibility() == View.VISIBLE) {
            materialDialog.setTitle(builder.customBtn);
            materialDialog.setActionButton(DialogAction.NEUTRAL, builder.presetsBtn);
            materialDialog.setActionButton(DialogAction.NEGATIVE, builder.cancelBtn);
            grid.setVisibility(View.INVISIBLE);
            colorChooserCustomFrame.setVisibility(View.VISIBLE);

            customColorTextWatcher =
                    new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            try {
                                selectedCustomColor = Color.parseColor("#" + s.toString());
                            } catch (IllegalArgumentException e) {
                                selectedCustomColor = Color.BLACK;
                            }
                            customColorIndicator.setBackgroundColor(selectedCustomColor);
                            if (customSeekA.getVisibility() == View.VISIBLE) {
                                int alpha = Color.alpha(selectedCustomColor);
                                customSeekA.setProgress(alpha);
                                customSeekAValue.setText(String.format(Locale.US, "%d", alpha));
                            }
                            int red = Color.red(selectedCustomColor);
                            customSeekR.setProgress(red);
                            int green = Color.green(selectedCustomColor);
                            customSeekG.setProgress(green);
                            int blue = Color.blue(selectedCustomColor);
                            customSeekB.setProgress(blue);
                            isInSub(false);
                            topIndex(-1);
                            subIndex(-1);
                            invalidateDynamicButtonColors();
                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                        }
                    };

            customColorHex.addTextChangedListener(customColorTextWatcher);
            customColorRgbListener =
                    new SeekBar.OnSeekBarChangeListener() {

                        @SuppressLint("DefaultLocale")
                        @Override
                        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                            if (fromUser) {
                                if (builder.allowUserCustomAlpha) {
                                    int color =
                                            Color.argb(
                                                    customSeekA.getProgress(),
                                                    customSeekR.getProgress(),
                                                    customSeekG.getProgress(),
                                                    customSeekB.getProgress());
                                    customColorHex.setText(String.format("%08X", color));
                                } else {
                                    int color =
                                            Color.rgb(
                                                    customSeekR.getProgress(),
                                                    customSeekG.getProgress(),
                                                    customSeekB.getProgress());
                                    customColorHex.setText(String.format("%06X", 0xFFFFFF & color));
                                }
                            }
                            customSeekAValue.setText(String.format("%d", customSeekA.getProgress()));
                            customSeekRValue.setText(String.format("%d", customSeekR.getProgress()));
                            customSeekGValue.setText(String.format("%d", customSeekG.getProgress()));
                            customSeekBValue.setText(String.format("%d", customSeekB.getProgress()));
                        }

                        @Override
                        public void onStartTrackingTouch(SeekBar seekBar) {
                        }

                        @Override
                        public void onStopTrackingTouch(SeekBar seekBar) {
                        }
                    };

            customSeekR.setOnSeekBarChangeListener(customColorRgbListener);
            customSeekG.setOnSeekBarChangeListener(customColorRgbListener);
            customSeekB.setOnSeekBarChangeListener(customColorRgbListener);
            if (customSeekA.getVisibility() == View.VISIBLE) {
                customSeekA.setOnSeekBarChangeListener(customColorRgbListener);
                customColorHex.setText(String.format("%08X", selectedCustomColor));
            } else {
                customColorHex.setText(String.format("%06X", 0xFFFFFF & selectedCustomColor));
            }
        } else {
            materialDialog.setTitle(builder.title);
            materialDialog.setActionButton(DialogAction.NEUTRAL, builder.customBtn);
            if (isInSub()) {
                materialDialog.setActionButton(DialogAction.NEGATIVE, builder.backBtn);
            } else {
                materialDialog.setActionButton(DialogAction.NEGATIVE, builder.cancelBtn);
            }
            grid.setVisibility(View.VISIBLE);
            colorChooserCustomFrame.setVisibility(View.GONE);
            customColorHex.removeTextChangedListener(customColorTextWatcher);
            customColorTextWatcher = null;
            customSeekR.setOnSeekBarChangeListener(null);
            customSeekG.setOnSeekBarChangeListener(null);
            customSeekB.setOnSeekBarChangeListener(null);
            customColorRgbListener = null;
        }
    }

    private String getTitle() {
        String title;
        if (isInSub()) {
            title = builder.titleSub;
        } else {
            title = builder.title;
        }
        return title;
    }

    @SuppressLint("PrivateResource")
    private void invalidate() {
        if (grid.getAdapter() == null) {
            grid.setAdapter(new ColorChooserDialog.ColorGridAdapter());
            grid.setSelector(
                    ResourcesCompat.getDrawable(builder.context.getResources(), R.drawable.md_transparent, null));
        } else {
            ((BaseAdapter) grid.getAdapter()).notifyDataSetChanged();
        }
        materialDialog.setTitle(builder.title);
    }

    private boolean isInSub() {
        return inSub;
    }

    private void isInSub(boolean value) {
        inSub = value;
    }

    private int topIndex() {
        return topIndex;
    }

    private void topIndex(int value) {
        if (value > -1) {
            findSubIndexForColor(value, colorsTop[value]);
        }
        topIndex = value;
    }

    private int subIndex() {
        if (colorsSub == null) {
            return -1;
        }
        return subIndex;
    }

    private void subIndex(int value) {
        if (colorsSub == null) {
            return;
        }
        subIndex = value;
    }


    @ColorInt
    private int getSelectedColor() {
        if (colorChooserCustomFrame != null
                && colorChooserCustomFrame.getVisibility() == View.VISIBLE) {
            return selectedCustomColor;
        }

        int color = 0;
        if (subIndex() > -1) {
            color = colorsSub[topIndex()][subIndex()];
        } else if (topIndex() > -1) {
            color = colorsTop[topIndex()];
        }
        if (color == 0) {
            int fallback = 0;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                fallback = DialogUtils.resolveColor(builder.context, android.R.attr.colorAccent);
            }
            color = DialogUtils.resolveColor(builder.context, R.attr.colorAccent, fallback);
        }
        return color;
    }

    private void findSubIndexForColor(int topIndex, int color) {
        if (colorsSub == null || colorsSub.length - 1 < topIndex) {
            return;
        }
        int[] subColors = colorsSub[topIndex];
        for (int subIndex = 0; subIndex < subColors.length; subIndex++) {
            if (subColors[subIndex] == color) {
                subIndex(subIndex);
                break;
            }
        }
    }

    void show() {
        materialDialog.show();
    }

    @Override
    public void onClick(View v) {
        if (v.getTag() != null) {
            final String[] tag = ((String) v.getTag()).split(":");
            final int index = Integer.parseInt(tag[0]);

            if (isInSub()) {
                subIndex(index);
            } else {
                topIndex(index);
                if (colorsSub != null && index < colorsSub.length) {
                    materialDialog.setActionButton(DialogAction.NEGATIVE, builder.backBtn);
                    isInSub(true);
                }
            }

            if (builder.allowUserCustom) {
                selectedCustomColor = getSelectedColor();
            }
            invalidateDynamicButtonColors();
            invalidate();
        }
    }

    @Override
    public boolean onLongClick(View v) {
        if (v.getTag() != null) {
            final String[] tag = ((String) v.getTag()).split(":");
            final int color = Integer.parseInt(tag[1]);
            ((CircleView) v).showHint(color);
            return true;
        }
        return false;
    }

    interface ColorCallback {
        void onColorSelection(@NonNull MaterialDialog dialog, @ColorInt int selectedColor);
    }

    private class ColorGridAdapter extends BaseAdapter {

        ColorGridAdapter() {
        }

        @Override
        public int getCount() {
            if (isInSub()) {
                return colorsSub[topIndex()].length;
            } else {
                return colorsTop.length;
            }
        }

        @Override
        public Object getItem(int position) {
            if (isInSub()) {
                return colorsSub[topIndex()][position];
            } else {
                return colorsTop[position];
            }
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @SuppressLint("DefaultLocale")
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = new CircleView(builder.context);
                convertView.setLayoutParams(new GridView.LayoutParams(circleSize, circleSize));
            }
            CircleView child = (CircleView) convertView;
            @ColorInt final int color = isInSub() ? colorsSub[topIndex()][position] : colorsTop[position];
            child.setBackgroundColor(color);
            if (isInSub()) {
                child.setSelected(subIndex() == position);
            } else {
                child.setSelected(topIndex() == position);
            }
            child.setTag(String.format("%d:%d", position, color));
            child.setOnClickListener(ColorChooserDialog.this);
            child.setOnLongClickListener(ColorChooserDialog.this);
            return convertView;
        }
    }
}
