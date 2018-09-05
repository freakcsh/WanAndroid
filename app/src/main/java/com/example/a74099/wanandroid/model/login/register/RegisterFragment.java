package com.example.a74099.wanandroid.model.login.register;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.a74099.wanandroid.R;
import com.example.a74099.wanandroid.base.BaseFragment;
import com.example.a74099.wanandroid.model.login.LoginActivity;
import com.example.a74099.wanandroid.util.ToastUtil;
import com.example.a74099.wanandroid.util.ToolUtils;

/**
 * Created by 74099 on 2018/8/29.
 */

public class RegisterFragment extends BaseFragment<RegisterPresenter> implements RegisterContract.View, View.OnClickListener {
    private EditText edt_register_re_pw, edt_register_pw, edt_register_user;
    private TextView tv_register_commit;

    @Override
    public void showError(String msg) {

    }

    public static RegisterFragment newInstance() {
        RegisterFragment fragment = new RegisterFragment();
        return fragment;
    }

    @Override
    protected RegisterPresenter createPresenter() {
        return new RegisterPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_register;
    }

    @Override
    protected void initEventAndData(View view) {
        edt_register_re_pw = view.findViewById(R.id.edt_register_re_pw);
        edt_register_pw = view.findViewById(R.id.edt_register_pw);
        edt_register_user = view.findViewById(R.id.edt_register_user);
        tv_register_commit = view.findViewById(R.id.tv_register_commit);
        tv_register_commit.setOnClickListener(this);
    }

    @Override
    protected void initLazyData() {

    }

    private String getRegisterRePw() {
        return edt_register_re_pw.getText().toString().trim();
    }

    private String getRegisterPw() {
        return edt_register_pw.getText().toString().trim();
    }

    private String getRegisterUserName() {
        return edt_register_user.getText().toString().trim();
    }

    @Override
    protected void showLoading() {

    }

    @Override
    public void doRegisterSuccess() {
        ToastUtil.showShort(getActivity(), getString(R.string.RegisterFragment_success));
        LoginActivity loginAct = (LoginActivity) getActivity();
        loginAct.doIntent();
        edt_register_re_pw.setText("");
        edt_register_pw.setText("");
        edt_register_user.setText("");
    }

    @Override
    public void doRegisterError(String msg) {
        ToastUtil.showShort(getActivity(), msg);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_register_commit:
                if (ToolUtils.isNull(getRegisterUserName())) {
                    ToastUtil.showShort(getActivity(), getString(R.string.login_user_hint));
                } else if (ToolUtils.isNull(getRegisterPw())) {
                    ToastUtil.showShort(getActivity(), "请输入密码");
                } else if (ToolUtils.isNull(getRegisterRePw())) {
                    ToastUtil.showShort(getActivity(), "请再次输入密码");
                } else if (!getRegisterPw().equals(getRegisterRePw())) {
                    ToastUtil.showShort(getActivity(), "两次输入密码不一致");
                } else {
                    mPresenter.doRegister(getRegisterUserName(), getRegisterPw(), getRegisterRePw());
                }
                break;
            default:
                break;
        }
    }
}
