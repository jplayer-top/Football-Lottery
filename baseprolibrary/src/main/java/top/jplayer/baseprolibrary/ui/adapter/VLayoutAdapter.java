package top.jplayer.baseprolibrary.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;

/**
 * Created by Obl on 2018/1/22.
 * top.jplayer.baseprolibrary.ui.adapter
 */

public abstract class VLayoutAdapter<VH extends RecyclerView.ViewHolder> extends DelegateAdapter.Adapter<VH> {
    public LayoutHelper helper;
    public Context context;
    public int count;
    public int itemType;

    public VLayoutAdapter(Context context, LayoutHelper helper, int count, int itemType) {
        this.helper = helper;
        this.context = context;
        this.count = count;
        this.itemType = itemType;
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {

    }

    @Override
    public int getItemViewType(int position) {
        return itemType;
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        return customCreateViewHolder(parent, viewType);
    }

    protected VH customCreateViewHolder(ViewGroup parent, int viewType) {
        return (VH) new RecyclerView.ViewHolder(LayoutInflater.from(context).inflate(addHolderLayout(parent, viewType),
                parent, false)) {
        };
    }

    protected abstract int addHolderLayout(ViewGroup parent, int viewType);


    @Override
    protected void onBindViewHolderWithOffset(VH holder, int position, int offsetTotal) {
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return helper;
    }

    @Override
    public int getItemCount() {
        return count;
    }
}
