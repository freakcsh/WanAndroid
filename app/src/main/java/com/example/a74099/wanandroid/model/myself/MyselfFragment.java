package com.example.a74099.wanandroid.model.myself;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.sdk.android.feedback.impl.FeedbackAPI;
import com.example.a74099.wanandroid.R;
import com.example.a74099.wanandroid.app.Constants;
import com.example.a74099.wanandroid.base.BaseFragment;
import com.example.a74099.wanandroid.model.login.LoginActivity;
import com.example.a74099.wanandroid.model.myself.activity.ShapeActivity;
import com.example.a74099.wanandroid.model.myself.activity.collect.CollectActivity;
import com.example.a74099.wanandroid.model.myself.fingerprint.FingerPrintSettingActivity;
import com.example.a74099.wanandroid.model.myself.lock.custom.WholePatternAlterActivity;
import com.example.a74099.wanandroid.model.myself.lock.custom.WholePatternSettingActivity;
import com.example.a74099.wanandroid.model.myself.lock.custom.util.PatternHelper;
import com.example.a74099.wanandroid.util.PermissionUtils;
import com.example.a74099.wanandroid.util.ToastUtil;
import com.example.a74099.wanandroid.util.ToolUtils;
import com.example.a74099.wanandroid.util.picture.BitmapUtil;
import com.example.a74099.wanandroid.util.picture.GetPictureUtils;
import com.example.a74099.wanandroid.util.picture.PopupGetPictureView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 我的
 */
public class MyselfFragment extends BaseFragment<MyselfPresenter> implements MyselfContract.View, View.OnClickListener {
    private AppBarLayout appBarLayout;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private LinearLayout headLayout;
    private Toolbar toolbar;
    private File userImgFile;
    private RelativeLayout rl_alter_photo, rl_gesture_pw, rl_fingerprint, rl_shape, rl_collect,rl_suggest;
    private CircleImageView img_user, tool_bar_img_user, img_login_photo;
    private String mPw;
    private TextView tv_pw_state, tv_login, tv_login_out, tv_version, tv_tool_bar_user_name;
    private String mLoginBean;

    @Override
    protected MyselfPresenter createPresenter() {
        return new MyselfPresenter();
    }

    @Override
    protected boolean needRegisterNetworkChangeObserver() {
        return false;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_myself;
    }

    @Override
    protected void initEventAndData(View view) {
        initView(view);
        setToolBarReplaceActionBar();
        setTitleToCollapsingToolbarLayout();

    }

