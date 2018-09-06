package com.example.a74099.wanandroid.model.myself.fingerprint;

import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a74099.wanandroid.R;
import com.example.a74099.wanandroid.app.App;
import com.example.a74099.wanandroid.base.SimpleActivity;
import com.example.a74099.wanandroid.model.myself.fingerprint.core.FingerprintCore;
import com.example.a74099.wanandroid.model.myself.fingerprint.util.FingerprintUtil;
import com.example.a74099.wanandroid.model.myself.fingerprint.util.KeyguardLockScreenManager;
import com.example.a74099.wanandroid.model.myself.lock.custom.WholePatternCheckingActivity;
import com.example.a74099.wanandroid.model.myself.lock.custom.util.PatternHelper;
import com.example.a74099.wanandroid.util.ToastUtil;
import com.example.a74099.wanandroid.util.ToolUtils;

/**
 * Created by 74099 on 2018/6/28.
 */

/***
 * 检测是否开启指纹解锁
 */

public class FingerPrintCheckActivity extends SimpleActivity implements View.OnClickListener {

    private ImageView img_fingerprint;
    private TextView tv_pw, tv_cancel;

    private FingerprintCore mFingerprintCore;

    private KeyguardLockScreenManager mKeyguardLockScreenManager;

    private Toast mToast;

    private Handler mHandler = new Handler(Looper.getMainLooper());
    private String mPw;
    private final long BACKPRESS_TIME = 2000;
    private long lastTimeMillis;
    private RelativeLayout back;

    @Override
    protected void onResume() {
        super.onResume();
        initFingerprintCore();
        startFingerprint();
        mPw = new PatternHelper().getFromStorage();
    }

    private void initFingerprintCore() {
        mFingerprintCore = new FingerprintCore(this);
        mFingerprintCore.setFingerprintManager(mresultlistener);
        mKeyguardLockScreenManager = new KeyguardLockScreenManager(this);
    }

    /**
     * 开始识别指纹
     */
    private void startFingerprint() {
        if (mFingerprintCore.isSupport()) {
            if (!mFingerprintCore.isHardwareDetected()) {
                toastTipMsg(R.string.fingerprint_recognition_not_enrolled);
                FingerprintUtil.openFingerPrintSettingPage(this);
                return;
            }
            toastTipMsg(R.string.fingerprint_recognition_tip);

            img_fingerprint.setBackgroundResource(R.mipmap.fingerprint);
            if (!mFingerprintCore.isAuthenticating()) {
                toastTipMsg(R.string.fingerprint_recognition_authenticating);
            } else {
                mFingerprintCore.startAuthenticate();
            }
        } else {
            toastTipMsg(R.string.fingerprint_recognition_not_support);
        }
    }

    private void toastTipMsg(int messageId) {
        if (mToast == null) {
            mToast = Toast.makeText(this, messageId, Toast.LENGTH_SHORT);
        }
        mToast.setText(messageId);
        mToast.cancel();
        mHandler.removeCallbacks(mShowToastRunnable);
        mHandler.postDelayed(mShowToastRunnable, 0);
    }

    private Runnable mShowToastRunnable = new Runnable() {
        @Override
        public void run() {
            mToast.show();
        }
    };


    private FingerprintCore.IFingerprintResultListener mresultlistener = new FingerprintCore.IFingerprintResultListener() {
        @Override
        public void onAuthenticateSuccess() {
            toastTipMsg(R.string.fingerprint_recognition_success);
            resetGuideViewState();
            finish();
        }

        @Override
        public void onAuthenticateFailed(int helpId) {
            toastTipMsg(R.string.fingerprint_recognition_failed);
        }

        @Override
        public void onAuthenticateError(int errMsgId) {
            resetGuideViewState();
            toastTipMsg(R.string.fingerprint_recognition_error);
        }

        @Override
        public void onStartAuthenticateResult(boolean isSuccess) {

        }
    };

    private void resetGuideViewState() {
        img_fingerprint.setBackgroundResource(R.mipmap.deblocking);
    }

    private void checkLocker() {
        if (!ToolUtils.isNull(mPw)) {
            WholePatternCheckingActivity.startAction(this);
            finish();
        } else {
            ToastUtil.showShort(this, "图形密码暂未开启");
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_cancel:
                App.getInstance().finishAll();
                break;
            case R.id.tv_pw:
                checkLocker();
                break;
            case R.id.back:
                App.getInstance().finishAll();
                break;
            default:
                break;

        }
    }

    @Override
    protected void onDestroy() {
        if (mFingerprintCore != null) {
            mFingerprintCore.onDestroy();
            mFingerprintCore = null;
        }
        if (mKeyguardLockScreenManager != null) {
            mKeyguardLockScreenManager.onDestroy();
            mKeyguardLockScreenManager = null;
        }
        mresultlistener = null;
        mShowToastRunnable = null;
        mToast = null;
        super.onDestroy();
    }

    @Override
    protected int getLayout() {
        return R.layout.lock;
    }

    @Override
    protected void initEventAndData() {
        setTitleTx("应用锁");
        img_fingerprint = findViewById(R.id.img_fingerprint);
        tv_pw = findViewById(R.id.tv_pw);
        tv_cancel = findViewById(R.id.tv_cancel);
        back = findViewById(R.id.back);

        tv_pw.setOnClickListener(this);
        tv_cancel.setOnClickListener(this);
        back.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() - lastTimeMillis <= BACKPRESS_TIME) {
            App.getInstance().finishAll();
            super.onBackPressed();
        } else {
            lastTimeMillis = System.currentTimeMillis();
            ToastUtil.showShort(this, getString(R.string.backpress_again_finish));
        }
    }
}
