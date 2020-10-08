/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author  Siddharth Patel
 */

public class Customer {

    private String Name;
    private String Address;
    private long Phone;
    private String Email;
    private String Type;

    
    //no-arg Constructor
    public Customer() {
    }
    

    // argument Constructor
    public Customer(String Name, String Address, long Phone, String Email, String Type) {
        this.Name = Name;
        this.Address = Address;
        this.Phone = Phone;
        this.Email = Email;
        this.Type = Type;
    }
 
 
    
    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public long getPhone() {
        return Phone;
    }

    public void setPhone(long Phone) {
        this.Phone = Phone;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getType() {
        return Type;
    }

    public void setType(String Type) {
        this.Type = Type;
    }
    

}
