package entities;

public class TerranSquad extends Squad
{
    private int nBuildings;
    private int techLevel;

    public TerranSquad(String name, int atkLevel, int defLevel, int nBuildings, int techLevel) {
        super(name, atkLevel, defLevel);
        this.nBuildings = nBuildings;
        this.techLevel = techLevel;
    }

    public int getnBuildings() {
        return nBuildings;
    }
    public void setnBuildings(int nBuildings) {
        this.nBuildings = nBuildings;
    }
    public int getTechLevel() {
        return techLevel;
    }
    public void setTechLevel(int techLevel) {
        this.techLevel = techLevel;
    }

    @Override
    protected double calculateAtkPower() {
        return atkLevel + atkLevel*techLevel*0.5;
    }

    @Override
    protected double calculateDefPower() {
        return defLevel + defLevel*nBuildings*0.25;
    }
}
