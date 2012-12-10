package dominion.view;

import dominion.control.Control;
import dominion.model.ChooseableCards;
import dominion.model.ChooseableCardsImpl;
import dominion.model.Game;
import java.util.ArrayList;
import java.util.List;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import dominion.model.cards.Card;
import java.util.Map.Entry;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;

/**
 * Kartenauswahl zu Beginn des Spiels.
 *
 * @author Michi
 */
public class SelectCards extends DominionShell {

    /**
     * Enthält die Karte, welche gerade in dem Vorschauimage angezeigt wird.
     */
    private Card shownCard;

    /**
     * Auswählbare/Verfügbare Karten.
     */
    private final ChooseableCards availableCards;

    /**
     * Anzahl an markierten Checkboxen.
     */
    private int checkedBoxes;

    /**
     * Maximale Anzahl an auswählbaren Karten.
     */
    private static final int CHOOSABLE_CARDS = 10;

    /**
     * @param newModel Model des Spiels.
     * @param newControl Controller des Spiels.
     * @param newDisplay Displayinstanz.
     */
    public SelectCards(final Game newModel, final Control newControl,
            final Display newDisplay) {
        super(newModel, newControl, newDisplay);
        this.availableCards = new ChooseableCardsImpl();
        this.checkedBoxes = 0;
    }

    /**
     * Listet alle verfügbaren Karten für das Deck auf.
     */
    public final void chooseCards() {
        /**
         * Arraylist mit allen verfügbaren Karten-Checkboxen.
         */
        final List<Button> checkBoxes = new ArrayList<Button>();
        final Shell shell = getShell();
        // Display display = display;
        shell.setData(DATA_KEY_ID, "selectCards");
        Image image = new Image(getDisplay(),
                DominionShell.class.getResourceAsStream("Images/Dominion.jpg"));
        shell.setImage(image);
        shell.setText("Bitte Kartendeck wählen");
        shell.setLayout(new GridLayout());

        // Textfeld:
        Composite compositeText = new Composite(shell, SWT.BORDER);
        compositeText.setLayout(new GridLayout(1, true));
        final Label label = new Label(compositeText, SWT.SINGLE);
        label.setText("Bitte wählen Sie 10 Aktionskarten für das Spiel:");

        // Boxen für Kartenauswahl
        Composite compositeCards = new Composite(shell, SWT.BORDER);
        GridLayout layoutCards = new GridLayout(5, true);
        compositeCards.setLayout(layoutCards);

        // Buttons
        Composite compositeButtons = new Composite(shell, SWT.NONE);
        GridLayout layoutButtons = new GridLayout(2, true);
        compositeButtons.setLayout(layoutButtons);

        // Bildlabel
        Composite compositeImage = new Composite(shell, SWT.NONE);
        GridLayout layoutImage = new GridLayout(1, true);
        compositeImage.setLayout(layoutImage);
        final Label bild = new Label(compositeImage, SWT.SINGLE);
        bild.setData(DATA_KEY_ID, "cardImage");
        bild.setImage(new Image(getDisplay(),
                DominionShell.class.getResourceAsStream(
                "Images/big/Hinten blau.jpg")));

        /**
         * Button für "weiter".
         */
        final Button next = new Button(compositeButtons, SWT.PUSH);
        next.setText("weiter");
        next.setEnabled(false);
        next.setData(DATA_KEY_ID, "buttonNext");
        next.setLayoutData(new GridData());
        next.addSelectionListener(new SelectionListener() {

            @Override
            public void widgetDefaultSelected(final SelectionEvent e) {
            }

            @Override
            public void widgetSelected(final SelectionEvent e) {
                Card[] cards = new Card[CHOOSABLE_CARDS];
                int i = 0;
                for (Button b : checkBoxes) {
                    if (b.getSelection()) {
                        cards[i] = (Card) b.getData(DATA_KEY_CARD);
                        i++;
                    }
                }
                dispose();
                getControl().addCards(cards);
            }
        });

        /**
         * Für alle auswählbaren Karten wird eine Checkbox und ein zugehöriger
         * Listener erstellt.
         */
        for (Entry<String, Card> entry : availableCards.getCards().entrySet()) {
            final Card card = entry.getValue();

            final Button button = new Button(compositeCards,
                    SWT.CHECK | SWT.BORDER);
            button.setData(DATA_KEY_ID, card.getName());
            button.setData(DATA_KEY_CARD, card);
            button.setText(card.getName());
            button.addMouseMoveListener(new MouseMoveListener() {

                @Override
                public void mouseMove(final MouseEvent e) {
                    // Verhindert Flackern
                    if (card != shownCard) {
                        bild.setImage(new Image(getDisplay(),
                                card.getResourceBigAsStream()));
                        bild.setData(DATA_KEY_CARD, card);
                        shownCard = card;
                    }
                }
            });

            button.addListener(SWT.Selection, new Listener() {

                @Override
                public void handleEvent(final Event event) {
                    if (button.getSelection()) {
                        checkedBoxes++;
                        controlCheckBoxes();
                    } else {
                        checkedBoxes--;
                        controlCheckBoxes();
                    }
                }

                /**
                 * Falls MAX_CARDS Checkboxen makiert sind wird der
                 * "Weiter"-Button verfügbar und alle nicht markierten
                 * Checkboxen ausgegraut.
                 */
                private void controlCheckBoxes() {
                    if (checkedBoxes == CHOOSABLE_CARDS) {
                        next.setEnabled(true);
                        for (Button b : checkBoxes) {
                            if (!b.getSelection()) {
                                b.setEnabled(false);
                            }
                        }
                    } else {
                        next.setEnabled(false);
                        for (Button b : checkBoxes) {
                            b.setEnabled(true);
                        }
                    }
                }
            });
            button.setLayoutData(new GridData(SWT.LEFT, SWT.TOP,
                    true, true, 1, 1));
            checkBoxes.add(button);
        }

        /**
         * Fenster zeichnen.
         */
        shell.open();
        while (!shell.isDisposed()) {
            if (!getDisplay().readAndDispatch()) {
                getDisplay().sleep();
            }
        }

    }

    @Override
    public final void display() {
        chooseCards();
    }
}
