package application.model.entities;

public class ZergSquad extends Squad
{
    private int nMinions;
    private int nOverlords;

    public ZergSquad(String name, int atkLevel, int defLevel, int nMinions, int nOverlords) {
        super(name, atkLevel, defLevel);
        this.nMinions = nMinions;
        this.nOverlords = nOverlords;
    }

    public int getnMinions() {
        return nMinions;
    }
    public void setnMinions(int nMinions) {
        this.nMinions = nMinions;
    }
    public int getnOverlords() {
        return nOverlords;
    }
    public void setnOverlords(int nOverlords) {
        this.nOverlords = nOverlords;
    }


    @Override
    public double calculateAtkPower() {
        return atkLevel + atkLevel*nMinions*0.15 + atkLevel*nOverlords*0.4;
    }

    @Override
    public double calculateDefPower() {
        return defLevel + defLevel*nMinions*0.3;
    }

    @Override
    public boolean improve(String propertyToImprove, int newPropertyValue) {
        switch(propertyToImprove) {
            case "esbirros": this.setnMinions(newPropertyValue); break;
            case "overlords": this.setnOverlords(newPropertyValue); break;
            default: return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Zerg {nombre="+super.getName()+", victorias="+super.getnVictories()+", esbirros="+nMinions+", overlords="+nOverlords+"}\n";
    }
}
