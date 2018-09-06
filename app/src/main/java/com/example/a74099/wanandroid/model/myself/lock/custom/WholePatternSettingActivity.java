package com.example.a74099.wanandroid.model.myself.lock.custom;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.example.a74099.wanandroid.R;
import com.example.a74099.wanandroid.base.SimpleActivity;
import com.example.a74099.wanandroid.model.myself.lock.core.IHitCellView;
import com.example.a74099.wanandroid.model.myself.lock.core.OnPatternChangeListener;
import com.example.a74099.wanandroid.model.myself.lock.core.PatternIndicatorView;
import com.example.a74099.wanandroid.model.myself.lock.core.PatternLockerView;
import com.example.a74099.wanandroid.model.myself.lock.custom.util.PatternHelper;
import com.example.a74099.wanandroid.util.ToolUtils;

import java.util.List;

/**
 * 设置图形密码
 */
public class WholePatternSettingActivity extends SimpleActivity {

    private PatternLockerView patternLockerView;
    private PatternIndicatorView patternIndicatorView;
    private TextView textMsg;
    private PatternHelper patternHelper;
    private LinearLayout ll_is_lock_open;
    private Switch switch_open_off;
    private String mPw;

    public static void startAction(Context context) {
        Intent intent = new Intent(context, WholePatternSettingActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected int getLayout() {
        return R.layout.activity_whole_pattern_setting;
    }

    @Override
    protected void initEventAndData() {
        setBackPress();
        setTitleTx(getString(R.string.title_pattern_setting));
        mPw = new PatternHelper().getFromStorage();
        ll_is_lock_open = findViewById(R.id.ll_is_lock_open);
        switch_open_off = findViewById(R.id.switch_open_off);
        this.patternIndicatorView = findViewById(R.id.pattern_indicator_view);
        this.patternLockerView = findViewById(R.id.pattern_lock_view);
        this.textMsg = findViewById(R.id.text_msg);


        final IHitCellView hitCellView = new RippleLockerHitCellView()
                .setHitColor(this.patternLockerView.getHitColor())
                .setErrorColor(this.patternLockerView.getErrorColor());

        this.patternLockerView.setHitCellView(hitCellView)
                .build();

        this.patternLockerView.setOnPatternChangedListener(new OnPatternChangeListener() {
            @Override
            public void onStart(PatternLockerView view) {
            }

            @Override
            public void onChange(PatternLockerView view, List<Integer> hitList) {
            }

            @Override
            public void onComplete(PatternLockerView view, List<Integer> hitList) {
                boolean isOk = isPatternOk(hitList);
                view.updateStatus(!isOk);
                patternIndicatorView.updateState(hitList, !isOk);
                updateMsg();
            }

            @Override
            public void onClear(PatternLockerView view) {
                finishIfNeeded();
            }
        });

        this.textMsg.setText("设置解锁图案");
        this.patternHelper = new PatternHelper();
        if (ToolUtils.isNull(mPw)){
            switch_open_off.setChecked(false);
            ll_is_lock_open.setVisibility(View.GONE);
        }else {
            switch_open_off.setChecked(true);
            ll_is_lock_open.setVisibility(View.VISIBLE);
        }
        switch_open_off.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    ll_is_lock_open.setVisibility(View.VISIBLE);
                }else {
                    ll_is_lock_open.setVisibility(View.GONE);
                }
            }
        });
    }

    private boolean isPatternOk(List<Integer> hitList) {
        this.patternHelper.validateForSetting(hitList);
        return this.patternHelper.isOk();
    }

    private void updateMsg() {
        this.textMsg.setText(this.patternHelper.getMessage());
        this.textMsg.setTextColor(this.patternHelper.isOk() ?
                getResources().getColor(R.color.colorPrimaryDark) :
                getResources().getColor(R.color.color_red));
    }

    private void finishIfNeeded() {
        if (this.patternHelper.isFinish()) {
            finish();
        }
    }
}
