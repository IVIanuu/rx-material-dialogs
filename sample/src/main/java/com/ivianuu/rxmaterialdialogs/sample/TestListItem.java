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

package com.ivianuu.rxmaterialdialogs.sample;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.ivianuu.rxmaterialdialogscommons.listcustom.CustomModelListItem;

/**
 * @author Manuel Wrage (IVIanuu)
 */
final class TestListItem extends CustomModelListItem<String, TestListItem.Holder> {

    TestListItem(@NonNull String s) {
        super(s);
    }

    @Override
    public int getLayoutRes() {
        return R.layout.item_custom;
    }

    @NonNull
    @Override
    public Holder createViewHolder(View view) {
        return new Holder(view);
    }

    @Override
    public void bind(@NonNull Holder holder) {
        holder.title.setText(getModel());
    }

    @Override
    public void unbind(@NonNull Holder holder) {
        holder.title.setText(null);
    }

    static class Holder extends RecyclerView.ViewHolder {
        private final TextView title;
        Holder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
        }
    }
}
