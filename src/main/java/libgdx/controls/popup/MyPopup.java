package libgdx.controls.popup;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.RunnableAction;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import libgdx.controls.ScreenRunnable;
import libgdx.controls.button.MyButton;
import libgdx.controls.button.builders.BackButtonBuilder;
import libgdx.controls.label.MyWrappedLabel;
import libgdx.controls.label.MyWrappedLabelConfigBuilder;
import libgdx.game.Game;
import libgdx.graphics.GraphicUtils;
import libgdx.resources.MainResource;
import libgdx.resources.ResourcesManager;
import libgdx.resources.dimen.MainDimen;
import libgdx.screen.AbstractScreen;
import libgdx.screen.AbstractScreenManager;
import libgdx.utils.ScreenDimensionsManager;
import libgdx.utils.Utils;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;


public abstract class MyPopup<TScreen extends AbstractScreen, TScreenManager extends AbstractScreenManager> extends Dialog implements Popup {

    private List<Actor> actorsToFront = new ArrayList<>();
    private TScreen screen;
    protected TScreenManager screenManager = (TScreenManager) Game.getInstance().getScreenManager();

    public MyPopup(TScreen screen) {
        super("", ResourcesManager.getSkin(), ResourcesManager.getPopupBackground());
        this.screen = screen;
    }

    @Override
    public PopupManager<MyPopup> getPopupManager() {
        return screen.getMyPopupManager();
    }

    @Override
    public MyPopup addToPopupManager() {
        if (Gdx.app.getType() == Application.ApplicationType.iOS) {
            MyButton backBtn = new BackButtonBuilder().createScreenBackButton(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    hide();
                }
            }, screen);
            getContentTable().add(backBtn).height(backBtn.getHeight()).width(backBtn.getWidth());
        }
        addText();
        addButtons();
        padBottom(MainDimen.vertical_general_margin.getDimen());
        padTop(MainDimen.vertical_general_margin.getDimen());
        setBackground();
        getPopupManager().addPopupToDisplay(this);
        return this;
    }

    @Override
    public void onBackKeyPress() {
    }

    @Override
    public TScreen getScreen() {
        return screen;
    }

    protected void setBackground() {
        setBackground(GraphicUtils.getNinePatch(MainResource.popup_background));
    }

    protected void addText() {
        MyWrappedLabel wrappedText = getLabel();
        addEmptyRowWithMargin(getContentTable());
        if (StringUtils.isNotBlank(getLabelText())) {
            getContentTable().add(wrappedText).width(wrappedText.getPrefWidth()).row();
            addEmptyRowWithMargin(getContentTable());
        }
    }

    protected MyWrappedLabel getLabel() {
        return new MyWrappedLabel(getInfoLabelConfigBuilder().build());
    }

    protected MyWrappedLabelConfigBuilder getInfoLabelConfigBuilder() {
        return new MyWrappedLabelConfigBuilder().setText(getLabelText()).setWidth(getPrefWidth() - getPrefWidth() / 10);
    }

    protected static void addEmptyRowWithMargin(Table table) {
        table.add(new Actor()).padBottom(MainDimen.vertical_general_margin.getDimen()).row();
    }

    protected void addButton(MyButton btn) {
        getButtonTable().add(btn).padBottom(MainDimen.vertical_general_margin.getDimen()).width(btn.getWidth()).height(btn.getHeight() * 1.05f).row();
    }

    protected void addActorsToFront(Actor actor) {
        getContentTable().getStage().getRoot().addActor(actor);
        actorsToFront.add(actor);
    }

    protected List<Actor> getActorsToFront() {
        return actorsToFront;
    }

    protected abstract String getLabelText();

    protected abstract void addButtons();

    @Override
    public void hide() {
        hide(Utils.createRunnableAction(new Runnable() {
            @Override
            public void run() {
            }
        }));
    }

    public void hide(final RunnableAction executeAfterHide) {
        super.hide();
        final MyPopup thisPopup = this;
        RunnableAction action = new RunnableAction();
        action.setRunnable(new ScreenRunnable(screen) {
            @Override
            public void executeOperations() {
                getPopupManager().hidePopup(thisPopup);
            }
        });
        addAction(Actions.sequence(
                Actions.delay(0.4f),
                Utils.createRunnableAction(new Runnable() {
                    @Override
                    public void run() {
                        getScreen().addAction(executeAfterHide);
                    }
                }),
                action));
    }

    @Override
    public float getPrefWidth() {
        return ScreenDimensionsManager.getScreenWidthValue(90);
    }

    @Override
    protected void drawStageBackground(Batch batch, float parentAlpha, float x, float y, float width, float height) {
        Color color = getColor();
        batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
        getStyle().stageBackground.draw(batch, x, y, ScreenDimensionsManager.getExternalDeviceWidth(), ScreenDimensionsManager.getExternalDeviceHeight());
    }

}
