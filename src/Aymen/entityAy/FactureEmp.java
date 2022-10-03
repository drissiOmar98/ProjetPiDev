package Aymen.entityAy;


import java.sql.Date;

public class FactureEmp {
    private String id;
    private String  nomEmp;
    private Date date;
    private String Regliment;
    private String EtatFacture;
    private int TVA;
    private int Bonus;
    private String NBFacture;
    private int Totale;

    public String getId() {
        return id;
    }

    public String getNomEmp() {
        return nomEmp;
    }

    public Date getDate() {
        return date;
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

    public int getBonus() {
        return Bonus;
    }

    public String getNBFacture() {
        return NBFacture;
    }

    public int getTotale() {
        return Totale;
    }

    public FactureEmp(String id, String nomEmp, Date date, String regliment, String etatFacture, int TVA, int Bonus, String NBFacture, int totale) {
        this.id = id;
        this.nomEmp = nomEmp;
        this.date = date;
        this.Regliment = regliment;
        this.EtatFacture = etatFacture;
        this.TVA = TVA;
        this.Bonus = Bonus;
        this.NBFacture = NBFacture;
        this.Totale = totale;
    }
}
