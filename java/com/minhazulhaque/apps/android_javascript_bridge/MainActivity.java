package com.minhazulhaque.apps.android_javascript_bridge;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Button;

import java.text.DateFormat;
import java.util.Date;


public class MainActivity extends ActionBarActivity {

    WebView webview = null;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webview = (WebView) this.findViewById(R.id.webView);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.loadUrl("file:///android_res/raw/index.html");
        webview.addJavascriptInterface(new JSInterface(), "JSInterface");
        webview.loadUrl("javascript:window.onload=function(e){setData(\"from onCreate\");}");
    }

    public void callWebview(View view) {
        String buttontext = ((Button)view).getText().toString();
        webview.loadUrl("javascript:setData(\"" + buttontext + "\");");
    }

    public class JSInterface {

        JSInterface() {}

        @JavascriptInterface
        public String getData() {
            return DateFormat.getDateTimeInstance().format(new Date());
        }
    }
}
