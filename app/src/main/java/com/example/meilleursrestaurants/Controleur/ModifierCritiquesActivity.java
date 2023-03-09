package com.example.meilleursrestaurants.Controleur;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.meilleursrestaurants.Modele.Critiquer;
import com.example.meilleursrestaurants.Modele.CritiquerDAO;
import com.example.meilleursrestaurants.R;

/**
 * Created by lili.pamphile on 28/03/2022.
 */

public class ModifierCritiquesActivity extends Activity {

    private String recupCommentaire, recupNomR, leCom, leNewCom;
    private int recupNote, recupIdR, recupIdU, laNote, laNewNote;
    private TextView nomR;
    private EditText note, com;
    private Button accueil, modifier, supprimer;
    private Critiquer ancienneCrit, nouvelleCrit;
    private CritiquerDAO critDAO = new CritiquerDAO(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modif_critiques);

        nomR = (TextView) findViewById(R.id.textViewNomR);
        note = (EditText) findViewById(R.id.editTextNote);
        com = (EditText) findViewById(R.id.editTextCom);

        recupCommentaire = getIntent().getStringExtra("commentaire");
        recupNomR = getIntent().getStringExtra("nomR");
        recupNote = getIntent().getIntExtra("note", 0);
        recupIdR = getIntent().getIntExtra("idR", 0);
        recupIdU = getIntent().getIntExtra("idU", 0);

        com.setText(recupCommentaire);
        nomR.setText(recupNomR);
        note.setText(String.valueOf(recupNote));

        laNote = Integer.parseInt(note.getText().toString());
        leCom = com.getText().toString();

        accueil = (Button) findViewById(R.id.buttonAccueil);
        modifier = (Button) findViewById(R.id.buttonModifier);
        supprimer = (Button)findViewById(R.id.buttonSupprimer);
        laNote = Integer.parseInt(note.getText().toString());

        ancienneCrit = new Critiquer(recupIdR, recupIdU, laNote, leCom);

        accueil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(v.getContext(),AccueilUtilisateurActivity.class);
                intent.putExtra("idU", recupIdU);
                startActivity(intent);
            }
        });

        modifier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                // message de confirmation

                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setMessage("Voulez-vous vraiment modifier cette critique ?").setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                laNewNote = Integer.parseInt(note.getText().toString());

                                if ((laNewNote > 5) || (laNewNote < 1)) {
                                    Toast.makeText(getApplicationContext(), "Veuillez saisir une note entre 1 et 5", Toast.LENGTH_LONG).show();
                                }
                                else {

                                    leNewCom = com.getText().toString();

                                    nouvelleCrit = new Critiquer(recupIdR, recupIdU, laNewNote, leNewCom);

                                    long res = critDAO.updateCritique(nouvelleCrit, ancienneCrit);

                                    if (res != 0) {
                                        Toast.makeText(getApplicationContext(), "Votre modification s'est bien passé", Toast.LENGTH_LONG).show();
                                        Intent intent = new Intent(getApplicationContext(), ConsulterCritiquesActivity.class);
                                        intent.putExtra("idU", recupIdU);
                                        startActivity(intent);
                                    } else {
                                        Toast.makeText(getApplicationContext(), "Echec modification", Toast.LENGTH_LONG).show();
                                    }
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

        supprimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                // message de confirmation

                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setMessage("Voulez-vous vraiment supprimer cette critique ?").setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                long res = critDAO.deleteCritiquer(recupIdU, recupIdR);

                                if (res != 0) {
                                    Toast.makeText(getApplicationContext(), "Votre suppression s'est bien passé", Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(getApplicationContext(), ConsulterCritiquesActivity.class);
                                    intent.putExtra("idU", recupIdU);
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(getApplicationContext(), "Echec suppression", Toast.LENGTH_LONG).show();
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