package dominion.view;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Widget;
import dominion.common.GamePhase;
import dominion.control.Control;
import dominion.model.Game;
import dominion.model.cards.Card;
import dominion.model.cards.CardBase;
import dominion.model.cards.Trash;
import dominion.model.cards.ActionCard.ActionCard;
import dominion.model.cards.MoneyCard.MoneyCardCooper;
import dominion.model.cards.MoneyCard.MoneyCardGold;
import dominion.model.cards.MoneyCard.MoneyCardSilver;
import dominion.model.cards.VictoryCard.VictoryCardCurse;
import dominion.model.cards.VictoryCard.VictoryCardDuchy;
import dominion.model.cards.VictoryCard.VictoryCardEstate;
import dominion.model.cards.VictoryCard.VictoryCardProvince;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;

/**
 * Hauptscreen waehrend des Spiels.
 *
 * @author Constanze
 */
public class GameScreen extends DominionShell {

    /**
     * Grad des Zoooms für die Bilder.
     */
    static final double ZOOM = 0.75;
    /**
     * Flag, ob der Betastatus (Aktionkarte angeschaltet) aktiviert sein soll.
     */
    static final boolean BETA = false;
    /**
     * Gruppe für Handkarten.
     */
    private Group groupHand;
    /**
     * Ablage-, Nachziehstapel.
     */
    private Group groupHandMisc;
    /**
     * Gruppe für Spielerinformationen.
     */
    private Group groupPlayer;
    /**
     * Label für die aktuelle Phase.
     */
    private Label phaseLabel;
    /**
     * Label für die Aktionen.
     */
    private Label actionLabel;
    /**
     * Laber für das vorhandene Geld.
     */
    private Label moneyLabel;
    /**
     * Label für die noch möglichen Käufe.
     */
    private Label purchaseLabel;
    /**
     * Label für alle vorhandenen Karten.
     */
    private Label allCardsLabel;
    /**
     * Button für den Ablagestapel.
     */
    private Button ablageButton;
    /**
     * Button für den Nachziehstapel.
     */
    private Button nachziehButton;

    /**
     * @param model
     *            Model des Spiels.
     * @param control
     *            Controller des Spiels.
     * @param display
     *            Display des Spiels.
     */
    public GameScreen(final Game model, final Control control, final Display display) {
        super(model, control, display);
        control.startPlaying();
        //model.getCurrentPlayer().resetPlayer();
    }

