package com.example.meilleursrestaurants.Modele;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by lili.pamphile on 17/03/2022.
 */

public class CritiquerDAO {
    private static String base = "Bd";
    private static int version = 1;
    BdSQLiteOpenHelper accesBD;
    private Context context;

    public CritiquerDAO(Context ct){
        accesBD = new BdSQLiteOpenHelper(ct, base, null, version);
        context=ct;
    }

    // ajoute une critique
    public long addCritiquer(Critiquer crit){
        long ret;
        SQLiteDatabase bd = accesBD.getWritableDatabase();

        ContentValues value = new ContentValues();
        value.put("idR", crit.getIdR());
        value.put("idU", crit.getIdU());
        value.put("note", crit.getNote());
        value.put("commentaire", crit.getCommentaire());
        ret = bd.insert("Critiquer", null, value);
        return ret;
    }

    // transforme curseur en ArrayList
    private ArrayList<Critiquer> cursorToCritiquerArrayList(Cursor curseur){
        ArrayList<Critiquer> listeCritiquer = new ArrayList<Critiquer>();
        int idR, note, idU;
        String com;

        curseur.moveToFirst();
        while (!curseur.isAfterLast()){
            idR = curseur.getInt(0);
            idU = curseur.getInt(1);
            note = curseur.getInt(2);
            com = curseur.getString(3);
            listeCritiquer.add(new Critiquer(idR,idU, note, com));
            curseur.moveToNext();
        }
        return listeCritiquer;
    }


    // recup la liste des critique d'un Utilisateur passé en parametre
    public ArrayList<Critiquer> getLesCritiquesDunUser(int idU){
        ArrayList<Critiquer> lesCritiques = new ArrayList<Critiquer>();
        Cursor curseur;
        RestaurantDAO restoDAO = new RestaurantDAO(context);
        Restaurant resto;

        String req = "select idR, note, commentaire From Critiquer where idU="+idU+";";
        curseur = accesBD.getReadableDatabase().rawQuery(req,null);

        curseur.moveToFirst();
        while (!curseur.isAfterLast()){
           Log.d("test**", "bfbbfb :"+curseur.getInt(0));
            resto = restoDAO.getRestaurant(curseur.getInt(0));
            Log.d("resto","**"+resto);
//           Log.d("test *** ","idResto :"+resto.getIdR());
            lesCritiques.add(new Critiquer(curseur.getInt(0),curseur.getInt(1),curseur.getString(2), resto));
            curseur.moveToNext();
        }
        return lesCritiques;
    }

    // recup la critique dun user passer en parametre sur un resto passer en parametre
    public Critiquer getUneCritique(int idU, int idR){
        Critiquer crit = null;
        Cursor curseur;
        curseur = accesBD.getReadableDatabase().rawQuery("select * from Critere where idU="+idU+" and idR="+idR+";",null);
        if (curseur.getCount() > 0) {
            curseur.moveToFirst();
            crit = new Critiquer(idU,idR, curseur.getInt(2), curseur.getString(3));
        }
        return crit;
    }

    // modifi la critique d'un user et retourne 1 si la suppression a foncitionné si non 0
    public long updateCritique(Critiquer nvCrit, Critiquer anCrit){
        long ret;
        SQLiteDatabase bd = accesBD.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put("idU", anCrit.getIdU());
        value.put("idR", anCrit.getIdR());
        value.put("note",nvCrit.getNote());
        value.put("commentaire",nvCrit.getCommentaire());

        String condition = "idU ="+anCrit.getIdU()+" and idR="+anCrit.getIdR()+";";
        ret = bd.update("Critiquer", value, condition,null);
        return ret;
    }

    // supprime une critique d'un user et retourne 1 si la suppression a foncitionné si non 0
    public long deleteCritiquer(int idU, int idR){
        long ret;
        SQLiteDatabase bd = accesBD.getWritableDatabase();
        String condition = "idU ="+idU+" and idR="+idR+";";
        ret = bd.delete("Critiquer", condition,null);
        return ret;
    }
}
