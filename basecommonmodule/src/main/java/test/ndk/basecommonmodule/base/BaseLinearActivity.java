package test.ndk.basecommonmodule.base;

import android.os.Bundle;
import android.widget.LinearLayout;

import test.ndk.basecommonmodule.R;

/**
 * Created by Administrator on 2018/6/12.
 */

public abstract class BaseLinearActivity extends BaseActivity {
    private static final String TAG = "BaseLinearActivity";
    public LinearLayout mLlRoot;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_only_linearlayout;
    }

    @Override
    public void bindView() {
        super.bindView();
        mLlRoot = (LinearLayout) findViewById(R.id.ll_root);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
}
