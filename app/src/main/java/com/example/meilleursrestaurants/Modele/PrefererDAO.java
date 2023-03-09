package com.example.meilleursrestaurants.Modele;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by lili.pamphile on 17/03/2022.
 */

public class PrefererDAO {
    private static String base = "Bd";
    private static int version = 1;
    BdSQLiteOpenHelper accesBD;

    public PrefererDAO(Context ct){
        accesBD = new BdSQLiteOpenHelper(ct, base, null, version);
    }

    // ajoute a un utilisateur un resto preferer qu'il a saisi
    public long addPreferer(Preferer pref){
        long ret;
        SQLiteDatabase bd = accesBD.getWritableDatabase();

        ContentValues value = new ContentValues();
        value.put("idU", pref.getIdU());
        value.put("idTC", pref.getIdTC());
        ret = bd.insert("Preferer", null, value);
        return ret;
    }

    // transforme curseur en ArrayList
    private ArrayList<Preferer> cursorToPrefererArrayList(Cursor curseur){
        ArrayList<Preferer> listePreferer = new ArrayList<Preferer>();
        int idU, idTC;

        curseur.moveToFirst();
        while (!curseur.isAfterLast()){
            idU = curseur.getInt(0);
            idTC = curseur.getInt(1);

            listePreferer.add(new Preferer(idU, idTC));
            curseur.moveToNext();
        }
        return listePreferer;
    }

    // recup la liste des id types de cuisines preferer d'un Utilisateur passé en parametre
    public ArrayList<Preferer> getLesPrefererDunUser(int idU){
        Cursor curseur;
        String req = "select * from Preferer where idU="+idU+";";
        curseur = accesBD.getReadableDatabase().rawQuery(req,null);
        return cursorToPrefererArrayList(curseur);
    }

    /* recup un type du cuisine passer en parametre pour un user passer en parametre
    public Preferer getUnPreferer(int idU){
        Preferer pref = null;
        Cursor curseur;
        curseur = accesBD.getReadableDatabase().rawQuery("select * from Preferer where idU="+idU+";",null);
        if (curseur.getCount() > 0) {
            curseur.moveToFirst();
            pref = new Preferer(curseur.getInt(0), curseur.getInt(1));
        }
        return pref;
    }*/

    // supprime un typeCuisine preferer d'un user et retourne 1 si la suppression a foncitionné si non 0
    public long deletePreferer(int idU, int idTC){
        long ret;
        SQLiteDatabase bd = accesBD.getWritableDatabase();
        String condition = "idU ="+idU+" and idTC="+idTC+";";
        ret = bd.delete("Preferer", condition,null);
        return ret;
    }

}
