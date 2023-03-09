package com.example.meilleursrestaurants.Modele;

/**
 * Created by lili.pamphile on 21/03/2022.
 */

public class Proposer {
    private  int idR, idTC;

    public Proposer(int idR, int idTC) {
        this.idR = idR;
        this.idTC = idTC;
    }

    public int getIdR() {
        return idR;
    }

    public void setIdR(int idR) {
        this.idR = idR;
    }

    public int getIdTC() {
        return idTC;
    }

    public void setIdTC(int idTC) {
        this.idTC = idTC;
    }

    @Override
    public String toString() {
        return "Proposer idR = " + idR +", idTC=" + idTC;
    }
}
