package activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import Async.loginAsync;
import Async.registerAsync;
import POJO.User;

public class HomeActivity extends AppCompatActivity {
    private Button btnConnect;
    private Button btnRegister;
    private EditText etLogin;
    private EditText etPwd;
    private TextView tvError;
    User user;

    //connexion action
    View.OnClickListener listenerConnect = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(etLogin.getText().toString().equals("") || etPwd.getText().toString().equals("")  ){ // verification des champs
                Toast.makeText(HomeActivity.this,getResources().getString(R.string.ErrorMessage),Toast.LENGTH_LONG).show();
            } else{ // si c'est ok, création  d'un objet avec les informations de log
                 user= new User(etLogin.getText().toString(),etPwd.getText().toString());
                //envoi des données pour verifier si c'est bon et changement d'activité(activité asynchrone)
                new loginAsync(HomeActivity.this).execute(user);
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
    }
    public void populate(User userrecup){
        if(userrecup.getPassWord().equals(user.getPassWord())){
            Toast.makeText(HomeActivity.this,"Connexion ok",Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(HomeActivity.this,getResources().getString(R.string.wrongpwd),Toast.LENGTH_LONG).show();
        }
    }
    public void populate_error(String response){
        Log.d("CA", "code  :" + response);
        if(response.equals("104"))
            Toast.makeText(HomeActivity.this,getResources().getString(R.string.wrongloginname),Toast.LENGTH_LONG).show();
        //erreur concernant la connexion à la bdd et au rpc
        if(response.equals("1"))
            Toast.makeText(HomeActivity.this,getResources().getString(R.string.code_1),Toast.LENGTH_LONG).show();
        if(response.equals("2"))
            Toast.makeText(HomeActivity.this,getResources().getString(R.string.code_2),Toast.LENGTH_LONG).show();
        if(response.equals("3"))
            Toast.makeText(HomeActivity.this,getResources().getString(R.string.code_1),Toast.LENGTH_LONG).show();
    }
}