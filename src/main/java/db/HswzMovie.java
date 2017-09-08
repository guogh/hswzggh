package db;

public class HswzMovie {
    private int id;
    private String name;
    private String upMaster;
    private String path;
    private String netUrl;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getNetUrl() {
        return netUrl;
    }

    public void setNetUrl(String netUrl) {
        this.netUrl = netUrl;
    }

    public String getUpMaster() {
        return upMaster;
    }

    public void setUpMaster(String upMaster) {
        this.upMaster = upMaster;
    }
}
