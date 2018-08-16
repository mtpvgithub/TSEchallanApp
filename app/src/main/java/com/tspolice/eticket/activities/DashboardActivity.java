package com.tspolice.eticket.activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tspolice.eticket.R;

public class DashboardActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        LinearLayout layoutAnimation = (LinearLayout) findViewById(R.id.layout_animation);
        layoutAnimation.setAnimation(AnimationUtils.loadAnimation(DashboardActivity.this, R.anim.fade_enter));

        RelativeLayout layout_spot = (RelativeLayout) findViewById(R.id.layout_spot);
        layout_spot.setOnClickListener(this);

        TextView tv_app_name, tv_app_version, tv_officer_name_ps, tv_spot_challan,
                tv_drunk_drive, tv_towing, tv_ghmc, tv_seizure, tv_court_disposal,
                tv_reports, tv_release_documents, tv_settings, tv_footer;
        tv_app_name = (TextView)findViewById(R.id.tv_app_name);
        tv_app_version = (TextView)findViewById(R.id.tv_app_version);
        tv_officer_name_ps = (TextView)findViewById(R.id.tv_officer_name_ps);
        tv_spot_challan = (TextView)findViewById(R.id.tv_spot_challan);
        tv_drunk_drive = (TextView)findViewById(R.id.tv_drunk_drive);
        tv_towing = (TextView)findViewById(R.id.tv_towing);
        tv_ghmc = (TextView)findViewById(R.id.tv_ghmc);
        tv_seizure = (TextView)findViewById(R.id.tv_seizure);
        tv_court_disposal = (TextView)findViewById(R.id.tv_court_disposal);
        tv_reports = (TextView)findViewById(R.id.tv_reports);
        tv_release_documents = (TextView)findViewById(R.id.tv_release_documents);
        tv_settings = (TextView)findViewById(R.id.tv_settings);
        tv_footer = (TextView)findViewById(R.id.footer);

        Typeface mFont = Typeface.createFromAsset(getAssets(), "fonts/Copperplate Gothic Heavy Regular.otf");
        tv_app_name.setTypeface(mFont);
        tv_app_version.setTypeface(mFont);
        tv_officer_name_ps.setTypeface(mFont);

        tv_spot_challan.setTypeface(mFont);
        tv_drunk_drive.setTypeface(mFont);
        tv_towing.setTypeface(mFont);
        tv_ghmc.setTypeface(mFont);
        tv_seizure.setTypeface(mFont);
        tv_court_disposal.setTypeface(mFont);
        tv_reports.setTypeface(mFont);
        tv_release_documents.setTypeface(mFont);
        tv_settings.setTypeface(mFont);
        tv_footer.setTypeface(mFont);
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
