package service.dto;

import model.Entity;

public class ReservationDTO extends Entity{

    private UserDTO tenantUser;
    private PostDTO post;
    private String reservationSinceDate;
    private String reservationUntilDate;
    private String statusReservation;
    private String owner;

    public ReservationDTO(){}

    public ReservationDTO(UserDTO tenant, PostDTO post,
                          String sinceDate, String untilDate, String status, int id, String owner){
        this.tenantUser=tenant;
        this.post=post;
        this.reservationSinceDate=sinceDate;
        this.reservationUntilDate=untilDate;
        this.statusReservation=status;
        this.setId(id);
        this.owner=owner;
    }

    public UserDTO getTenantUser() {
        return tenantUser;
    }

    public void setTenantUser(UserDTO tenantUser) {
        this.tenantUser = tenantUser;
    }

    public PostDTO getPost() {
        return post;
    }

    public void setPost(PostDTO post) {
        this.post = post;
    }

    public String getReservationSinceDate() {
        return reservationSinceDate;
    }

    public void setReservationSinceDate(String reservationSinceDate) {
        this.reservationSinceDate = reservationSinceDate;
    }

    public String getReservationUntilDate() {
        return reservationUntilDate;
    }

    public void setReservationUntilDate(String reservationUntilDate) {
        this.reservationUntilDate = reservationUntilDate;
    }

    public String isStatusReservation() {
        return statusReservation;
    }

    public void setStatusReservation(String statusReservation) {
        this.statusReservation = statusReservation;
    }

    public String getStatusReservation() {
        return statusReservation;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}
