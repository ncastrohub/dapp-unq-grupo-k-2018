package model;

public class User {

    public String name;
    public String lastName;
    public String cuil;
    public String email;
    public SystemRateManager rateManager;


    public User(String name, String lastName, String cuil, String email) {
        this.name = name;
        this.lastName = lastName;
        this.cuil = cuil;
        this.email = email;
        this.rateManager = new SystemRateManager();
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

