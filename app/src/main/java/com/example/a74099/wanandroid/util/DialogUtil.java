package com.example.a74099.wanandroid.util;

import android.app.Activity;
import android.os.Bundle;

import com.example.a74099.wanandroid.bean.SystemClassifyBean;
import com.example.a74099.wanandroid.dialog.SystemSecondDialogFragment;

import java.io.Serializable;
import java.util.List;

public class DialogUtil {
    public static void showSystemSecond(Activity activity, String title, List<SystemClassifyBean.Children> mChildrenList, SystemSecondDialogFragment.OnTipsListener onTipsListener) {
        SystemSecondDialogFragment dialogFragment = new SystemSecondDialogFragment();
        Bundle args = new Bundle();
        args.putString("tipsTitle", title);
        args.putSerializable("mChildrenList", (Serializable) mChildrenList);
        dialogFragment.setArguments(args);
        dialogFragment.setCancelable(false);
        dialogFragment.setOnConfirmListener(onTipsListener);
        dialogFragment.show(activity.getFragmentManager(), "");
    }
}
