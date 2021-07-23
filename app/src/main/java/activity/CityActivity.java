package activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import Async.cityAsync;
import activity.R;

public class CityActivity extends AppCompatActivity {
    private LinearLayout llcity;
    private String itemListCity;

    View.OnClickListener listener= new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            //Récupere l'id du bouton cliqué
            int id=v.getId();
            Button btn=findViewById(id);
            //envoye les données  du bouton cliqué dans registeractivity
            Intent intent_retour= new Intent();
            intent_retour.putExtra("cityName",btn.getText().toString());
            setResult(RESULT_OK,intent_retour);
            finish();
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);
        llcity=findViewById(R.id.ll_city);
        new cityAsync(CityActivity.this).execute();
    }
    public void populate(List<String> listRep){
        if(listRep != null){
            Button btn;
            int cpt=1;
            for(String item : listRep){
                btn=new Button(this);
                btn.setId(cpt);
                btn.setText(item);
                btn.setOnClickListener(listener);
                llcity.addView(btn);
                cpt++;
            }
        }else {
            TextView tv=new TextView(this);
            tv.setText(getResources().getString(R.string.emptylistcity));
            llcity.addView(tv);
        }
    }
    public void populate_error(String reponse){
       //notification pour informer l'utilisateur qu'il y a un soucis
        if(reponse == "1")
            Toast.makeText(CityActivity.this,getResources().getString(R.string.code_1),Toast.LENGTH_LONG).show();
        if(reponse == "2")
            Toast.makeText(CityActivity.this,getResources().getString(R.string.code_2),Toast.LENGTH_LONG).show();
        if(reponse == "3")
            Toast.makeText(CityActivity.this,getResources().getString(R.string.code_1),Toast.LENGTH_LONG).show();
    }
}


