package com.ivianuu.rxmaterialdialogs.sample;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Pair;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.simplelist.MaterialSimpleListItem;
import com.ivianuu.rxmaterialdialogs.RxMaterialDialogs;
import com.ivianuu.rxmaterialdialogs.input.InputDialogEvent;
import com.ivianuu.rxmaterialdialogs.listcustom.CustomListDialogBuilder;
import com.ivianuu.rxmaterialdialogs.listcustom.CustomListDialogEvent;
import com.ivianuu.rxmaterialdialogs.listcustom.CustomModelListItem;
import com.ivianuu.rxmaterialdialogs.listsimple.SimpleListDialogEvent;
import com.ivianuu.rxmaterialdialogscommons.RxMaterialDialogsCommons;
import com.ivianuu.rxmaterialdialogscommons.seekbar.SeekBarDialogEvent;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class MainActivity extends AppCompatActivity {

    private Disposable disposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        disposable = RxMaterialDialogsCommons.seekBarDialog(this)
                .title("Title")
                .positiveText("OK")
                .minProgress(0)
                .maxProgress(100)
                .currentProgress(59)
                .build()
                .subscribe(new Consumer<SeekBarDialogEvent>() {
                    @Override
                    public void accept(SeekBarDialogEvent seekBarDialogEvent) throws Exception {
                        Toast.makeText(MainActivity.this, "Selected " + seekBarDialogEvent.getProgress(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void fileChooserDialog() {
        disposable = RxMaterialDialogsCommons.fileChooserDialogBuilder(this)
                .build()
                .subscribe(fileChooserDialogEvent -> Toast.makeText(MainActivity.this, "file selected " + fileChooserDialogEvent.getSelectedFile().getAbsolutePath(), Toast.LENGTH_SHORT).show());
    }

    private void customListDialog() {
        CustomListDialogBuilder<TestListItem> builder
                = RxMaterialDialogs.customListDialog(this);

        for (int i = 0; i < 100; i++) {
            builder.addItem(new TestListItem("Test " + i));
        }

        disposable = builder.build()
                .map(CustomListDialogEvent::getItem)
                .map(CustomModelListItem::getModel)
                .subscribe(s -> Toast.makeText(MainActivity.this, s + "clicked", Toast.LENGTH_SHORT).show());
    }

    private void colorChooserDialog() {
        disposable = RxMaterialDialogsCommons.colorChooserDialog(this)
                .title("Color chooser")
                .allowUserColorInput(true)
                .allowUserColorInputAlpha(true)
                .doneButton(R.string.md_done_label)
                .cancelButton(R.string.md_cancel_label)
                .backButton(R.string.md_back_label)
                .customButton(R.string.md_custom_label)
                .dynamicButtonColor(true)
                .preselect(Color.BLACK)
                .build()
                .subscribe(colorChooserDialogEvent -> Toast.makeText(MainActivity.this, "Color selected " + colorChooserDialogEvent.getSelectedColor(), Toast.LENGTH_SHORT).show());
    }

    private void simpleMaterialList() {
        List<MaterialSimpleListItem> items = new ArrayList<>();
        int count = 0;
        while (count < 100) {
            MaterialSimpleListItem item = new MaterialSimpleListItem.Builder(this)
                    .id(count)
                    .content("Hallo " + (count + 1))
                    .icon(R.mipmap.ic_launcher)
                    .build();

            items.add(item);

            count++;
        }

        disposable = RxMaterialDialogsCommons.materialSimpleListDialog
                (this)
                .negativeText("HEhe")
                .addItems(items)
                .build()
                .subscribe(materialSimpleListDialogEvent -> {
                    //noinspection ConstantConditions
                    Toast.makeText(MainActivity.this, materialSimpleListDialogEvent.getItem().getContent() + " clicked", Toast.LENGTH_SHORT).show();
                });
    }

    private void singleChoice() {
        disposable = RxMaterialDialogs.singleChoiceListDialog(this)
                .items("Ha", "jfdjf", "kgsklkgesg", "klmsklgmrhmlh")
                .positiveText("Ok")
                .negativeText("Cance")
                .build()
                .subscribe(singleChoiceListDialogEvent -> Toast.makeText(MainActivity.this, singleChoiceListDialogEvent.getText(), Toast.LENGTH_SHORT).show());
    }

    private void multiChoice() {
        disposable = RxMaterialDialogs.multiChoiceListDialog(this)
                .items("Ha", "He", "hp", "iknff", "mktlkt", "kmgmg", "kmkgggrreesss")
                .positiveText("Ok")
                .neutralText("Netral")
                .negativeText("Cancel")
                .build()
                .subscribe(__ -> {});
    }

    private void simpleList() {
        disposable = RxMaterialDialogs.simpleListDialog(this)
                .title("Select a item")
                .content("Please :D")
                .items("Hallo", "Bye", "Joa", "Alles klar", "Noch", "Eins")
                .build()
                .subscribe(simpleListDialogEvent -> Toast.makeText(MainActivity.this, "Text: "
                                + simpleListDialogEvent.getText() + " Position: "
                                + simpleListDialogEvent.getPosition() + " Was Long click ? "
                                + String.valueOf(simpleListDialogEvent.getEventType() == SimpleListDialogEvent.EventType.LONG_CLICK),
                        Toast.LENGTH_SHORT).show(), throwable -> Toast.makeText(MainActivity.this, "Cancelled", Toast.LENGTH_SHORT).show());
    }

    private void login() {
        disposable = RxMaterialDialogs.singleButtonDialog(this)
                .title("You have to login")
                .positiveText("OK")
                .negativeText("Not yet")
                .iconRes(R.mipmap.ic_launcher)
                .build()
                .filter(singleButtonDialogEvent -> singleButtonDialogEvent.isButton(DialogAction.POSITIVE))
                .flatMap(__ -> RxMaterialDialogs.inputDialog(MainActivity.this)
                        .title("Type in your username")
                        .input("Username..", "")
                        .positiveText("OK")
                        .negativeText("Cancel")
                        .build())
                .map(InputDialogEvent::getInput)
                .flatMap(username -> RxMaterialDialogs.inputDialog(MainActivity.this)
                        .title("Type in your password")
                        .input("Passwordd..", "")
                        .positiveText("OK")
                        .negativeText("CANCEL")
                        .build()
                        .map(passwordEvent -> new Pair<>(username, passwordEvent.getInput())))
                .subscribe(credentials ->
                        Toast.makeText(MainActivity.this, "Successfully logged in Username: " + credentials.first + " Password: " + credentials.second, Toast.LENGTH_SHORT).show(),
                        Throwable::printStackTrace,
                        () -> Toast.makeText(MainActivity.this, "Complete", Toast.LENGTH_SHORT).show());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (disposable != null) disposable.dispose();
    }
}
