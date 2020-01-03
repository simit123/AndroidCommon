package com.common.core.myweb;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.common.core.views.Loader;

/**
 * @author by wuYang
 * @date 2019/12/17
 * @describe BaseWebFragment使用例子，使用时只需要将web的url传入即可
 */
public class ExampleWebFragment extends BaseWebFragment {


    public static ExampleWebFragment newInstance(String url) {
        ExampleWebFragment fragment = new ExampleWebFragment();
        Bundle bundle = new Bundle();
        bundle.putString(RouteKeys.WEB_URL, url);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return getWebView();
    }
}
