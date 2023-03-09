package com.example.meilleursrestaurants.Modele;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by lili.pamphile on 17/03/2022.
 */

public class AimerDAO {
    private static String base = "Bd";
    private static int version = 1;
    BdSQLiteOpenHelper accesBD;

    public AimerDAO(Context ct){
        accesBD = new BdSQLiteOpenHelper(ct, base, null, version);
    }

    // ajoute a un utilisateur un resto qu'il a aimer
    public long addAimer(Aimer aimer){
        long ret;
        SQLiteDatabase bd = accesBD.getWritableDatabase();

        ContentValues value = new ContentValues();
        value.put("idR", aimer.getIdR());
        value.put("idU", aimer.getIdU());
        ret = bd.insert("Aimer", null, value);

        return ret;
    }

    // transforme curseur en ArrayList
    private ArrayList<Aimer> cursorToAimerArrayList(Cursor curseur){
        ArrayList<Aimer> listeAimer = new ArrayList<Aimer>();
        int idR;
        int idU;

        curseur.moveToFirst();
        while (!curseur.isAfterLast()){
            idR = curseur.getInt(0);
            idU = curseur.getInt(1);
            listeAimer.add(new Aimer(idR,idU));
            curseur.moveToNext();
        }
        return listeAimer;
    }

    // recup la liste des numéros de resto en fonction de l'id Utilisateur passé en parametre
    public ArrayList<Aimer> getLesAimerDunUser(int idU){
        Cursor curseur ;
        String req = "select * from Aimer where idU="+idU+";";
        curseur = accesBD.getReadableDatabase().rawQuery(req,null);
        return cursorToAimerArrayList(curseur);
    }

    // recup l'id dun resto passer un parametre aimer par l'user passer en parametre
    /*public Aimer getAimer(int idU, int idR){
        Aimer aimer = null;
        Cursor curseur;
        curseur = accesBD.getReadableDatabase().rawQuery("select * from Aimer where idU="+idU+" and idR="+idR+";",null);
        if (curseur.getCount() > 0) {
            curseur.moveToFirst();
            aimer = new Aimer(idU,idR);
        }
        return aimer;
    }*/

    // supprime un resto aimer par un user et retourne 1 si la suppression a foncitionné si non 0
    public long deleteAimer(int idU, int idR){
        long ret;
        SQLiteDatabase bd = accesBD.getWritableDatabase();
        String condition = "idU ="+idU+" and idR="+idR+";";
        ret = bd.delete("Aimer", condition,null);
        return ret;
    }



}

