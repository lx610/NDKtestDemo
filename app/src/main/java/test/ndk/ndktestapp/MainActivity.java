package test.ndk.ndktestapp;

import android.view.View;

import test.ndk.basecommonmodule.base.BaseLinearActivity;
import test.ndk.ndktestmodule.NDkModuleMainActivity;

public class MainActivity extends BaseLinearActivity {

    @Override
    public void initView() {
        mLlRoot.addView(generateTextButton("NDKmoludel", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityByActName(NDkModuleMainActivity.class);
            }
        }));
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }
}
