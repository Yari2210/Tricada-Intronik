package tricada.intronik.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AdditionalModel {

    @JsonProperty("id")
    private Double id;

    @JsonProperty("additional")
    private String additional;

    @JsonProperty("description")
    private String description;

    @JsonProperty("price")
    private Double price;


    public String getAdditional() {
        return additional;
    }

    public void setAdditional(String additional) {
        this.additional = additional;
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
