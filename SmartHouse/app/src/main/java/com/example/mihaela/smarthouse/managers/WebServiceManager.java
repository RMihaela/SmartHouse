package com.example.mihaela.smarthouse.managers;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.lang.reflect.Method;

/**
 * Created by Mihaela on 15.05.2016.
 */
public class WebServiceManager {

    private static WebServiceManager instance;
    private static RequestQueue requestQueue;
    private static Context context;

    /**
     * @param context The current application context
     */
    private WebServiceManager(Context context) {
        this.context = context;
        this.requestQueue = getRequestQueue();
    }

    /**
     * @param context Current application context. Usage:  WebServiceManager.getInstance(this.getApplicationContext());
     * @return The WebServiceManager instance for the given context.
     */
    public static synchronized WebServiceManager getInstance(Context context) {
        if (instance == null) {
            instance = new WebServiceManager(context);
        }
        return instance;
    }

    public RequestQueue getRequestQueue() {
        if (this.requestQueue == null) {
            // getApplicationContext() is key, it keeps you from leaking the
            // Activity or BroadcastReceiver if someone passes one in.
            this.requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        }
        return this.requestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }

    /*  startGETRequest example

        String requestURL = "http://52.38.78.32:8080/api/sensors/garden/default";
        WebServiceManager.getInstance(this.getApplicationContext()).startGETRequest(requestURL, this, "logJson");
     */

    /**
     * @param url       The URL of the method
     * @param obj       A pointer to the Object whose 'objMethod' method should be called to handle the JSONObject response
     * @param objMethod The method that will be called when a response is received. The method MUST
     *                  only have ONE JSONObject parameter. Ex: 'public [methodReturnType] someMethod(JSONObject obj);'
     */
    public void startGETRequest(String url, final Object obj, final String objMethod) {

        final Class[] parameterTypes = {JSONObject.class};
        JsonObjectRequest jsonGetRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Method method = obj.getClass().getMethod(objMethod, parameterTypes);
                            method.invoke(obj, response);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("JSON", "That didn't work!" + error.getLocalizedMessage());
                    }
                });
        requestQueue.add(jsonGetRequest);
    }

    public void startGETRequest(String url, final JSONObject responseObject) {

        final Class[] parameterTypes = {JSONObject.class};
        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url, responseObject, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("JSON", "That didn't work!" + error.getLocalizedMessage());
                    }
                });
        requestQueue.add(jsObjRequest);
    }

    /* POST request call example:

        String requestURL = "http://jsonplaceholder.typicode.com/posts";
        Map<String, String> parameters = new HashMap<>();
        parameters.put("parameterName", "parameterValue");
        JSONObject jsonObject = new JSONObject(parameters);

        WebServiceManager.getInstance(this.getApplicationContext()).startPOSTRequest(requestURL, jsonObject, this, "logJson");

     */

    /**
     * @param url           The URL of the method
     */
    public void startPOSTRequest(String url) {
        final Class[] parameterTypes = {JSONObject.class};

        JsonObjectRequest jsonPostRequest = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            Method method = this.getClass().getMethod("nothing", parameterTypes);
                            method.invoke(this, response);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Log.e("Error: ", error.getMessage());
            }
        });

        requestQueue.add(jsonPostRequest);
    }

    public void nothing() {
        return;
    }

    /* PUT request call example:

        String requestURL = "http://jsonplaceholder.typicode.com/posts";
        Map<String, String> parameters = new HashMap<>();
        parameters.put("parameterName", "parameterValue");
        JSONObject jsonObject = new JSONObject(parameters);

        WebServiceManager.getInstance(this.getApplicationContext()).startPUTRequest(requestURL, jsonObject, this, "logJson");

     */

    /**
     * @param url           The URL of the method
     * @param requestObject The json object containing the parameters that will be given to the method
     * @param obj           A pointer to the Object whose 'objMethod' method should be called to handle the JSONObject response
     * @param objMethod     The method that will be called when a response is received. The method MUST
     *                      only have ONE JSONObject parameter. Ex: 'public [methodReturnType] someMethod(JSONObject obj);'
     */
    public void startPUTRequest(String url, final JSONObject requestObject, final Object obj, final String objMethod) {
        final Class[] parameterTypes = {JSONObject.class};

        JsonObjectRequest jsonPutRequest = new JsonObjectRequest(Request.Method.PUT, url, requestObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Method method = obj.getClass().getMethod(objMethod, parameterTypes);
                            method.invoke(obj, response);
                        } catch (Exception e) {
                            Log.i("[WebServiceManager]", "Couldn't find the specified method.");
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error: ", "");
            }
        });

        requestQueue.add(jsonPutRequest);
    }

}
