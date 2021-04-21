package training.etnes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {
    private Button btnConnect;
    private Button btnRegister;
    private EditText etLogin;
    private EditText etPwd;
    private TextView tvError;

    //connexion action
    View.OnClickListener listenerConnect = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(etLogin.getText().toString().equals("") || etPwd.getText().toString().equals("")){
                Toast.makeText(HomeActivity.this,getResources().getString(R.string.ErrorMessage),Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(HomeActivity.this,"ok",Toast.LENGTH_LONG).show();
            }
        }
    };
    View.OnClickListener listenerRegister=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
           Intent intent =new Intent(HomeActivity.this,RegisterActivity.class);
           startActivity(intent);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnConnect=findViewById(R.id.btn_connect);
        etLogin=findViewById(R.id.et_login);
        etPwd=findViewById(R.id.et_pwd);
        tvError=findViewById(R.id.tv_error);
        btnConnect.setOnClickListener(listenerConnect);
        btnRegister=findViewById(R.id.btn_register);
        btnRegister.setOnClickListener(listenerRegister);
        ImageView imageview= (ImageView)findViewById(R.id.image);
        imageview.setImageResource(R.drawable.forum);
    }
}