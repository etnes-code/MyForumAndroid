package Async;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import POJO.Post;
import POJO.Topic;
import activity.PostActivity;

public class postAsync extends AsyncTask<String, Void, String> {
    PostActivity activity;
    public postAsync(PostActivity activity){this.activity=activity;}
    @Override
    protected String doInBackground(String...data) {
        try {

            String rpc = "GetAllMessage.php";
            URL url = new URL(CONST.url_base+rpc);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setConnectTimeout(5000);
            OutputStream outputStream = connection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            String id = "idTopic=" + data[0];
            String param = id;
            bufferedWriter.write(param);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();

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
            return "2";
        }
}
    protected void onPostExecute(String response) {
        if(response.equals("1") || response.equals("2") || response.equals("3")) {
            this.activity.populate_error(response);
        } else {
            try {
                JSONObject jResponse = new JSONObject(response);
                if(jResponse.isNull("code")) {
                    JSONArray jListPost = jResponse.getJSONArray("list_message");
                    ArrayList<Post> listPost = new ArrayList<Post>();
                    for (int i = 0; i < jListPost.length(); i++) {
                        JSONObject jIndexResponse = jListPost.getJSONObject(i);
                        Post post=new Post();
                        post.setId(jIndexResponse.getInt("id"));
                        post.setContent(jIndexResponse.getString("content"));
                        post.setDate(jIndexResponse.getString("date"));
                        post.setAuthor(jIndexResponse.getString("author"));
                        post.setGender(jIndexResponse.getString("gender"));
                        listPost.add(post);
                    }
                    this.activity.populate_list(listPost);
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
