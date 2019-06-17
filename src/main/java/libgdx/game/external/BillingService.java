package libgdx.game.external;


import libgdx.transactions.ProductBillingInfo;

public interface BillingService {

    boolean removeAdsAlreadyBought();

    void buyRemoveAds();

    ProductBillingInfo getRemoveAdsProductBillingInfo();

    void buyOffer1();

}
