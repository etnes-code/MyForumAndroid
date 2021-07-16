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
}