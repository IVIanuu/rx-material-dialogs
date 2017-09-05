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

/**
 * Custom model list item
 */
public abstract class CustomModelListItem<Model, VH extends RecyclerView.ViewHolder> implements CustomListItem<VH> {

    private final Model model;

    public CustomModelListItem(@NonNull Model model) {
        this.model = model;
    }

    /**
     * Returns the model of this list item
     */
    @NonNull
    public Model getModel() {
        return model;
    }
}
