package top.jplayer.bestwork.mvpb.ui.adapter;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.github.florent37.viewanimator.ViewAnimator;

import java.util.List;
import java.util.Locale;

import top.jplayer.baseprolibrary.utils.StringUtils;
import top.jplayer.bestwork.R;
import top.jplayer.bestwork.mvpb.bean.HeaderGameTime;
import top.jplayer.bestwork.mvpb.bean.JczqBean;
import top.jplayer.bestwork.mvpb.ui.cai.JczqActivity;

/**
 * Created by Obl on 2018/4/27.
 * top.jplayer.bestwork.mvpb.ui.adapter
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */

public class JczqExpandAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder> {


    private static final String TAG = JczqExpandAdapter.class.getSimpleName();

    public static final int TYPE_LEVEL_0 = 0;
    public static final int TYPE_LEVEL_1 = 1;
    public JczqActivity mJczqActivity;

    public JczqExpandAdapter(List<MultiItemEntity> data, JczqActivity activity) {
        super(data);
        addItemType(TYPE_LEVEL_0, R.layout.item_expandable_header);
        addItemType(TYPE_LEVEL_1, R.layout.item_expandable_body);
        this.mJczqActivity = activity;
    }


    @Override
    protected void convert(final BaseViewHolder holder, final MultiItemEntity item) {
        int adapterPosition = holder.getLayoutPosition();
        switch (holder.getItemViewType()) {
            case TYPE_LEVEL_0:
                final HeaderGameTime lv0 = (HeaderGameTime) item;
                holder.setText(R.id.tvTime, lv0.tvTime).setText(R.id.tvCountGame, lv0.tvCountGame);
                ImageView imageView = holder.itemView.findViewById(R.id.iv);
                ViewAnimator.animate(imageView).rotation(lv0.isExpanded() ? 90 : 270).duration(500).start();
                holder.itemView.setOnClickListener(v -> {
                    if (lv0.isExpanded()) {
                        collapse(holder.getLayoutPosition());
                    } else {
                        expand(holder.getLayoutPosition());
                    }
                    mJczqActivity.resetRecord();
                });
                break;
            case TYPE_LEVEL_1:
                final JczqBean.ResultBean bodyResult = (JczqBean.ResultBean) item;
                String letVs_letPoint = bodyResult.letVs_letPoint;
                int parseInt = Integer.parseInt(letVs_letPoint);
                String homeRank = bodyResult.homeRank;
                if (TextUtils.equals("", homeRank)) {
                    homeRank = "";
                } else {
                    homeRank = String.format(Locale.CHINA, "[%s]", homeRank);
                }
                String awayRank = bodyResult.awayRank;
                if (TextUtils.equals("", awayRank)) {
                    awayRank = "";
                } else {
                    awayRank = String.format(Locale.CHINA, "[%s]", awayRank);
                }
                holder.setText(R.id.tvGame, String.format(Locale.CHINA, "%s\n%s\n%s 截止", bodyResult.teamId,
                        bodyResult.league, bodyResult.endTime.split(" ")[1]))
                        .setText(R.id.tvHomeTeam, String.format(Locale.CHINA, "%s %s", homeRank,
                                bodyResult.homeTeam))
                        .setText(R.id.tvAwayTeam, String.format(Locale.CHINA, "%s %s", bodyResult.guestTeam,
                                awayRank))
                        .setText(R.id.tvLetV, String.format(Locale.CHINA, "%s", letVs_letPoint))
                        .setText(R.id.tvV3, StringUtils.init().fixNullStr("胜  ", bodyResult.v3, "--"))
                        .setText(R.id.tvV1, StringUtils.init().fixNullStr("平  ", bodyResult.v1, "--"))
                        .setText(R.id.tvV0, StringUtils.init().fixNullStr("负  ", bodyResult.v0, "--"))
                        .setText(R.id.tvLetV3, StringUtils.init().fixNullStr("让胜 ", bodyResult.letVs_v3, "--"))
                        .setText(R.id.tvLetV1, StringUtils.init().fixNullStr("让平 ", bodyResult.letVs_v1, "--"))
                        .setText(R.id.tvLetV0, StringUtils.init().fixNullStr("让负 ", bodyResult.letVs_v0, "--"))
                        .setBackgroundColor(R.id.tvLetV, parseInt < 0 ? R.color.indianred : R.color.blue);

                View v3 = holder.itemView.findViewById(R.id.tvV3);
                View v1 = holder.itemView.findViewById(R.id.tvV1);
                View v0 = holder.itemView.findViewById(R.id.tvV0);
                v3.setOnClickListener(viewOnClickListener(v3, bodyResult, adapterPosition));
                v1.setOnClickListener(viewOnClickListener(v1, bodyResult, adapterPosition));
                v0.setOnClickListener(viewOnClickListener(v0, bodyResult, adapterPosition));
                v3.setSelected(bodyResult.v3_sel);
                v1.setSelected(bodyResult.v1_sel);
                v0.setSelected(bodyResult.v0_sel);


                View letV3 = holder.itemView.findViewById(R.id.tvLetV3);
                View letV1 = holder.itemView.findViewById(R.id.tvLetV1);
                View letV0 = holder.itemView.findViewById(R.id.tvLetV0);
                letV3.setOnClickListener(viewOnClickListener(letV3, bodyResult, adapterPosition));
                letV1.setOnClickListener(viewOnClickListener(letV1, bodyResult, adapterPosition));
                letV0.setOnClickListener(viewOnClickListener(letV0, bodyResult, adapterPosition));
                letV3.setSelected(bodyResult.letVs_v3_sel);
                letV1.setSelected(bodyResult.letVs_v1_sel);
                letV0.setSelected(bodyResult.letVs_v0_sel);
                break;
        }
    }

    @NonNull
    private View.OnClickListener viewOnClickListener(View v, JczqBean.ResultBean bodyResult, int position) {

        return v1 -> {
            if (v.getId() == R.id.tvV3) {
                bodyResult.v3_sel = !bodyResult.v3_sel;
            } else if (v.getId() == R.id.tvV1) {
                bodyResult.v1_sel = !bodyResult.v1_sel;
            } else if (v.getId() == R.id.tvV0) {
                bodyResult.v0_sel = !bodyResult.v0_sel;
            } else if (v.getId() == R.id.tvLetV3) {
                bodyResult.letVs_v3_sel = !bodyResult.letVs_v3_sel;
            } else if (v.getId() == R.id.tvLetV1) {
                bodyResult.letVs_v1_sel = !bodyResult.letVs_v1_sel;
            } else if (v.getId() == R.id.tvLetV0) {
                bodyResult.letVs_v0_sel = !bodyResult.letVs_v0_sel;
            }
            notifyItemChanged(position);
        };
    }
}
