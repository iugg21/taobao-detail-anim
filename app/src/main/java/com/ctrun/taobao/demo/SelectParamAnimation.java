package com.ctrun.taobao.demo;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.view.View;

/**
 * Created by ctrun on 2017/7/29.
 */

public class SelectParamAnimation {

    public static void playToBackground(View target) {
        final int width = target.getWidth();
        final int height = target.getHeight();
        target.setPivotX(width / 2);
        target.setPivotY(height / 2);

        PropertyValuesHolder rotateX = PropertyValuesHolder.ofFloat("rotationX", 20f);
        PropertyValuesHolder scaleX = PropertyValuesHolder.ofFloat("scaleX", 0.8f);
        PropertyValuesHolder scaleY = PropertyValuesHolder.ofFloat("scaleY", 0.8f);
        PropertyValuesHolder translationY = PropertyValuesHolder.ofFloat("translationY", -(height - height * 0.8f) / 2);
        ObjectAnimator movingAnimator = ObjectAnimator.ofPropertyValuesHolder(target, rotateX, scaleX, scaleY, translationY);
        movingAnimator.setDuration(500);

        ObjectAnimator resumeRotateXAnimator = ObjectAnimator.ofFloat(target, "rotationX", 0);
        resumeRotateXAnimator.setStartDelay(250);

        AnimatorSet s = new AnimatorSet();
        s.playTogether(movingAnimator, resumeRotateXAnimator);
        s.start();
    }

    public static void playToForeground(View target) {
        PropertyValuesHolder rotateX = PropertyValuesHolder.ofFloat("rotationX", 20f);
        PropertyValuesHolder scaleX = PropertyValuesHolder.ofFloat("scaleX", 1.0f);
        PropertyValuesHolder scaleY = PropertyValuesHolder.ofFloat("scaleY", 1.0f);
        PropertyValuesHolder translationY = PropertyValuesHolder.ofFloat("translationY", 0);
        ObjectAnimator movingAnimator = ObjectAnimator.ofPropertyValuesHolder(target, rotateX, scaleX, scaleY, translationY);
        movingAnimator.setDuration(500);

        ObjectAnimator resumeRotateXAnimator = ObjectAnimator.ofFloat(target, "rotationX", 0);
        resumeRotateXAnimator.setStartDelay(250);

        AnimatorSet s = new AnimatorSet();
        s.playTogether(movingAnimator, resumeRotateXAnimator);
        s.start();
    }

}
