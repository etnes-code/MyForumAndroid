package activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import activity.R;

public class CityActivity extends AppCompatActivity {

    View.OnClickListener listener= new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //recuprer l'id et le text du bouton
            //envoyer les données dans registeractivity
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);
        Button btncity=findViewById(R.id.city1);
        btncity.setOnClickListener(listener);
    }
}