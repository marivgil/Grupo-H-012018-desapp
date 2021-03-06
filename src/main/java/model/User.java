package model;

import model.exceptions.InvalidEmailException;
import model.exceptions.NameTooLongException;
import model.exceptions.NameTooShortException;
import model.exceptions.NoAddressException;
import model.interfaces.IUserState;

import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/***
	 **This class sets an User in the system. At the moment this can:
	 *  - Build an user. This requires: CUIL, name, surname, address and email
	 *  - Getters y setters.
	 *  - Delagate the ability to Add/Debit Credit to his own account.
	 *  - Delegate the ability to post or rent to the states .
	 *  - Save scores, check the current status. 
	 *  - Calculate his own reputation.
	 */

public class User{

	private String CUIL;

	private String name;
	private String surname;
	private String address;

	@Id
	private String email;
	private Account account;
	private IUserState status;
	private List<Integer> scores;
	private String userName;

	
	/**
	 * Constructors
	 */
	
	public User(){
		//super();
		this.account= new Account();
		this.status= new UserEnabled();
		this.scores= new ArrayList<Integer>();
	};

	public User(String CUIL, String name, String surname, String address, String email){
		int completeName=(name+surname).length();
		if(completeName<=4 && name!=null && !name.equals("") && surname!=null && !surname.equals("")){
			throw new NameTooShortException();
		}
		
		if(completeName>=50){
			throw new NameTooLongException();
		}
		if(address==null|| address.isEmpty()){
			throw new NoAddressException();
		}
		
		if(email==null || !email.contains("@")){
			throw new InvalidEmailException();
		}
		
		String mailLastPart=email.split("@")[1];
		if (!mailLastPart.contains(".")){
			throw new InvalidEmailException();
		}
		
		this.CUIL=CUIL;
		this.name=name;
		this.surname=surname;
		this.address=address;
		this.email=email;
		this.account= new Account();
		this.status= new UserEnabled();
		this.scores= new ArrayList<Integer>();
	}

	/**
	 * Getters y setters
	 */

	public Account getAccount(){
		return this.account;
	}


	/**
	 * Public Methods.
	 */

	//Try to make a post
	public Post post(Vehicle vehicle, Coord pickUpCoord, Coord returnCoords,
					 LocalDateTime sinceDate, LocalDateTime UntilDate, double costPerHour){
		return this.status.post(vehicle,this, pickUpCoord,returnCoords,
				sinceDate, UntilDate, costPerHour);
	}
	
	//Try to rent a vehicle
	public Reservation rent(Post post, LocalDateTime reservationSinceDate,
							LocalDateTime reservationUntilDate){
		return this.status.rent(post, reservationSinceDate, reservationUntilDate, this);
	}
	
	//Save the score obtained in one transaction and check the current status.
	public void processScore(Integer score){
		saveScore(score);
		checkUserStatus();
	}
	
	//Return the AVG of the scores or new User default
	public double reputation(){
		if(isNewUser()){
			return 3.0;
		}else{
			return this.avgOfScores();
		}
	}
	
	//Return true if the user is enabled.
	public boolean isEnabled(){
		return this.status.isEnabled();
	}

	//Add credit to his own account.
	public double addCredit(double creditToAdd){
		return account.addCredit(creditToAdd);
	}
	
	//Try to debit credit.
	public double debitCredit(double creditToDebit){
		return account.debitCredit(creditToDebit);
	}

	/**
	 * Private Methods
	 */
	
	//Return true if the score is between the established limits.
	private boolean isCorrectScore(Integer score){
		return 0<=score && score<=maxScore();
	}
	
	//Disable the user if the reputation is lower than the minimum.
	private void checkUserStatus(){
		if (reputation()<minScoreEnabling()){
			disableUser();
		}
	}
	
	//Disable the user to make some transactions.
	private void disableUser(){
		this.status= new UserDisabled();
	}
	
	//Return the minimum score allowed
	private double minScoreEnabling(){
		return 3.0;
	}
	
	//Sets the maximum score that an user can receive
	private double maxScore(){
		return 5.0;
	}	
	
	private boolean isNewUser() {
		return this.scores.isEmpty();
	}

	private double avgOfScores(){
		int sum=0;
		for(Integer i:scores){
			sum+=i;
		}
		return (sum/scores.size());
	}
	
	//Save the score obtained in one transaction if it is within the correct range
	private void saveScore(Integer score){
		if(!isCorrectScore(score)){
			throw new NoSuchFieldError("El puntaje es incorrecto");
		 }else{
			this.scores.add(score);
		  }
	}

		public void enabledUser() {
		this.status=new UserEnabled();
		}

    public String getAddress() {
        return address;
    }

	public String getCUIL() {
		return CUIL;
	}

	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}

	public String getEmail() {
		return email;
	}

	public void setCUIL(String CUIL) {
		this.CUIL = CUIL;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public IUserState getStatus() {
		return status;
	}

	public void setStatus(IUserState status) {
		this.status = status;
	}

	public List<Integer> getScores() {
		return scores;
	}

	public void setScores(List<Integer> scores) {
		this.scores = scores;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
