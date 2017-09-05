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

package com.ivianuu.rxmaterialdialogscommons.listmaterialsimple;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.simplelist.MaterialSimpleListAdapter;
import com.afollestad.materialdialogs.simplelist.MaterialSimpleListItem;
import com.ivianuu.rxmaterialdialogs.base.DialogMaybe;

import java.util.List;

import io.reactivex.Maybe;
import io.reactivex.MaybeEmitter;

/**
 * Material simple list dialog observable
 */
class MaterialSimpleListDialogMaybe extends DialogMaybe<MaterialSimpleListDialogEvent> {

    private final List<MaterialSimpleListItem> items;
    private final RecyclerView.LayoutManager layoutManager;

    private MaterialSimpleListDialogMaybe(MaterialDialog.Builder dialogBuilder,
                                          List<MaterialSimpleListItem> items,
                                          RecyclerView.LayoutManager layoutManager) {
        super(dialogBuilder);
        this.items = items;
        this.layoutManager = layoutManager;
    }

    static Maybe<MaterialSimpleListDialogEvent> create(@NonNull MaterialDialog.Builder builder,
                                                       @NonNull List<MaterialSimpleListItem> items,
                                                       @NonNull RecyclerView.LayoutManager layoutManager) {
        return Maybe.create(new MaterialSimpleListDialogMaybe(builder, items, layoutManager));
    }

    @Override
    protected void onPreBuild(@NonNull final MaybeEmitter<MaterialSimpleListDialogEvent> e, @NonNull MaterialDialog.Builder dialogBuilder) {
        // create adapter
        MaterialSimpleListAdapter adapter = new MaterialSimpleListAdapter(new MaterialSimpleListAdapter.Callback() {
            @Override
            public void onMaterialListItemSelected(MaterialDialog dialog, int index, MaterialSimpleListItem item) {
                if (!e.isDisposed()) {
                    e.onSuccess(new MaterialSimpleListDialogEvent(dialog, index, item));
                    e.onComplete();
                }
            }
        });

        // add items
        for (MaterialSimpleListItem item : items) {
            adapter.add(item);
        }

        // set adapter
        dialogBuilder
                .adapter(adapter, layoutManager)
                .onAny(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        if (!e.isDisposed()) {
                            e.onComplete();
                        }
                    }
                });
    }
}
