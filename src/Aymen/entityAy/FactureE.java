package Aymen.entityAy;

import java.sql.Date;

public class FactureE {
    private int id;
    private String  nomClient;
    private Date date;
    private String Regliment;
    private String EtatFacture;
    private int TVA;
    private int Remise;
    private String NBFacture;
    private int Totale;

    public Date getDate() {
        return date;
    }
    public FactureE() {
    }

    public FactureE(int id, String nomClient, Date date, String Regliment, String EtatFacture, int TVA, int Remise, String NBFacture, int Totale) {
        this.id = id;
        this.nomClient = nomClient;
        this.date = date;
        this.Regliment = Regliment;
        this.EtatFacture = EtatFacture;
        this.TVA = TVA;
        this.Remise = Remise;
        this.NBFacture = NBFacture;
        this.Totale = Totale;
    }

    public int getId() {
        return id;
    }

    public String getNomClient() {
        return nomClient;
    }



    public String getRegliment() {
        return Regliment;
    }

    public String getEtatFacture() {
        return EtatFacture;
    }

    public int getTVA() {
        return TVA;
    }

    public int getRemise() {
        return Remise;
    }

    public String getNBFacture() {
        return NBFacture;
    }

    public int getTotale() {
        return Totale;
    }
}

