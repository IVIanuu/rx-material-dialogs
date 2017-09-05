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

import android.graphics.PorterDuff;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.internal.MDAdapter;
import com.ivianuu.rxmaterialdialogscommons.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Material list adapter
 */
final class MaterialListAdapter extends RecyclerView.Adapter<MaterialListAdapter.ListVH> implements MDAdapter {

    private MaterialDialog dialog;
    private final List<MaterialListItem> items;
    private final Callback callback;

    MaterialListAdapter(Callback callback) {
        this.items = new ArrayList<>(4);
        this.callback = callback;
    }

    MaterialListItem getItem(int index) {
        return items.get(index);
    }

    @Override
    public void setDialog(MaterialDialog dialog) {
        this.dialog = dialog;
    }

    @Override
    public ListVH onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view =
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.md_list_item, parent, false);
        return new ListVH(view, this);
    }

    @Override
    public void onBindViewHolder(ListVH holder, int position) {
        if (dialog != null) {
            final MaterialListItem item = items.get(position);
            if (item.getIcon() != null) {
                holder.icon.setImageDrawable(item.getIcon());
                holder.icon.setPadding(
                        item.getIconPadding(),
                        item.getIconPadding(),
                        item.getIconPadding(),
                        item.getIconPadding());
                holder
                        .icon
                        .getBackground()
                        .setColorFilter(item.getBackgroundColor(), PorterDuff.Mode.SRC_IN);
            } else {
                holder.icon.setVisibility(View.GONE);
            }
            holder.title.setTextColor(dialog.getBuilder().getItemColor());
            holder.title.setText(item.getTitle());
            dialog.setTypeface(holder.title, dialog.getBuilder().getRegularFont());
            holder.text.setTextColor(dialog.getBuilder().getItemColor());
            holder.text.setText(item.getText());
            dialog.setTypeface(holder.text, dialog.getBuilder().getRegularFont());
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    interface Callback {
        void onMaterialListItemSelected(MaterialDialog dialog, int index, MaterialListItem item);
    }

    static class ListVH extends RecyclerView.ViewHolder implements View.OnClickListener {

        final ImageView icon;
        final TextView title;
        final TextView text;
        final MaterialListAdapter adapter;

        ListVH(View itemView, MaterialListAdapter adapter) {
            super(itemView);
            icon = itemView.findViewById(android.R.id.icon);
            title = itemView.findViewById(android.R.id.title);
            text = itemView.findViewById(android.R.id.text1);
            this.adapter = adapter;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (adapter.callback != null) {
                adapter.callback.onMaterialListItemSelected(
                        adapter.dialog, getAdapterPosition(), adapter.getItem(getAdapterPosition()));
            }
        }

    }
}
