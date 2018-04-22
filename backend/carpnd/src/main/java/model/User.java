package model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "USER")
public class User extends IdModel {

    public String name;
    public String lastName;
    public String cuil;
    public String email;
    private SystemRateManager rateManager;

    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    @LazyCollection(LazyCollectionOption.FALSE)
    public List<Vehicle> vehicleList;

    public User(String name, String lastName, String cuil, String email) {
        this.name = name;
        this.lastName = lastName;
        this.cuil = cuil;
        this.email = email;
        this.rateManager = new SystemRateManager();
        this.vehicleList = new LinkedList<>();
    }

    /*
    Return user's owner rate on the system
     */
    public Long ownerRate() {
//        TODO: Implementar
        return rateManager.ownerRate;
    }

    /*
    Return user's customer rate on the system
     */
    public Long customerRate() {
//        TODO: Implementar
        return rateManager.customerRate;
    }
}

