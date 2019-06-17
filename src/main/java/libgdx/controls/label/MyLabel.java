package libgdx.controls.label;


import com.badlogic.gdx.scenes.scene2d.ui.Label;

import libgdx.resources.ResourcesManager;
import libgdx.resources.FontManager;

public class MyLabel extends Label {

    MyLabel(String text) {
        super(text, ResourcesManager.getSkin());
        setFontScale(FontManager.getNormalFontDim());
    }

    public void setStyle(String styleName) {
        setStyle(ResourcesManager.getSkin().get(styleName, LabelStyle.class));
    }

    /**
     * In some cases wrap displays wrong. Use {@link MyWrappedLabel} instead.
     */
    @Override
    public void setWrap(boolean wrap) {
        throw new RuntimeException("Wrap is not supported, see javadoc for other component");
    }
}
