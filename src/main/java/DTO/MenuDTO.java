/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import entities.DayPlan;
import entities.MenuPlan;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ludvig
 */
public class MenuDTO {
    
    private int id;
    private List<String> shoppingList;
    private List<DayPlan> weekPlan = new ArrayList();
    private String message;
    
    public MenuDTO(MenuPlan mp){
        this.id = mp.getId();
        this.shoppingList = mp.getShoppingList();
        this.weekPlan = mp.getWeekPlan();
        this.message = mp.getMessage();
    }
}
