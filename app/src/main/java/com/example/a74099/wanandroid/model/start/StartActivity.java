package com.example.a74099.wanandroid.model.start;

import com.example.a74099.wanandroid.R;
import com.example.a74099.wanandroid.base.SimpleActivity;
import com.example.a74099.wanandroid.model.MainActivity;
import com.example.a74099.wanandroid.model.myself.fingerprint.FingerPrintCheckActivity;
import com.example.a74099.wanandroid.util.ToolUtils;

public class StartActivity extends SimpleActivity {
    @Override
    protected int getLayout() {
        return R.layout.act_start;
    }

    @Override
    protected void initEventAndData() {
        if (!ToolUtils.isNull(ToolUtils.getFingerState(this))) {
            FingerPrintCheckActivity.startAction(this);
        }else {
            MainActivity.startAction(this);
        }
    }
}
