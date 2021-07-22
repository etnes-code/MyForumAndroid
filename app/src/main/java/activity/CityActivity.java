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
            //envoye les données dans registeractivity
            //test 2(fonctionnelle pour la suite)
            Intent intent_retour= new Intent();
            intent_retour.putExtra("cityName",itemListCity.toString());
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
            }
        }else {
            TextView tv=new TextView(this);
            tv.setText(getResources().getString(R.string.emptylistcity));
            llcity.addView(tv);
        }
    }
    public void populate_error(String reponse){
        Toast.makeText(CityActivity.this,"code : "+reponse,Toast.LENGTH_LONG).show();
    }
}


