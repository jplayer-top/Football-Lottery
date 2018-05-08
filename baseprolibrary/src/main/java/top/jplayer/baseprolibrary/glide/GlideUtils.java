package top.jplayer.baseprolibrary.glide;

import android.support.annotation.DrawableRes;

import com.bumptech.glide.load.engine.DiskCacheStrategy;

import top.jplayer.baseprolibrary.R;

/**
 * Created by Obl on 2018/1/30.
 * top.jplayer.baseprolibrary.glide
 */

public class GlideUtils {
    private static GlideUtils customGlideOptions;

    public static synchronized GlideUtils init() {
        if (customGlideOptions == null) {
            synchronized (GlideUtils.class) {
                if (customGlideOptions == null) {
                    customGlideOptions = new GlideUtils();
                }
            }
        }
        return customGlideOptions;
    }

    public GlideOptions options() {
        return new GlideOptions().placeholder(R.drawable.placeholder).error(R.drawable.placeholder)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE);
    }

    public GlideOptions gif() {
        return new GlideOptions().centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE);
    }

    public GlideOptions options(@DrawableRes int res) {
        return new GlideOptions().placeholder(res).error(res)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE);
    }

    public GlideOptions optionsSkip(@DrawableRes int res) {
        return new GlideOptions().placeholder(res).error(res)
                .skipMemoryCache(true);
    }
}
