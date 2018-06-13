package test.ndk.ndktestmodule.http;

import android.view.View;

import test.ndk.basecommonmodule.base.BaseLinearActivity;
import test.ndk.ndktestmodule.http.Okhttp.OkhttpTestActivty;

/**
 * Created by Administrator on 2018/6/13.
 */

public class HttpActivity extends BaseLinearActivity {
    @Override
    public void initView() {
        mLlRoot.addView(generateTextButton("OkttpTest", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityByActName(OkhttpTestActivty.class);
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
