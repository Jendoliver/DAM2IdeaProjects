package application.model.entities;

import application.model.exceptions.InvalidDataException;

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
    public double calculateAtkPower() {
        return atkLevel + atkLevel*techLevel*0.5;
    }

    @Override
    public double calculateDefPower() {
        return defLevel + defLevel*nBuildings*0.25;
    }

    @Override
    public void improve(String propertyToImprove, int newPropertyValue) throws InvalidDataException {
        switch(propertyToImprove) {
            case "edificios": this.setnBuildings(newPropertyValue); break;
            case "tecnologia": this.setTechLevel(newPropertyValue); break;
            default: throw new InvalidDataException("< ERROR 006: Propiedad incorrecta >");
        }
    }

    @Override
    public String toString() {
        return "Terran {nombre="+super.getName()+", victorias="+super.getnVictories()+", edificios="+nBuildings+", tecnologia="+techLevel+"}\n";
    }
}
