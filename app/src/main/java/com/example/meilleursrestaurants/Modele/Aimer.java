package com.example.meilleursrestaurants.Modele;

/**
 * Created by lili.pamphile on 17/03/2022.
 */

public class Aimer {
    private int idR;
    private int idU;

    public Aimer(int idR, int idU) {
        this.idR = idR;
        this.idU = idU;
    }

    public int getIdR() {
        return idR;
    }

    public void setIdR(int idR) {
        this.idR = idR;
    }

    public int getIdU() {
        return idU;
    }

    public void setIdU(int idU) {
        this.idU = idU;
    }

    @Override
    public String toString() {
        return "Aimer{ idR = " + idR + '}';
    }
}
