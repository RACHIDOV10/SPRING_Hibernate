/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="machines")
public class Machine {
    
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private int id;
    
    private String refe;
    
    @Temporal(TemporalType.DATE)
    private Date dateAchat;

    public Machine( String refe, Date dateAchat) {
        this.refe = refe;
        this.dateAchat = dateAchat;
    }
    
    public Machine(){
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRefe() {
        return refe;
    }

    public void setRefe(String refe) {
        this.refe = refe;
    }

    public Date getDateAchat() {
        return dateAchat;
    }

    public void setDateAchat(Date dateAchat) {
        this.dateAchat = dateAchat;
    }

    @Override
    public String toString() {
        return "Machine{" + "id=" + id + ", refe=" + refe + ", dateAchat=" + dateAchat + '}';
    }
    
    @ManyToOne
    private Salle salle;
    
    
    
}
