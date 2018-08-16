package com.tspolice.eticket.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.tspolice.eticket.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SpotActivity extends BaseActivity
        implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    private Spinner spinner_regnNo_chssNo_engnNo;
    private EditText et_dl_no;
    private Button btn_dob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spot);

        Toolbar spot_toolbar = (Toolbar) findViewById(R.id.toolbar_spot);
        setSupportActionBar(spot_toolbar);

        ImageView img_toolbar = (ImageView) spot_toolbar.findViewById(R.id.img_toolbar);
        img_toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToastShort("You clicked logo in spot challan");
            }
        });
        TextView tv_toolbar = (TextView) spot_toolbar.findViewById(R.id.tv_toolbar);
        tv_toolbar.setText("Spot Challan");

        spinner_regnNo_chssNo_engnNo = (Spinner) findViewById(R.id.spinner_regnNo_chssNo_engnNo);
        spinner_regnNo_chssNo_engnNo.setOnItemSelectedListener(this);
        List<String> spinner_List = new ArrayList<>();
        spinner_List.add("Vehicle No.");
        spinner_List.add("Chassis No.");
        spinner_List.add("Engine No.");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(SpotActivity.this,
                android.R.layout.simple_spinner_item, spinner_List);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_regnNo_chssNo_engnNo.setAdapter(arrayAdapter);

        /*et_dl_no = (EditText) findViewById(R.id.et_dl_no);
        btn_dob = (Button) findViewById(R.id.btn_dob);
        btn_dob.setVisibility(View.GONE);
        et_dl_no.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!et_dl_no.getText().toString().isEmpty()
                        && et_dl_no.getText().toString().trim().length() >= 5) {
                    btn_dob.setVisibility(View.VISIBLE);
                    btn_dob.setOnClickListener(SpotActivity.this);
                } else {
                    btn_dob.setVisibility(View.GONE);
                }
            }
        });*/

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_spot, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_violations:
                startActivity(new Intent(SpotActivity.this, ViolationsActivity.class));
                return true;
            case R.id.action_about:
                showToastShort("About version selected from spot");
                return true;
            case R.id.action_settings:
                showToastShort("Settings selected from spot");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        startActivity(new Intent(SpotActivity.this, HomeActivity.class));
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_dob:
                showToastShort("You clicked on dob");
                break;
            default:
                break;
        }
    }
}
