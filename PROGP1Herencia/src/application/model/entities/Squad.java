package application.model.entities;

public abstract class Squad implements Comparable<Squad>
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
    public void addVictory() { this.nVictories++; }

    public abstract double calculateAtkPower();
    public abstract double calculateDefPower();
    public abstract boolean improve(String propertyToImprove, int newPropertyValue);

    @Override
    public abstract String toString();

    @Override
    public int compareTo(Squad squad) {
        return Integer.compare(nVictories, squad.nVictories);
    }
}
