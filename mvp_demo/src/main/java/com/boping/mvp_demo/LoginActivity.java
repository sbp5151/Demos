package com.boping.mvp_demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

/**
 * 进一步降低view和model之间的耦合，解放activity
 */
public class LoginActivity extends AppCompatActivity implements ILoginView {

    private EditText etName;
    private EditText etPassword;
    private ProgressBar progressBar;
    private LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        etName = findViewById(R.id.et_name);
        etPassword = findViewById(R.id.et_password);
        progressBar = findViewById(R.id.progressBar);

        loginPresenter = new LoginPresenter(this, this);
    }

    public void login(View view) {
        loginPresenter.login();
    }

    public void clear(View view) {
        etName.setText("");
        etPassword.setText("");
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public String getName() {
        return etName.getText().toString();
    }

    @Override
    public String getPassword() {
        return etPassword.getText().toString();
    }

    @Override
    public void toMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void showFailedError(String errorMsg) {
        Toast.makeText(this, errorMsg, Toast.LENGTH_SHORT).show();
    }
}
