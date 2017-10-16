package entities;

public abstract class Squad
{
    private String name;
    private int nVictories;
    protected int atkLevel; // Declared protected because calculations need the methods quite a lot
    protected int defLevel;

    public Squad(String name, int atkLevel, int defLevel) {
        this.name = name;
        this.nVictories = 0;
        this.atkLevel = atkLevel;
        this.defLevel = defLevel;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getnVictories() {
        return nVictories;
    }
    public void setnVictories(int nVictories) {
        this.nVictories = nVictories;
    }
    public int getAtkLevel() {
        return atkLevel;
    }
    public void setAtkLevel(int atkLevel) {
        this.atkLevel = atkLevel;
    }
    public int getDefLevel() {
        return defLevel;
    }
    public void setDefLevel(int defLevel) {
        this.defLevel = defLevel;
    }

    protected abstract double calculateAtkPower();
    protected abstract double calculateDefPower();
}
