package com.blink.dagger.gank.ui.gancontent;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.blink.dagger.gank.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class GanhuoDetailActivity extends AppCompatActivity {

    @InjectView(R.id.title)
    TextSwitcher title;
    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.app_bar_layout)
    AppBarLayout appBarLayout;
    @InjectView(R.id.webView)
    WebView webView;

    private String mUrl;
    private String mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ganhuo_detail);
        ButterKnife.inject(this);
        mUrl = getIntent().getStringExtra("url");
        mTitle = getIntent().getStringExtra("title");
        initAll();
    }

    private void initAll() {
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setLoadWithOverviewMode(true);
        settings.setAppCacheEnabled(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setSupportZoom(true);
        webView.loadUrl(mUrl);
        title.setInAnimation(this, android.R.anim.fade_in);
        title.setOutAnimation(this, android.R.anim.fade_out);
        title.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                TextView textView = new TextView(GanhuoDetailActivity.this);
                textView.setTextAppearance(GanhuoDetailActivity.this, R.style.WebTitle);
                textView.setSingleLine(true);
                textView.setEllipsize(TextUtils.TruncateAt.MARQUEE);
                textView.setSelected(true);
                return textView;
            }
        });
        if (mTitle != null)
            title.setText(mTitle);
    }
}
