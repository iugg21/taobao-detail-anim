package com.ctrun.taobao.demo;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

/**
 * Created by ctrun on 2017/7/29.
 */

public class SelectParamPopupWindow extends PopupWindow {

    private View mRealContentLayout;

    public SelectParamPopupWindow(final Activity activity) {
        super(activity.getLayoutInflater().inflate(R.layout.popup_select_param, null), RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);

        getContentView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        mRealContentLayout = getContentView().findViewById(R.id.realContentLayout);
        mRealContentLayout.setVisibility(View.INVISIBLE);
        mRealContentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        mRealContentLayout.findViewById(R.id.iv_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setOutsideTouchable(true);
        setTouchable(true);
        setFocusable(true);
    }

    public void showPop(View parent) {
        if (isShowing() || getContentView() == null) {
            return;
        }

        showAtLocation(parent, Gravity.CENTER, 0, 0);

        mRealContentLayout.setVisibility(View.VISIBLE);
        Animation inAnimation = AnimationUtils.loadAnimation(getContentView().getContext(), R.anim.slide_in_bottom);
        mRealContentLayout.startAnimation(inAnimation);
    }

    /**
     * 按返回键的时候会调用这个方法
     * 所以这里重写该方法，主要是解决按返回键也具有动画效果
     */
    @Override
    public void dismiss() {
        if (!isShowing()) {
            return;
        }

        mRealContentLayout.setVisibility(View.VISIBLE);
        Animation outAnimation = AnimationUtils.loadAnimation(getContentView().getContext(), R.anim.slide_out_bottom);
        outAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                SelectParamPopupWindow.super.dismiss();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        mRealContentLayout.startAnimation(outAnimation);
        if(mListener != null) {
            mListener.onCancel();
        }
    }

    private OnCancelListener mListener;
    public void setOnCancelListener(OnCancelListener listener) {
        mListener = listener;
    }

    public interface OnCancelListener {
        public void onCancel();
    }
}
