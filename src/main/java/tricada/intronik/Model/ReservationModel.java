package tricada.intronik.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;

public class ReservationModel {

    @JsonProperty("id")
    private String id;

    @JsonProperty("username")
    private String username;

    @JsonProperty("roomtype")
    private String roomtype;

    @JsonProperty("additional")
    private List<String> additional;

    @JsonProperty("numberofnights")
    private Integer numberofnights;

    @JsonProperty("checkin")
    private String checkin;

    @JsonProperty("checkout")
    private String checkout;

    @JsonProperty("status")
    private String status;

    @JsonProperty("updatedby")
    private String updatedby;

    @JsonProperty("updateddate")
    private Date updateddate;

    @JsonProperty("createddate")
    private Date createddate;

    @JsonProperty("createdby")
    private String createdby;

    @JsonProperty("totalprice")
    private Double totalprice;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRoomtype() {
        return roomtype;
    }

    public void setRoomtype(String roomtype) {
        this.roomtype = roomtype;
    }

    public List<String> getAdditional() {
        return additional;
    }

    public void setAdditional(List<String> additional) {
        this.additional = additional;
    }

    public Integer getNumberofnights() {
        return numberofnights;
    }

    public void setNumberofnights(Integer numberofnights) {
        this.numberofnights = numberofnights;
    }

    public String getCheckin() {
        return checkin;
    }

    public void setCheckin(String checkin) {
        this.checkin = checkin;
    }

    public String getCheckout() {
        return checkout;
    }

    public void setCheckout(String checkout) {
        this.checkout = checkout;
    }

    public String getUpdatedby() {
        return updatedby;
    }

    public void setUpdatedby(String updatedby) {
        this.updatedby = updatedby;
    }

    public Date getUpdateddate() {
        return updateddate;
    }

    public void setUpdateddate(Date updateddate) {
        this.updateddate = updateddate;
    }

    public Date getCreateddate() {
        return createddate;
    }

    public void setCreateddate(Date createddate) {
        this.createddate = createddate;
    }

    public String getCreatedby() {
        return createdby;
    }

    public void setCreatedby(String createdby) {
        this.createdby = createdby;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(Double totalprice) {
        this.totalprice = totalprice;
    }
}
