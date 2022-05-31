package org.spring.ensapay.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class User {

    //Attributes
    @Id
    private String userName;//email
    private String userFirstName;
    private String userLastName;
    private String userPhone;
    private String userCIN;
    private Integer clientSolde;
    private String userPassword;
    private String clientProduct;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getClientProduct() {
        return clientProduct;
    }

    public void setClientProduct(String clientProduct) {
        this.clientProduct = clientProduct;
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
