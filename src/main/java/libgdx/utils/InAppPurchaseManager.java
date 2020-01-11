package libgdx.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.pay.Information;
import com.badlogic.gdx.pay.Offer;
import com.badlogic.gdx.pay.OfferType;
import com.badlogic.gdx.pay.PurchaseManagerConfig;
import com.badlogic.gdx.pay.PurchaseObserver;
import com.badlogic.gdx.pay.Transaction;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.RunnableAction;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import libgdx.controls.button.ButtonBuilder;
import libgdx.controls.button.MainButtonSize;
import libgdx.controls.button.MyButton;
import libgdx.controls.popup.InAppPurchasesPopup;
import libgdx.controls.popup.notificationpopup.MyNotificationPopupConfigBuilder;
import libgdx.controls.popup.notificationpopup.MyNotificationPopupCreator;
import libgdx.game.Game;
import libgdx.preferences.InAppPurchasesPreferencesService;
import libgdx.resources.gamelabel.MainGameLabel;
import libgdx.utils.model.FontColor;
import libgdx.utils.model.FontConfig;

public class InAppPurchaseManager {

    public static final String EXTRA_CONTENT_PRODUCT_ID = "extraContent";

    private boolean restorePressed;
    private MyButton buyButton;
    private MyButton restoreButton;
    private InAppPurchasesPreferencesService inAppPurchasesService;
    private Information skuInfo;
    private InAppPurchasesPopup inAppPurchasesPopup;

    public InAppPurchaseManager() {
        this.inAppPurchasesService = new InAppPurchasesPreferencesService();
        initPurchaseManager();
    }

    public void displayInAppPurchasesPopup() {
        if (buyButton == null || restoreButton == null) {
            initButtons();
        }
        String localName = skuInfo == null || skuInfo.equals(Information.UNAVAILABLE) ? MainGameLabel.l_not_available.getText() : skuInfo.getLocalName();
        inAppPurchasesPopup = new InAppPurchasesPopup(Game.getInstance().getAbstractScreen(), localName, buyButton, restoreButton);
        inAppPurchasesPopup.addToPopupManager();
    }

    private void hideInAppPurchasesPopup(RunnableAction executeAfterHide) {
        if (inAppPurchasesPopup != null) {
            inAppPurchasesPopup.hide(executeAfterHide);
        }
    }

