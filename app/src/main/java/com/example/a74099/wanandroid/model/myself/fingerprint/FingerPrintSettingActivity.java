package com.example.a74099.wanandroid.model.myself.fingerprint;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a74099.wanandroid.R;
import com.example.a74099.wanandroid.base.SimpleActivity;
import com.example.a74099.wanandroid.model.myself.fingerprint.core.FingerprintCore;
import com.example.a74099.wanandroid.model.myself.fingerprint.util.FingerprintUtil;
import com.example.a74099.wanandroid.model.myself.fingerprint.util.KeyguardLockScreenManager;

/**
 * Created by 74099 on 2018/8/25.
 */

public class FingerPrintSettingActivity extends SimpleActivity implements View.OnClickListener{
    private FingerprintCore mFingerprintCore;

    private KeyguardLockScreenManager mKeyguardLockScreenManager;

    private Toast mToast;

    private Handler mHandler = new Handler(Looper.getMainLooper());

    private ImageView mFingerGuideImg;
    private TextView mFingerGuideTxt;

    /**
     * 开始识别指纹
     */
    private Button fingerprint_recognition_start;
    /**
     * 取消指纹识别
     */
    private Button fingerprint_recognition_cancel;
    /**
     * 测试密码解锁屏幕
     */
    private Button fingerprint_recognition_sys_unlock;
    /**
     * 进入系统设置页面
     */
    private Button fingerprint_recognition_sys_setting;

public static void startAction(Context context) {
    Intent intent = new Intent(context, FingerPrintSettingActivity.class);
    context.startActivity(intent);
}
    private void initFingerprintCore() {
        if (mFingerprintCore == null) {
            mFingerprintCore = new FingerprintCore(this);
            mFingerprintCore.setFingerprintManager(mResultListener);
        }else {
            mFingerprintCore.setFingerprintManager(mResultListener);
        }
        mKeyguardLockScreenManager = new KeyguardLockScreenManager(this);
    }

    private FingerprintCore.IFingerprintResultListener mResultListener = new FingerprintCore.IFingerprintResultListener() {
        @Override
        public void onAuthenticateSuccess() {
            Log.e("freak", "onAuthenticateSuccess() ");
            toastTipMsg(R.string.fingerprint_recognition_success);
            resetGuideViewState();
        }

        @Override
        public void onAuthenticateFailed(int helpId) {
            toastTipMsg(R.string.fingerprint_recognition_failed);
            mFingerGuideTxt.setText(R.string.fingerprint_recognition_failed);
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

    @Override
    protected void onResume() {
        super.onResume();

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


    private void resetGuideViewState() {
        mFingerGuideTxt.setText(R.string.fingerprint_recognition_guide_tip);
        mFingerGuideImg.setBackgroundResource(R.mipmap.fingerprint_normal);
    }

    private void initViewListeners() {
        fingerprint_recognition_start.setOnClickListener(this);
        fingerprint_recognition_cancel.setOnClickListener(this);
        fingerprint_recognition_sys_unlock.setOnClickListener(this);
        fingerprint_recognition_sys_setting.setOnClickListener(this);
    }

    /**
     * 初始化控件
     */
    private void initViews() {
        mFingerGuideImg = (ImageView) findViewById(R.id.fingerprint_guide);
        mFingerGuideTxt = (TextView) findViewById(R.id.fingerprint_guide_tip);

        fingerprint_recognition_start = findViewById(R.id.fingerprint_recognition_start);
        fingerprint_recognition_cancel = findViewById(R.id.fingerprint_recognition_cancel);
        fingerprint_recognition_sys_unlock = findViewById(R.id.fingerprint_recognition_sys_unlock);
        fingerprint_recognition_sys_setting = findViewById(R.id.fingerprint_recognition_sys_setting);
        mFingerprintCore = new FingerprintCore(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            /**
             * 开始识别指纹
             */
            case R.id.fingerprint_recognition_start:
                startFingerprintRecognition();
                break;
            /**
             * 取消指纹识别
             */
            case R.id.fingerprint_recognition_cancel:
                cancelFingerprintRecognition();
                break;
            /**
             * 测试密码解锁屏幕
             */
            case R.id.fingerprint_recognition_sys_unlock:
                startFingerprintRecognitionUnlockScreen();
                break;
            /**
             * 进入系统设置页面
             */
            case R.id.fingerprint_recognition_sys_setting:
                enterSysFingerprintSettingPage();
                break;
            default:
                break;
        }
    }

    /**
     * 进入系统设置页面
     */
    private void enterSysFingerprintSettingPage() {
        FingerprintUtil.openFingerPrintSettingPage(this);
    }

    /**
     * 测试密码解锁屏幕
     */
    private void startFingerprintRecognitionUnlockScreen() {
        if (mKeyguardLockScreenManager == null) {
            return;
        }
        if (!mKeyguardLockScreenManager.isOpenLockScreenPwd()) {
            toastTipMsg(R.string.fingerprint_not_set_unlock_screen_pws);
            FingerprintUtil.openFingerPrintSettingPage(this);
            return;
        }
        mKeyguardLockScreenManager.showAuthenticationScreen(this);
    }

    /**
     * 取消指纹识别
     */
    private void cancelFingerprintRecognition() {
        if (mFingerprintCore.isAuthenticating()) {
            mFingerprintCore.cancelAuthenticate();
            resetGuideViewState();
        }
    }

    /**
     * 开始识别指纹
     */
    private void startFingerprintRecognition() {

        if (mFingerprintCore.isSupport()) {
            if (!mFingerprintCore.isHardwareDetected()) {
                toastTipMsg(R.string.fingerprint_recognition_not_enrolled);
                FingerprintUtil.openFingerPrintSettingPage(this);
                return;
            }
            toastTipMsg(R.string.fingerprint_recognition_tip);
            mFingerGuideTxt.setText(R.string.fingerprint_recognition_tip);
            mFingerGuideImg.setBackgroundResource(R.mipmap.fingerprint_guide);
            if (mFingerprintCore.isAuthenticating()) {
                toastTipMsg(R.string.fingerprint_recognition_authenticating);
            } else {
                mFingerprintCore.startAuthenticate();
            }
        } else {
            toastTipMsg(R.string.fingerprint_recognition_not_support);
            mFingerGuideTxt.setText(R.string.fingerprint_recognition_tip);
        }
    }
//
//    public void IntentFingerprint() {
//        if (mFingerprintCore.isSupport()) {
//            if (!mFingerprintCore.hasEnrolledFingerprints()) {
//                initFingerprintCore();
//            } else {
////                Intent intent = new Intent(this, LockOfApplicationActivity.class);
//                startActivity(intent);
//            }
//
//        }
//    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == KeyguardLockScreenManager.REQUEST_CODE_CONFIRM_DEVICE_CREDENTIALS) {
            // 测试完成后，继续使用密码
            if (resultCode == RESULT_OK) {
                toastTipMsg(R.string.sys_pwd_recognition_success);
            } else {
                toastTipMsg(R.string.sys_pwd_recognition_failed);
            }
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
        mResultListener = null;
        mShowToastRunnable = null;
        mToast = null;
        super.onDestroy();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_finger_print_setting;
    }

    @Override
    protected void initEventAndData() {
        initViews();
        initViewListeners();
        initFingerprintCore();
//        IntentFingerprint();
    }
}
