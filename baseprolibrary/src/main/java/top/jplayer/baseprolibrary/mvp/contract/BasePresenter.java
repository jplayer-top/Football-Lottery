package top.jplayer.baseprolibrary.mvp.contract;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by Obl on 2018/1/25.
 * com.modiwu.mah.mvp.constract
 */

public class BasePresenter<T extends IContract.IView> implements IContract.IPresenter {
    public T mIView;
    private CompositeDisposable mCompositeDisposable;


    public BasePresenter(T iView) {
        mCompositeDisposable = new CompositeDisposable();
        this.mIView = iView;
    }



    @Override
    public void detachView() {
        if (mIView != null) {
            mIView = null;
        }
        if (!mCompositeDisposable.isDisposed()) {
            mCompositeDisposable.clear();
        }
    }

    public void addSubscription(Disposable disposable) {
        mCompositeDisposable.add(disposable);
    }
}
