package top.jplayer.baseprolibrary.widgets.dialog;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;

import top.jplayer.baseprolibrary.R;
import top.jplayer.baseprolibrary.mvp.model.bean.CartBean;
import top.jplayer.baseprolibrary.ui.adapter.AdapterDialogCart;

/**
 * Created by Obl on 2018/3/15.
 * top.jplayer.baseprolibrary.widgets.dialog
 */

public class DialogFragmentOrder extends BaseCustomDialogFragment {


    private RecyclerView mRecyclerView;

    @Override
    public int setAnim() {
        return R.style.AnimBottom;
    }

    @Override
    public int setGravity() {
        return Gravity.BOTTOM;
    }

    @Override
    protected void initView(View view) {
        view.findViewById(R.id.ivCancel).setOnClickListener(v -> dismiss());
        mRecyclerView = view.findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
    }

    @Override
    public void initData(View view) {
        Bundle bundle = getArguments();
        CartBean cartBean = bundle.getParcelable("cart");
        if (cartBean != null) {
            AdapterDialogCart adapter = new AdapterDialogCart(R.layout.adapter_sample_02, cartBean.attrs);
            mRecyclerView.setAdapter(adapter);
        }
    }

    @Override
    public int initLayout() {
        return R.layout.dialog_order;
    }
}
