package com.example.meilleursrestaurants.Controleur;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.meilleursrestaurants.Modele.Restaurant;
import com.example.meilleursrestaurants.Modele.RestaurantDAO;
import com.example.meilleursrestaurants.R;

import java.util.ArrayList;

public class SupprimerRestoActivity extends Activity {
    private Spinner spinnerRestos;
    private ArrayList<Restaurant> listeRestos;
    private Button boutonSupprimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supprimer_resto);

        spinnerRestos=(Spinner) findViewById(R.id.spinnerRestos);
        boutonSupprimer = (Button) findViewById(R.id.buttonSupprimerR);

        final RestaurantDAO restoDAO= new RestaurantDAO(this);
        listeRestos=restoDAO.getRestaurants();
        ArrayAdapter<Restaurant> spinRestosAdapter = new ArrayAdapter<Restaurant>(this.getBaseContext(),android.R.layout.simple_spinner_item);

        for(int i=0;i<listeRestos.size();i++){
            spinRestosAdapter.add(listeRestos.get(i));
        }

        spinnerRestos.setAdapter(spinRestosAdapter);

        boutonSupprimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                // message de confirmation

                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setMessage("Voulez-vous vraiment supprimer ce restaurant?").setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                for(int i=0;i<listeRestos.size();i++){
                                    int idR = listeRestos.get(spinnerRestos.getSelectedItemPosition()).getIdR();
                                    restoDAO.deleteResto(idR);
                                    Toast. makeText(getApplicationContext(),"Restaurant supprimé !", Toast. LENGTH_LONG).show();
                                    Intent intent = new Intent(getApplicationContext(), GestionRestosActivity.class);
                                    startActivity(intent);
                                }

                                dialog.cancel();

                            }
                        })

                        .setNegativeButton("No", new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                builder.create().show();
            }
        });
    }
}
