package libgdx.game;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.I18NBundle;

import libgdx.game.external.AppInfoService;
import libgdx.game.external.BillingService;
import libgdx.game.external.FacebookService;
import libgdx.game.external.LoginService;
import libgdx.resources.FontManager;
import libgdx.resources.Res;
import libgdx.screen.AbstractScreen;
import libgdx.screen.AbstractScreenManager;
import libgdx.screen.SplashScreen;
import libgdx.utils.EnumUtils;
import libgdx.utils.InternetUtils;

public abstract class Game<
        TAppInfoService extends AppInfoService,
        TMainDependencyManager extends MainDependencyManager,
        TSubGameDependencyManager extends SubGameDependencyManager,
        TScreen extends AbstractScreen,
        TScreenManager extends AbstractScreenManager,
        TGameId extends Enum & GameId>
        extends com.badlogic.gdx.Game {

    private static Game instance;

    private boolean firstTimeMainMenuDisplayed = true;
    private Boolean hasInternet;

    private TAppInfoService appInfoService;
    protected LoginService loginService;
    private FacebookService facebookService;
    private BillingService billingService;

    private AssetManager assetManager;
    private TSubGameDependencyManager subGameDependencyManager;
    private TMainDependencyManager mainDependencyManager;
    protected TScreenManager screenManager;
    private FontManager fontManager;

    public Game(FacebookService facebookService,
                BillingService billingService,
                TAppInfoService appInfoService,
                TMainDependencyManager mainDependencyManager) {
        super();
        instance = this;
        this.appInfoService = appInfoService;
        this.facebookService = facebookService;
        this.billingService = billingService;
        this.mainDependencyManager = mainDependencyManager;
        subGameDependencyManager = (TSubGameDependencyManager) ((TGameId) EnumUtils.getEnumValue(mainDependencyManager.getGameIdClass(), appInfoService.getGameIdPrefix())).getDependencyManager();
        screenManager = (TScreenManager) mainDependencyManager.createScreenManager();
    }

    public boolean hasInternet() {
        if (hasInternet == null) {
            this.hasInternet = InternetUtils.hasInternet();
        }
        return hasInternet;
    }

    public void setNewContext(TAppInfoService newAppInfoService) {
        this.appInfoService = newAppInfoService;
        fontManager = new FontManager();
        screenManager.showMainScreen();
    }

    @Override
    public void create() {
        initAssetManager();
        screenManager.initialize(this);
    }

    public void executeAfterAssetsLoaded() {
        displayScreenAfterAssetsLoad();
        fontManager = new FontManager();
    }

    public TMainDependencyManager getMainDependencyManager() {
        return mainDependencyManager;
    }

    private void initAssetManager() {
        super.setScreen(new SplashScreen());
        assetManager = new AssetManager();
        for (Res resource : mainDependencyManager.createResourceService().getAllRes()) {
            Class<?> classType = resource.getClassType();
            if (classType != null) {
                String path = resource.getPath();
                String labelResLanguage = path.substring(path.length() - 2);
                if (classType.equals(I18NBundle.class) && !labelResLanguage.equals(appInfoService.getLanguage())) {
                    continue;
                }
                assetManager.load(path, classType);
            }
        }
    }

    public FontManager getFontManager() {
        return fontManager;
    }

    public TAppInfoService getAppInfoService() {
        return appInfoService;
    }

    public String getGameId() {
        return getGameIdPrefix() + "_" + getAppInfoService().getLanguage();
    }

    public String getGameIdPrefix() {
        return getAppInfoService().getGameIdPrefix();
    }

    public BillingService getBillingService() {
        return billingService;
    }

    public FacebookService getFacebookService() {
        return facebookService;
    }

    public LoginService getLoginService() {
        return loginService;
    }

    public static Game getInstance() {
        return instance;
    }

    public AssetManager getAssetManager() {
        return assetManager;
    }

    public boolean isFirstTimeMainMenuDisplayed() {
        return firstTimeMainMenuDisplayed;
    }

    public void setFirstTimeMainMenuDisplayed(boolean firstTimeMainMenuDisplayed) {
        this.firstTimeMainMenuDisplayed = firstTimeMainMenuDisplayed;
    }

    protected abstract void displayScreenAfterAssetsLoad();

    public TSubGameDependencyManager getSubGameDependencyManager() {
        return subGameDependencyManager;
    }

    public TScreenManager getScreenManager() {
        return screenManager;
    }

    public TScreen getAbstractScreen() {
        return (TScreen) getScreen();
    }

    public void setScreen(AbstractScreen screen) {
        super.setScreen(screen);
    }

    @Override
    public void dispose() {
        super.dispose();
        assetManager.clear();
    }
}
