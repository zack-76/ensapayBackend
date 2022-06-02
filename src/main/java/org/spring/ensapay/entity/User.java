package org.spring.ensapay.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class User {

    //Attributes
    @Id
    private String userPhone;  // signin using phoneNumber
    private String userFirstName;
    private String userLastName;
    private String userAddress;
    private String userBirthDate;
    private String userEmail;
    private String userCIN;
    private Integer clientSolde;
    private String userPassword;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "USER_ROLE",
            joinColumns = {
                    @JoinColumn(name = "USER_ID")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "ROLE_ID")
            }
    )
    private Set<Role> role;

    //Getter and Setter
    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getUserBirthDate() {
        return userBirthDate;
    }

    public void setUserBirthDate(String userBirthDate) {
        this.userBirthDate = userBirthDate;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }


    public Set<Role> getRole() {
        return role;
    }

    public void setRole(Set<Role> role) {
        this.role = role;
    }
    public String getUserCIN() {
        return userCIN;
    }

    public void setUserCIN(String userCIN) {
        this.userCIN = userCIN;
    }

    public Integer getClientSolde() {
        return clientSolde;
    }

    public void setClientSolde(Integer clientSolde) {
        this.clientSolde = clientSolde;
    }
}
