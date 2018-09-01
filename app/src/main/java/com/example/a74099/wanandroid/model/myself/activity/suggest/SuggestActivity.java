package com.example.a74099.wanandroid.model.myself.activity.suggest;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.a74099.wanandroid.R;
import com.example.a74099.wanandroid.base.SimpleActivity;
import com.example.a74099.wanandroid.util.ToastUtil;
import com.example.a74099.wanandroid.util.ToolUtils;

/**
 * Created by 74099 on 2018/9/1.
 */

public class SuggestActivity extends SimpleActivity implements View.OnClickListener {
    private EditText edt_feed_back_problem;
    private TextView tv_suggest_commit;
    public static void startAction(Context context) {
        Intent intent = new Intent(context, SuggestActivity.class);
        context.startActivity(intent);
    }
    @Override
    protected int getLayout() {
        return R.layout.act_suggest;
    }

    @Override
    protected void initEventAndData() {
        setBackPress();
        setTitleTx("意见反馈");
        edt_feed_back_problem = findViewById(R.id.edt_feed_back_problem);
        tv_suggest_commit = findViewById(R.id.tv_suggest_commit);
        tv_suggest_commit.setOnClickListener(this);
    }

    private String getSuggest() {
        return edt_feed_back_problem.getText().toString().trim();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_suggest_commit:
                if (ToolUtils.isNull(getSuggest())) {
                    ToastUtil.showShort(this, "请输入反馈意见");
                } else {
//                    ShareUtil.sendEmail(this, "玩android意见反馈" + getSuggest());
                    Intent i = new Intent(Intent.ACTION_SEND);
                    // i.setType("text/plain"); //模拟器请使用这行
                    i.setType("message/rfc822"); // 真机上使用这行
                    i.putExtra(Intent.EXTRA_EMAIL,
                            new String[] { "740997937@qq.com" });
                    i.putExtra(Intent.EXTRA_SUBJECT, "您的建议");
                    i.putExtra(Intent.EXTRA_TEXT, "我们很希望能得到您的建议！！！");
                    startActivity(Intent.createChooser(i,
                            "Select email application."));
                }
                break;
            default:
                break;
        }
    }
}
