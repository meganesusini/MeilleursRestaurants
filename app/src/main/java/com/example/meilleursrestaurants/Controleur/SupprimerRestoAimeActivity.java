package com.example.meilleursrestaurants.Controleur;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.meilleursrestaurants.Modele.AimerDAO;
import com.example.meilleursrestaurants.R;

/**
 * Created by lali.nguyen on 31/03/2022.
 */

public class SupprimerRestoAimeActivity extends Activity {

    private Button buttonSupp, buttonret;
    private TextView nomR, adrR, localisationR;
    private String nomResto, villeR, cpR, numR, rueR, adresseR, localR;
    public int idResto, idUserCo;
    private AimerDAO aDAO = new AimerDAO(this);

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supprimer_resto_aime);
        buttonSupp = (Button)findViewById(R.id.buttonSuppAime);
        buttonret = (Button)findViewById(R.id.btnRetour);
        nomR = (TextView) findViewById(R.id.txtResto);
        adrR = (TextView)findViewById(R.id.txtAdr);
        localisationR = (TextView)findViewById(R.id.txtVille);

        idResto = getIntent().getIntExtra("idR",0);
        nomResto = getIntent().getStringExtra("nomR");
        numR = getIntent().getStringExtra("numR");
        rueR = getIntent().getStringExtra("rueR");
        villeR = getIntent().getStringExtra("villeR");
        cpR = getIntent().getStringExtra("cpR");
        idUserCo = getIntent().getIntExtra("idU", idUserCo);

        adresseR = numR + " " + rueR;
        localR = villeR +" "+cpR;
        //Log.d("****nomR", nomResto);
        nomR.setText(nomResto);
        adrR.setText(adresseR);
        localisationR.setText(localR);

        buttonSupp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                // message de confirmation

                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setMessage("Voulez-vous vraiment supprimer cette utilisateur?").setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                long delete = aDAO.deleteAimer(idUserCo, idResto);
                                if (delete == 1) {
                                    Toast. makeText(getApplicationContext(),"Restaurant supprimé de vos favoris !", Toast. LENGTH_LONG).show();
                                    Intent intent = new Intent(getApplicationContext(), AccueilUtilisateurActivity.class);
                                    intent.putExtra("idU", idUserCo);
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

        buttonret.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), AccueilUtilisateurActivity.class);
                intent.putExtra("idU", idUserCo);
                startActivity(intent);
            }
        });


        buttonret.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), AccueilUtilisateurActivity.class);
                intent.putExtra("idU", idUserCo);
                startActivity(intent);
            }
        });





    }

}
