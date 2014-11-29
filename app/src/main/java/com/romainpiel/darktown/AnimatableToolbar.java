package com.romainpiel.darktown;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Toolbar;

public class AnimatableToolbar extends Toolbar {

    private AnimatorSet hideAnimator;
    private AnimatorSet showAnimator;

    private final Animator.AnimatorListener hideAnimatorListener = new AnimatorListenerAdapter() {
        @Override
        public void onAnimationEnd(Animator animation) {
            hideAnimator = null;
        }
    };
    private final Animator.AnimatorListener showAnimatorListener = new AnimatorListenerAdapter() {
        @Override
        public void onAnimationEnd(Animator animation) {
            showAnimator = null;
        }
    };

    public AnimatableToolbar(Context context) {
        super(context);
    }

    public AnimatableToolbar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AnimatableToolbar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public AnimatableToolbar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void hide() {
        translate(-getHeight(), hideAnimator, showAnimator, hideAnimatorListener);
    }

    public void show() {
        translate(0, showAnimator, hideAnimator, showAnimatorListener);
    }

    private void translate(float to, AnimatorSet animator, AnimatorSet reverseAnimator, Animator.AnimatorListener listener) {

        if (animator != null || getTranslationY() == to) {
            return;
        }

        if (reverseAnimator != null) {
            reverseAnimator.cancel();
        }

        animator = new AnimatorSet();
        animator.addListener(listener);
        animator.play(ObjectAnimator.ofFloat(this, View.TRANSLATION_Y, to));
        animator.start();
    }
}
