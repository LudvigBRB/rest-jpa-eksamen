/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import entities.MenuPlan;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ludvig
 */
public class MenuDTOs {
    
    private List<MenuDTO> menusDTO = new ArrayList<MenuDTO>();
    
    public MenuDTOs(List<MenuPlan> menus) {    
        for(int i = 0; i < menus.size(); i++) {
            this.menusDTO.add(new MenuDTO(menus.get(i)));          
        }  
    }

    public List<MenuDTO> getMenusDTO() {
        return menusDTO;
    }    
    
}
