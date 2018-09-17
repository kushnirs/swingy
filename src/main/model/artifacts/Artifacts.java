package main.model.artifacts;

public class Artifacts {
    protected String    name;
    protected int       quality = 100;
    protected int       value;

    public Artifacts(String name, int value) {
        this.name = name;
        this.value = value;
    }

    //Getter
    public int getQuality() {
        return quality;
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    //Setter
    public void setQuality(int quality) {
        this.quality = quality;
    }
}
