package com.tspolice.eticket.activities;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TextInputLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.tspolice.eticket.R;

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private ImageView header_image;
    private TextView tv_forgot_password;
    private TextView tv_new_user;
    private EditText et_pid_username, et_password;
    private Button btn_login;
    TextInputLayout textInputLayout1, textInputLayout2;
    final int SPLASH_DIALOG = 0;
    private Typeface mFont;
    private AlertDialog alertDialog;

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
        }, 2000);

        loadUiComponents();

        header_image.setOnClickListener(LoginActivity.this);
        btn_login.setOnClickListener(LoginActivity.this);
        tv_forgot_password.setOnClickListener(LoginActivity.this);
        tv_new_user.setOnClickListener(LoginActivity.this);
    }

    private void loadUiComponents() {
        header_image = (ImageView) findViewById(R.id.header_image);

        TextView tv_app_name = (TextView) findViewById(R.id.tv_app_name);
        TextView tv_app_version = (TextView) findViewById(R.id.tv_app_version);
        TextView tv_login = (TextView) findViewById(R.id.tv_login);
        TextView tv_footer = (TextView) findViewById(R.id.footer);
        tv_forgot_password = (TextView) findViewById(R.id.tv_forgot_password);
        tv_new_user = (TextView) findViewById(R.id.tv_new_user);

        textInputLayout1 = (TextInputLayout) findViewById(R.id.textInputLayout1);
        textInputLayout2 = (TextInputLayout) findViewById(R.id.textInputLayout2);

        et_pid_username = (EditText) findViewById(R.id.et_pid_username);
        et_password = (EditText) findViewById(R.id.et_password);

        btn_login = (Button) findViewById(R.id.btn_login);

        mFont = Typeface.createFromAsset(getAssets(), "fonts/Copperplate Gothic Heavy Regular.otf");
        tv_app_name.setTypeface(mFont);
        tv_app_version.setTypeface(mFont);
        tv_login.setTypeface(mFont);
        btn_login.setTypeface(mFont);
        tv_footer.setTypeface(mFont);

        Typeface cambriab = Typeface.createFromAsset(getAssets(), "fonts/cambriab.ttf");
        et_pid_username.setTypeface(cambriab);
        et_password.setTypeface(cambriab);
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

                TextView tv_welcome, tv_ttp, tv_app_name, footer;
                tv_welcome = (TextView) dialogSplash.findViewById(R.id.tv_welcome);
                tv_ttp = (TextView) dialogSplash.findViewById(R.id.tv_ttp);
                tv_app_name = (TextView) dialogSplash.findViewById(R.id.tv_app_name);
                footer = (TextView) dialogSplash.findViewById(R.id.footer);
                tv_welcome.setTypeface(mFont);
                tv_ttp.setTypeface(mFont);
                tv_app_name.setTypeface(mFont);
                footer.setTypeface(mFont);
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

                String pid = et_pid_username.getText().toString().trim();
                String password = et_password.getText().toString().trim();

                if (pid.isEmpty() && password.isEmpty()) {
                    showToastShort("PID is required !");
                    et_pid_username.requestFocus();
                } else if (pid.isEmpty()) {
                    showToastShort("PID is required !");
                    et_pid_username.requestFocus();
                } else if (password.isEmpty()) {
                    showToastShort("Password is required !");
                    et_password.requestFocus();
                } else if (password.length() < 4) {
                    showToastShort("Password length should be min 4 digits");
                    et_password.requestFocus();
                } else {
                    startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                }
                break;

            case R.id.header_image:
                startActivity(new Intent(LoginActivity.this, DashboardActivity.class));
                break;

            case R.id.tv_forgot_password:
                commonAlertDialog(R.layout.forgot_password, R.string.forgot_password, "FORGOT_PASSWORD");
                break;

            case R.id.btn_dialog_cancel:
                alertDialog.cancel();
                break;

            case R.id.btn_dialog_submit:
                alertDialog.dismiss();
                break;

            case R.id.tv_new_user:
                commonAlertDialog(R.layout.new_user, R.string.new_user_sign_up, "NEW_USER");
                break;

            case R.id.btn_new_user_dialog_cancel:
                alertDialog.cancel();
                break;

            case R.id.btn_new_user_dialog_submit:
                alertDialog.cancel();
                break;

            default:
                break;
        }
    }

    public void commonAlertDialog(int resource, int titleId, String dialogFlag) {
        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
        LayoutInflater inflater = LayoutInflater.from(LoginActivity.this);
        @SuppressLint("InflateParams") View view = inflater.inflate(resource, null);
        builder.setView(view);
        builder.setCancelable(false);
        builder.setIcon(R.drawable.ic_ts_police);
        builder.setTitle(titleId);
        alertDialog = builder.create();
        alertDialog.show();

        if ("FORGOT_PASSWORD".equals(dialogFlag)) {
            Button btn_dialog_cancel, btn_dialog_submit;
            btn_dialog_cancel = (Button) alertDialog.findViewById(R.id.btn_dialog_cancel);
            btn_dialog_submit = (Button) alertDialog.findViewById(R.id.btn_dialog_submit);
            btn_dialog_cancel.setOnClickListener(LoginActivity.this);
            btn_dialog_submit.setOnClickListener(LoginActivity.this);
        }

        if ("NEW_USER".equals(dialogFlag)) {
            Button btn_new_user_dialog_cancel = (Button) alertDialog.findViewById(R.id.btn_new_user_dialog_cancel);
            Button btn_new_user_dialog_submit = (Button) alertDialog.findViewById(R.id.btn_new_user_dialog_submit);
            btn_new_user_dialog_cancel.setOnClickListener(LoginActivity.this);
            btn_new_user_dialog_submit.setOnClickListener(LoginActivity.this);
        }
    }
}
