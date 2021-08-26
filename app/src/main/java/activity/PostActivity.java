package activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import Async.newPostAsync;
import Async.postAsync;
import Async.topicAsync;
import POJO.Post;
import POJO.Topic;
import POJO.User;

public class PostActivity extends AppCompatActivity {
    private Button btnnew;
    private EditText etNew;
    private User user;
    private  int idrecup;
    private LinearLayout llPost;


    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(etNew.getText().toString().equals("")){
                Toast.makeText(PostActivity.this,getResources().getString(R.string.newpostempty),Toast.LENGTH_LONG).show();
            }else{
                SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
                String date = sdf.format(new Date());
                Post post= new Post(etNew.getText().toString(),date,user.getLoginName(),user.getGender());
                new newPostAsync(PostActivity.this).execute(post,idrecup);
                etNew.setText("");
                }
            }
        };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        btnnew=findViewById(R.id.btnNewMessage);
        btnnew.setOnClickListener(listener);
        etNew=findViewById(R.id.et_newMessage);
        llPost=findViewById(R.id.ll_message);
        Intent intent_recup= getIntent();
        user= (User) intent_recup.getSerializableExtra("user");
        idrecup= intent_recup.getIntExtra("idtopic",0);
        if(savedInstanceState!=null){
            llPost.removeAllViews();
            user= (User) savedInstanceState.getSerializable("user");
            idrecup=savedInstanceState.getInt("idrecup");
            new postAsync(PostActivity.this).execute(String.valueOf(idrecup));
        }else{
            user= (User) intent_recup.getSerializableExtra("user");
            idrecup= intent_recup.getIntExtra("idtopic",0);
            new postAsync(PostActivity.this).execute(String.valueOf(idrecup));
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        llPost.removeAllViews();
        new postAsync(PostActivity.this).execute(String.valueOf(idrecup));

    }

    public void populate(){

        Toast.makeText(PostActivity.this,getResources().getString(R.string.postCreated),Toast.LENGTH_LONG).show();
        llPost.removeAllViews();
        new postAsync(PostActivity.this).execute(String.valueOf(idrecup));
    }
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("user",user);
        outState.putInt("idrecup",idrecup);
    }
    public void populate_list(ArrayList<Post> listPost){
        if(listPost != null){
            TextView tv;
            for(Post item : listPost){
                tv=new TextView(this);
                tv.setId(item.getId());
                tv.setText("Date:" +item.getDate()+"\n"+item.getContent()+"\n"+getResources().getString(R.string.PostWritter)+item.getAuthor());
                tv.setPadding(0,10,0,0);
                if(item.getGender().equals("Male")){
                    tv.setTextColor(getResources().getColor(R.color.color_male));
                }else{
                    tv.setTextColor(getResources().getColor(R.color.color_female));
                }
                llPost.addView(tv);
            }
        }else {
            TextView tv=new TextView(this);
            tv.setText(getResources().getString(R.string.emptylistPost));
            llPost.addView(tv);
        }
    }
    public void populate_error(String response){

        //erreur concernant la connexion à la bdd et au rpc
        if(response.equals("1"))
            Toast.makeText(PostActivity.this,getResources().getString(R.string.code_1),Toast.LENGTH_LONG).show();
        if(response.equals("2"))
            Toast.makeText(PostActivity.this,getResources().getString(R.string.code_2),Toast.LENGTH_LONG).show();
        if(response.equals("3"))
            Toast.makeText(PostActivity.this,getResources().getString(R.string.code_1),Toast.LENGTH_LONG).show();
        //erreur sur l'envoi des params
        if(response.equals("4001") || response.equals("4002") || response.equals("4003") || response.equals("4004") || response.equals("4005"))
            Toast.makeText(PostActivity.this,getResources().getString(R.string.errorP),Toast.LENGTH_LONG).show();
        if(response.equals("4006"))
            Toast.makeText(PostActivity.this,getResources().getString(R.string.errorRequest),Toast.LENGTH_LONG).show();
        if(response.equals("4009")){
            llPost.removeAllViews();
            TextView tvEmpty=new TextView(this);
            tvEmpty.setText(getResources().getString(R.string.emptylistPost));
            tvEmpty.setTextColor(getResources().getColor(R.color.color_female));
            llPost.addView(tvEmpty);
        }

    }
}