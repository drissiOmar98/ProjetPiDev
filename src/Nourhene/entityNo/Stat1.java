package Nourhene.entityNo;

public class Stat1 {
    private Integer referencestat;
    private Integer nbrpanne;

    public Stat1(Integer referencestat, Integer nbrpanne) {
        this.referencestat = referencestat;
        this.nbrpanne = nbrpanne;
    }

    public Integer getReferencestat() {
        return referencestat;
    }

    public void setReferencestat(Integer referencestat) {
        this.referencestat = referencestat;
    }

    public Integer getNbrpanne() {
        return nbrpanne;
    }

    public void setNbrpanne(Integer nbrpanne) {
        this.nbrpanne = nbrpanne;
    }

    @Override
    public String toString() {
        return "Stat{" +
                "referencestat=" + referencestat +
                ", nbrpanne=" + nbrpanne +
                '}';
    }
}
