package com.avci.berkeravciproject1;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MyIntentService extends IntentService {
    JSONObject jsonObject;
    RequestQueue requestQueue;
    private String jsonStr;

    ArrayList<Product> productList;

    public MyIntentService() {
        super("MyIntentService");
        Log.d("Service","Service Started");
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        productList = new ArrayList<>();

        jsonStr = loadFileFromRaw("products");

        if (jsonStr != null) {
            try {
                jsonObject = new JSONObject(jsonStr);
                JSONArray jsonArr = jsonObject.getJSONArray("products");
                Log.d("JSON", jsonArr.length()+"");

                for(int i=0; i<jsonArr.length(); i++){

                    JSONObject jsobj = jsonArr.getJSONObject(i);
                    String name = jsobj.getString("name");
                    String number = jsobj.getString("number");
                    String price = jsobj.getString("price");

                    Product product = new Product(name,number,price);
                    productList.add(product);
                }
                Intent broadcastIntent = new Intent();
                broadcastIntent.setAction("JSON_PARSE_COMPLETED_ACTION");
                if(productList.size()>0) {
                    Bundle b=new Bundle();
                    b.putParcelableArrayList("products", productList);
                    broadcastIntent.putExtras(b);

                    broadcastIntent.putExtra("result","FOUND");
                }
                else{
                    broadcastIntent.putExtra("result","NOTFOUND");
                }
                sendBroadcast(broadcastIntent);
            } catch (JSONException ee) {
                ee.printStackTrace();
            }
        }
    }
    private String loadFileFromRaw(String fileName) {
        String fileContent = null;
        try {
            InputStream is = getResources().openRawResource(
                    getResources().getIdentifier(fileName,
                            "raw", getPackageName()));
            int size = is.available();
            byte[] buffer = new byte[size];

            is.read(buffer);
            is.close();

            fileContent = new String(buffer, "UTF-8");

        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return fileContent;
    }
}
