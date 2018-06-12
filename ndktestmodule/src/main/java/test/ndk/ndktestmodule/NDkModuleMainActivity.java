package test.ndk.ndktestmodule;

import test.ndk.basecommonmodule.base.BaseLinearActivity;

/**
 * Created by Administrator on 2018/6/12.
 */

public class NDkModuleMainActivity extends BaseLinearActivity {
    @Override
    public void initView() {
        mLlRoot.addView(generateTextButton("ndk",null));
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }
}
