package libgdx.game.external;

public interface AppInfoService {

    String getGameIdPrefix();

    String getAppName();

    String getStoreAppId();

    String getLanguage();

    boolean googleFacebookLoginEnabled();

    void showPopupAd();

    void showRewardedVideoAd();

    String getMainResourcesFolder();

    String getResourcesFolder();

    String getImplementationGameResourcesFolder();

    boolean screenShotMode();

    float gameScreenTopMargin();

    String proVersionStoreAppId();

    boolean isProVersion();

    boolean isPortraitMode();
}
