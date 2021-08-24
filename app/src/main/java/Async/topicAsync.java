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
import java.util.Scanner;

import POJO.Topic;
import activity.TopicActivity;

public class topicAsync  extends AsyncTask<Void,Void,String> {



    private TopicActivity activity;
    public topicAsync(TopicActivity activity){this.activity=activity;}


    @Override
    protected String doInBackground(Void... voids) {
        try {
            String rpc = "getAllTopic.php";
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
                if(jResponse.isNull("code")) {
                    JSONArray jListTopic = jResponse.getJSONArray("list_topic");
                    ArrayList<Topic> listTopic = new ArrayList<Topic>();
                    for (int i = 0; i < jListTopic.length(); i++) {
                        JSONObject jIndexResponse = jListTopic.getJSONObject(i);
                        Topic topic = new Topic();
                        topic.setTitle(jIndexResponse.getString("title"));
                        topic.setAuthor(jIndexResponse.getString("author"));
                        topic.setIdTopic(jIndexResponse.getInt("id"));
                        listTopic.add(topic);
                    }
                    this.activity.populate(listTopic);
                } else {
                    int error_code = jResponse.getInt("code");
                    this.activity.populate_error(String.valueOf(error_code));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }
}


