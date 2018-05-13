package service.dto;


import model.Entity;
import model.enums.VehicleType;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name="vehicle")
public class VehicleDTO  extends Entity {

    private int owner;
    private VehicleType type;
    private int capacity;
    private String description;
    private List<String> photos;


    public int getOwner() {
        return owner;
    }

    public void setOwner(int owner) {
        this.owner = owner;
    }

    public VehicleType getType() {
        return type;
    }

    public void setType(VehicleType type) {
        this.type = type;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getPhotos() {
        return photos;
    }

    public void setPhotos(List<String> photos) {
        this.photos = photos;
    }
}