    private void initButtons() {
        ButtonBuilder buyButtonBuilder = new ButtonBuilder()
//                .setFixedButtonSize(MainButtonSize.TWO_ROW_BUTTON_SIZE)
                .setDefaultButton();

        ButtonBuilder restoreButtonBuilder = new ButtonBuilder()
                .setDefaultButton();

        if (skuInfo == null || skuInfo.equals(Information.UNAVAILABLE)) {
            restoreButtonBuilder.setDisabled(true);
            restoreButtonBuilder.setText(MainGameLabel.l_not_available.getText());
            buyButtonBuilder.setDisabled(true);
            buyButtonBuilder.setText(MainGameLabel.l_not_available.getText());
        } else {
//            buyButtonBuilder.setText(skuInfo.getLocalName() + "\n" + skuInfo.getLocalPricing() + " " + skuInfo.getPriceCurrencyCode());
            restoreButtonBuilder.setText(MainGameLabel.l_restore_purchase.getText());
            buyButtonBuilder.setText(MainGameLabel.l_buy.getText());
        }
        buyButton = buyButtonBuilder.build();
        buyButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                buyItem();
            }
        });

        restoreButton = restoreButtonBuilder.build();
        restoreButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                restorePressed = true;
                restoreButton.setDisabled(true);
                Game.getInstance().purchaseManager.purchaseRestore();
            }
        });
    }

    private void initPurchaseManager() {
        // the purchase manager config here in the core project works if your SKUs are the same in every
        // payment system. If this is not the case, inject them like the PurchaseManager is injected
        PurchaseManagerConfig pmc = new PurchaseManagerConfig();
        pmc.addOffer(new Offer().setType(OfferType.ENTITLEMENT).setIdentifier(EXTRA_CONTENT_PRODUCT_ID));

        Game.getInstance().purchaseManager.install(new MyPurchaseObserver(), pmc, true);
    }

    private void buyItem() {
        Game.getInstance().purchaseManager.purchase(EXTRA_CONTENT_PRODUCT_ID);
    }

    private void setBought() {
        inAppPurchasesService.savePurchase(EXTRA_CONTENT_PRODUCT_ID);
        buyButton.setDisabled(true);
        hideInAppPurchasesPopup(Utils.createRunnableAction(new Runnable() {
            @Override
            public void run() {
                MyNotificationPopupConfigBuilder myNotificationPopupConfigBuilder = new MyNotificationPopupConfigBuilder()
                        .setText(MainGameLabel.l_purchased.getText())
                        .setTransferBetweenScreens(true);
                myNotificationPopupConfigBuilder.setFontConfig(new FontConfig(FontColor.BLACK.getColor(), FontConfig.FONT_SIZE));
                new MyNotificationPopupCreator(myNotificationPopupConfigBuilder.build()).shortNotificationPopup().addToPopupManager();
                Game.getInstance().getScreenManager().showMainScreen();
            }
        }));
    }

    private class MyPurchaseObserver implements PurchaseObserver {

        @Override
        public void handleInstall() {
            Gdx.app.postRunnable(new Runnable() {
                @Override
                public void run() {
                    skuInfo = Game.getInstance().purchaseManager.getInformation(EXTRA_CONTENT_PRODUCT_ID);
                    initButtons();
                }
            });
        }

        @Override
        public void handleInstallError(final Throwable e) {
            Gdx.app.postRunnable(new Runnable() {
                @Override
                public void run() {
                    initButtons();
                    showErrorOnMainThread(e.getMessage());
                }
            });
        }

        @Override
        public void handleRestore(final Transaction[] transactions) {
            if (transactions != null && transactions.length > 0)
                for (Transaction t : transactions) {
                    handlePurchase(t, true);
                }
            else if (restorePressed) {
                showErrorOnMainThread(MainGameLabel.l_nothing_to_restore.getText());
            }
        }

        @Override
        public void handleRestoreError(Throwable e) {
            if (restorePressed) {
                showErrorOnMainThread("Error: " + e.getMessage());
            }
        }

        @Override
        public void handlePurchase(final Transaction transaction) {
            handlePurchase(transaction, false);
        }

        void handlePurchase(final Transaction transaction, final boolean fromRestore) {
            Gdx.app.postRunnable(new Runnable() {
                @Override
                public void run() {
                    if (transaction.isPurchased()) {
                        if (transaction.getIdentifier().equals(EXTRA_CONTENT_PRODUCT_ID)) {
                            setBought();
                        }
                    }
                }
            });
        }

        @Override
        public void handlePurchaseError(Throwable e) {
            showErrorOnMainThread("Error:" + e.getMessage());
        }

        @Override
        public void handlePurchaseCanceled() {

        }

        private void showErrorOnMainThread(final String message) {
            Gdx.app.postRunnable(new Runnable() {
                @Override
                public void run() {
                    hideInAppPurchasesPopup(Utils.createRunnableAction(new Runnable() {
                        @Override
                        public void run() {
                            MyNotificationPopupConfigBuilder myNotificationPopupConfigBuilder = new MyNotificationPopupConfigBuilder()
                                    .setText(message)
                                    .setTransferBetweenScreens(true);
                            myNotificationPopupConfigBuilder.setFontConfig(new FontConfig(FontColor.BLACK.getColor(), FontConfig.FONT_SIZE));
                            new MyNotificationPopupCreator(myNotificationPopupConfigBuilder.build()).shortNotificationPopup().addToPopupManager();
                        }
                    }));
                }
            });
        }
    }
}