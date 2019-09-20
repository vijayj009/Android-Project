package com.barclays.finambulance.ui.login;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.barclays.finambulance.R;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class OffersActivity extends AppCompatActivity {

/*    @Override
    public void onCreate(Bundle savedInstanceState) {
        MutableLiveData<LoginResult> loginResult = new MutableLiveData<>();
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        Bundle extras = getIntent().getExtras();
        String username = extras.getString("username");
        loginResult.setValue(new LoginResult(new LoggedInUserView(username)));
        setContentView(R.layout.activity_offers);
    }

    public void popUpWindow(View view){
        Intent intent = new Intent(getApplicationContext(),AvailableFacilityImpl.class);
        startActivity(intent);
    }*/

    Button showPopupBtn, closePopupBtn, negativebutton,skipButton,submitButton;
    PopupWindow popupWindow;
    PopupWindow submitPopWindow;
    RelativeLayout linearLayout1;
    RelativeLayout linearLayout2;
    TextView textView;
    private RequestQueue requestQueue;
    private static OffersActivity mInstance;

    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;
    final String url = "https://www.google.com";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offers);
        Bundle extras = getIntent().getExtras();
        final String username = extras.getString("username");
        showPopupBtn = (Button) findViewById(R.id.showPopupBtn);
        //showPopupBtn.setBackgroundColor(Color.GREEN);
        negativebutton = findViewById(R.id.negativebutton);
      //  negativebutton.setBackgroundColor(Color.RED);
        linearLayout1 = (RelativeLayout) findViewById(R.id.linearLayout1);
       // linearLayout2 = (RelativeLayout) findViewById(R.id.);

        //textView = findViewById(R.id.textView);
        /*String message = "Dear %s , You appear to be situated in hurricane-affected  zone\n Are you in need of payment relief support during this natural tragedy?";
        textView.setText(message);*/

         Button btnRequest;
/*        final String url = "https://www.google.com";
        URL baseUrl = null;
        try {
             baseUrl = new URL("https://www.google.com");
             HttpURLConnection connection = (HttpURLConnection) baseUrl.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            int responseCode = connection.getResponseCode();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        negativebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater layoutInflater = (LayoutInflater) OffersActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View customView = layoutInflater.inflate(R.layout.activity_notrequired, null);
                closePopupBtn = (Button) customView.findViewById(R.id.closePopupBtn);
                /*String received_message = getString(R.string.received_message);
                Resources resources = getResources();
                @SuppressLint("StringFormatMatches") String response = String.format(resources.getString(R.string.received_message,responseString));*/
                //instantiate popup window

                popupWindow = new PopupWindow(customView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

                //display the popup window
                popupWindow.showAtLocation(linearLayout1, Gravity.CENTER, 0, 0);

                //close the popup window on button click
                closePopupBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                    }
                });
            }
        });
        showPopupBtn.setOnClickListener(new View.OnClickListener() {

            //@SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {
                //instantiate the popup.xml layout file
                LayoutInflater layoutInflater = (LayoutInflater) OffersActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View customView = null;
                String responseString = sendResponse(username);
                Resources resources = getResources();

                if("Nishigandha".equals(username)) {
                    customView = layoutInflater.inflate(R.layout.activity_facility, null);
                }else if("Siva".equals(username)){
                    customView = layoutInflater.inflate(R.layout.activity_fee_waiver,null);

                }else if ("Vijay".equals(username)){
                    customView = layoutInflater.inflate(R.layout.activity_apr_discount,null);
                }
               // customView = layoutInflater.inflate(R.layout.activity_facility, null);
                closePopupBtn = (Button) customView.findViewById(R.id.closePopupBtn);
                skipButton = customView.findViewById(R.id.skipButton);
                /*String received_message = getString(R.string.received_message);
                Resources resources = getResources();
                @SuppressLint("StringFormatMatches") String response = String.format(resources.getString(R.string.received_message,responseString));*/
                resources.getString(R.string.received_message).concat(responseString);
                //instantiate popup window

                popupWindow = new PopupWindow(customView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

                //display the popup window
                popupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
                popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
                popupWindow.showAtLocation(linearLayout1, Gravity.CENTER, 0, 0);

                //close the popup window on button click
                closePopupBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        LayoutInflater layoutInflater = (LayoutInflater) OffersActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                        View view = layoutInflater.inflate(R.layout.activity_accept,null);
                        submitButton = view.findViewById(R.id.submitButton);
                        submitPopWindow = new PopupWindow(view,ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

                        submitPopWindow.showAtLocation(linearLayout1, Gravity.CENTER, 0, 0);

                        submitButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                submitPopWindow.dismiss();
                            }
                        });
                        popupWindow.dismiss();
                    }
                });

            }
        });
    }



    public static synchronized OffersActivity getInstance() {
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (requestQueue == null)
            requestQueue = Volley.newRequestQueue(getApplicationContext());
        return requestQueue;
    }
    public void addToRequestQueue(Request request, String tag) {
        request.setTag(tag);
        getRequestQueue().add(request);
    }
    /*
    Cancel all the requests matching with the given tag
    */
    public void cancelAllRequests(String tag) {
        getRequestQueue().cancelAll(tag);
    }

        public String getOffersDetails (String username){
            return null;
        }
    public String sendResponse(String userName){
        mRequestQueue = Volley.newRequestQueue(this);
        mStringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

               // Toast.makeText(getApplicationContext(),"Response :" + response.toString(), Toast.LENGTH_LONG).show();//display the response on screen

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("Hello","Error :" + error.toString());
            }
        });
        mRequestQueue.add(mStringRequest);
        return "Congratulation!!! Due date is pushed by 10 days.";
        }
}

