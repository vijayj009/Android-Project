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
import com.barclays.finambulance.ui.login.LoginActivity;
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

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class UserProfile extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    PopupWindow popupWindow,popupWindowNotRequired,popupWindowValid,popwinddowValidNotRequired,popWindowAvailableFacility;
    Button popupBtn,negativebutton,showPopupBtn,closePopupBtn,skipButton,submitButton,okBtn;
    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;
    FloatingActionButton fab;
    Button logout;
    final String url = "https://wilpdm31491.juniper.com:8086/offer-engine/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Bundle extras = getIntent().getExtras();
        final String username = extras.getString("username");

         fab = findViewById(R.id.fab);
/*        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
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
                R.id.nav_tools, R.id.nav_share, R.id.nav_send, R.id.logout)
                .setDrawerLayout(drawer)
                .build();

      /*if (id == R.id.logout) {
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }*/

//        mAppBarConfiguration.getDrawerLayout().findViewById(R.id.logout);
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

        //popupBtn = findViewById(R.id.popupBtn);
        final DrawerLayout layout = findViewById(R.id.drawer_layout);
        fab.setOnClickListener(new View.OnClickListener() {
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
                                popupWindow.dismiss();
                                showPopupBtn.setVisibility(View.INVISIBLE);
                            }
                        });

                    }
                });

                showPopupBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        LayoutInflater layoutInflater = (LayoutInflater) UserProfile.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                       View facilityView= null;
                       View feeWaiverView= null;
                       View aprDiscountView= null;
                       View creditIncremnetView = null;
                      /* String responseString = sendResponse(username);
                       Toast.makeText(getApplicationContext(),"Response :" + responseString, Toast.LENGTH_LONG).show();*/
                        if("Ben".equals(username)) {
                             feeWaiverView = layoutInflater.inflate(R.layout.activity_fee_waiver, null);

                            closePopupBtn = (Button) feeWaiverView.findViewById(R.id.closePopupBtn);
                            skipButton = feeWaiverView.findViewById(R.id.skipButton);


                            popWindowAvailableFacility = new PopupWindow(feeWaiverView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

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
                                    fab.setEnabled(false);
                                    //popupBtn.setVisibility(View.INVISIBLE);
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
                                    fab.setEnabled(false);
                                    //popupBtn.setVisibility(View.INVISIBLE);
                                }
                            });
                        }else if("Tom".equals(username)){
                            String response = sendResponse(username);
                            Toast.makeText(getApplicationContext(),"Response :" + response, Toast.LENGTH_LONG).show();
                            facilityView = layoutInflater.inflate(R.layout.activity_facility,null);
                            closePopupBtn = (Button) facilityView.findViewById(R.id.acceptBtn);
                            skipButton = facilityView.findViewById(R.id.ignoreBtn);


                            popWindowAvailableFacility = new PopupWindow(facilityView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

                            //display the popup window
                            popWindowAvailableFacility.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
                            popWindowAvailableFacility.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
                            popWindowAvailableFacility.showAtLocation(layout, Gravity.CENTER, 0, 0);
                            closePopupBtn.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    LayoutInflater layoutInflater = (LayoutInflater) UserProfile.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                                    View validView = layoutInflater.inflate(R.layout.activity_accept_late_fee_waiver,null);
                                    submitButton = validView.findViewById(R.id.allDoneBtn);
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
                                    fab.setEnabled(false);
                                    //popupBtn.setVisibility(View.INVISIBLE);
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
                                    fab.setEnabled(false);
                                    //popupBtn.setVisibility(View.INVISIBLE);
                                }
                            });
                        }
                        else if ("Steve".equals(username)){
                            aprDiscountView = layoutInflater.inflate(R.layout.activity_apr_discount,null);

                            closePopupBtn = (Button) aprDiscountView.findViewById(R.id.agreeBtn);
                            skipButton = aprDiscountView.findViewById(R.id.nevermindbtn);


                            popWindowAvailableFacility = new PopupWindow(aprDiscountView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

                            //display the popup window
                            popWindowAvailableFacility.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
                            popWindowAvailableFacility.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
                            popWindowAvailableFacility.showAtLocation(layout, Gravity.CENTER, 0, 0);
                            closePopupBtn.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    LayoutInflater layoutInflater = (LayoutInflater) UserProfile.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                                    View validView = layoutInflater.inflate(R.layout.activity_accept_apr,null);
                                    submitButton = validView.findViewById(R.id.doneBtn);
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
                                    fab.setEnabled(false);
                                    //popupBtn.setVisibility(View.INVISIBLE);
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
                                    fab.setEnabled(false);
                                    //popupBtn.setVisibility(View.INVISIBLE);
                                }
                            });
                        }
                        else if("John".equals(username)){
                            creditIncremnetView = layoutInflater.inflate(R.layout.activity_instant_credit,null);

                            closePopupBtn = (Button) creditIncremnetView.findViewById(R.id.acptBtn);
                            skipButton = creditIncremnetView.findViewById(R.id.dontCareBtn);


                            popWindowAvailableFacility = new PopupWindow(creditIncremnetView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

                            //display the popup window
                            popWindowAvailableFacility.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
                            popWindowAvailableFacility.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
                            popWindowAvailableFacility.showAtLocation(layout, Gravity.CENTER, 0, 0);
                            closePopupBtn.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    LayoutInflater layoutInflater = (LayoutInflater) UserProfile.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                                    View validView = layoutInflater.inflate(R.layout.activity_accept_fee,null);
                                    submitButton = validView.findViewById(R.id.finishBtn);
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
                                    fab.setEnabled(false);
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
                                    fab.setEnabled(false);
                                    //popupBtn.setVisibility(View.INVISIBLE);
                                }
                            });
                        }
                    }
                });


            }
        });
        //popupBtn.setVisibility(View.INVISIBLE);

    }


   /* public void offerdetails(){
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
    }*/
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

    public String sendResponse(String userName) {
        StringBuilder result = new StringBuilder();
        HttpURLConnection urlConnection = null;
        try {
            String apiUrl = url.concat(userName); // concatenate uri with base url eg: localhost:8080/ + uri
            URL requestUrl = new URL(apiUrl);
            urlConnection = (HttpURLConnection) requestUrl.openConnection();
            urlConnection.connect(); // no connection is made
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            urlConnection.disconnect();
        }
        return result.toString();
       /* String baseUrl = url.concat(userName);
        mRequestQueue = Volley.newRequestQueue(this);
        mStringRequest = new StringRequest(Request.Method.GET, baseUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

               Toast.makeText(getApplicationContext(),"Response :" + response.toString(), Toast.LENGTH_LONG).show();//display the response on screen

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("Hello","Error :" + error.toString());
            }
        });
        mRequestQueue.add(mStringRequest);
        return "Congratulation!!! Due date is pushed by 10 days.";
    }*/
    }
}
