package com.example.meilleursrestaurants.Controleur;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.meilleursrestaurants.Controleur.ConnexionActivity;
import com.example.meilleursrestaurants.Controleur.InscriptionActivity;
import com.example.meilleursrestaurants.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Button btnIns, btnCo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnCo = (Button) findViewById(R.id.buttonCo);
        btnCo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ConnexionActivity.class);
                startActivity(intent);
            }
        });

        btnIns = (Button) findViewById(R.id.buttonIns);
        btnIns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), InscriptionActivity.class);
                startActivity(intent);
            }
        });


    }

}