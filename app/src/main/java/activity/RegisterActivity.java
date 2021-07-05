package activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import activity.R;

public class RegisterActivity extends AppCompatActivity {

    private EditText etLogin;
    private EditText etPwd1;
    private EditText etPwd2;
    private TextView tvCity;
    private Button btnCity;
    private Button btnRegister;
    View.OnClickListener listener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            RadioGroup rgGender=(RadioGroup) findViewById(R.id.rg_gender);
            int selectedId= rgGender.getCheckedRadioButtonId();
            if(selectedId== -1){
                Toast.makeText(RegisterActivity.this,getResources().getString(R.string.genderEmpty),Toast.LENGTH_LONG).show();
            }else{
                RadioButton rbGender=(RadioButton) findViewById(selectedId);
                Toast.makeText(RegisterActivity.this,rbGender.getText().toString(),Toast.LENGTH_LONG).show();
            }
            if(etLogin.getText().toString().isEmpty() ||etLogin.getText().toString()==null )
                Toast.makeText(RegisterActivity.this,getResources().getString(R.string.loginEmpty),Toast.LENGTH_LONG).show();
            else if(etPwd1.getText().toString().isEmpty() ||etPwd1.getText().toString()==null )
                Toast.makeText(RegisterActivity.this,getResources().getString(R.string.pwd1Empty),Toast.LENGTH_LONG).show();
            else if(etPwd2.getText().toString().isEmpty() ||etPwd2.getText().toString()==null )
                Toast.makeText(RegisterActivity.this,getResources().getString(R.string.pwd1Empty),Toast.LENGTH_LONG).show();
            else if(tvCity.getText().toString().isEmpty() ||tvCity.getText().toString()==null )
                Toast.makeText(RegisterActivity.this,getResources().getString(R.string.cityEmpty),Toast.LENGTH_LONG).show();
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
    }
}