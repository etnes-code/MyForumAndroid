package activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import Async.registerAsync;
import POJO.User;
import activity.R;

public class RegisterActivity extends AppCompatActivity {

    private EditText etLogin;
    private EditText etPwd1;
    private EditText etPwd2;
    private TextView tvCity;
    private Button btnCity;
    private Button btnRegister;
    final int NUM_REQUETE=1;
    View.OnClickListener listener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            RadioGroup rgGender=(RadioGroup) findViewById(R.id.rg_gender);
            int selectedId= rgGender.getCheckedRadioButtonId();
            // verification des champs
            if(etLogin.getText().toString().isEmpty() ||etLogin.getText().toString()==null )
                Toast.makeText(RegisterActivity.this,getResources().getString(R.string.loginEmpty),Toast.LENGTH_LONG).show();
            else if(etPwd1.getText().toString().isEmpty() ||etPwd1.getText().toString()==null )
                Toast.makeText(RegisterActivity.this,getResources().getString(R.string.pwd1Empty),Toast.LENGTH_LONG).show();
            else if(etPwd1.getText().toString().length()<6)
                Toast.makeText(RegisterActivity.this,getResources().getString(R.string.pwd1toshort),Toast.LENGTH_LONG).show();
            else if(etPwd2.getText().toString().isEmpty() ||etPwd2.getText().toString()==null )
                Toast.makeText(RegisterActivity.this,getResources().getString(R.string.pwd1Empty),Toast.LENGTH_LONG).show();
            else if(!etPwd1.getText().toString().equals(etPwd2.getText().toString()))
                Toast.makeText(RegisterActivity.this,getResources().getString(R.string.pwdnotthesame),Toast.LENGTH_LONG).show();
            else if(tvCity.getText().toString().isEmpty() ||tvCity.getText().toString()==null )
                Toast.makeText(RegisterActivity.this,getResources().getString(R.string.cityEmpty),Toast.LENGTH_LONG).show();
            else if(selectedId== -1)
                Toast.makeText(RegisterActivity.this,getResources().getString(R.string.genderEmpty),Toast.LENGTH_LONG).show();
            else{
            //création de l'objet contenant les informations à sauver dans la base de donnée
                RadioButton rbGender=(RadioButton) findViewById(selectedId);
                User user= new User(etLogin.getText().toString(), etPwd1.getText().toString(), rbGender.getText().toString(), tvCity.getText().toString());
            //envoi des informations d'inscription à la base de données
                new registerAsync(RegisterActivity.this).execute(user);
            }
        }
    };
    View.OnClickListener listenercity=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
           /* Intent intent= new Intent(RegisterActivity.this,CityActivity.class);
            startActivity(intent);
            finish();*/
            Intent intent= new Intent(RegisterActivity.this,CityActivity.class);
            startActivityForResult(intent, 1);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        etLogin=findViewById(R.id.et_login);
        etPwd1=findViewById(R.id.et_pwd1);
        etPwd2=findViewById(R.id.et_pwd2);
        tvCity=findViewById(R.id.tv_city);
        btnCity=findViewById(R.id.btn_city);
        btnRegister=findViewById(R.id.btn_ok);
        btnRegister.setOnClickListener(listener);
        btnCity.setOnClickListener(listenercity);
    }
    //récuperer la ville du wizard
    protected void onActivityResult(int num_requete, int code_retour, Intent intent_retour) {
        super.onActivityResult(num_requete, code_retour, intent_retour);
        if(num_requete==NUM_REQUETE){
            if(code_retour == RESULT_OK){
                String cityName= intent_retour.getStringExtra("cityName");
                tvCity.setText(cityName);
            }
        }
    }
    public void populate(){
        Toast.makeText(RegisterActivity.this,getResources().getString(R.string.user_created),Toast.LENGTH_LONG).show();
        finish();
    }
    public void populate_error(String response){
        //pour les erreurs concernant les paramètres transmis
        if(response == "101")
            Toast.makeText(RegisterActivity.this,getResources().getString(R.string.code_101),Toast.LENGTH_LONG).show();
        if(response == "102")
            Toast.makeText(RegisterActivity.this,getResources().getString(R.string.code_102),Toast.LENGTH_LONG).show();
        if(response == "103")
            Toast.makeText(RegisterActivity.this,getResources().getString(R.string.code_103),Toast.LENGTH_LONG).show();
        if(response == "104")
            Toast.makeText(RegisterActivity.this,getResources().getString(R.string.code_104),Toast.LENGTH_LONG).show();
        if(response == "105")
            Toast.makeText(RegisterActivity.this,getResources().getString(R.string.code_105),Toast.LENGTH_LONG).show();
        //erreur concernant la connexion à la bdd et au rpc
        if(response == "1")
            Toast.makeText(RegisterActivity.this,getResources().getString(R.string.code_1),Toast.LENGTH_LONG).show();
        if(response == "2")
            Toast.makeText(RegisterActivity.this,getResources().getString(R.string.code_2),Toast.LENGTH_LONG).show();
        if(response == "3")
            Toast.makeText(RegisterActivity.this,getResources().getString(R.string.code_1),Toast.LENGTH_LONG).show();
    }
}