package libgdx.preferences;


public class InAppPurchasesPreferencesService {

    private PreferencesService preferencesService = new PreferencesService("InAppPurchasesService");

    public InAppPurchasesPreferencesService() {
        this.preferencesService.clear();
    }

    public boolean isPurchased(String purchaseId) {
        return preferencesService.getPreferences().getBoolean(purchaseId, false);
    }

    public void savePurchase(String purchaseId) {
        preferencesService.putBoolean(purchaseId, true);
    }
}