    private void initView(View view) {
        collapsingToolbarLayout = view.findViewById(R.id.collapsing_toolbar_layout);
        appBarLayout = view.findViewById(R.id.app_bar);
        headLayout = view.findViewById(R.id.head_layout);
        toolbar = view.findViewById(R.id.toolbar);
        rl_alter_photo = view.findViewById(R.id.rl_alter_photo);
        img_user = view.findViewById(R.id.img_user);
        rl_gesture_pw = view.findViewById(R.id.rl_gesture_pw);
        rl_fingerprint = view.findViewById(R.id.rl_fingerprint);
        rl_shape = view.findViewById(R.id.rl_shape);
        tv_pw_state = view.findViewById(R.id.tv_pw_state);
        rl_collect = view.findViewById(R.id.rl_collect);
        rl_suggest = view.findViewById(R.id.rl_suggest);
        tv_login = view.findViewById(R.id.tv_login);
        tv_login_out = view.findViewById(R.id.tv_login_out);
        tv_version = view.findViewById(R.id.tv_version);
        tv_tool_bar_user_name = view.findViewById(R.id.tv_tool_bar_user_name);
        tool_bar_img_user = view.findViewById(R.id.tool_bar_img_user);
        img_login_photo = view.findViewById(R.id.img_login_photo);
        rl_alter_photo.setOnClickListener(this);
        rl_gesture_pw.setOnClickListener(this);
        rl_fingerprint.setOnClickListener(this);
        rl_shape.setOnClickListener(this);
        rl_collect.setOnClickListener(this);
        tv_login.setOnClickListener(this);
        tv_login_out.setOnClickListener(this);
        rl_suggest.setOnClickListener(this);
        mPw = new PatternHelper().getFromStorage();
        if (ToolUtils.isNull(mPw)) {
            tv_pw_state.setText("未开启");
        } else {
            tv_pw_state.setText("点击修改");
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        setLoginInfo();
        setVision();
    }

    private void setLoginInfo() {
        mLoginBean = ToolUtils.getLoginBean(getActivity());
        if (ToolUtils.isLogin(getActivity())) {
            if (!TextUtils.isEmpty(mLoginBean)) {
                tv_login.setText(mLoginBean);
                tv_tool_bar_user_name.setText(mLoginBean);
                if (ToolUtils.isNull(ToolUtils.getPhotoUrl(getActivity()))) {
                    tool_bar_img_user.setBackgroundResource(R.mipmap.mine_icon_user_photo);
                    img_login_photo.setBackgroundResource(R.mipmap.mine_icon_user_photo);
                    img_user.setBackgroundResource(R.mipmap.mine_icon_user_photo);
                } else {
                    String path = ToolUtils.getPhotoUrl(getActivity());
                    Uri uri = Uri.fromFile(new File(path));
                    tool_bar_img_user.setImageURI(uri);
                    img_login_photo.setImageURI(uri);
                    img_user.setImageURI(uri);
                }
            } else {
                tv_tool_bar_user_name.setText("请登录");
                tv_login.setText("请登录");
                tool_bar_img_user.setBackgroundResource(R.mipmap.mine_icon_user_photo);
                img_login_photo.setBackgroundResource(R.mipmap.mine_icon_user_photo);
                img_user.setBackgroundResource(R.mipmap.mine_icon_user_photo);
            }

        } else {
            tv_login.setText("请登录");
            tv_tool_bar_user_name.setText("请登录");
            tool_bar_img_user.setBackgroundResource(R.mipmap.mine_icon_user_photo);
            img_login_photo.setBackgroundResource(R.mipmap.mine_icon_user_photo);
            img_user.setBackgroundResource(R.mipmap.mine_icon_user_photo);
        }
    }

    /**
     * 用toolBar替换ActionBar
     */
    private void setToolBarReplaceActionBar() {
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        toolbar.setTitle("");
    }


    /**
     * 使用CollapsingToolbarLayout必须把title设置到CollapsingToolbarLayout上，
     * 设置到Toolbar上则不会显示
     */
    private void setTitleToCollapsingToolbarLayout() {
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (verticalOffset <= -headLayout.getHeight()) {
                    collapsingToolbarLayout.setTitle("请登录");
                    toolbar.setVisibility(View.VISIBLE);
                    if (!ToolUtils.isLogin(getActivity())) {
                        tv_login.setText(mLoginBean);
                        tv_tool_bar_user_name.setText(mLoginBean);
                        if (ToolUtils.isNull(ToolUtils.getPhotoUrl(getActivity()))) {
                            tool_bar_img_user.setBackgroundResource(R.mipmap.mine_icon_user_photo);
                            img_user.setBackgroundResource(R.mipmap.mine_icon_user_photo);
                            img_login_photo.setBackgroundResource(R.mipmap.mine_icon_user_photo);
                        } else {
                            String path = ToolUtils.getPhotoUrl(getActivity());
                            Uri uri = Uri.fromFile(new File(path));
                            tool_bar_img_user.setImageURI(uri);
                            img_login_photo.setImageURI(uri);
                            img_user.setImageURI(uri);
                        }

                    } else {
                        tv_login.setText("请登录");
                        tv_tool_bar_user_name.setText("请登录");
                        tool_bar_img_user.setBackgroundResource(R.mipmap.mine_icon_user_photo);
                        img_login_photo.setBackgroundResource(R.mipmap.mine_icon_user_photo);
                        img_user.setBackgroundResource(R.mipmap.mine_icon_user_photo);

                    }
                    //使用下面两个CollapsingToolbarLayout的方法设置展开透明->折叠时你想要的颜色
                    collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(android.R.color.transparent));
                    collapsingToolbarLayout.setCollapsedTitleTextColor(getResources().getColor(R.color.colorAccent));
                } else {
                    collapsingToolbarLayout.setTitle("");
                    toolbar.setVisibility(View.GONE);

                }
            }
        });
    }

    @Override
    protected void initLazyData() {

    }

    @Override
    protected void showLoading() {

    }

    @Override
    public void getMyselfSuccess() {

    }


    @Override
    public void showError(String msg) {

    }

    public void selectImgPop() {
        PopupGetPictureView popupGetPictureView = new PopupGetPictureView(getActivity(), new
                PopupGetPictureView.GetPicture() {
                    @Override
                    public void takePhoto(View v) {
                        if (PermissionUtils.checkTakePhotoPermission(getActivity())) {
                            userImgFile = GetPictureUtils.takePicture(getActivity(), Constants.GETPICTURE_TAKEPHOTO);
                        }
                    }

                    @Override
                    public void selectPhoto(View v) {
                        if (PermissionUtils.checkAlbumStroagePermission(getActivity())) {
                            GetPictureUtils.selectPhoto(getActivity(), Constants.GETPICTURE_SELECTPHOTO);
                        }
                    }
                });
        popupGetPictureView.showPop(rl_alter_photo);
    }

    /***
     * 以下为图片选择操作 onActivityResult、compressAndcommitImg、compressAndcommitImg
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 0) {
            return;
        }

        switch (requestCode) {
            //拍照
            case Constants.GETPICTURE_TAKEPHOTO:
                userImgFile = GetPictureUtils.cutPicture(getActivity(), userImgFile);
                break;
            //选择照片
            case Constants.GETPICTURE_SELECTPHOTO:
                userImgFile = GetPictureUtils.getPhotoFromIntent(data, getActivity());
                userImgFile = GetPictureUtils.cutPicture(getActivity(), userImgFile);
                break;
            //裁剪照片
            case Constants.CUT_PHOTO:
                if (resultCode == Activity.RESULT_OK) {
                    compressAndcommitImg(userImgFile);
                }
                break;
            default:
                break;
        }

    }

    public void compressAndcommitImg(File file) {
        List<File> list = new ArrayList<>();
        list.add(file);

        BitmapUtil.compressFiles(list, new BitmapUtil.CompressImageResponse() {
            @Override
            public void onSuccess(List<File> imgs) {
                File imgFile = imgs.get(0);
//                ToolUtils.saveScreemShots(getActivity(),false,imgFile);
                ToolUtils.savePhotoUrl(getActivity(), imgFile.getPath());
                setLoginInfo();
//                Log.e("freak", imgFile.getPath());
//                Uri uri = Uri.fromFile(imgFile);
//                img_user.setImageURI(uri);
            }

            @Override
            public void onDo() {
//                showLoading(view.getMContext());
            }

            @Override
            public void onFail() {

            }

            @Override
            public void onFinish() {

            }
        });
    }

    public void setVision() {
        try {
            PackageManager manager = getActivity().getPackageManager();
            PackageInfo info = null;
            info = manager.getPackageInfo(getActivity().getPackageName(), 0);
            tv_version.setText(info.versionName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_alter_photo:
                selectImgPop();
                break;
            case R.id.rl_gesture_pw:
                if (ToolUtils.isNull(mPw)) {
                    WholePatternSettingActivity.startAction(getActivity());
                } else {
                    WholePatternAlterActivity.startAction(getActivity());
                }
                break;
            case R.id.rl_fingerprint:
                FingerPrintSettingActivity.startAction(getActivity());
                break;
            case R.id.rl_shape:
                ShapeActivity.startAction(getActivity());
//                ToastUtil.showShort(getActivity(),"此功能正在开发中，敬请期待");
                break;
            case R.id.rl_collect:
                if (ToolUtils.isLogin(getActivity())) {
                    CollectActivity.startAction(getActivity());
                } else {
                    ToastUtil.showShort(getActivity(), "请先登录");
                }
                break;
            case R.id.tv_login:
                if (ToolUtils.isLogin(getActivity())) {
                    LoginActivity.startAction(getActivity());
                } else {
                    ToastUtil.showShort(getActivity(), "您已登录，请勿重复操作");
                }

                break;
            case R.id.tv_login_out:
                if (ToolUtils.isNull(mLoginBean)) {
                    ToastUtil.showShort(getActivity(), "请先登录");
                } else {
                    ToolUtils.logout(getActivity());
                    setLoginInfo();
                }
                break;
            case R.id.rl_suggest:
                FeedbackAPI.openFeedbackActivity();
                break;
            default:
                break;
        }
    }
}
