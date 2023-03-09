package com.example.meilleursrestaurants.Modele;

/**
 * Created by quen.labeyrie1 on 21/03/2022.
 */

public class TypeCuisine {
    private int idTC;
    private String libelleTC;

    public TypeCuisine(int idTC, String libelleTC) {
        this.idTC = idTC;
        this.libelleTC = libelleTC;
    }

    public TypeCuisine(String libelleTC) {
        this.libelleTC = libelleTC;
    }

    public int getIdTC() {
        return idTC;
    }

    public void setIdTC(int idTC) {
        this.idTC = idTC;
    }

    public String getLibelleTC() {
        return libelleTC;
    }

    public void setLibelleTC(String libelleTC) {
        this.libelleTC = libelleTC;
    }

    @Override
    public String toString() {
        return "TypeCuisine{" +
                "idTC=" + idTC +
                ", libelleTC='" + libelleTC + '\'' +
                '}';
    }
}
