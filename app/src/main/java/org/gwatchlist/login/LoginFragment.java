package org.gwatchlist.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

import org.gwatchlist.R;
import org.gwatchlist.data.entities.User;
import org.gwatchlist.util.GraphicUtils;

/**
 *
 * Created by giovanni on 1/02/17.
 */
public class LoginFragment extends Fragment implements LoginContract.View,
        GoogleApiClient.OnConnectionFailedListener {
    private static final int RESULT_CODE_SIGN_IN = 100;

    private LoginContract.Presenter mPresenter;

    private GoogleApiClient mGoogleApiClient;
    private View rootView;


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_CODE_SIGN_IN) {
            GoogleSignInResult signInResult = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (signInResult.isSuccess()) {
                Log.d(getClass().getName(), "Login through google successful");

                GoogleSignInAccount account = signInResult.getSignInAccount();
                if (account != null) {
                    GraphicUtils.showProgressDialog(
                            getContext(),
                            null,
                            getString(R.string.progress_logining),
                            true
                    );
                    mPresenter.attemptLogin(account.getEmail(), account.getDisplayName());
                }

            } else {
                Log.w(getClass().getName(), "Sign in cancelled by user");

                mPresenter.showFailedLogin();
            }
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.google_client_id))
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(getContext())
                .enableAutoManage(getActivity(), this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.frag_login, container, false);
        this.configureButtonsListeners();

        return rootView;
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.e(getClass().getName(), "Google connection failed");
    }

    @Override
    public void setPresenter(@NonNull LoginContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void attemptGoogleLogin() {
        Intent signinIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signinIntent, RESULT_CODE_SIGN_IN);
    }

    @Override
    public void showFailedLogin() {
        GraphicUtils.hideProgressDialog();
        GraphicUtils.showAlert(
                getContext(),
                getString(R.string.error_login_title),
                getString(R.string.error_login)
        );
    }

    @Override
    public void showPersonalList(User user) {
        GraphicUtils.hideProgressDialog();
        Log.d(getClass().getName(), "Going to personal list fragment");
    }

    private void configureButtonsListeners() {

        //
        // Configure the login button
        Button btnLogin = (Button) rootView.findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.attemptLogin();
            }
        });
    }
}
