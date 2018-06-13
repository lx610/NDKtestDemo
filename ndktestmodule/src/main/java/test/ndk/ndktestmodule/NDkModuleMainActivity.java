package test.ndk.ndktestmodule;

import android.view.View;

import test.ndk.basecommonmodule.base.BaseLinearActivity;
import test.ndk.ndktestmodule.http.HttpActivity;

/**
 * Created by Administrator on 2018/6/12.
 */

public class NDkModuleMainActivity extends BaseLinearActivity {
    @Override
    public void initView() {
        mLlRoot.addView(generateTextButton("ndk",null));
        mLlRoot.addView(generateTextButton("HttpActivtiy", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityByActName(HttpActivity.class);
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
