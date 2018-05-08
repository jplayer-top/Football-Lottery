package top.jplayer.baseprolibrary.mvp.contract;

/**
 * Created by Obl on 2018/1/25.
 * com.modiwu.mah.mvp.constract
 */

public interface IContract {
    interface IView {

        void showError();

        void showLoading();

        void showEmpty();
    }

    interface IPresenter {

        void detachView();
    }
}
