package test.ndk.basecommonmodule.base;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import test.ndk.basecommonmodule.BuildConfig;
import test.ndk.basecommonmodule.R;

/**
 * Created by Administrator on 2018/6/12.
 */

abstract public class BaseActivity extends AppCompatActivity{

    private Unbinder mUnbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());
        //绑定到butterknife
        mUnbinder = ButterKnife.bind(this);
        bindView();//让BaseLinearLayout先绑定布局对象，否则BaseLinear的实现类会先触发initView，导致空指针
        initView();
        initData();
        initListener();
        ActivityController.addActivity(this);

        if (BuildConfig.DEBUG){
            StrictMode.VmPolicy policy =new StrictMode.VmPolicy.Builder()
                    .detectAll()
                    .penaltyLog()
                    .build();
            StrictMode.setVmPolicy(policy);
        }
    }

    public abstract int getLayoutResId();
    public void bindView(){}
    public abstract void initView();
    public abstract void initData();
    public abstract void initListener();


    public void startActivityByActName(Class activityClass){
        startActivity(new Intent(BaseActivity.this,activityClass));
    }


    public View generateTextButton(String buttonName, View.OnClickListener oncliclickLiener) {
        TextView textBt=new TextView(this);
        LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(10,10,10,10);
        textBt.setLayoutParams(params);
        textBt.setText(buttonName);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            textBt.setBackground(getDrawable(R.color.pink));
        }
        textBt.setPadding(10,10,10,10);
        textBt.setOnClickListener(oncliclickLiener);
        return textBt;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }
}
