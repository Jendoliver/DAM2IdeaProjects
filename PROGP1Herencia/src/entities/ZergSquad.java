package entities;

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
    protected double calculateAtkPower() {
        return atkLevel + atkLevel*nMinions*0.15 + atkLevel*nOverlords*0.4;
    }

    @Override
    protected double calculateDefPower() {
        return defLevel + defLevel*nMinions*0.3;
    }
}
