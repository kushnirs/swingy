package ua.unitfactory.swingy.model.artifacts;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Artifacts {
    @NotNull(message = "Name cannot be null")
    @Size(min = 1, message = "Name must be at least 1 character")
    protected String    name;
    @Min(value = 0, message = "artifact quality should not be less than 0")
    protected int       quality = 100;
    @Min(value = 0, message = "artifact value should not be less than 0")
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
