package libgdx.controls.animations;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.AlphaAction;
import com.badlogic.gdx.scenes.scene2d.actions.RunnableAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Align;

import libgdx.controls.ScreenRunnable;
import libgdx.game.Game;
import libgdx.graphics.GraphicUtils;
import libgdx.resources.Res;
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
        float duration = 0.2f;
        AlphaAction fadeOut = Actions.fadeOut(duration);
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
}
