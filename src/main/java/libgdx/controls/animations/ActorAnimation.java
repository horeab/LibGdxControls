package libgdx.controls.animations;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.AlphaAction;
import com.badlogic.gdx.scenes.scene2d.actions.RunnableAction;
import com.badlogic.gdx.utils.Align;

import libgdx.controls.ScreenRunnable;
import libgdx.screen.AbstractScreen;

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
