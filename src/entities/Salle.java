/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="salles")
public class Salle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private String code;

    public Salle( String code) {
        this.code = code;
    }
    
    public Salle(){
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }
    @Column
private LocalDate dateCreation; // champ date de création

// Getter
public LocalDate getDateCreation() {
    return dateCreation;
}

// Setter
public void setDateCreation(LocalDate dateCreation) {
    this.dateCreation = dateCreation;
}

    @Override
    public String toString() {
        return "Salle{" + "id=" + id + ", code=" + code + '}';
    }

    public void setCode(String code) {
        this.code = code;
    }
    
    @OneToMany(mappedBy = "salle",fetch = FetchType.EAGER)
    private List<Machine> machines;   
    
}
