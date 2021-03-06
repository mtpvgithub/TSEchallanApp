package com.tspolice.eticket.activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tspolice.eticket.R;

public class HomeActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        LinearLayout layoutAnimation = (LinearLayout) findViewById(R.id.layout_animation);
        layoutAnimation.setAnimation(AnimationUtils.loadAnimation(HomeActivity.this, R.anim.fade_enter));

        RelativeLayout layout_spot = (RelativeLayout) findViewById(R.id.layout_spot);
        layout_spot.setOnClickListener(this);

        TextView tv_spot_challan, tv_drunk_drive, tv_towing, tv_ghmc, tv_seizure, tv_court_disposal,
                tv_reports, tv_release_documents, tv_settings, tv_footer;
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
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            //super.onBackPressed();
            alertDialogOkCancel("Do you want to logout ?", false, "LOGOUT");

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.home, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            showToastShort("Settings selected from home");
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layout_spot:
                startActivity(new Intent(HomeActivity.this, SpotActivity.class));
                break;
            default:
                break;
        }
    }
}
