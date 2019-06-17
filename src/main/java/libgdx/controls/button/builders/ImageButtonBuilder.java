package libgdx.controls.button.builders;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import org.apache.commons.lang3.StringUtils;

import libgdx.controls.animations.ActorAnimation;
import libgdx.controls.button.ButtonBuilder;
import libgdx.controls.button.ButtonSkin;
import libgdx.controls.button.MainButtonSize;
import libgdx.controls.button.MainButtonSkin;
import libgdx.controls.button.MyButton;
import libgdx.resources.MainResource;
import libgdx.resources.dimen.MainDimen;
import libgdx.screen.AbstractScreen;
import libgdx.controls.labelimage.LabelImage;
import libgdx.resources.FontManager;
import libgdx.graphics.GraphicUtils;

public class ImageButtonBuilder extends ButtonBuilder {

    private boolean animateFadeInFadeOut;
    private boolean animateZoomInZoomOut;
    private AbstractScreen screen;

    public ImageButtonBuilder(ButtonSkin buttonSkin, AbstractScreen screen) {
        setButtonSkin(buttonSkin);
        setFixedButtonSize(MainButtonSize.STANDARD_IMAGE);
        this.screen = screen;
    }

    public ImageButtonBuilder animateFadeInFadeOut() {
        this.animateFadeInFadeOut = true;
        return this;
    }

    public ImageButtonBuilder animateZoomInZoomOut() {
        this.animateZoomInZoomOut = true;
        return this;
    }

    public ImageButtonBuilder setText(String text) {
        if (StringUtils.isNotBlank(text)) {
            LabelImage textTable = createTextTable(text, MainDimen.horizontal_general_margin.getDimen() * 15, FontManager.calculateMultiplierStandardFontSize(0.7f));
            textTable.setBackground(GraphicUtils.getNinePatch(MainResource.popup_background));
            addCenterTextImageColumn(textTable);
        }
        return this;
    }

    public ImageButtonBuilder addFadeOutOnClick() {
        addChangeListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                actor.setTouchable(Touchable.disabled);
                actor.addAction(Actions.sequence(Actions.fadeOut(0.5f), Actions.hide()));
            }
        });
        return this;
    }

    @Override
    public MyButton build() {
        MyButton button = super.build();
        if (animateFadeInFadeOut) {
            new ActorAnimation(button, screen).animateFadeInFadeOut();
        }
        if (animateZoomInZoomOut) {
            button.setTransform(true);
            new ActorAnimation(button, screen).animateZoomInZoomOut();
        }
        Table centerRow = button.getCenterRow();
        if (centerRow != null) {
            centerRow.padTop(button.getHeight() * 1.6f);
        }
        return button;
    }
}
