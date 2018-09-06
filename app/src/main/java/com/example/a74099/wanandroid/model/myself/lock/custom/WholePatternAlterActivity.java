package com.example.a74099.wanandroid.model.myself.lock.custom;

import android.content.Context;
import android.content.Intent;
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
import com.example.a74099.wanandroid.util.ToastUtil;
import com.example.a74099.wanandroid.util.ToolUtils;

import java.util.List;

/**
 * 修改图形密码
 */
public class WholePatternAlterActivity extends SimpleActivity {

    private PatternLockerView patternLockerView;
    private PatternIndicatorView patternIndicatorView;
    private TextView textMsg;
    private PatternHelper patternHelper;
    private boolean mIsError;
    private LinearLayout ll_is_lock_open;
    private Switch switch_open_off;
    private String mPw;

    public static void startAction(Context context) {
        Intent intent = new Intent(context, WholePatternAlterActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected int getLayout() {
        return R.layout.activity_whole_pattern_checking;
    }

    @Override
    protected void initEventAndData() {
        setBackPress();
        setTitleTx("修改手势密码");
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
                .setLinkedLineView(null)
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
                mIsError = !isPatternOk(hitList);
                view.updateStatus(mIsError);
                patternIndicatorView.updateState(hitList, mIsError);
                updateMsg();
            }

            @Override
            public void onClear(PatternLockerView view) {
                if (!mIsError) {
                    if (switch_open_off.isChecked()) {
                        WholePatternSettingActivity.startAction(WholePatternAlterActivity.this);
                    } else {
                        new PatternHelper().saveToStorage("");
                    }
                }
                finishIfNeeded();
            }
        });

        this.textMsg.setText("绘制解锁图案");
        this.patternHelper = new PatternHelper();
        if (ToolUtils.isNull(mPw)) {
            switch_open_off.setChecked(false);
        } else {
            switch_open_off.setChecked(true);
        }
        switch_open_off.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    if (!ToolUtils.isNull(mPw)){
                        switch_open_off.setChecked(false);
                        ToastUtil.showShort(WholePatternAlterActivity.this,"请验证手势密码");
                    }
                } else {
                    setTitleTx("关闭手势密码");
                    textMsg.setText("验证手势密码");
                }
            }
        });
    }

    /**
     * 判断手势密码是否一致
     *
     * @param hitList
     * @return
     */
    private boolean isPatternOk(List<Integer> hitList) {
        this.patternHelper.validateForChecking(hitList);
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
