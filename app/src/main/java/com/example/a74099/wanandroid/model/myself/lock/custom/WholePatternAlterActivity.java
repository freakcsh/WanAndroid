package com.example.a74099.wanandroid.model.myself.lock.custom;

import android.content.Context;
import android.content.Intent;
import android.widget.TextView;

import com.example.a74099.wanandroid.R;
import com.example.a74099.wanandroid.base.SimpleActivity;
import com.example.a74099.wanandroid.model.myself.lock.core.IHitCellView;
import com.example.a74099.wanandroid.model.myself.lock.core.OnPatternChangeListener;
import com.example.a74099.wanandroid.model.myself.lock.core.PatternIndicatorView;
import com.example.a74099.wanandroid.model.myself.lock.core.PatternLockerView;
import com.example.a74099.wanandroid.model.myself.lock.custom.util.PatternHelper;

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

    public static void startAction(Context context) {
        Intent intent = new Intent(context, WholePatternAlterActivity.class);
        context.startActivity(intent);
    }

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_whole_pattern_checking);
//
//        this.patternIndicatorView = findViewById(R.id.pattern_indicator_view);
//        this.patternLockerView = findViewById(R.id.pattern_lock_view);
//        this.textMsg = findViewById(R.id.text_msg);
//
//        final IHitCellView hitCellView = new RippleLockerHitCellView()
//                .setHitColor(this.patternLockerView.getHitColor())
//                .setErrorColor(this.patternLockerView.getErrorColor());
//
//        this.patternLockerView.setHitCellView(hitCellView)
//                .setLinkedLineView(null)
//                .build();
//
//        this.patternLockerView.setOnPatternChangedListener(new OnPatternChangeListener() {
//            @Override
//            public void onStart(PatternLockerView view) {
//            }
//
//            @Override
//            public void onChange(PatternLockerView view, List<Integer> hitList) {
//            }
//
//            @Override
//            public void onComplete(PatternLockerView view, List<Integer> hitList) {
//                mIsError = !isPatternOk(hitList);
//                view.updateStatus(mIsError);
//                patternIndicatorView.updateState(hitList, mIsError);
//                updateMsg();
//            }
//
//            @Override
//            public void onClear(PatternLockerView view) {
//                if (!mIsError) {
//                    WholePatternSettingActivity.startAction(WholePatternAlterActivity.this);
//                }
//                finishIfNeeded();
//            }
//        });
//
//        this.textMsg.setText("绘制解锁图案");
//        this.patternHelper = new PatternHelper();
//    }

    @Override
    protected int getLayout() {
        return R.layout.activity_whole_pattern_checking;
    }

    @Override
    protected void initEventAndData() {
        setBackPress();
        setTitleTx("修改手势密码");
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
                    WholePatternSettingActivity.startAction(WholePatternAlterActivity.this);
                }
                finishIfNeeded();
            }
        });

        this.textMsg.setText("绘制解锁图案");
        this.patternHelper = new PatternHelper();
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
