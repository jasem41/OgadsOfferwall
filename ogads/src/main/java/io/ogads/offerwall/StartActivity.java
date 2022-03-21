package io.ogads.offerwall;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import io.ogads.offerwall.adpter.MyOffersLisAdapter;
import io.ogads.offerwall.adpter.OffersDataModel;

public class StartActivity extends AppCompatActivity {

    String url, username,api_key;
    int points;
    ListView listView;

    ArrayList<OffersDataModel> dataModelArrayList1;
    private MyOffersLisAdapter listAdapter1;
    private static ProgressDialog mProgressDialog;
      Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        Intent jasem= getIntent();
         url = jasem.getStringExtra("url");
        username =jasem.getStringExtra("user");
        api_key = jasem.getStringExtra("api_key");
        points =jasem.getIntExtra("points",200);



        listView = findViewById(R.id.ls2);
        retrieveJSON();


    }


    private void retrieveJSON() {
     showSimpleProgressDialog(StartActivity.this, "Loading...", "يتم جلب العروض..الرجاء الانتظار", false);
        Volley.newRequestQueue(StartActivity.this).add(new StringRequest(Request.Method.POST, url+"?points="+points, new Response.Listener<String>() {
            public void onResponse(String str) {
                Log.d("strrrrr", ">>" + str);

                //  Toast.makeText(Offerlista.this, str, Toast.LENGTH_LONG).show();


                try {
                    JSONObject jSONObject = new JSONObject(str);
                    if (jSONObject.optString("success").equals("true")) {
                        dataModelArrayList1 = new ArrayList<>();
                        JSONArray jSONArray = jSONObject.getJSONArray("offers");
                        for (int i = 0; i < jSONArray.length(); i++) {

                            OffersDataModel offersDataModel = new OffersDataModel();
                            JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                            offersDataModel.setNames(jSONObject2.getString("names"));
                            offersDataModel.setLink(jSONObject2.getString("link"));
                            offersDataModel.setAdcopy(jSONObject2.getString("adcopy"));
                            offersDataModel.setDsc(jSONObject2.getString("dsc"));
                            offersDataModel.setPayout(jSONObject2.getInt("payout"));

                            offersDataModel.setPicture(jSONObject2.getString("picture"));

                            dataModelArrayList1.add(offersDataModel);
                        }
                        setupListview();

                    } else {
                        removeSimpleProgressDialog();
                        Dialog dialog = new Dialog(StartActivity.this, R.style.Theme_AppCompat_NoActionBar);
                        dialog.setContentView(R.layout.dialog_close_app);
                        dialog.show();
                        TextView close = (TextView) dialog.findViewById(R.id.close);
                        close.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                reconnect();
                            }
                        });


                    }

                } catch (JSONException e) {
                    Toast.makeText(StartActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();

                    removeSimpleProgressDialog();
                    Dialog dialog = new Dialog(StartActivity.this, R.style.Theme_AppCompat_NoActionBar);
                    dialog.setContentView(R.layout.dialog_close_app);
                    dialog.show();
                    TextView close = (TextView) dialog.findViewById(R.id.close);
                    close.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            reconnect();
                        }
                    });

                    TextView share = (TextView) dialog.findViewById(R.id.mShare);
                    share.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            finish();
                        }
                    });


                }


            }
        }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(StartActivity.this, volleyError.getMessage(), Toast.LENGTH_LONG).show();
                removeSimpleProgressDialog();
                Dialog dialog = new Dialog(StartActivity.this, R.style.Theme_AppCompat_NoActionBar);
                dialog.setContentView(R.layout.dialog_close_app);
                dialog.show();
                TextView close = (TextView) dialog.findViewById(R.id.close);
                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        reconnect();
                    }
                });

                TextView share = (TextView) dialog.findViewById(R.id.mShare);
                share.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });


            }
        }) {
            /* access modifiers changed from: protected */
            public Map<String, String> getParams() {
                HashMap hashMap = new HashMap();
                hashMap.put("user", username);
                hashMap.put("api_key", api_key);
                return hashMap;
            }
        });
    }


    public void setupListview() {
       removeSimpleProgressDialog();
        this.listAdapter1 = new MyOffersLisAdapter(StartActivity.this, this.dataModelArrayList1);
        this.listView.setAdapter(this.listAdapter1);
    }

    public void removeSimpleProgressDialog() {
        try {
            if (mProgressDialog != null && mProgressDialog.isShowing()) {
                mProgressDialog.dismiss();
                mProgressDialog = null;
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (RuntimeException e2) {
            e2.printStackTrace();
        } catch (Exception e3) {
            e3.printStackTrace();
        }
    }

    public void showSimpleProgressDialog(Context context, String str, String str2, boolean z) {
        try {
            if (mProgressDialog == null) {
                mProgressDialog = ProgressDialog.show(context, str, str2);
                mProgressDialog.setCancelable(z);
            }
            if (!mProgressDialog.isShowing()) {
                mProgressDialog.show();
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (RuntimeException e2) {
            e2.printStackTrace();
        } catch (Exception e3) {
            e3.printStackTrace();
        }
    }


    public void reconnect() {

        retrieveJSON();
    }


    @Override
    protected void onPause() {
        super.onPause();
      //  retrieveJSON();
    }

    @Override
    protected void onResume() {
        super.onResume();
       // retrieveJSON();


    }
}