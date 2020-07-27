package com.xxx.recommendfilm.ui.login;

import android.content.Intent;
import android.view.View;
import androidx.lifecycle.Observer;
import com.xxx.recommendfilm.R;
import com.xxx.recommendfilm.databinding.ActivityLoginBinding;
import com.xxx.recommendfilm.ui.base.BaseActivity;
import com.xxx.recommendfilm.ui.home.MainActivity;
import com.xxx.recommendfilm.ui.register.RegisterActivity;

public class LoginActivity extends BaseActivity<ActivityLoginBinding, LoginViewModel> {

    @Override
    protected Class<LoginViewModel> getViewModelClazz() {
        return LoginViewModel.class;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        viewModel.isLoginSuccess.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean){
                    finish();
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                }

            }
        });
        binding.btnServerLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.login();
            }
        });
        binding.btnGoRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jumpToRegister();
            }
        });
    }

    @Override
    protected void initData() {

    }

    private void jumpToRegister(){
        startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
        finish();
    }


}