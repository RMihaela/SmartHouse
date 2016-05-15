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

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URL;

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
     * @param url The URL of the method
     * @param obj A pointer to the Object whose 'objMethod' method should be called to handle the JSONObject response
     * @param objMethod The method that will be called when a response is received. The method MUST
     *                   only have ONE JSONObject parameter. Ex: 'public [methodReturnType] someMethod(JSONObject obj);'
     */
    public void startGETRequest(String url, final Object obj, final String objMethod) {

        final Class[] parameterTypes = {JSONObject.class};

        JsonObjectRequest jsObjRequest = new JsonObjectRequest
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
        requestQueue.add(jsObjRequest);
    }

    public static String excutePut(String targetURL, String urlParameters) {
        HttpURLConnection connection = null;
        try {
            //Create connection
            URL url = new URL(targetURL);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("PUT"); //aici poti schimba metoda de apelare gen: GET, POST, PUT, DELETE (api ul nu are delete :)))  )

            connection.setRequestProperty("Content-Length",
                    Integer.toString(urlParameters.getBytes().length));


            connection.setUseCaches(false); // pana aici seteaza niste headere - merge si fara astea dar s ar putea sa fie surprize
            connection.setDoOutput(true); // aici spune ca voi avea un body pentru request, adica trimit niste date. ca si cum as deschide un socket

            //Send request
            DataOutputStream wr = new DataOutputStream(
                    connection.getOutputStream()); // aici mai exact deschit un fel de socket-- nu e socket, doar de comparatie spun asa :D
            wr.writeBytes(urlParameters);
            wr.close();// scriu corpul requstului:

    /*
        un exemplu de corp al requestului poate fi asa, legat de temperatura pentru frigider

        {
            "temperatura":40; // asta daca vrei sa ti se strice mancarea
        }

        sau

        {
            "nume":"Noul si misto-ul meu frigider",
            "temperatura": -40; // asta pentru cand e faorte cald afara

        }
    */
            //si dupa ce se trimite requestul PUT cu datele alea, eu le modific in baza de date, scriu in alta tabela ca s-a schimbat ceva
            // pentru ca monica sa stie si sa poate "face frigiderul sa invete" ceva si apoi trimit inapoi datele actualizate legate de friciderul //respectiv, deci va trebui sa iei raspunsul de la api :)
            //Get Response
            InputStream is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            StringBuilder response = new StringBuilder(); // or StringBuffer if not Java 5+
            String line;
            // daca aveti nevoie sa stiti si codul http de raspuns il puteti lua simplu asa : connection.getResponseCode();
            if (connection.getResponseCode() != 200) {
                return null; //deci ceva nu a mers bine;
            }

            while ((line = rd.readLine()) != null) {
                response.append(line);
                response.append('\r');
            }
            rd.close();
            JSONObject ob = new JSONObject(response.toString());
            return ob.toString();
            // raspunsul il primesti ca si un string, dar trebuie sa le transformi in JSON ca sa lucrezi usor cu ele ...
            // aveti nevoie de 2 tipuri: JSONArray - lista de Json atunci cand luati taote frigiderele: raspunsul e [{...},{...}]
            // si JSONObject care e un json obisnuit {}
            // daca nu aveti nevoie neaparat de raspuns ci doar de codul http, returnati codul http
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

}
