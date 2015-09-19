package org.leanpoker.leanpokerandroid.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import org.leanpoker.Utils;
import org.leanpoker.data.model.AccessToken;
import org.leanpoker.leanpokerandroid.R;
import org.leanpoker.leanpokerandroid.navigator.Navigator;
import org.leanpoker.leanpokerandroid.presenter.LoginPresenter;
import org.leanpoker.leanpokerandroid.view.LoginView;
import org.leanpoker.util.GithubUtils;

import java.util.UUID;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Subscriber;

/**
 * Created by tmolnar on 19/09/15.
 */
public class LoginFragment extends BaseFragment implements LoginView {

    private WebViewClient mWebViewClient;
    private LoginPresenter mPresenter;

    @Bind(R.id.login_webview)
    protected WebView mWebView;

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, true);
        ButterKnife.bind(this, view);
        setupUI();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable final Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mPresenter = new LoginPresenter(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenter.pause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.destroy();
    }

    private void setupUI() {
        initWebView();
    }

    private void initWebView() {
        mWebViewClient = new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(final WebView view, final String url) {
                if (url.startsWith(GithubUtils.APP_URI_SCHEME)) {
                    //code=asd19023klas2108asflj2
                    String accessCodeAsKeyValue = url.substring(url.indexOf(GithubUtils.ACCESS_CODE_KEY));
                    String accessCode = Utils.getValueFromKeyValuePair(accessCodeAsKeyValue);
                    mPresenter.delegateTokenRequest(accessCode, UUID.randomUUID().toString());
                    return true;
                }
                return super.shouldOverrideUrlLoading(view, url);
            }
        };
        mWebView.setWebViewClient(mWebViewClient);
        mWebView.loadUrl(GithubUtils.GITHUB_OAUTH_URL);
    }

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    public void navigateToEventsAcitivity() {
        Navigator.getInstance().naviateToEventListActivity(getActivity());
    }
}
