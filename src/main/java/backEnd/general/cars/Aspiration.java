/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backEnd.general.cars;

/**
 *
 * @author jonathan
 */
public enum Aspiration {
    NA ("NA"),
    T ("T"),
    SC ("SC"),
    EV("EV");
    
    private final String description;

    private Aspiration(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
    
    public static Aspiration toAspiration(String description) {
        Aspiration aspiration = null;
        
        for (Aspiration value : Aspiration.values()) {
            if(description.equals(value.getDescription())) {
                aspiration = value;
            }
        }
        
        return aspiration;
    }
}
