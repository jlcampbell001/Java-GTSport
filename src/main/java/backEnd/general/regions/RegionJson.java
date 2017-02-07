/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backEnd.general.regions;

/**
 *
 * @author jonathan
 */
public class RegionJson {
    private String primaryKey = "";
    private String description = "";

    public String getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(String primaryKey) {
        this.primaryKey = primaryKey;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "RegionJson [" + "primaryKey=" + primaryKey + ", description=" + description + "]";
    }
    
    
}
