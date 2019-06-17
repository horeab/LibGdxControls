package libgdx.utils.startgame.test;

import libgdx.game.external.BillingService;
import libgdx.game.external.FacebookService;
import libgdx.transactions.ProductBillingInfo;

public class DefaultBillingService implements BillingService {

    @Override
    public void buyRemoveAds() {
    }

    @Override
    public boolean removeAdsAlreadyBought() {
        return false;
    }

    @Override
    public void buyOffer1() {
    }

    @Override
    public ProductBillingInfo getRemoveAdsProductBillingInfo() {
        return new ProductBillingInfo(1.0, "RON");
    }
}
