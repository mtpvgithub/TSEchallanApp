package com.tspolice.eticket.activities;

import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.tspolice.eticket.R;
import com.tspolice.eticket.adapters.MyDividerItemDecoration;
import com.tspolice.eticket.adapters.RecyclerAdapter;
import com.tspolice.eticket.models.ViolationsModel;
import com.tspolice.eticket.utils.Networking;
import com.tspolice.eticket.utils.PidSecEncrypt;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.ArrayList;
import java.util.List;

public class ViolationsActivity extends BaseActivity
        implements RecyclerAdapter.ViolationClickListener {

    private static final String TAG = ViolationsActivity.class.getSimpleName();
    private List<ViolationsModel> violationList;
    private RecyclerAdapter mAdapter;
    private SearchView searchView;
    private RecyclerView recyclerView;
    private Toolbar mainToolbar;
    private static final String testUrl = "http://192.168.11.10:8080/eTicketMobileHyd";
    private static final String liveUrl = "https://www.echallan.org/TSeTicketMobile";
    private static final String urlFix = "/services/MobileEticketServiceImpl?wsdl";
    private static final String url2 = liveUrl + urlFix;
    private static final String URL = "https://api.androidhive.info/json/contacts.json";

    private static String nameSpace = "http://service.et.mtpv.com";
    private static String authenticateUser = "authenticateUser";
    private static String soapAction = nameSpace + authenticateUser;
    private String response;
    private ProgressDialog progressDialog;
    private String[] violationsMasters;
    private String[][] violationsDetailedViews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_violations);

        toolbarSetup();

        recyclerViewSetup();

        if (Networking.isNetworkAvailable(ViolationsActivity.this)) {
            new GetViolations().execute();
        } else {
            Toast.makeText(ViolationsActivity.this, "Please check your network connection !", Toast.LENGTH_SHORT).show();
        }

        violationList = new ArrayList<>();
    }

    private void recyclerViewSetup() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setSelected(true);
        recyclerView.addItemDecoration(new MyDividerItemDecoration(this, DividerItemDecoration.VERTICAL, 36));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int flags = recyclerView.getSystemUiVisibility();
            flags |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            recyclerView.setSystemUiVisibility(flags);
            getWindow().setStatusBarColor(Color.WHITE);
        }
    }

    private void toolbarSetup() {
        mainToolbar = (Toolbar) findViewById(R.id.toolbar);
        if (mainToolbar != null) {
            setSupportActionBar(mainToolbar);
            if (getSupportActionBar() != null) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                getSupportActionBar().setDisplayShowHomeEnabled(true);
                getSupportActionBar().setTitle("Select violations");
            }
            mainToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                    startActivity(new Intent(ViolationsActivity.this, SpotActivity.class));
                }
            });
        }
    }

    private class GetViolations extends AsyncTask<Void, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(ViolationsActivity.this);
            progressDialog.setMessage("Please wait...");
        }

        @Override
        protected String doInBackground(Void... params) {
            try {
                SoapObject request = new SoapObject(nameSpace, "getOffenceDetailsbyWheelerChallanType");
                request.addProperty("wheelercode", "2");
                request.addProperty("challanType", "TE");
                SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.dotNet = true;
                envelope.setOutputSoapObject(request);
                HttpTransportSE httpTransportSE = new HttpTransportSE(url2);
                httpTransportSE.call(soapAction, envelope);
                Object result = envelope.getResponse();

                if (result != null) {
                    response = new PidSecEncrypt().decrypt(result.toString());
                }

            } catch (SoapFault soapFault) {
                soapFault.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return response;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            progressDialog.dismiss();

            if (response != null && !"".equals(response) && !"0".equals(response)
                    && response.length() > 0 && response.contains("!")) {

                violationsMasters = new String[0];
                violationsDetailedViews = new String[0][0];
                violationList = new ArrayList<>();

                try {
                    response = response.substring(1, response.length());
                    violationsMasters = response.split("!");

                    if (violationsMasters.length > 0) {

                        violationsDetailedViews = new String[violationsMasters.length][6];

                        for (int i = 0; i < violationsMasters.length; i++) {

                            if (violationsMasters[i].contains("@")) {
                                violationsDetailedViews[i] = violationsMasters[i].trim().split("@");
                                ViolationsModel violationsModel = new ViolationsModel();
                                violationsModel.setVioOffenceCode(violationsDetailedViews[i][0]);
                                violationsModel.setVioSection(violationsDetailedViews[i][1]);
                                violationsModel.setViolation(violationsDetailedViews[i][2]);
                                violationsModel.setVioMinAmount("MIN: " + violationsDetailedViews[i][3]);
                                violationsModel.setVioMaxAmount("MAX: " + violationsDetailedViews[i][4]);
                                violationsModel.setVioAvgAmount("AVG: " + violationsDetailedViews[i][5]);
                                violationsModel.setWheelerCode(violationsDetailedViews[i][6]);
                                violationsModel.setVioFlag(violationsDetailedViews[i][7]);
                                violationList.add(violationsModel);
                            }
                        }

                        mAdapter = new RecyclerAdapter(ViolationsActivity.this, violationList, ViolationsActivity.this);
                        recyclerView.setAdapter(mAdapter);

                    } else {
                        Toast.makeText(ViolationsActivity.this, "Data not found !", Toast.LENGTH_SHORT).show();
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    e.printStackTrace();
                }
            } else {
                Toast.makeText(ViolationsActivity.this, "Data not found !", Toast.LENGTH_SHORT).show();
            }

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_violations, menu);
        // Associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        // listening to search query text change
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // filter recycler view when query submitted
                mAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                // filter recycler view when text is changed
                mAdapter.getFilter().filter(query);
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        // close search view on back button pressed
        if (!searchView.isIconified()) {
            searchView.setIconified(true);
            return;
        }
        //super.onBackPressed();
        finish();
        startActivity(new Intent(ViolationsActivity.this, SpotActivity.class));
    }

    @Override
    public void onViolationSelected(ViolationsModel contact) {
        showToastLong("Selected: " + contact.getViolation() + " ( " + contact.getVioSection() + " ) ");
    }
}
