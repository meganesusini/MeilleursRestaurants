package com.example.meilleursrestaurants.Modele;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by mega.susini on 21/03/2022.
 */

public class ProposerDAO {
    private static String base = "Bd";
    private static int version = 1;
    BdSQLiteOpenHelper accesBD;

    public ProposerDAO(Context ct){
        accesBD = new BdSQLiteOpenHelper(ct, base, null, version);
    }

    // ajoute un typeCuisine proposer par un resto
    public long addProposer(Proposer proposer){
        long ret;
        SQLiteDatabase bd = accesBD.getWritableDatabase();

        ContentValues value = new ContentValues();
        value.put("idR", proposer.getIdR());
        value.put("idTC", proposer.getIdTC());
        ret = bd.insert("Proposer", null, value);
        return ret;
    }

    // transforme curseur en ArrayList
    private ArrayList<Proposer> cursorToProposerArrayList(Cursor curseur){
        ArrayList<Proposer> listeProposer = new ArrayList<Proposer>();
        int idR, idTC;

        curseur.moveToFirst();
        while (!curseur.isAfterLast()){
            idR = curseur.getInt(0);
            idTC = curseur.getInt(1);

            listeProposer.add(new Proposer(idR, idTC));
            curseur.moveToNext();
        }
        return listeProposer;
    }

    // recup la liste des id types de cuisines proposer par un resto passé en parametre
    public ArrayList<Proposer> getLesProposerDunResto(int idR){
        Cursor curseur;
        String req = "select * from Proposer where idR="+idR+";";
        curseur = accesBD.getReadableDatabase().rawQuery(req,null);
        return cursorToProposerArrayList(curseur);
    }

    // supprime un typeCuisine proposer par un Resto et retourne 1 si la suppression a foncitionné si non 0
    public long deleteProposer(int idR, int idTC){
        long ret;
        SQLiteDatabase bd = accesBD.getWritableDatabase();
        String condition = "idR ="+idR+" and idTC="+idTC+";";
        ret = bd.delete("Proposer", condition,null);
        return ret;
    }

}
