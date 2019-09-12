package libgdx.game.external;

public interface AppInfoService {

    String getGameIdPrefix();

    String getAppName();

    String getStoreAppId();

    String getProVersionStoreAppId();

    String getLanguage();

    boolean googleFacebookLoginEnabled();

    void showPopupAd(Runnable afterClose);

    void showRewardedVideoAd();

    String getMainResourcesFolder();

    String getResourcesFolder();

    String getImplementationGameResourcesFolder();

    boolean isScreenShotMode();

    float gameScreenTopMargin();

    boolean isProVersion();

    boolean isPortraitMode();
}