    /**
     * Zeichnet den Hauptbildschirm.
     */
    private void drawMainscreen() {
        final Shell shell = getShell();
        shell.setData(DATA_KEY_ID, "gameScreen");
        Image image = new Image(getDisplay(), DominionShell.class.getResourceAsStream("Images/Dominion.jpg"));
        shell.setImage(image);
        shell.setText("Let's Play");
        shell.setLocation(0, 0);
        shell.setLayout(new FillLayout());
        final ScrolledComposite scom = new ScrolledComposite(shell, SWT.V_SCROLL | SWT.H_SCROLL | SWT.BORDER);
        final Composite com = new Composite(scom, SWT.NONE);
        scom.setContent(com);
        scom.setMinSize(WIDTH - 40, HEIGHT - 40);
        scom.setExpandHorizontal(true);
        scom.setExpandVertical(true);
        GridLayout gridLayout = new GridLayout(2, false);
        gridLayout.horizontalSpacing = 5;
        com.setLayout(gridLayout);

        // Erzeuge Gruppe für Geld- und Punktekarten.
        Group groupCommon = new Group(com, SWT.SHADOW_ETCHED_IN);
        groupCommon.setData(DATA_KEY_ID, "commonGroup");
        groupCommon.setText("Geldkarten, Punktekarten");
        groupCommon.setLayout(new GridLayout(3, true));

        // Erzeuge Gruppe für Aktionskarten.
        Group groupAction = new Group(com, SWT.SHADOW_ETCHED_IN);
        groupAction.setData(DATA_KEY_ID, "actionGroup");
        groupAction.setText("Aktionskarten");
        groupAction.setLayout(new GridLayout(5, true));
        groupAction.setLayoutData(new GridData(
                GridData.VERTICAL_ALIGN_BEGINNING));

        // Erzeuge Gruppe für Müll- und Fluchkarten.
        Group groupMisc = new Group(com, SWT.SHADOW_ETCHED_IN);
        groupMisc.setData(DATA_KEY_ID, "miscGroup");
        groupMisc.setText("Müll, Fluch");
        groupMisc.setLayout(new GridLayout(2, true));
        groupMisc.setLayoutData(new GridData(GridData.VERTICAL_ALIGN_BEGINNING));

        // Erzeuge Gruppe für Spieleranzeige.
        groupPlayer = new Group(com, SWT.SHADOW_ETCHED_IN);
        groupPlayer.setData(DATA_KEY_ID, "playerGroup");
        groupPlayer.setFont(new Font(getDisplay(), "Arial", 11, SWT.ITALIC));
        groupPlayer.setText("Spieler: " + getModel().getCurrentPlayer().getName());
        groupPlayer.setLayout(new GridLayout(3, true));
        groupPlayer.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING | GridData.VERTICAL_ALIGN_FILL));

        // Erzeuge Gruppe für Ablage- und Nachziehstapel
        groupHandMisc = new Group(com, SWT.SHADOW_ETCHED_IN);
        groupHandMisc.setData(DATA_KEY_ID, "handMiscGroup");
        groupHandMisc.setText("Nachzieh- und Ablagestapel");
        groupHandMisc.setLayout(new GridLayout(2, true));
        groupHandMisc.setLayoutData(new GridData(
                GridData.VERTICAL_ALIGN_BEGINNING));

        // Erzeuge Gruppe für Handkarten.
        groupHand = new Group(com, SWT.SHADOW_ETCHED_IN);
        groupHand.setData(DATA_KEY_ID, "handGroup");
        groupHand.setText("Handkarten");
        groupHand.setLayout(new GridLayout(10, true));
        groupHand.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.VERTICAL_ALIGN_FILL | GridData.GRAB_HORIZONTAL));

        // Ablagestapel- und Nachziestapelbilder zeichnen.
        nachziehButton = new Button(groupHandMisc, SWT.PUSH);
        ablageButton = new Button(groupHandMisc, SWT.PUSH);
        drawAblage();

        // Spieleranzeige füllen.
        phaseLabel = new Label(groupPlayer, SWT.SINGLE);
        phaseLabel.setFont(new Font(getDisplay(), "Arial", 10, SWT.BOLD));
        phaseLabel.setData(DATA_KEY_ID, "LabelPhase");
        phaseLabel.setText(getModel().getPhase().toString());
        final Button phase = new Button(groupPlayer, SWT.PUSH);
        phase.setData(DATA_KEY_ID, "buttonPhase");
        phase.setText("nächste Phase");
        final Button end = new Button(groupPlayer, SWT.PUSH);
        end.setData(DATA_KEY_ID, "buttonEnd");
        end.setText("Spielzug beenden");
        actionLabel = new Label(groupPlayer, SWT.PUSH);
        moneyLabel = new Label(groupPlayer, SWT.PUSH);
        purchaseLabel = new Label(groupPlayer, SWT.PUSH);
        allCardsLabel = new Label(groupPlayer, SWT.PUSH);
        drawLabels();

        // Mache den Button nächste Phase funktionstüchtig.
        phase.addSelectionListener(new SelectionListener() {

            @Override
            public void widgetSelected(final SelectionEvent se) {
                // Setze auf nächst Spielphase
                getControl().nextPhase();
                // Aktuallisiere Anzeige
                drawLabels();
                drawHandcards();
                drawAblage();
            }

            @Override
            public void widgetDefaultSelected(final SelectionEvent se) {
            }
        });

        // Mache den Button end funktionstüchtig.
        end.addSelectionListener(new SelectionListener() {

            @Override
            public void widgetSelected(final SelectionEvent se) {
                getControl().nextPlayer(true);
                drawLabels();
                drawHandcards();
                drawAblage();
            }

            @Override
            public void widgetDefaultSelected(final SelectionEvent se) {
            }
        });

        // Zeichne Geldkarte und mache Sie funktionstüchtig.
        Button cooperButton = new Button(groupCommon, SWT.PUSH);
        cooperButton.setImage(resizeImage(new Image(getDisplay(), new MoneyCardCooper().getResourceSmallAsStream()), ZOOM));
        cooperButton.addMouseListener(new MouseListener() {

            @Override
            public void mouseDoubleClick(final MouseEvent e) {
            }

            @Override
            public void mouseDown(final MouseEvent e) {
            }

            @Override
            public void mouseUp(final MouseEvent e) {
                Card c = new MoneyCardCooper();
                cardEvent(e, c);
            }
        });

        // Zeichne Geldkarte und mache Sie funktionstüchtig.
        Button silverButton = new Button(groupCommon, SWT.PUSH);
        silverButton.setImage(resizeImage(new Image(getDisplay(), new MoneyCardSilver().getResourceSmallAsStream()), ZOOM));
        silverButton.addMouseListener(new MouseListener() {

            @Override
            public void mouseDoubleClick(final MouseEvent e) {
            }

            @Override
            public void mouseDown(final MouseEvent e) {
            }

            @Override
            public void mouseUp(final MouseEvent e) {
                Card c = new MoneyCardSilver();
                cardEvent(e, c);
            }
        });

        // Zeichne Geldkarte und mache Sie funktionstüchtig.
        Button goldButton = new Button(groupCommon, SWT.PUSH);
        goldButton.setImage(resizeImage(new Image(getDisplay(), new MoneyCardGold().getResourceSmallAsStream()), ZOOM));
        goldButton.addMouseListener(new MouseListener() {

            @Override
            public void mouseDoubleClick(final MouseEvent e) {
            }

            @Override
            public void mouseDown(final MouseEvent e) {
            }

            @Override
            public void mouseUp(final MouseEvent e) {
                Card c = new MoneyCardGold();
                cardEvent(e, c);
            }
        });

        // Zeichne Punktekarte und mache Sie funktionstüchtig.
        Button estateButton = new Button(groupCommon, SWT.PUSH);
        estateButton.setImage(resizeImage(new Image(getDisplay(), new VictoryCardEstate().getResourceSmallAsStream()), ZOOM));
        estateButton.addMouseListener(new MouseListener() {

            @Override
            public void mouseDoubleClick(final MouseEvent e) {
            }

            @Override
            public void mouseDown(final MouseEvent e) {
            }

            @Override
            public void mouseUp(final MouseEvent e) {
                Card c = new VictoryCardEstate();
                cardEvent(e, c);
            }
        });

        // Zeichne Punktekarte und mache Sie funktionstüchtig.
        Button duchyButton = new Button(groupCommon, SWT.PUSH);
        duchyButton.setImage(resizeImage(new Image(getDisplay(), new VictoryCardDuchy().getResourceSmallAsStream()), ZOOM));
        duchyButton.addMouseListener(new MouseListener() {

            @Override
            public void mouseDoubleClick(final MouseEvent e) {
            }

            @Override
            public void mouseDown(final MouseEvent e) {
            }

            @Override
            public void mouseUp(final MouseEvent e) {
                Card c = new VictoryCardDuchy();
                cardEvent(e, c);
            }
        });

        // Zeichne Punktekarte und mache Sie funktionstüchtig.
        Button provinceButton = new Button(groupCommon, SWT.PUSH);
        provinceButton.setImage(resizeImage(new Image(getDisplay(), new VictoryCardProvince().getResourceSmallAsStream()), ZOOM));
        provinceButton.addMouseListener(new MouseListener() {

            @Override
            public void mouseDoubleClick(final MouseEvent e) {
            }

            @Override
            public void mouseDown(final MouseEvent e) {
            }

            @Override
            public void mouseUp(final MouseEvent e) {
                Card c = new VictoryCardProvince();
                cardEvent(e, c);
            }
        });

        // Liste der Karten im Spiel holen und nach Kosten sortieren.
        List<Card> myActionCards = new ArrayList<Card>(getModel().getCards());
        Collections.sort(myActionCards, new CardBase.CardCostComparator());

        // Zeichne alle Aktionskarten und mache Sie funktionstüchtig.
        Button actionCardButton;
        for (final Card c : myActionCards) {
            actionCardButton = new Button(groupAction, SWT.PUSH);
            actionCardButton.setImage(resizeImage(new Image(getDisplay(), c.getResourceSmallAsStream()), ZOOM));
            actionCardButton.setData(DATA_KEY_CARD, c);
            Menu menu = new Menu(actionCardButton);
            MenuItem item = new MenuItem(menu, SWT.NONE);
            item.setImage(new Image(getDisplay(), c.getResourceBigAsStream()));
            actionCardButton.setMenu(menu);
            actionCardButton.addMouseListener(new MouseListener() {

                @Override
                public void mouseDoubleClick(final MouseEvent e) {
                }

                @Override
                public void mouseDown(final MouseEvent e) {
                }

                @Override
                public void mouseUp(final MouseEvent e) {
                    cardEvent(e, c);
                }
            });
        }

        // TODO: Zeichne Müll.
        Button trashButton = new Button(groupMisc, SWT.PUSH);
        trashButton.setImage(resizeImage(new Image(getDisplay(), new Trash().getResourceSmallAsStream()), ZOOM));

        // Zeichne Karte Fluch und mache Sie funktionstüchtig.
        Button victoryCardCurse = new Button(groupMisc, SWT.PUSH);
        victoryCardCurse.setImage(resizeImage(new Image(getDisplay(), new VictoryCardCurse().getResourceSmallAsStream()), ZOOM));
        victoryCardCurse.addMouseListener(new MouseListener() {

            @Override
            public void mouseDoubleClick(final MouseEvent e) {
            }

            @Override
            public void mouseDown(final MouseEvent e) {
            }

            @Override
            public void mouseUp(final MouseEvent e) {
                Card c = new VictoryCardCurse();
                cardEvent(e, c);
            }
        });

        // Zeichne Handkarten.
        drawHandcards();

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
        drawMainscreen();
    }

    /**
     * Ändert die Größe eines Bildes.
     *
     * @param origImage Original Bild
     * @param zoom Grad des Zooms
     *
     * @return neues Bild
     */
    private Image resizeImage(final Image origImage, final double zoom) {
        final int width = origImage.getBounds().width;
        final int height = origImage.getBounds().height;
        return new Image(Display.getDefault(), origImage.getImageData().scaledTo((int) (width * zoom), (int) (height * zoom)));
    }

    /**
     * Mouse-Event: Einkäufe auf linker Maustaste.
     *
     * @param e MouseEvent
     * @param c Karte für MouseEvent
     */
    private void cardEvent(final MouseEvent e, final Card c) {
        // e.button == 0 wird für den SWTBot benötigt
        if ((e.button == 0 || e.button == 1)) {
            if (getModel().getPhase().equals(GamePhase.Buy)) {
                if (getControl().buyCard(c)) {
                    drawLabels();
                    drawAblage();
                } else {
                    MessageDialog.show("Kauf ist nicht möglich!", "Fehler", MessageDialog.Type.ERROR);
                }
            } else {
                MessageDialog.show("Keine Käufe in der " + getModel().getPhase().toString() + " möglich!", "Hinweis", MessageDialog.Type.INFO);
            }
        }
    }

    /**
     * Zeichnet die Label im Spielermenü.
     *
     */
    private void drawLabels() {
        phaseLabel.setText(getModel().getPhase().toString());
        groupPlayer.setText("Spieler: " + getModel().getCurrentPlayer().getName());
        moneyLabel.setText("Verfügbares Geld: " + getModel().getCurrentPlayer().getMoney());
        purchaseLabel.setText("Verfügbare Käufe: " + getModel().getCurrentPlayer().getPurchaseCounter());
        actionLabel.setText("Ausspielbare Aktionen: " + getModel().getCurrentPlayer().getActionCounter());
        allCardsLabel.setText("Gesamtanzahl an Karten: " + getModel().getCurrentPlayer().getNumberAllCards());
        groupPlayer.layout();
    }

    /**
     * Zeichnet den Ablagestapel.
     */
    private void drawAblage() {
        Card card = getModel().getCurrentPlayer().getUppestPlayedCard();
        int number = getModel().getCurrentPlayer().getCards().size();
        if (card != null) {
            ablageButton.setImage(resizeImage(new Image(getDisplay(), card.getResourceSmallAsStream()), ZOOM));
        } else {
            ablageButton.setImage(resizeImage(new Image(getDisplay(), DominionShell.class
                    .getResourceAsStream("Images/small/Empty Card.jpg")), ZOOM));
        }
        if (number == 0) {
            nachziehButton.setImage(resizeImage(new Image(getDisplay(), DominionShell.class
                    .getResourceAsStream("Images/small/Empty Card.jpg")), ZOOM));;
        } else {
            nachziehButton.setImage(resizeImage(new Image(getDisplay(), DominionShell.class
                    .getResourceAsStream("Images/small/Hinten gold.jpg")), ZOOM));
        }
        groupHandMisc.layout();
    }

    /**
     * Zeichnet die Karten auf der Hand.
     *
     */
    private void drawHandcards() {
        Widget[] childrens = groupHand.getChildren();

        for (int i = 0; i < childrens.length; i++) {
            childrens[i].dispose();
        }

        Button handCardButton;

        for (final Card c : getModel().getCurrentPlayer().getCardsOnHand()) {
            handCardButton = new Button(groupHand, SWT.PUSH);
            handCardButton.setImage(resizeImage(new Image(getDisplay(), c.getResourceSmallAsStream()), ZOOM));
            handCardButton.setData(DATA_KEY_CARD, c);
            Menu menu = new Menu(handCardButton);
            MenuItem item = new MenuItem(menu, SWT.PUSH);
            item.setImage(new Image(getDisplay(), c.getResourceBigAsStream()));
            handCardButton.setMenu(menu);
            // Listener für Actionkarten
            if (BETA) {
                handCardButton.addMouseListener(new MouseListener() {

                    @Override
                    public void mouseDoubleClick(final MouseEvent e) {
                    }

                    @Override
                    public void mouseDown(final MouseEvent e) {
                    }

                    @Override
                    public void mouseUp(final MouseEvent e) {
                        if (e.button == 1 && getModel().getPhase().equals(GamePhase.Action)) {
                            if (c instanceof ActionCard) {
                                if (!getModel().getCurrentPlayer().compareUsedActionCard(c)) {
                                    getModel().getCurrentPlayer().addUsedActionCard(c);
                                    ActionCard m = (ActionCard) c;
                                    // TODO: CONTROL!!!!
                                    m.play(getModel().getCurrentPlayer());
                                    getModel().getCurrentPlayer().addActions(-1);
                                    drawLabels();
                                    // redrawLabels(groupPlayer, moneyLabel, purchaseLabel, actionLabel, allCards);
                                    drawHandcards();
                                }
                            }
                        }
                    }
                });
            }
        }
        groupHand.layout();
    }
}
