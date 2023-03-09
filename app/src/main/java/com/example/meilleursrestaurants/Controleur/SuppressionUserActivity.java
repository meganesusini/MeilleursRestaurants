package com.example.meilleursrestaurants.Controleur;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.meilleursrestaurants.Modele.Utilisateur;
import com.example.meilleursrestaurants.Modele.UtilisateurDAO;
import com.example.meilleursrestaurants.R;

import java.util.ArrayList;

/**
 * Created by lili.pamphile on 22/03/2022.
 */

public class SuppressionUserActivity extends Activity {

    private Button suppression, retour;
    private Spinner spinUser;
    private ArrayList<Utilisateur> listeUser;
    private UtilisateurDAO unUserDAO = new UtilisateurDAO(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suppression_user);

        suppression = (Button) findViewById(R.id.buttonSupprimer);
        retour = (Button) findViewById(R.id.buttonAccueil);

        spinUser = (Spinner) findViewById(R.id.spinnerUser);

        listeUser = unUserDAO.getQueLesUtilisateurs();
        ArrayAdapter<String> spinUserAdapter = new ArrayAdapter<String>(this.getBaseContext(),android.R.layout.simple_spinner_item);

        for(int i=0;i<listeUser.size();i++){
            spinUserAdapter.add(listeUser.get(i).toString());
        }
        spinUser.setAdapter(spinUserAdapter);

        spinUser.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub
            }

            // methode declancher lorqu'aucun element est selectionné
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });

        suppression.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                // message de confirmation

                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setMessage("Voulez-vous vraiment supprimer cette utilisateur?").setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                int idU = listeUser.get(spinUser.getSelectedItemPosition()).getIdU();
                                long res = unUserDAO.deleteUser(idU);

                                if (res!=0){
                                    Toast.makeText(getApplicationContext(), "Votre suppression s'est bien passé", Toast.LENGTH_LONG).show();
                                } else {
                                    Toast.makeText(getApplicationContext(), "Echec suppression", Toast.LENGTH_LONG).show();
                                }

                                Intent intent = new Intent(getApplicationContext(), MenuAdminGestionUserActivity.class);
                                startActivity(intent);

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

        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(v.getContext(), MenuAdminGestionUserActivity.class);
                startActivity(intent);
            }
        });

    }
}
