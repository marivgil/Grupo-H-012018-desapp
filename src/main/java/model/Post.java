package model;

import model.exceptions.NoCoordsEnoughException;
import model.exceptions.TimeOutOfRangeException;
import model.exceptions.UserBlockedException;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class Post extends Entity{

	private Vehicle vehicle;
	private User ownerUser;
	private Coord pickUpCoord;
	private List<Coord> returnCoords= new ArrayList<Coord>();
	private LocalDateTime sinceDate;
	private LocalDateTime UntilDate;
	private double costPerDay;
	private int phone;
	private String location;

	
	public Post(){}

	public Post(Vehicle vehicle, User user, Coord pickUpCoord, List<Coord> returnCoords2,
                LocalDateTime sinceDate, LocalDateTime UntilDate, double costPerDay, String location){

		long days= sinceDate.until(UntilDate, ChronoUnit.DAYS);

		if( days<1 || days>5 ){
			throw new TimeOutOfRangeException();
		}
		if(!user.isEnabled()){
		    throw new UserBlockedException();
        }
		if(pickUpCoord==null || returnCoords2.isEmpty()){
			throw new NoCoordsEnoughException();
		}
		
		this.vehicle=vehicle;
		this.ownerUser=user;
		this.pickUpCoord=pickUpCoord;
		this.returnCoords=returnCoords2;
        this.sinceDate = sinceDate;
		this.UntilDate = UntilDate;
		//this.phone = phone;
        this.costPerDay = costPerDay;
        this.location= location;
	}
	
	public double getCostPerDay(){
		return this.costPerDay;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public User getOwnerUser() {
		return ownerUser;
	}

	public void setOwnerUser(User ownerUser) {
		this.ownerUser = ownerUser;
	}

	public Coord getPickUpCoord() {
		return pickUpCoord;
	}

	public void setPickUpCoord(Coord pickUpCoord) {
		this.pickUpCoord = pickUpCoord;
	}

	public List<Coord> getReturnCoords() {
		return returnCoords;
	}

	public void setReturnCoords(List<Coord> returnCoords) {
		this.returnCoords = returnCoords;
	}

	public LocalDateTime getSinceDate() {
		return sinceDate;
	}

	public void setSinceDate(LocalDateTime sinceDate) {
		this.sinceDate = sinceDate;
	}

	public LocalDateTime getUntilDate() {
		return UntilDate;
	}

	public void setUntilDate(LocalDateTime untilDate) {
		UntilDate = untilDate;
	}

	public void setCostPerDay(double costPerDay) {
		this.costPerDay = costPerDay;
	}

	public int getPhone() {
		return phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
}
