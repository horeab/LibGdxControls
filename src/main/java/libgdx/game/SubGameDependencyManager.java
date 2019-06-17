package libgdx.game;

import java.util.List;

import libgdx.constants.ScreenContrast;
import libgdx.resources.IncrementingRes;
import libgdx.resources.SpecificResource;
import libgdx.utils.model.RGBColor;

public abstract class SubGameDependencyManager {

    public abstract <T extends Enum<T> & SpecificResource> Class<T> getSpecificResourceTypeEnum();

    public abstract List<? extends IncrementingRes> getIncrementResList();

    public ScreenContrast getScreenContrast() {
        return ScreenContrast.LIGHT;
    }

    public RGBColor getScreenBackgroundColor() {
        return getScreenContrast() == ScreenContrast.LIGHT ? RGBColor.LIGHT_BLUE : RGBColor.DARK_BLUE;
    }
}
