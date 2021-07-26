package Async;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import activity.CityActivity;

public class cityAsync extends AsyncTask<Void, Void, String> {

    private CityActivity activity;
    public cityAsync(CityActivity activity){
        this.activity=activity;
    }
    @Override
    protected String doInBackground(Void... voids) {
        try {
            String rpc = "getAllCity.php";
            URL url = new URL(CONST.url_base+rpc);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.connect();
            int responseCode = connection.getResponseCode();
            if(responseCode == 200) {
                InputStream inputStream = connection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                Scanner scanner = new Scanner(inputStream);
                String retour = "";
                scanner.useDelimiter("\n");
                while(scanner.hasNext()) {
                    retour += scanner.next();
                }
                return retour;
            }
            else return "3";
        }catch(MalformedURLException e) {
            e.printStackTrace();
            return "1";
        }
        catch(IOException e) {
            e.printStackTrace();
            Log.d("CA", "Erreure  :" + e.getMessage());
            return "2";
        }
    }
    @Override
    protected void onPostExecute(String response) {
        if(response.equals("1") || response.equals("2") || response.equals("3")) {
            this.activity.populate_error(response);
        } else {
            try {
                JSONObject jResponse = new JSONObject(response);
                if(jResponse.isNull("error_code")) {
                    JSONArray jListCity = jResponse.getJSONArray("tab_City");
                    List<String> listCity = new ArrayList<String>();
                    for (int i = 0; i < jListCity.length(); i++) {
                        JSONObject jIndexResponse = jListCity.getJSONObject(i);
                        listCity.add(jIndexResponse.getString("name"));
                    }
                    Collections.sort(listCity);
                    this.activity.populate(listCity);
                } else {
                    int error_code = jResponse.getInt("error_code");
                    this.activity.populate_error(String.valueOf(error_code));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
