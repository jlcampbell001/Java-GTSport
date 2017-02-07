/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backEnd.general.regions;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author jonathan
 */
@Entity
@Table(name = "Regions")
public class Region implements Serializable {

    @Id
    @Column(name = "RegKey")
    private String primaryKey = "";

    @Column(name = "RegDescription")
    private String description = "";

    public Region(String primaryKey, String description) {
        this.primaryKey = primaryKey;
        this.description = description;
    }
    
    public Region() {
        this("", "");
    }
    
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
        return "Region[ primaryKey=" + primaryKey + ", description=" + description + " ]";
    }

}
