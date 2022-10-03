package GestionOffre.models;


import java.time.LocalDate;

public class Favoris {
    String idFav;
    String idoffre;
    int VL;
    LocalDate datelike;



    public Favoris(String idFav, String idoffre, int VL, LocalDate datelike) {
        this.idFav = idFav;
        this.idoffre = idoffre;
        this.VL = VL;
        this.datelike = datelike;
    }

    public String getIdFav() {
        return idFav;
    }

    public void setIdFav(String idFav) {
        this.idFav = idFav;
    }

    public String getIdoffre() {
        return idoffre;
    }

    public void setIdoffre(String idoffre) {
        this.idoffre = idoffre;
    }

    public int getVL() {
        return VL;
    }

    public void setVL(int VL) {
        this.VL = VL;
    }

    public LocalDate getDatelike() {
        return datelike;
    }

    public void setDatelike(LocalDate datelike) {
        this.datelike = datelike;
    }
}
