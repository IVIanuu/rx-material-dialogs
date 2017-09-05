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
import com.ivianuu.rxmaterialdialogs.listmultichoice.MultiChoiceListDialogEvent;
import com.ivianuu.rxmaterialdialogs.listsimple.SimpleListDialogEvent;
import com.ivianuu.rxmaterialdialogs.listsinglechoice.SingleChoiceListDialogEvent;
import com.ivianuu.rxmaterialdialogs.singlebutton.SingleButtonDialogEvent;
import com.ivianuu.rxmaterialdialogscommons.RxMaterialDialogsCommons;
import com.ivianuu.rxmaterialdialogscommons.color.ColorChooserDialogEvent;
import com.ivianuu.rxmaterialdialogs.listcustom.CustomListDialogBuilder;
import com.ivianuu.rxmaterialdialogs.listcustom.CustomListDialogEvent;
import com.ivianuu.rxmaterialdialogscommons.listmaterialsimple.MaterialSimpleListDialogEvent;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.MaybeSource;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;

public class MainActivity extends AppCompatActivity {

    private Disposable disposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        customListDialog();
    }

    private void customListDialog() {
        CustomListDialogBuilder<TestListItem> builder
                = RxMaterialDialogsCommons.customListDialog(this);

        for (int i = 0; i < 100; i++) {
            builder.addItem(new TestListItem("Test " + i));
        }

        disposable = builder.build()
                .map(new Function<CustomListDialogEvent<TestListItem>, TestListItem>() {
                    @Override
                    public TestListItem apply(CustomListDialogEvent<TestListItem> event) throws Exception {
                        return event.getItem();
                    }
                })
                .map(new Function<TestListItem, String>() {
                    @Override
                    public String apply(TestListItem testListItem) throws Exception {
                        return testListItem.getModel();
                    }
                })
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Toast.makeText(MainActivity.this, s + "clicked", Toast.LENGTH_SHORT).show();
                    }
                });
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
                .subscribe(new Consumer<ColorChooserDialogEvent>() {
                    @Override
                    public void accept(ColorChooserDialogEvent colorChooserDialogEvent) throws Exception {
                        Toast.makeText(MainActivity.this, "Color selected " + colorChooserDialogEvent.getSelectedColor(), Toast.LENGTH_SHORT).show();
                    }
                });
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
                .subscribe(new Consumer<MaterialSimpleListDialogEvent>() {
                    @Override
                    public void accept(MaterialSimpleListDialogEvent materialSimpleListDialogEvent) throws Exception {
                        //noinspection ConstantConditions
                        Toast.makeText(MainActivity.this, materialSimpleListDialogEvent.getItem().getContent() + " clicked", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void singleChoice() {
        disposable = RxMaterialDialogs.singleChoiceListDialog(this)
                .items("Ha", "jfdjf", "kgsklkgesg", "klmsklgmrhmlh")
                .positiveText("Ok")
                .negativeText("Cance")
                .build()
                .subscribe(new Consumer<SingleChoiceListDialogEvent>() {
                    @Override
                    public void accept(SingleChoiceListDialogEvent singleChoiceListDialogEvent) throws Exception {
                        Toast.makeText(MainActivity.this, singleChoiceListDialogEvent.getText(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void multiChoice() {
        disposable = RxMaterialDialogs.multiChoiceListDialog(this)
                .items("Ha", "He", "hp", "iknff", "mktlkt", "kmgmg", "kmkgggrreesss")
                .positiveText("Ok")
                .neutralText("Netral")
                .negativeText("Cancel")
                .build()
                .subscribe(new Consumer<MultiChoiceListDialogEvent>() {
                    @Override
                    public void accept(MultiChoiceListDialogEvent multiChoiceListDialogEvent) throws Exception {

                    }
                });
    }

    private void simpleList() {
        disposable = RxMaterialDialogs.simpleListDialog(this)
                .title("Select a item")
                .content("Please :D")
                .items("Hallo", "Bye", "Joa", "Alles klar", "Noch", "Eins")
                .build()
                .subscribe(new Consumer<SimpleListDialogEvent>() {
                    @Override
                    public void accept(SimpleListDialogEvent simpleListDialogEvent) throws Exception {
                        Toast.makeText(MainActivity.this, "Text: "
                                        + simpleListDialogEvent.getText() + " Position: "
                                        + simpleListDialogEvent.getPosition() + " Was Long click ? "
                                        + String.valueOf(simpleListDialogEvent.getEventType() == SimpleListDialogEvent.EventType.LONG_CLICK),
                                Toast.LENGTH_SHORT).show();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(MainActivity.this, "Cancelled", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void login() {
        disposable = RxMaterialDialogs.singleButtonDialog(this)
                .title("You have to login")
                .positiveText("OK")
                .negativeText("Not yet")
                .iconRes(R.mipmap.ic_launcher)
                .build()
                .filter(new Predicate<SingleButtonDialogEvent>() {
                    @Override
                    public boolean test(SingleButtonDialogEvent singleButtonDialogEvent) throws Exception {
                        return singleButtonDialogEvent.isButton(DialogAction.POSITIVE);
                    }
                })
                .flatMap(new Function<SingleButtonDialogEvent, MaybeSource<InputDialogEvent>>() {
                    @Override
                    public MaybeSource<InputDialogEvent> apply(SingleButtonDialogEvent __) throws Exception {
                        return RxMaterialDialogs.inputDialog(MainActivity.this)
                                .title("Type in your username")
                                .input("Username..", "")
                                .positiveText("OK")
                                .negativeText("Cancel")
                                .build();
                    }
                })
                .map(new Function<InputDialogEvent, CharSequence>() {
                    @Override
                    public CharSequence apply(InputDialogEvent inputDialogEvent) throws Exception {
                        return inputDialogEvent.getInput();
                    }
                })
                .flatMap(new Function<CharSequence, MaybeSource<Pair<CharSequence, CharSequence>>>() {
                    @Override
                    public MaybeSource<Pair<CharSequence, CharSequence>> apply(final CharSequence username) throws Exception {
                        return RxMaterialDialogs.inputDialog(MainActivity.this)
                                .title("Type in your password")
                                .input("Passwordd..", "")
                                .positiveText("OK")
                                .negativeText("CANCEL")
                                .build()
                                .map(new Function<InputDialogEvent, Pair<CharSequence, CharSequence>>() {
                                    @Override
                                    public Pair<CharSequence, CharSequence> apply(InputDialogEvent passwordEvent) throws Exception {
                                        return new Pair<>(username, passwordEvent.getInput());
                                    }
                                });
                    }
                })
                .subscribe(new Consumer<Pair<CharSequence, CharSequence>>() {
                    @Override
                    public void accept(Pair<CharSequence, CharSequence> credentials) throws Exception {
                        Toast.makeText(MainActivity.this, "Successfully logged in Username: " + credentials.first + " Password: " + credentials.second, Toast.LENGTH_SHORT).show();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        Toast.makeText(MainActivity.this, "Complete", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (disposable != null) disposable.dispose();
    }
}
