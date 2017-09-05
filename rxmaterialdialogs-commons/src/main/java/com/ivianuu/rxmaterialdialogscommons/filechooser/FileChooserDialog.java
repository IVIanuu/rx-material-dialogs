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

package com.ivianuu.rxmaterialdialogscommons.filechooser;

import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.MimeTypeMap;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * File chooser dialog
 */
final class FileChooserDialog implements MaterialDialog.ListCallback {

    private final FileChooserDialogBuilder builder;
    
    private File parentFolder;
    private File[] parentContents;
    private boolean canGoUp = true;

    MaterialDialog materialDialog;

    FileChooserDialog(FileChooserDialogBuilder builder) {
        this.builder = builder;

        parentFolder = new File(builder.initialPath);
        checkIfCanGoUp();
        parentContents = listFiles(builder.mimeType, builder.extensions);
        materialDialog = new MaterialDialog.Builder(builder.context)
                .title(parentFolder.getAbsolutePath())
                .typeface(builder.mediumFont, builder.regularFont)
                .items(getContentsArray())
                .itemsCallback(this)
                .onNegative(
                        new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                dialog.dismiss();
                            }
                        })
                .autoDismiss(false)
                .negativeText(builder.cancelButton)
                .build();
    }

    private CharSequence[] getContentsArray() {
        if (parentContents == null) {
            if (canGoUp) {
                return new String[] {builder.goUpLabel};
            }
            return new String[] {};
        }
        String[] results = new String[parentContents.length + (canGoUp ? 1 : 0)];
        if (canGoUp) {
            results[0] = builder.goUpLabel;
        }
        for (int i = 0; i < parentContents.length; i++) {
            results[canGoUp ? i + 1 : i] = parentContents[i].getName();
        }
        return results;
    }

    private File[] listFiles(@Nullable String mimeType, @Nullable String[] extensions) {
        File[] contents = parentFolder.listFiles();
        List<File> results = new ArrayList<>();
        if (contents != null) {
            MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
            for (File fi : contents) {
                if (fi.isDirectory()) {
                    results.add(fi);
                } else {
                    if (extensions != null) {
                        boolean found = false;
                        for (String ext : extensions) {
                            if (fi.getName().toLowerCase().endsWith(ext.toLowerCase())) {
                                found = true;
                                break;
                            }
                        }
                        if (found) {
                            results.add(fi);
                        }
                    } else if (mimeType != null) {
                        if (fileIsMimeType(fi, mimeType, mimeTypeMap)) {
                            results.add(fi);
                        }
                    }
                }
            }
            Collections.sort(results, new FileSorter());
            return results.toArray(new File[results.size()]);
        }
        return null;
    }

    private boolean fileIsMimeType(File file, String mimeType, MimeTypeMap mimeTypeMap) {
        if (mimeType == null || mimeType.equals("*/*")) {
            return true;
        } else {
            // get the file mime type
            String filename = file.toURI().toString();
            int dotPos = filename.lastIndexOf('.');
            if (dotPos == -1) {
                return false;
            }
            String fileExtension = filename.substring(dotPos + 1);
            if (fileExtension.endsWith("json")) {
                return mimeType.startsWith("application/json");
            }
            String fileType = mimeTypeMap.getMimeTypeFromExtension(fileExtension);
            if (fileType == null) {
                return false;
            }
            // check the 'type/subtype' pattern
            if (fileType.equals(mimeType)) {
                return true;
            }
            // check the 'type/*' pattern
            int mimeTypeDelimiter = mimeType.lastIndexOf('/');
            if (mimeTypeDelimiter == -1) {
                return false;
            }
            String mimeTypeMainType = mimeType.substring(0, mimeTypeDelimiter);
            String mimeTypeSubtype = mimeType.substring(mimeTypeDelimiter + 1);
            if (!mimeTypeSubtype.equals("*")) {
                return false;
            }
            int fileTypeDelimiter = fileType.lastIndexOf('/');
            if (fileTypeDelimiter == -1) {
                return false;
            }
            String fileTypeMainType = fileType.substring(0, fileTypeDelimiter);
            if (fileTypeMainType.equals(mimeTypeMainType)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void onSelection(MaterialDialog materialDialog, View view, int i, CharSequence s) {
        if (canGoUp && i == 0) {
            parentFolder = parentFolder.getParentFile();
            if (parentFolder.getAbsolutePath().equals("/storage/emulated")) {
                parentFolder = parentFolder.getParentFile();
            }
            canGoUp = parentFolder.getParent() != null;
        } else {
            parentFolder = parentContents[canGoUp ? i - 1 : i];
            canGoUp = true;
            if (parentFolder.getAbsolutePath().equals("/storage/emulated")) {
                parentFolder = Environment.getExternalStorageDirectory();
            }
        }
        if (parentFolder.isFile()) {
            builder.fileCallback.onFileSelection(this, parentFolder);
            materialDialog.dismiss();
        } else {
            parentContents = listFiles(builder.mimeType, builder.extensions);
            materialDialog.setTitle(parentFolder.getAbsolutePath());
            materialDialog.setItems(getContentsArray());
        }
    }

    private void checkIfCanGoUp() {
        try {
            canGoUp = parentFolder.getPath().split("/").length > 1;
        } catch (IndexOutOfBoundsException e) {
            canGoUp = false;
        }
    }

    void show() {
        materialDialog.show();
    }

    public interface FileCallback {
        void onFileSelection(@NonNull FileChooserDialog dialog, @NonNull File file);
    }

    private static class FileSorter implements Comparator<File> {

        @Override
        public int compare(File lhs, File rhs) {
            if (lhs.isDirectory() && !rhs.isDirectory()) {
                return -1;
            } else if (!lhs.isDirectory() && rhs.isDirectory()) {
                return 1;
            } else {
                return lhs.getName().toLowerCase().trim().compareTo(rhs.getName().toLowerCase().trim());
            }
        }
    }
}