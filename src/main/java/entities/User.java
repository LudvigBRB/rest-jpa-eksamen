/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author Ludvig
 */
@Entity
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String userName;
    private String password;

    @OneToMany
    private List<MenuPlan> menuPlans;

    public User() {
    }
    
    public void setMenuPlans(List<MenuPlan> menuPlans) {
        this.menuPlans = menuPlans;
    }

    public List<MenuPlan> getMenuPlans() {
        return menuPlans;
    }

    public void addMenuPlan(MenuPlan menuPlan) {
        menuPlans.add(menuPlan);
    }

    public User(String userName, String userPass) {
        this.userName = userName;
        this.password = BCrypt.hashpw(userPass, BCrypt.gensalt(12));
    }

    public boolean verifyPassword(String plainText, String pw) {
        return (BCrypt.checkpw(plainText, password));
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "entities.User[ id=" + id + " ]";
    }

}
