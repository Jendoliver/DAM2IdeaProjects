package domain;

import java.util.List;

public class BugReport
{
    private int id;
    private List<Package> packages;

    public BugReport() { }
    public BugReport(int id, List<Package> packages) {
        this.id = id;
        this.packages = packages;
    }

    public int getId() {
        return id;
    }
    public BugReport setId(int id) {
        this.id = id;
        return this;
    }
    public List<Package> getPackages() {
        return packages;
    }
    public BugReport setPackages(List<Package> packages) {
        this.packages = packages;
        return this;
    }
    public void send() {
        // Generate the report and write to emailMainteiner.txt
    }
}
