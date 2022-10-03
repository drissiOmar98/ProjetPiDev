package Nourhene.entityNo;

import java.util.Date;

public class Vol  {
    private String nomv;
    private int numv;
    private Date dated;
    private String datea;
    private String chauffeure;
    private String depart;
    private String arriver;
    private String prix;



    public Vol(int numv, String nomv,String depart , String arriver,  Date  dated, String datea,String chauffeure, String prix) {
        this.numv= numv;
        this.nomv= nomv;
        this.dated = dated;
        this.datea = datea;
        this.chauffeure = chauffeure;
        this.depart = depart ;
        this.arriver = arriver;

        this.prix = prix;
    }
    public String getPrix() { return prix; }

    public String getNomv() {
        return nomv;
    }

    public void setNomv(String nomv) {
        this.nomv = nomv;
    }

    public int getNumv() {
        return numv;
    }

    public void setNumv(int numv) {
        this.numv = numv;
    }

    public Date getDated() {
        return dated;
    }

    public void setDated(Date dated) {
        this.dated = dated;
    }

    public String getDatea() {
        return datea;
    }

    public void setDatea(String datea) {
        this.datea = datea;
    }

    public String getChauffeure() {
        return chauffeure;
    }

    public void setChauffeure(String chauffeure) {
        this.chauffeure = chauffeure;
    }

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public String getArriver() {
        return arriver;
    }

    public void setArriver(String arriver) {
        this.arriver = arriver;
    }

    @Override
    public String toString() {
        return "Vol{" +
                "nomv='" + nomv + '\'' +
                ", numv='" + numv + '\'' +
                ", dated=" + dated +
                ", datea=" + datea +
                ", chauffeure='" + chauffeure + '\'' +
                ", depart='" + depart + '\'' +
                ", arriver='" + arriver + '\'' +


                '}';
    }
}