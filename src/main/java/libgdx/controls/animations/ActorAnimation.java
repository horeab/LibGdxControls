package libgdx.controls.animations;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.AlphaAction;
import com.badlogic.gdx.scenes.scene2d.actions.RunnableAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;

import libgdx.controls.ScreenRunnable;
import libgdx.game.Game;
import libgdx.graphics.GraphicUtils;
import libgdx.resources.MainResource;
import libgdx.resources.Res;
import libgdx.resources.dimen.MainDimen;
import libgdx.screen.AbstractScreen;
import libgdx.utils.ActorPositionManager;
import libgdx.utils.ScreenDimensionsManager;
import libgdx.utils.Utils;

public class ActorAnimation {

    private Actor actor;
    private AbstractScreen screen;

    public ActorAnimation(Actor actor, AbstractScreen screen) {
        this.actor = actor;
        this.screen = screen;
        actor.setOrigin(Align.center);
    }

    public ActorAnimation(AbstractScreen screen) {
        this.screen = screen;
    }

    public void animateFadeInFadeOut() {
        animateFadeInFadeOut(1.5f, 0.3f);
    }

    public void animateFastFadeInFadeOut() {
        animateFadeInFadeOut(1f, 0.6f);
    }

    public void animateFastFadeInFadeOutWithTotalFadeOut() {
        animateFadeInFadeOut(1f, 0.1f);
    }

    public void animateFastFadeIn() {
        animateFadeIn(0.2f);
    }


    public void animateFadeIn(float duration) {
        AlphaAction fadeOut = Actions.fadeOut(0);
        fadeOut.setAlpha(0f);
        actor.addAction(Actions.sequence(fadeOut, Utils.createRunnableAction(new ScreenRunnable(screen) {
            @Override
            public void executeOperations() {
                actor.setVisible(true);
            }
        }), Actions.fadeIn(duration)));
    }

    public void animateFadeInFadeOut(final float duration, final float alpha) {
        RunnableAction run = new RunnableAction();
        run.setRunnable(new ScreenRunnable(screen) {
            @Override
            public void executeOperations() {
                animateFadeInFadeOut(duration, alpha);
            }
        });
        AlphaAction fadeOut = Actions.fadeOut(duration);
        fadeOut.setAlpha(alpha);
        actor.addAction(Actions.sequence(fadeOut, Actions.fadeIn(duration), run));
    }

    public static void animateImageCenterScreenFadeOut(Res imgRes, float duration) {
        Image image = GraphicUtils.getImage(imgRes);
        float imgSideDimen = ScreenDimensionsManager.getScreenWidthValue(50);
        image.setWidth(imgSideDimen);
        image.setHeight(imgSideDimen);
        Game.getInstance().getAbstractScreen().addActor(image);
        ActorPositionManager.setActorCenterScreen(image);
        image.addAction(
                Actions.sequence(
                        Actions.fadeOut(duration),
                        Utils.createRemoveActorAction(image)
                ));
        image.addAction(Actions.scaleBy(-0.1f, -0.1f, duration));
    }

    public static void pressFinger(float x, float y) {
        float zoomAmount2 = 3f;
        float duration2 = 0.8f;
        Image pressFinger = GraphicUtils.getImage(MainResource.press_finger);
        float imgDim = MainDimen.horizontal_general_margin.getDimen() * 10;
        pressFinger.setWidth(imgDim);
        pressFinger.setHeight(imgDim);
        pressFinger.addAction(
                Actions.sequence(
                        Actions.scaleBy(zoomAmount2, zoomAmount2, duration2),
                        Actions.scaleBy(-zoomAmount2, -zoomAmount2, duration2 / 2f),
                        Actions.delay(0.3f),
                        Actions.fadeOut(0.5f),
                        Actions.removeActor(pressFinger)
                ));
        pressFinger.setY(y);
        pressFinger.setX(x);
        Game.getInstance().getAbstractScreen().addActor(pressFinger);
    }

    public void animateZoomInZoomOut() {
        animateZoomInZoomOut(0.2f);
    }

    public void animateZoomInZoomOut(final float zoomAmount) {
        RunnableAction run = new RunnableAction();
        run.setRunnable(new ScreenRunnable(screen) {
            @Override
            public void executeOperations() {
                animateZoomInZoomOut(zoomAmount);
            }
        });
        float duration = 0.8f;
        actor.addAction(Actions.sequence(Actions.scaleBy(zoomAmount, zoomAmount, duration), Actions.scaleBy(-zoomAmount, -zoomAmount, duration), run));
    }

    public void animatePulse() {
        float duration = 0.2f;
        final Table table = new Table();
        table.setFillParent(true);
        table.setBackground(GraphicUtils.getNinePatch(MainResource.popup_background));
        RunnableAction runnableAction = new RunnableAction() {
            @Override
            public void run() {
                table.remove();
            }
        };
        table.addAction(Actions.sequence(Actions.alpha(0), Actions.fadeIn(duration), Actions.fadeOut(duration), runnableAction));
        screen.addActor(table);
    }

    public void createScrollingBackground(Res backgr) {
        int scrollSpeed = 7;
        Image image1 = GraphicUtils.getImage(backgr);
        image1.setY(0);
        image1.setFillParent(true);
        Image image2 = GraphicUtils.getImage(backgr);
        image2.setY(-ScreenDimensionsManager.getScreenHeight());
        image2.setFillParent(true);
        screen.addActor(image1);
        screen.addActor(image2);
        animateMoveUp(scrollSpeed, image1);
        animateMoveUp(scrollSpeed, image2);
    }

    private void animateMoveUp(final float amount, final Actor actor) {
        RunnableAction run = new RunnableAction();
        run.setRunnable(new ScreenRunnable(screen) {
            @Override
            public void executeOperations() {
                animateMoveUp(amount, actor);
                int screenHeight = ScreenDimensionsManager.getScreenHeight();
                if (actor.getY() > screenHeight) {
                    actor.setY(-screenHeight);
                }
            }
        });
        float duration = 0.5f;
        actor.addAction(Actions.sequence(Actions.moveBy(0, amount, duration), run));
    }

}
