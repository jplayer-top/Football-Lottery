package top.jplayer.baseprolibrary.ui;

import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import top.jplayer.baseprolibrary.R;

/**
 * Created by Obl on 2017/3/20.
 * com.ilanchuang.xiaoi.activity
 */
public class WebFullScreenActivity extends SuperBaseActivity {


    private Bundle mBundle;
    private WebView mWebView;

    /**
     * 设置WebView
     */
    private void setType() {
        WebSettings settings = mWebView.getSettings();
        settings.setDomStorageEnabled(true);
        settings.setDatabaseEnabled(true);
        settings.setAppCacheEnabled(true);
//设置可以访问文件
        settings.setAllowFileAccess(true);
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();  //接受所有证书
            }

        });
        mWebView.setWebChromeClient(new WebChromeClient());
        settings.setJavaScriptEnabled(true);
        getWindow().addFlags(
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);
        mWebView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        mWebView.setWebChromeClient(new WebChromeClient());//设置能够使js正常弹窗
        if (Build.VERSION.SDK_INT >= 19) {
            mWebView.getSettings().setLoadsImagesAutomatically(true);
        } else {
            mWebView.getSettings().setLoadsImagesAutomatically(false);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mWebView.resumeTimers();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mWebView.pauseTimers();
    }

    @Override
    public void onBackPressed() {
        if (mWebView != null && mWebView.canGoBack()) {
            mWebView.goBack();
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        if (mWebView != null) {
            ViewGroup parent = (ViewGroup) mWebView.getParent();
            if (parent != null) {
                parent.removeView(mWebView);
            }
            mWebView.setVisibility(View.GONE);
            mWebView.removeAllViews();
            mWebView.destroy();
        }
        super.onDestroy();
    }
    @Override
    protected int initRootLayout() {
        return R.layout.webview;
    }

    @Override
    public void initRootData(View view) {
        mWebView = view.findViewById(R.id.webView);
        setType();
        mBundle = getIntent().getBundleExtra("bundle");
        mWebView.loadUrl(mBundle.getString("url"));
    }

}
