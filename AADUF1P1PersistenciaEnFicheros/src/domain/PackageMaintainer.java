package domain;

public class PackageMaintainer
{
    private String maintainerName;
    private String maintainerEmail;

    public PackageMaintainer() { }
    public PackageMaintainer(String maintainerName, String maintainerEmail) {
        this.maintainerName = maintainerName;
        this.maintainerEmail = maintainerEmail;
    }

    public String getMaintainerName() {
        return maintainerName;
    }
    public PackageMaintainer setMaintainerName(String maintainerName) {
        this.maintainerName = maintainerName;
        return this;
    }
    public String getMaintainerEmail() {
        return maintainerEmail;
    }
    public PackageMaintainer setMaintainerEmail(String maintainerEmail) {
        this.maintainerEmail = maintainerEmail;
        return this;
    }
}
