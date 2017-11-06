package domain;

public class Package
{
    private String packageName;
    private PackageMaintainer packageMaintainer;

    public Package() { }
    public Package(String packageName, PackageMaintainer packageMaintainer) {
        this.packageName = packageName;
        this.packageMaintainer = packageMaintainer;
    }

    public String getPackageName() {
        return packageName;
    }
    public Package setPackageName(String packageName) {
        this.packageName = packageName;
        return this;
    }
    public PackageMaintainer getPackageMaintainer() {
        return packageMaintainer;
    }
    public Package setPackageMaintainer(PackageMaintainer packageMaintainer) {
        this.packageMaintainer = packageMaintainer;
        return this;
    }
}
