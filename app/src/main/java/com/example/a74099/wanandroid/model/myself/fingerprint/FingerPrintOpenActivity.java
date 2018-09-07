package com.example.a74099.wanandroid.model.myself.fingerprint;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a74099.wanandroid.R;
import com.example.a74099.wanandroid.app.App;
import com.example.a74099.wanandroid.base.SimpleActivity;
import com.example.a74099.wanandroid.model.myself.fingerprint.core.FingerprintCore;
import com.example.a74099.wanandroid.model.myself.fingerprint.util.FingerprintUtil;
import com.example.a74099.wanandroid.model.myself.fingerprint.util.KeyguardLockScreenManager;
import com.example.a74099.wanandroid.util.ToastUtil;
import com.example.a74099.wanandroid.util.ToolUtils;

/**
 * Created by 74099 on 2018/6/28.
 */

/***
 * 检测是否开启指纹解锁
 */

public class FingerPrintOpenActivity extends SimpleActivity implements View.OnClickListener {

    private ImageView img_fingerprint;
    private TextView tv_pw, tv_cancel;

    private FingerprintCore mOpenFingerprintCore;

    private KeyguardLockScreenManager mOpenKeyguardLockScreenManager;

    private Toast mToast;

    private Handler mHandler = new Handler(Looper.getMainLooper());
    private final long BACKPRESS_TIME = 2000;
    private long lastTimeMillis;
    private RelativeLayout back;
    private LinearLayout finger_open_off;
    private Switch switch_open_off_finger;

    public static void startAction(Context context) {
        Intent intent = new Intent(context, FingerPrintOpenActivity.class);
        context.startActivity(intent);
    }
    private void initFingerprintCore() {
        mOpenFingerprintCore = new FingerprintCore(this);
        mOpenFingerprintCore.setFingerprintManager(mresultOpenlistener);
        mOpenKeyguardLockScreenManager = new KeyguardLockScreenManager(this);
    }

    /**
     * 开始识别指纹
     */
    private void startFingerprint() {
        if (mOpenFingerprintCore.isSupport()) {
            //判断是否有指纹硬件支持
            if (mOpenFingerprintCore.isHardwareDetected()) {
                //判断是否有录入指纹
                if (!mOpenFingerprintCore.hasEnrolledFingerprints()){
                    toastTipMsg(R.string.fingerprint_recognition_not_enrolled);
                    FingerprintUtil.openFingerPrintSettingPage(this);
                    finger_open_off.setVisibility(View.GONE);
                    return;
                }else {
                    finger_open_off.setVisibility(View.VISIBLE);
                }

            }else {
                finger_open_off.setVisibility(View.GONE);
            }
//            if (!ToolUtils.isNull(ToolUtils.getFingerState(this))){
//                toastTipMsg(R.string.fingerprint_recognition_tip);
//            }

            img_fingerprint.setBackgroundResource(R.mipmap.fingerprint);
            if (!mOpenFingerprintCore.isAuthenticating()) {
//                toastTipMsg(R.string.fingerprint_recognition_authenticating);
            } else {
                //开始验证指纹
                mOpenFingerprintCore.startAuthenticate();
            }
        } else {
            toastTipMsg(R.string.fingerprint_recognition_not_support);
            finger_open_off.setVisibility(View.GONE);
            switch_open_off_finger.setChecked(false);
        }
    }

    private void checkFingerprint() {
        if (mOpenFingerprintCore.isSupport()) {
            //判断是否有指纹硬件支持
            if (mOpenFingerprintCore.isHardwareDetected()) {
                //判断是否有录入指纹
                if (!mOpenFingerprintCore.hasEnrolledFingerprints()){
                    toastTipMsg(R.string.fingerprint_recognition_not_enrolled);
                    FingerprintUtil.openFingerPrintSettingPage(this);
                    finger_open_off.setVisibility(View.GONE);
                    return ;
                }else {
                    finger_open_off.setVisibility(View.VISIBLE);
                    img_fingerprint.setBackgroundResource(R.mipmap.fingerprint);

                }
            }else {
                finger_open_off.setVisibility(View.GONE);

            }

        } else {
            toastTipMsg(R.string.fingerprint_recognition_not_support);
            finger_open_off.setVisibility(View.GONE);
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


    private FingerprintCore.IFingerprintResultListener mresultOpenlistener = new FingerprintCore.IFingerprintResultListener() {
        @Override
        public void onAuthenticateSuccess() {

            resetGuideViewState();
            if (ToolUtils.isNull(ToolUtils.getFingerState(FingerPrintOpenActivity.this))){
                ToolUtils.saveFingerState(FingerPrintOpenActivity.this,"open");
                toastTipMsg(R.string.fingerprint_recognition_success1);
            }else {
                ToolUtils.saveFingerState(FingerPrintOpenActivity.this,"");
                toastTipMsg(R.string.fingerprint_recognition_success2);
            }
            finish();
        }

        @Override
        public void onAuthenticateFailed(int helpId) {
            toastTipMsg(R.string.fingerprint_recognition_failed);
        }

        @Override
        public void onAuthenticateError(int errMsgId) {
//            resetGuideViewState();
            toastTipMsg(R.string.fingerprint_recognition_error);
        }

        @Override
        public void onStartAuthenticateResult(boolean isSuccess) {

        }
    };

    private void resetGuideViewState() {
        img_fingerprint.setBackgroundResource(R.mipmap.deblocking);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                App.getInstance().finishAll();
                break;
            default:
                break;

        }
    }

    @Override
    protected void onDestroy() {
        if (mOpenFingerprintCore != null) {
            mOpenFingerprintCore.onDestroy();
            mOpenFingerprintCore = null;
        }
        if (mOpenKeyguardLockScreenManager != null) {
            mOpenKeyguardLockScreenManager.onDestroy();
            mOpenKeyguardLockScreenManager = null;
        }
        mresultOpenlistener = null;
        mShowToastRunnable = null;
        mToast = null;
        super.onDestroy();
    }

    @Override
    protected int getLayout() {
        return R.layout.lock_open;
    }

    @Override
    protected void initEventAndData() {
        setTitleTx("指纹解锁");
        img_fingerprint = findViewById(R.id.img_fingerprint);
        tv_pw = findViewById(R.id.tv_pw);
        tv_cancel = findViewById(R.id.tv_cancel);
        back = findViewById(R.id.back);
        finger_open_off = findViewById(R.id.finger_open_off);
        switch_open_off_finger = findViewById(R.id.switch_open_off_finger);

        tv_pw.setOnClickListener(this);
        tv_cancel.setOnClickListener(this);
        back.setOnClickListener(this);
        initFingerprintCore();
        startFingerprint();

        if (ToolUtils.isNull(ToolUtils.getFingerState(this))){
            switch_open_off_finger.setChecked(false);
            finger_open_off.setVisibility(View.GONE);
        }else {
            switch_open_off_finger.setChecked(true);
            finger_open_off.setVisibility(View.VISIBLE);
        }
        switch_open_off_finger.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    if (ToolUtils.isNull(ToolUtils.getFingerState(FingerPrintOpenActivity.this))){
                        checkFingerprint();
                    }else {
                        switch_open_off_finger.setChecked(false);
                        ToastUtil.showShort(FingerPrintOpenActivity.this,"请验证指纹关闭指纹解锁");
                    }
                } else {
                    finger_open_off.setVisibility(View.VISIBLE);
                    ToastUtil.showShort(FingerPrintOpenActivity.this,"请验证指纹关闭指纹解锁");
                }
            }
        });
    }

}
