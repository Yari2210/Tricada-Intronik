package tricada.intronik.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RoomModel {

    @JsonProperty("id")
    private Double id;

    @JsonProperty("roomtype")
    private String roomtype;

    @JsonProperty("description")
    private String description;

    @JsonProperty("price")
    private Double price;



    public String getRoomtype() {
        return roomtype;
    }

    public void setRoomtype(String roomtype) {
        this.roomtype = roomtype;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getId() {
        return id;
    }

    public void setId(Double id) {
        this.id = id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
