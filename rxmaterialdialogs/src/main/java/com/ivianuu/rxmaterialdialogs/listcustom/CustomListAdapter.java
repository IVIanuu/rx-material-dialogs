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

package com.ivianuu.rxmaterialdialogs.listcustom;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.internal.MDAdapter;

import java.util.List;

/**
 * Custom list adapter
 */
final class CustomListAdapter<Item extends CustomListItem>
        extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements MDAdapter {

    private final List<Item> items;
    private final Callback<Item> callback;

    private MaterialDialog dialog;

    CustomListAdapter(@NonNull List<Item> items,
                      @NonNull Callback<Item> callback) {
        this.items = items;
        this.callback = callback;
    }

    @Override
    public void setDialog(MaterialDialog dialog) {
        this.dialog = dialog;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int position) {
        Item item = getItem(position);
        int layoutRes = item.getLayoutRes();
        View view = LayoutInflater.from(parent.getContext()).inflate(layoutRes, parent, false);
        return item.createViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        final Item item = getItem(position);
        item.bind(holder);

        holder.itemView.setOnClickListener(view -> {
            // notify clicks
            callback.onCustomListItemSelected(dialog, holder.getAdapterPosition(), item);
        });
    }

    @Override
    public void onViewRecycled(RecyclerView.ViewHolder holder) {
        super.onViewRecycled(holder);
        Item item = getItem(holder.getAdapterPosition());
        item.unbind(holder);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public int getItemViewType(int position) {
        // small trick to get the position of the item in on create view holder
        return position;
    }

    private Item getItem(int position) {
        return items.get(position);
    }

    interface Callback<Item extends CustomListItem> {
        void onCustomListItemSelected(MaterialDialog dialog, int index, Item item);
    }
}
