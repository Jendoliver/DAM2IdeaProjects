package application.model.entities;

public class ProtossSquad extends Squad
{
    private int nPylons;

    public ProtossSquad(String name, int atkLevel, int defLevel, int nPylons) {
        super(name, atkLevel, defLevel);
        this.nPylons = nPylons;
    }

    public int getnPylons() {
        return nPylons;
    }
    public void setnPylons(int nPylons) {
        this.nPylons = nPylons;
    }

    @Override
    public double calculateAtkPower() {
        return atkLevel + atkLevel*nPylons*0.5;
    }

    @Override
    public double calculateDefPower() {
        return defLevel + defLevel*nPylons*0.5;
    }

    @Override
    public String toString() {
        return "Protoss {nombre="+super.getName()+", victorias="+super.getnVictories()+", pilones="+nPylons+"}\n";
    }
}
