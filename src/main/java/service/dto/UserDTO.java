package service.dto;

import model.Account;
import model.Entity;
import model.interfaces.IUserState;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

@XmlRootElement(name="user")
public class UserDTO  extends Entity {

    @Id
    private String CUIL;

    private String name;
    private String surname;
    private String address;
    private String email;
    //private Account account;
    //private IUserState status;
    //private ArrayList<Integer> scores;
    private String userName;
    private String password;

    public UserDTO(){}

    public String getCUIL() {
        return CUIL;
    }

    public void setCUIL(String CUIL) {
        this.CUIL = CUIL;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
