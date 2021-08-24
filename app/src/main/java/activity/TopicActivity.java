package activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import Async.cityAsync;
import Async.newTopicAsync;
import Async.topicAsync;
import POJO.Topic;
import POJO.User;
import activity.R;

public class TopicActivity extends AppCompatActivity {
    private User user;
    private TextView tvWelcome;
    private Button btnNew;
    private LinearLayout llTopic;

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(v.getId()==btnNew.getId()){
                Intent intent= new Intent(TopicActivity.this,NewTopicActivity.class);
                intent.putExtra("user",user);
                startActivity(intent);
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic);
        Intent intent_recup= getIntent();
        if(savedInstanceState!=null){
            user= (User) savedInstanceState.getSerializable("user");
            new topicAsync(TopicActivity.this).execute();
        }else{
            user= (User) intent_recup.getSerializableExtra("user");
            new topicAsync(TopicActivity.this).execute();

        }
        tvWelcome=findViewById(R.id.tv_welcome);
        tvWelcome.setText(getResources().getString(R.string.welcomeUser)+" "+user.getLoginName());
        btnNew=findViewById(R.id.btn_newtopic);
        btnNew.setOnClickListener(listener);
        llTopic=findViewById(R.id.ll_topic);
    }
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("user",user);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
    public void populate(ArrayList<Topic> listtopic){
        if(listtopic != null){
            Button btn;
            for(Topic item : listtopic){

                Log.d("CA", "liste pas vide  ");
                btn=new Button(this);
                btn.setId(item.getIdTopic());
                btn.setText(item.getTitle());
                btn.setOnClickListener(listener);
                btn.setTextColor(getResources().getColor(R.color.textColor_btn));
                btn.setBackgroundColor(getResources().getColor(R.color.background_btn));
                btn.setGravity(Gravity.CENTER_HORIZONTAL);
                llTopic.addView(btn);
            }
        }else {
            TextView tv=new TextView(this);
            tv.setText(getResources().getString(R.string.emptylisttopic));
            llTopic.addView(tv);
        }
    }

    public void populate_error(String response){
        Log.d("CA", response);
        //erreur concernant la connexion à la bdd et au rpc
        if(response.equals("1"))
            Toast.makeText(TopicActivity.this,getResources().getString(R.string.code_1),Toast.LENGTH_LONG).show();
        if(response.equals("2"))
            Toast.makeText(TopicActivity.this,getResources().getString(R.string.code_2),Toast.LENGTH_LONG).show();
        if(response.equals("3"))
            Toast.makeText(TopicActivity.this,getResources().getString(R.string.code_3),Toast.LENGTH_LONG).show();
        if(response.equals("2001")){
            TextView tv=new TextView(this);
            tv.setText(getResources().getString(R.string.emptylisttopic));
            llTopic.addView(tv);
        }

    }
}