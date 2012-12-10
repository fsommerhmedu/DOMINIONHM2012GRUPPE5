package dominion.view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

/**
 * Kappselt die MessageBox und stellt einen Ersatzdialog für SWTBot-Tests zur
 * Verfügung.
 *
 * Normale MessageBoxen sind nativ und werden nicht vom SWTBot unterstützt.
 *
 * @author Tobi
 */
public final class MessageDialog {

    /**
     * Dialog ID.
     */
    public static final String DIALOG_ID = "dominion.messagedialog";

    /**
     * Dialog Typ.
     */
    public static final String DATA_KEY_TYPE = "dominion.messagedialog.type";

    /**
     * Data message.
     */
    public static final String DATA_KEY_MESSAGE =
            "dominion.messagedialog.message";

    /**
     * Nachricht.
     */
    private final String message;

    /**
     * Titel.
     */
    private final String title;

    /**
     * Buttons.
     */
    private final Buttons buttons;

    /**
     * Typ.
     */
    private final Type type;

    /**
     * Shell.
     */
    private Shell dialog;

    /**
     * Messagedialog.
     */
    private final MessageDialog messageDialog;

    /**
     * Result.
     */
    private Result result = Result.CANCEL;

    /**
     * Ctor.
     *
     * @param newMessage Nachricht
     * @param newTitle Titel
     * @param newType Typ
     * @param newButtons Buttons
     */
    private MessageDialog(final String newMessage, final String newTitle,
            final Type newType, final Buttons newButtons) {
        this.message = newMessage;
        this.title = newTitle;
        this.type = newType;
        this.buttons = newButtons;
        this.messageDialog = this;
    }

    /**
     * Zeigt ein MessageBox-Fenster an.
     * @param message Nachricht
     * @return Ergebnis
     */
    public static Result show(final String message) {
        return show(message, "Hinweis");
    }

    /**
     * Zeigt ein MessageBox-Fenster an.
     * @param message Nachricht
     * @param title Titel
     * @return Ergebnis
     */
    public static Result show(final String message, final String title) {
        return show(message, title, Type.INFO);
    }

    /**
     * Zeigt ein MessageBox-Fenster an.
     * @param message Nachricht
     * @param title Titel
     * @param type Typ
     * @return Ergebnis
     */
    public static Result show(final String message, final String title,
            final Type type) {
        return show(message, title, type, Buttons.OK);
    }

    /**
     * Zeigt ein MessageBox-Fenster an.
     * @param message Nachricht
     * @param title Titel
     * @param type Typ
     * @param buttons Buttons
     * @return Ergebnis
     */
    public static Result show(final String message, final String title,
            final Type type, final Buttons buttons) {
        return new MessageDialog(message, title, type, buttons).show();
    }

    /**
     * Zeigt das Dialogfenster an.
     * @return Ergebnis
     */
    private Result show() {
        final boolean testMode = System.getProperty("dominion.testmode", "0").
                equals("1");

        final Display display = Display.getDefault();

        display.syncExec(new Runnable() {

            @Override
            public void run() {
                final Shell shell = display.getActiveShell();

                dialog = new Shell(shell, SWT.APPLICATION_MODAL
                        | SWT.DIALOG_TRIM);

                if (testMode) {
                    dialog.setData(DominionShell.DATA_KEY_ID, DIALOG_ID);
                    dialog.setData(DATA_KEY_TYPE, type);
                    dialog.setData(DATA_KEY_MESSAGE, message);
                    dialog.setText(title);

                    GridLayout layout = new GridLayout(1, false);
                    dialog.setLayout(layout);

                    Label label = new Label(dialog, SWT.SINGLE);
                    label.setText(message);

                    if (buttons == Buttons.OK) {
                        appendButton(SWT.OK, "Ok", Result.OK);
                    } else if (buttons == Buttons.YES_NO) {
                        appendButton(SWT.YES, "Ja", Result.YES);
                        appendButton(SWT.NO, "Nein", Result.NO);
                    }

                    dialog.setSize(dialog.computeSize(SWT.DEFAULT,
                            SWT.DEFAULT));

                    /**
                     * Fenster zeichnen.
                     */
                    dialog.open();
                    while (!dialog.isDisposed()) {
                        if (!display.readAndDispatch()) {
                            display.sleep();
                        }
                    }
                } else {
                    int style = SWT.CLOSE;
                    if (buttons == Buttons.YES_NO) {
                        style = style | SWT.YES | SWT.NO;
                    }
                    if (type == Type.ERROR) {
                        style = style | SWT.ICON_ERROR;
                    } else if (type == Type.INFO) {
                        style = style | SWT.ICON_INFORMATION;
                    } else if (type == Type.QUESTION) {
                        style = style | SWT.ICON_QUESTION;
                    }

                    MessageBox messageBox = new MessageBox(shell, style
                            | SWT.CLOSE);
                    messageBox.setMessage(message);
                    messageBox.setText(title);
                    int res = messageBox.open();
                    if (res == SWT.YES) {
                        result = Result.YES;
                    } else if (res == SWT.NO) {
                        result = Result.NO;
                    } else if (res == SWT.OK) {
                        result = Result.OK;
                    } else {
                        result = Result.CANCEL;
                    }
                }
            }
        });

        return result;
    }

    /**
     * Fügt den Button in das Dialogfenster ein.
     *
     * @param id ID
     * @param text Beschriftung
     * @param newResult Ergebnis bei Klick
     */
    private void appendButton(final int id, final String text,
            final Result newResult) {
        final Button next = new Button(dialog, SWT.PUSH);
        next.setText(text);
        next.setData(DominionShell.DATA_KEY_ID, id + "");
        next.setLayoutData(new GridData());
        next.addSelectionListener(new SelectionListener() {

            @Override
            public void widgetDefaultSelected(final SelectionEvent e) {
            }

            @Override
            public void widgetSelected(final SelectionEvent e) {
                if (e.doit) {
                    messageDialog.result = newResult;
                    dialog.dispose();
                }
            }
        });
    }

    /**
     * Buttons die angezeigt werden sollen.
     */
    public enum Buttons {
        /**
         * Ok-Button.
         */
        OK,

        /**
         * Ja/Nein-Button.
         */
        YES_NO;

    }

    /**
     * Typ des Dialogs.
     */
    public enum Type {
        /**
         * Fehler.
         */
        ERROR,

        /**
         * Information.
         */
        INFO,

        /**
         * Frage.
         */
        QUESTION;

    }

    /**
     * Ergebnis des Dialogs.
     */
    public enum Result {
        /**
         * Dialog ok.
         */
        OK,

        /**
         * Dialog abbrechen.
         */
        CANCEL,

        /**
         * Ja.
         */
        YES,

        /**
         * Nein.
         */
        NO;
    }
}
