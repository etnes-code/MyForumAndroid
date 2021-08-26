package Async;

import android.os.AsyncTask;
import android.util.Log;

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
import java.util.Scanner;

import POJO.Post;
import activity.PostActivity;

public class newPostAsync extends AsyncTask<Object, Void, String> {
    PostActivity activity;
    public newPostAsync(PostActivity activity){this.activity=activity;}
    @Override
    protected String doInBackground(Object...data) {
        try {
            String rpc = "savePost.php";
            URL url = new URL(CONST.url_base +rpc);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setConnectTimeout(5000);
            OutputStream outputStream = connection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            Post post = (Post) data[0];
            String content = "content=" + post.getContent();
            String date = "date="+ post.getDate();
            String author = "author=" + post.getAuthor();
            String gender="gender=" + post.getGender();
            String idtopic = "idTopic="+ String.valueOf(data[1]);
            String param = content+"&"+date+"&"+author+"&"+gender+"&"+idtopic;
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
            Log.d("CA", "Erreure  :" + e.getMessage());
            return "2";
        }
    }

    protected void onPostExecute(String response) {
        if (response.equals("1") || response.equals("2") || response.equals("3") ) {
            this.activity.populate_error(response);
        }else {
            try {
                JSONObject jResponse = new JSONObject(response);
                int code = jResponse.getInt("code");
                if(code==4000)
                    this.activity.populate();
                else
                    this.activity.populate_error(String.valueOf(code));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
