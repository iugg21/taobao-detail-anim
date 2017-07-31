package com.ctrun.taobao.demo;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

public class MainActivity extends AppCompatActivity {
    private SelectParamPopupWindow mPopupWindow;
    private View mBtnSelectParam;
    private View mRootView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar actionBarToolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
        if (actionBarToolbar != null) {
            setSupportActionBar(actionBarToolbar);
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mRootView = ((ViewGroup) findViewById(android.R.id.content)).getChildAt(0);

        mBtnSelectParam = findViewById(R.id.btn_selectParam);
        mBtnSelectParam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopupWindow.showPop(mRootView);
                SelectParamAnimation.playToBackground(mRootView);
            }
        });

        mPopupWindow = new SelectParamPopupWindow(this);
        mPopupWindow.setOnCancelListener(new SelectParamPopupWindow.OnCancelListener() {
            @Override
            public void onCancel() {
                SelectParamAnimation.playToForeground(mRootView);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
