package com.common.androidcommon;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.SupportActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.common.core.base.mvp.AbstractBaseActivity;
import com.common.core.base.mvp.BaseActivity;
import com.common.core.base.mvp.BasePresenter;
import com.common.core.myweb.BaseWebFragment;
import com.common.core.myweb.ExampleWebFragment;
import com.common.core.utils.toast.ToastUtils;
import com.common.core.web.Route;
import com.common.core.web.WebFragmentImpl;
import com.common.core.web.WebViewClientImpl;
import com.common.core.web.WebViewInitailizer;

import butterknife.BindView;

public class MainActivity extends AbstractBaseActivity {
    private Button bt;


//    @BindView(R.id.WebViewClientImpl)
//    WebView WebViewClientImpl;

//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//    }

    @Override
    protected void onViewCreated() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {
        ExampleWebFragment baseWebFragment = ExampleWebFragment.newInstance("file:///android_asset/js2.html");
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.content, baseWebFragment);
        ft.commit();

//        baseWebFragment.loadLocalPage("js2.html");



//        bt = ((Button) findViewById(R.id.bt));
//        initWebView();
    }

//    @Override
//    public BasePresenter getP() {
//        return null;
//    }

    @SuppressLint("SetJavaScriptEnabled")
    private void initWebView() {
        final WebView web = ((WebView) findViewById(R.id.web));
        WebSettings webSettings = web.getSettings();

        // 设置与Js交互的权限
        webSettings.setJavaScriptEnabled(true);
        // 设置允许JS弹窗
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);

        // 先载入JS代码
        // 格式规定为:file:///android_asset/文件名.html
        web.loadUrl("file:///android_asset/js2.html");

//        bt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                WebViewClientImpl.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        //调用js中的calljs()方法，方法名要与js中的一致
//
//                        /***
//                         *  WebViewClientImpl.loadUrl("javascript:callJS()");//第一种android调用js的方式
//                         *  WebViewClientImpl.evaluateJavascript()  //第二种Android 调用js的方式4.4以上可用 效率高
//                         *
//                         */
//                        WebViewClientImpl.evaluateJavascript("javascript:callJS()", new ValueCallback<String>() {
//                            @Override
//                            public void onReceiveValue(String value) {
//                                //接收js回调结果
//                                ToastUtils.show(value);
//                            }
//                        });
//                    }
//                });
//            }
//        });

//        WebViewClientImpl.setWebChromeClient(new WebChromeClient() {
//            @Override
//            public boolean onJsAlert(WebView view, String url, String message, final JsResult result) {
//                AlertDialog.Builder b = new AlertDialog.Builder(MainActivity.this);
//                b.setTitle("Alert");
//                b.setMessage(message);
//                b.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        result.confirm();
//                    }
//                });
//                b.setCancelable(false);
//                b.create().show();
//                return true;
//            }
//        });

        //js调用android
//        1、通过WebView的addJavascriptInterface（）进行对象映射
//        2、通过 WebViewClient 的shouldOverrideUrlLoading ()方法回调拦截 url
//        3、通过 WebChromeClient 的onJsAlert()、onJsConfirm()、onJsPrompt（）方法回调拦截JS对话框alert()、confirm()、prompt（） 消息


//        WebViewClientImpl.addJavascriptInterface(new AndroidToJS(), "test1");//将AndroidToJS()对象以 test1的方式注入进js文件中


//        web.setWebViewClient(new WebViewClient() {
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//
//                Uri uri = Uri.parse(url);
//                if (uri.getScheme().equals("js")) {
//                    if (uri.getAuthority().equals("webview")) {
//                        ToastUtils.show("js调用了android的方法");
//                    }
//                    return true;
//                }
//                return super.shouldOverrideUrlLoading(view, url);
//            }
//        });


    }


}
