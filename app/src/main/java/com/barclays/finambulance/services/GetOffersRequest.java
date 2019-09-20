package com.barclays.finambulance.services;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.barclays.finambulance.ui.login.OffersActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.logging.Logger;

public class GetOffersRequest {
    public void request(String username){

       String baseUrl = "https://www.google.com";

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                baseUrl, null,
                new Response.Listener() {
                    @Override
                    public void onResponse(Object response) {

                    }

                    },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Failure Callback
                    }
                });

        OffersActivity.getInstance().addToRequestQueue(jsonObjReq,"tag");
// Adding the request to the queue along with a unique string tag

   /*     StringRequest request = new StringRequest(Request.Method.GET, baseUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                    final JSONObject jsonObject = new JSONObject(response);
            }
        });*/
        //JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,baseUrl,null, new JsRe);
    }
}
