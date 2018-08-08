package com.tspolice.sample.activities;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.tspolice.sample.R;

@SuppressLint("Registered")
public class BaseActivity extends AppCompatActivity {

    private ProgressDialog mProgressDialog;

    public void showProgressDialog(String message, boolean cancelable) {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(BaseActivity.this);
            mProgressDialog.setMessage(message);
            mProgressDialog.setCancelable(cancelable);
        }
        mProgressDialog.show();
    }

    public void showProgressDialogLogoTitle(String message, boolean cancelable) {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(BaseActivity.this);
            mProgressDialog.setTitle(getResources().getString(R.string.app_name));
            mProgressDialog.setIcon(R.drawable.ic_ts_police);
            mProgressDialog.setMessage(message);
            mProgressDialog.setCancelable(cancelable);
        }
        mProgressDialog.show();
    }

    public void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    public void alertDialogOkCancel(String message, boolean cancelable, final String actionFlag) {
        AlertDialog.Builder builder = new AlertDialog.Builder(BaseActivity.this);
        builder.setTitle(getResources().getString(R.string.app_name));
        builder.setIcon(getResources().getDrawable(R.drawable.ic_ts_police));
        builder.setMessage(message);
        builder.setCancelable(cancelable);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if ("CLOSE".equals(actionFlag)) {
                    finish();
                } else if ("LOGOUT".equals(actionFlag)) {
                    Intent intent_Login = new Intent(BaseActivity.this, LoginActivity.class);
                    intent_Login.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent_Login);
                    finish();
                }
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void alertDialogOk(String message, boolean cancelable, final String actionFlag) {
        AlertDialog.Builder builder = new AlertDialog.Builder(BaseActivity.this);
        builder.setTitle(getResources().getString(R.string.app_name));
        builder.setIcon(getResources().getDrawable(R.drawable.ic_ts_police));
        builder.setMessage(message);
        builder.setCancelable(cancelable);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void alertDialogOkCancelNeutral(String message, boolean cancelable, final String actionFlag) {
        AlertDialog.Builder builder = new AlertDialog.Builder(BaseActivity.this);
        builder.setTitle(getResources().getString(R.string.app_name));
        builder.setIcon(getResources().getDrawable(R.drawable.ic_ts_police));
        builder.setMessage(message);
        builder.setCancelable(cancelable);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.setNeutralButton("Neutral", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void alertDialog(String message, boolean cancelable, final String actionFlag) {
        AlertDialog.Builder builder = new AlertDialog.Builder(BaseActivity.this);
        builder.setMessage(message);
        builder.setCancelable(cancelable);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void showToastShort(String message) {
        Toast.makeText(BaseActivity.this, "" + message, Toast.LENGTH_SHORT).show();
    }

    public void showToastLong(String message) {
        Toast.makeText(BaseActivity.this, "" + message, Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                showToastShort("Settings selected");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
