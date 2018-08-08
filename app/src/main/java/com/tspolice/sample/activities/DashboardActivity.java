package com.tspolice.sample.activities;

import android.content.Intent;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.tspolice.sample.R;

public class DashboardActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        LinearLayout layoutAnimation = (LinearLayout) findViewById(R.id.layout_animation);
        layoutAnimation.setAnimation(AnimationUtils.loadAnimation(DashboardActivity.this, R.anim.fade_enter));

        RelativeLayout layout_spot = (RelativeLayout) findViewById(R.id.layout_spot);
        layout_spot.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layout_spot:
                startActivity(new Intent(DashboardActivity.this, SpotActivity.class));
                break;
            default:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        alertDialogOkCancel("Do you want to logout ?", false, "LOGOUT");
    }
}
