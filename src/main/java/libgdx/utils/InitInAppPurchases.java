package libgdx.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.pay.Information;
import com.badlogic.gdx.pay.Offer;
import com.badlogic.gdx.pay.OfferType;
import com.badlogic.gdx.pay.PurchaseManagerConfig;
import com.badlogic.gdx.pay.PurchaseObserver;
import com.badlogic.gdx.pay.Transaction;

import libgdx.controls.button.MyButton;
import libgdx.game.Game;

class InitInAppPurchases {

    public static final String MY_ENTITLEMENT = "extraContent";

    private final String sku;
    private final int usdCents;
    private MyButton buyButton;
    private MyButton restorePurchase;

    public InitInAppPurchases(String sku,
                              int usdCents,
                              MyButton buyButton,
                              MyButton restorePurchase) {
        this.sku = sku;
        this.usdCents = usdCents;
        this.buyButton = buyButton;
        this.restorePurchase = restorePurchase;
        initPurchaseManager(buyButton);
    }

    private void initPurchaseManager(MyButton myButton) {
        if (!Game.getInstance().purchaseManager.installed()) {
            // the purchase manager config here in the core project works if your SKUs are the same in every
            // payment system. If this is not the case, inject them like the PurchaseManager is injected
            PurchaseManagerConfig pmc = new PurchaseManagerConfig();
            pmc.addOffer(new Offer().setType(OfferType.ENTITLEMENT).setIdentifier(MY_ENTITLEMENT));

            Game.getInstance().purchaseManager.install(new MyPurchaseObserver(myButton), pmc, true);
        }
    }

    private void buyItem() {
        Game.getInstance().purchaseManager.purchase(sku);
    }

    public void setBought(MyButton myButton) {
        myButton.setDisabled(true);
    }

    public void updateFromManager(MyButton myButton) {
        Information skuInfo = Game.getInstance().purchaseManager.getInformation(sku);

        if (skuInfo == null || skuInfo.equals(Information.UNAVAILABLE)) {
            myButton.setDisabled(true);
            myButton.setText("Not available");
        } else {
            myButton.setText(skuInfo.getLocalName() + " " + skuInfo.getLocalPricing());
        }
    }

    private class MyPurchaseObserver implements PurchaseObserver {

        private MyButton buyBtn;

        public MyPurchaseObserver(MyButton buyBtn) {
            this.buyBtn = buyBtn;
        }

        @Override
        public void handleInstall() {
            Gdx.app.log("IAP", "Installed");

            Gdx.app.postRunnable(new Runnable() {
                @Override
                public void run() {
                    Game.getInstance().getScreenManager().showMainScreen();
                }
            });
        }

        @Override
        public void handleInstallError(final Throwable e) {
            Gdx.app.error("IAP", "Error when trying to install PurchaseManager", e);
            Gdx.app.postRunnable(new Runnable() {
                @Override
                public void run() {
                    Game.getInstance().getScreenManager().showMainScreen();
                }
            });
        }

        @Override
        public void handleRestore(final Transaction[] transactions) {
            if (transactions != null && transactions.length > 0)
                for (Transaction t : transactions) {
                    handlePurchase(t, true);
                }
            else if (restorePressed)
                showErrorOnMainThread("Nothing to restore");
        }

        @Override
        public void handleRestoreError(Throwable e) {
            if (restorePressed)
                showErrorOnMainThread("Error restoring purchases: " + e.getMessage());
        }

        @Override
        public void handlePurchase(final Transaction transaction) {
            handlePurchase(transaction, false);
        }

        protected void handlePurchase(final Transaction transaction, final boolean fromRestore) {
            Gdx.app.postRunnable(new Runnable() {
                @Override
                public void run() {
                    if (transaction.isPurchased()) {
                        if (transaction.getIdentifier().equals(MY_ENTITLEMENT)) {
                            setBought(buyBtn);
                        }

                    }
                }
            });
        }

        @Override
        public void handlePurchaseError(Throwable e) {
            showErrorOnMainThread("Error on buying:\n" + e.getMessage());
        }

        @Override
        public void handlePurchaseCanceled() {

        }

        private void showErrorOnMainThread(final String message) {
            Gdx.app.postRunnable(new Runnable() {
                @Override
                public void run() {
                    // show a dialog here...
                }
            });
        }
    }
}
