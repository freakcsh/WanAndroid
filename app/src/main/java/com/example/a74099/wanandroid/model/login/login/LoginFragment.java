package com.example.a74099.wanandroid.model.login.login;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.a74099.wanandroid.R;
import com.example.a74099.wanandroid.base.BaseFragment;
import com.example.a74099.wanandroid.bean.LoginBean;
import com.example.a74099.wanandroid.util.ToastUtil;
import com.example.a74099.wanandroid.util.ToolUtils;

/**
 * Created by 74099 on 2018/8/29.
 */

public class LoginFragment extends BaseFragment<LoginPresenter> implements LoginContract.View, View.OnClickListener {
    private EditText edt_login_user, edt_login_pw;
    private TextView tv_login_commit, tv_forget_pw, tv_find_pw;

    @Override
    public void showError(String msg) {

    }

    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();
        return fragment;
    }

    @Override
    protected LoginPresenter createPresenter() {
        return new LoginPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_login;
    }

    @Override
    protected void initEventAndData(View view) {
        edt_login_user = view.findViewById(R.id.edt_login_user);
        edt_login_pw = view.findViewById(R.id.edt_login_pw);
        tv_login_commit = view.findViewById(R.id.tv_login_commit);
        tv_forget_pw = view.findViewById(R.id.tv_forget_pw);
        tv_find_pw = view.findViewById(R.id.tv_find_pw);
        tv_login_commit.setOnClickListener(this);
        tv_forget_pw.setOnClickListener(this);
        tv_find_pw.setOnClickListener(this);
    }

    private String getUserName() {
        return edt_login_user.getText().toString().trim();
    }

    private String getPassword() {
        return edt_login_pw.getText().toString().trim();
    }

    @Override
    protected void initLazyData() {

    }

    @Override
    protected void showLoading() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_find_pw:
                break;
            case R.id.tv_forget_pw:
                break;
            case R.id.tv_login_commit:
                if (ToolUtils.isNull(getUserName())) {
                    ToastUtil.showLong(getActivity(), "请输入账号");
                } else if (ToolUtils.isNull(getPassword())) {
                    ToastUtil.showShort(getActivity(), "请输入密码");
                } else {
                    mPresenter.doLogin(getUserName(),getPassword());
                }
                break;
            default:
                break;
        }
    }


    @Override
    public void doLoginSuccess(LoginBean loginBean) {
        ToolUtils.saveLoginInfo(getActivity(),loginBean.getUsername());
        getActivity().finish();
    }

    @Override
    public void doLoginError(String msg) {
        getActivity().finish();
    }
}
