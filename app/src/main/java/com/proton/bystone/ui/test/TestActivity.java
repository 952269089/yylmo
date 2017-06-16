package com.proton.bystone.ui.test;

import android.os.Bundle;
import android.view.View;

import com.proton.bystone.R;
import com.proton.library.ui.MTFBaseActivity;
import com.proton.library.ui.annotation.MTFActivityFeature;

import butterknife.OnClick;

@MTFActivityFeature(layout = R.layout.activity_test_list)
public class TestActivity extends MTFBaseActivity {

    @Override
    public void initialize(Bundle savedInstanceState) {

    }

    @Override
    public void backPressed() {
        animFinish();
    }

    @OnClick(R.id.m_title_left_btn)
    public void leftClick(View v) {
        animFinish();
    }
}
