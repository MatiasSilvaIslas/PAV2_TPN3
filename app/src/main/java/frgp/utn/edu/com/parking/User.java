package frgp.utn.edu.com.parking;

import java.io.Serializable;

public class User implements Serializable {
    public User( String name, String email, String password)
    {
        this.Name = name;
        this.Email = email;
        this.Password = password;
    }

    private String Name;
    private String Email;
    private String Password;


    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    @Override
    public String toString() {
        return "Nombre de usuario: " + getName() + "\n" +
                "Email: " + getEmail();
    }
}
