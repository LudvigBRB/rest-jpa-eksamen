/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Ludvig
 */
@Entity
//@Table(name = "menu")
@NamedQuery(name = "MenuPlan.deleteAllRows", query = "DELETE from MenuPlan")
public class MenuPlan implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private List<String> shoppingList;
    
    private String message;
    
    @OneToMany (cascade = CascadeType.PERSIST)
    private List<DayPlan> weekPlan = new ArrayList();

    public MenuPlan() {
    }

    public MenuPlan(String message, List<String> shoppingList, List<DayPlan> weekPlan) {
        this.message = message;
        this.shoppingList = shoppingList;
        this.weekPlan = weekPlan;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setShoppingList(List<String> shoppingList) {
        this.shoppingList = shoppingList;
    }
    
    public void addToBuy(String thing) {
        shoppingList.add(thing);
    }

    public List<String> getShoppingList() {
        return shoppingList;
    }

    public void setWeekPlan(List<DayPlan> weekPlan) {
        this.weekPlan = weekPlan;
    }
    
    public void addDayPlan(DayPlan dayPlan) {
        weekPlan.add(dayPlan);
    }

    public List<DayPlan> getWeekPlan() {
        return weekPlan;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "entities.MenuPlan[ Denne menuplans id=" + id + " ]";
    }
    
}
