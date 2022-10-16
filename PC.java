package src.lab_assessment_02;

public class PC {
    private String assetID, name, LCDname;
    private int RAMsize, diskSize;

    public PC(String assetID, String name, String LCDname, int RAMsize, int diskSize) {
        this.assetID = assetID;
        this.name = name;
        this.LCDname = LCDname;
        this.RAMsize = RAMsize;
        this.diskSize = diskSize;
    }

    public PC(PC pc) {
        this.assetID = pc.assetID;
        this.name = pc.name;
        this.LCDname = pc.LCDname;
        this.RAMsize = pc.RAMsize;
        this.diskSize = pc.diskSize;
    }

    public String getAssetID() {
        return assetID;
    }

    public void setAssetID(String assetID) {
        this.assetID = assetID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLCDname() {
        return LCDname;
    }

    public void setLCDname(String LCDname) {
        this.LCDname = LCDname;
    }

    public int getRAMsize() {
        return RAMsize;
    }

    public void setRAMsize(int RAMsize) {
        this.RAMsize = RAMsize;
    }

    public int getDiskSize() {
        return diskSize;
    }

    public void setDiskSize(int diskSize) {
        this.diskSize = diskSize;
    }

    public String toString() {
        return String.format("\nName: %s\nAsset ID: %s\nLCD: %s\nRAM size: %dGB\nDisk Size: %dGB\n", this.name, this.assetID,
                this.LCDname, this.RAMsize, this.diskSize);
    }

    public Object clone() {
        return new PC(this.assetID, this.name, this.LCDname, this.RAMsize, this.diskSize);
    }

    public boolean equals(Object o) {
        PC temp = (PC) o;
        boolean isResult = false;

        if (temp.name == temp.name && temp.LCDname == this.LCDname && this.assetID == temp.assetID
                && this.RAMsize == temp.RAMsize && this.diskSize == temp.diskSize) {
            isResult = true;
        } else {
            isResult = false;
        }

        return isResult;
    }
}
