package com.example.osamahqawasmeh.eatit.Model;

/**
 * Created by osamahqawasmeh on 12/25/17.
 */

public class User {
    private String Name;
    private String Passowrd;

    public User() {
    }

    public User(String name, String passowrd) {
        Name = name;
        Passowrd = passowrd;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPassowrd() {
        return Passowrd;
    }

    public void setPassowrd(String passowrd) {
        Passowrd = passowrd;
    }
}
