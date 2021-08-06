package activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import Async.newTopicAsync;
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
                finish();
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
        }else{
            user= (User) intent_recup.getSerializableExtra("user");
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
        /*
        llTopic.removeAllViews();
        new newTopicAsync(this).execute();*/
    }
}