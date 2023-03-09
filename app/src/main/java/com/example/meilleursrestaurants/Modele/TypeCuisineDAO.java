package com.example.meilleursrestaurants.Modele;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by quen.labeyrie1 on 21/03/2022.
 */

public class TypeCuisineDAO {
    private static String base = "Bd";
    private static int version = 1;
    BdSQLiteOpenHelper accesBD;

    public TypeCuisineDAO(Context ct){
        accesBD = new BdSQLiteOpenHelper(ct, base, null, version);
    }

    // Ajouter un type de cuisine
    public long addTypeCuisine(TypeCuisine unTC){
        long ret;
        SQLiteDatabase bd = accesBD.getWritableDatabase();

        ContentValues value = new ContentValues();
        value.put("idTC", unTC.getIdTC());
        value.put("libelleTC", unTC.getLibelleTC());
        ret = bd.insert("TypeCuisine", null, value);

        return ret;
    }

    // Consulter les types de cuisine
    public ArrayList<TypeCuisine> getTypesCuisine(){
        Cursor curseur;
        String req = "select * from TypeCuisine";
        curseur = accesBD.getReadableDatabase().rawQuery(req,null);
        return cursorToTypeCuisineArrayList(curseur);
    }

    private ArrayList<TypeCuisine> cursorToTypeCuisineArrayList(Cursor curseur){
        ArrayList<TypeCuisine> listeTypesCuisine = new ArrayList<TypeCuisine>();
        int idTC;
        String libelleTC;

        curseur.moveToFirst();
        while (!curseur.isAfterLast()){
            idTC = curseur.getInt(0);
            libelleTC = curseur.getString(1);

            listeTypesCuisine.add(new TypeCuisine(idTC,libelleTC));
            curseur.moveToNext();
        }

        return listeTypesCuisine;
    }

    // Consulter un type de cuisine
    public TypeCuisine getTypeCuisine(int idTC){
        TypeCuisine leTC = null;
        Cursor curseur;
        curseur = accesBD.getReadableDatabase().rawQuery("select * from TypeCuisine where idTC="+idTC+";",null);
        if (curseur.getCount() > 0) {
            curseur.moveToFirst();
            leTC = new TypeCuisine(idTC, curseur.getString(1));
        }
        return leTC;
    }

    public long updateTC(TypeCuisine oldTC, TypeCuisine newTC){
        long res;
        SQLiteDatabase bd = accesBD.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put("idTC", oldTC.getIdTC());
        value.put("libelleTC", newTC.getLibelleTC());

        String condition = "idTC ="+oldTC.getIdTC()+";";
        res = bd.update("TypeCuisine", value, condition,null);
        return res;
    }

    // Supprimer un type de cuisine
    public int deleteTC(int idTC) {
        int ret;
        SQLiteDatabase bd = accesBD.getWritableDatabase();
        String condition = "idTC = "+idTC+";";
        ret = bd.delete("TypeCuisine", condition,null);
        return ret;
    }
}
