package top.jplayer.baseprolibrary.mvp.presenter;

import android.text.TextUtils;

import java.util.Locale;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import top.jplayer.baseprolibrary.mvp.contract.BasePresenter;
import top.jplayer.baseprolibrary.mvp.contract.SampleContract;
import top.jplayer.baseprolibrary.mvp.model.SampleModel;
import top.jplayer.baseprolibrary.mvp.model.bean.LoginBean;
import top.jplayer.baseprolibrary.mvp.model.bean.SampleBean;
import top.jplayer.baseprolibrary.ui.SampleActivity;
import top.jplayer.baseprolibrary.utils.SharePreUtil;
import top.jplayer.baseprolibrary.utils.ToastUtils;

/**
 * Created by Administrator on 2018/1/27.
 * 抢红包功能
 */

public class SamplePresenter extends BasePresenter<SampleActivity> implements SampleContract.ISamplePresenter {
    private SampleModel sampleModel;

    public SamplePresenter(SampleActivity iView) {
        super(iView);
        sampleModel = new SampleModel();
    }

    @Override
    public void requestHBList() {
        Disposable disposable = sampleModel.requestHBList()
                .map(sampleBean -> {
                    if (TextUtils.equals("0000", sampleBean.errorCode)) {
                        if (sampleBean.data != null && sampleBean.data.list != null) {
                            return sampleBean;
                        } else return null;
                    }
                    return null;
                })
                .subscribe(sampleBean ->
                {

                    if (sampleBean.data.list.size() < 1) {
                        mIView.showEmpty();
                    } else {

                        mIView.setHBList(sampleBean);
                    }
                }, throwable -> mIView.showError());
        addSubscription(disposable);
    }

    @Override
    public void requestGrad(String id, String userNo) {
        Disposable subscribe = sampleModel.requestGrad(id, userNo).subscribe(gradBean ->
        {
            if (TextUtils.equals("0000", gradBean.errorCode)) {
                requestGet(id, userNo);
            } else if (!TextUtils.equals("0009", gradBean.errorCode)) {
                requestGrad(id, userNo);
            }
        });
        addSubscription(subscribe);
    }

    @Override
    public void requestGet(String id, String userNo) {
        Disposable disposable = sampleModel.requestGet(id, userNo).subscribe(gradBean -> {
            if (TextUtils.equals("0000", gradBean.errorCode)) {
                ToastUtils.init().showSuccessToast(mIView, "抢到了，关闭该界面吧");
            } else if (!TextUtils.equals("0009", gradBean.errorCode)) {
                requestGet(id, userNo);
            }
        }, throwable -> requestGet(id, userNo));
        addSubscription(disposable);
    }

    @Override
    public void addAccount(String phone, String password) {
        Disposable disposable = sampleModel.login(phone, password).subscribe(loginBean -> {
                    LoginBean.ResultBean result = loginBean.result;
                    if (result != null) {
                        ToastUtils.init().showSuccessToast(mIView, String.format(Locale.CHINA, "%s-登陆成功", result.name));
                        String userNo = (String) SharePreUtil.getData(mIView, "userNo", "");
                        String name = (String) SharePreUtil.getData(mIView, "name", "");
                        if (TextUtils.equals("", userNo)) {
                            userNo = result.userNo;
                        } else {
                            userNo = userNo + "," + result.userNo;
                        }
                        if (TextUtils.equals("", name)) {
                            name = result.name;
                        } else {
                            name = name + "," + result.name;
                        }
                        SharePreUtil.saveData(mIView, "userNo", userNo);
                        SharePreUtil.saveData(mIView, "name", name);
                        mIView.loginSuccess();
                    }
                }
                , throwable -> ToastUtils.init().showErrorToast(mIView, "登陆失败"));
        addSubscription(disposable);
    }
}
