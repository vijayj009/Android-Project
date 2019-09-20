package com.barclays.finambulance;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.barclays.finambulance.ui.login.OffersActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.text.Layout;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class UserProfile extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    PopupWindow popupWindow,popupWindowNotRequired,popupWindowValid,popwinddowValidNotRequired,popWindowAvailableFacility;
    Button popupBtn,negativebutton,showPopupBtn,closePopupBtn,skipButton,submitButton,okBtn;
    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;
    final String url = "https://www.google.com";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Bundle extras = getIntent().getExtras();
        final String username = extras.getString("username");

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView =  (NavigationView) findViewById(R.id.nav_view);
        //navigationView.setNavigationItemSelectedListener((NavigationView.OnNavigationItemSelectedListener) this);
        TextView txtProfileName = (TextView) navigationView.getHeaderView(0).findViewById(R.id.profileHolder);
        txtProfileName.setText(username);
        TextView viewById = navigationView.getHeaderView(0).findViewById(R.id.profileEmailAddress);
        String emailId = username.concat("@yahoo.com");
        viewById.setText(emailId);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,
                R.id.nav_tools, R.id.nav_share, R.id.nav_send)
                .setDrawerLayout(drawer)
                .build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        // change username

      /*  Layout headerLayout = navigationView.
        TextView viewProfileHolder = viewUser.findViewById(R.id.profileHolder);
        viewProfileHolder.setText(username);

        TextView viewEmailId = viewUser.findViewById(R.id.profileEmailAddress);
        String emailId = username.concat("@yahoo.com");
        viewEmailId.setText(emailId);*/

        popupBtn = findViewById(R.id.popupBtn);
        final DrawerLayout layout = findViewById(R.id.drawer_layout);
        popupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater layoutInflater = (LayoutInflater) UserProfile.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View customView = layoutInflater.inflate(R.layout.activity_offers, null);
                Toast.makeText(getApplicationContext(), "Awesome!!!!!!!!!", Toast.LENGTH_LONG).show();
                TextView textView = customView.findViewById(R.id.textOffersView);
                showPopupBtn = customView.findViewById(R.id.showPopupBtn);
                //showPopupBtn.setBackgroundColor(Color.GREEN);
                negativebutton = customView.findViewById(R.id.negativebutton);
                String message = "Dear ".concat(username).concat(", \n You appear to be situated in hurricane-affected  zone \n Are you in need of payment relief support during this natural tragedy?");
                textView.setText(message);
                popupWindow = new PopupWindow(customView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

                //display the popup window
                popupWindow.showAtLocation(layout, Gravity.CENTER, 0, 0);



                negativebutton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        LayoutInflater layoutInflater = (LayoutInflater) UserProfile.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                        View viewNotRequired = layoutInflater.inflate(R.layout.activity_notrequired, null);
                        RelativeLayout noServiceLayout = viewNotRequired.findViewById(R.id.layoutnotrequired);
                        closePopupBtn = (Button) viewNotRequired.findViewById(R.id.closePopupBtn);
                        //instantiate popup window

                        popupWindowNotRequired = new PopupWindow(viewNotRequired, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

                        //display the popup window
                        popupWindowNotRequired.showAtLocation(layout, Gravity.CENTER, 0, 0);

                        //close the popup window on button click
                        closePopupBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                popupWindowNotRequired.dismiss();
                            }
                        });
                    }
                });

                showPopupBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        LayoutInflater layoutInflater = (LayoutInflater) UserProfile.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                        View facilityView = null;
                        String responseString = sendResponse(username);

                        if("Nishigandha".equals(username)) {
                            facilityView = layoutInflater.inflate(R.layout.activity_facility, null);
                        }else if("Siva".equals(username)){
                            facilityView = layoutInflater.inflate(R.layout.activity_fee_waiver,null);
                        }
                        else if ("Vijay".equals(username)){
                            facilityView = layoutInflater.inflate(R.layout.activity_apr_discount,null);
                        }
                        closePopupBtn = (Button) facilityView.findViewById(R.id.closePopupBtn);
                        skipButton = facilityView.findViewById(R.id.skipButton);


                        popWindowAvailableFacility = new PopupWindow(facilityView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

                        //display the popup window
                        popWindowAvailableFacility.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
                        popWindowAvailableFacility.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
                        popWindowAvailableFacility.showAtLocation(layout, Gravity.CENTER, 0, 0);
                        closePopupBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                LayoutInflater layoutInflater = (LayoutInflater) UserProfile.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                                View validView = layoutInflater.inflate(R.layout.activity_accept,null);
                                submitButton = validView.findViewById(R.id.submitButton);
                                popupWindowValid = new PopupWindow(validView,ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

                                popupWindowValid.showAtLocation(layout, Gravity.CENTER, 0, 0);

                                submitButton.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        popupWindowValid.dismiss();
                                    }
                                });
                                popWindowAvailableFacility.dismiss();
                                popupWindow.dismiss();
                                popupBtn.setVisibility(View.INVISIBLE);
                            }
                        });
                        skipButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                LayoutInflater layoutInflater = (LayoutInflater) UserProfile.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                                View skipView = layoutInflater.inflate(R.layout.activity_skip,null);
                                okBtn = skipView.findViewById(R.id.okBtn);
                                popwinddowValidNotRequired = new PopupWindow(skipView,ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);

                                popwinddowValidNotRequired.showAtLocation(layout,Gravity.CENTER,0,0);

                                okBtn.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        popwinddowValidNotRequired.dismiss();
                                    }
                                });
                                popWindowAvailableFacility.dismiss();
                                popupWindow.dismiss();
                                popupBtn.setVisibility(View.INVISIBLE);
                            }
                        });
                    }
                });


            }
        });
        //popupBtn.setVisibility(View.INVISIBLE);

    }


    public void offerdetails(){
        popupBtn = findViewById(R.id.popupBtn);
        final DrawerLayout layout = findViewById(R.id.drawer_layout);

        popupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater layoutInflater = (LayoutInflater) UserProfile.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View customView = layoutInflater.inflate(R.layout.activity_offers, null);

                popupWindow = new PopupWindow(customView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

                //display the popup window
                popupWindow.showAtLocation(layout, Gravity.CENTER, 0, 0);

            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.user_profile, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
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
