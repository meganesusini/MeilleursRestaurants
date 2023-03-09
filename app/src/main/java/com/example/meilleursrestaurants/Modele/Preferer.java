package com.example.meilleursrestaurants.Modele;

/**
 * Created by lili.pamphile on 17/03/2022.
 */

public class Preferer {
    private int idU, idTC;

    public Preferer(int idU, int idTC) {
        this.idU = idU;
        this.idTC = idTC;
    }

    public int getIdU() {
        return idU;
    }

    public void setIdU(int idU) {
        this.idU = idU;
    }

    public int getIdTC() {
        return idTC;
    }

    public void setIdTC(int idTC) {
        this.idTC = idTC;
    }

    @Override
    public String toString() {
        return "Preferer idU = " + idU +", idTC =" + idTC ;
    }
}
