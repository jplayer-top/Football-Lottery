package top.jplayer.bestwork.mvpb.presenter;

import top.jplayer.baseprolibrary.mvp.contract.BasePresenter;
import top.jplayer.bestwork.mvpb.AppServer;
import top.jplayer.bestwork.mvpb.model.CaiModel;
import top.jplayer.bestwork.mvpb.ui.cai.JczqActivity;

/**
 * Created by Obl on 2018/4/27.
 * top.jplayer.bestwork.mvpb.presenter
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class JczqPresenter extends BasePresenter<JczqActivity> {

    private final CaiModel mModel;

    public JczqPresenter(JczqActivity iView) {
        super(iView);
        mModel = new CaiModel(AppServer.class);
    }

    public void reqJcaqBean() {
        mModel.reqJczqBean().subscribe(jczqBean -> mIView.repJczqBean(jczqBean),
                throwable -> mIView.showError());
    }
}
