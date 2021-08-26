package activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import Async.newTopicAsync;
import POJO.Topic;
import POJO.User;

public class NewTopicActivity extends AppCompatActivity {
    private EditText etTitle;
    private Button btnNew;
    private User user;

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Topic topic= new Topic(etTitle.getText().toString(),user.getLoginName());
            new newTopicAsync(NewTopicActivity.this).execute(topic);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_topic);
        etTitle=findViewById(R.id.et_title);
        btnNew=findViewById(R.id.btn_new);
        btnNew.setOnClickListener(listener);
        Intent intent_recup= getIntent();
        user= (User) intent_recup.getSerializableExtra("user");
    }

    public void populate(){
        Intent intent= new Intent(NewTopicActivity.this,TopicActivity.class);
        intent.putExtra("user",user);
        startActivity(intent);
        finish();
    }
    public void populate_error(String response){
        //erreur concernant la connexion à la bdd et au rpc
        if(response.equals("1"))
            Toast.makeText(NewTopicActivity.this,getResources().getString(R.string.code_1),Toast.LENGTH_LONG).show();
        if(response.equals("2"))
            Toast.makeText(NewTopicActivity.this,getResources().getString(R.string.code_2),Toast.LENGTH_LONG).show();
        if(response.equals("3"))
            Toast.makeText(NewTopicActivity.this,getResources().getString(R.string.code_1),Toast.LENGTH_LONG).show();
        if(response.equals("2003") || response.equals("2004") )
            Toast.makeText(NewTopicActivity.this,getResources().getString(R.string.errorP),Toast.LENGTH_LONG).show();
        if(response.equals("2005")  )
            Toast.makeText(NewTopicActivity.this,getResources().getString(R.string.errorRequest),Toast.LENGTH_LONG).show();
        //erreur si la sujet est déjà présent
        if(response.equals("2010"))
            Toast.makeText(NewTopicActivity.this,getResources().getString(R.string.code_2000),Toast.LENGTH_LONG).show();
    }
}