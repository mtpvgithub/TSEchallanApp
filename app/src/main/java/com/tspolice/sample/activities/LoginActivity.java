package com.tspolice.sample.activities;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.tspolice.sample.R;
import com.tspolice.sample.utils.Networking;

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private ImageView header_image;
    private EditText et_pid_username, et_password;
    private Button btn_login;
    private String pid, password;
    TextInputLayout textInputLayout1, textInputLayout2;
    private final int SPLASH_DIALOG = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        showDialog(SPLASH_DIALOG);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                removeDialog(SPLASH_DIALOG);
            }
        }, 1000);

        header_image = (ImageView) findViewById(R.id.header_image);

        et_pid_username = (EditText) findViewById(R.id.et_pid_username);
        et_password = (EditText) findViewById(R.id.et_password);
        btn_login = (Button) findViewById(R.id.btn_login);

        header_image.setOnClickListener(this);
        btn_login.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        alertDialogOkCancel("Do you want to close the application ?", false, "CLOSE");
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case SPLASH_DIALOG:
                Dialog dialogSplash = new Dialog(this, android.R.style.Theme_Holo_Light_NoActionBar_Fullscreen);
                dialogSplash.setCancelable(false);
                dialogSplash.setContentView(R.layout.splash);
                return dialogSplash;
            default:
                break;
        }
        return super.onCreateDialog(id);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:

                //textInputLayout1.setError("");
                //textInputLayout2.setError("");

                pid = et_pid_username.getText().toString().trim();
                password = et_password.getText().toString().trim();

//                if (!Networking.isNetworkAvailable(LoginActivity.this)) {
//                    showToastShort("Please check your n/w data connection !");
//                } else {
//                    if (pid.isEmpty() && password.isEmpty()) {
//                        //et_pid_username.setError("Enter pid");
//                        //textInputLayout1.setError("Enter employee id");
//                        showToastShort("PID is required !");
//                        et_pid_username.requestFocus();
//                    } else if (pid.isEmpty()) {
//                        //et_pid_username.setError("Enter pid");
//                        //textInputLayout1.setError("Enter employee id");
//                        showToastShort("PID is required !");
//                        et_pid_username.requestFocus();
//                    } else if (password.isEmpty()) {
//                        //et_password.setError("Please enter password");
//                        //textInputLayout2.setError("Please enter pin");
//                        showToastShort("Password is required !");
//                        et_password.requestFocus();
//                    } else if (password.length() < 4) {
//                        //et_password.setError("Password length should be min 4 Digits");
//                        //textInputLayout2.setError("PIN length should be min 4 Digits");
//                        et_password.requestFocus();
//                        showToastShort("Password length should be min 4 digits");
//                    } else {
//                        startActivity(new Intent(LoginActivity.this, HomeActivity.class));
//                    }
//                }
                startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                break;

            case R.id.header_image:
                startActivity(new Intent(LoginActivity.this, DashboardActivity.class));
                break;

            default:
                break;
        }
    }
}
