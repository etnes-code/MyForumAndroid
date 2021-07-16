package activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import activity.R;

public class CityActivity extends AppCompatActivity {

    View.OnClickListener listener= new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //Récupere l'id du bouton cliqué
            int id=v.getId();
            Button btn=findViewById(id);
            //envoyer les données dans registeractivity
            //test 2
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
        Button city1=findViewById(R.id.city1);
        city1.setOnClickListener(listener);
    }
}