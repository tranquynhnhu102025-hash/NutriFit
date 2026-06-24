package com.example.nutrifit.data.model;

public class User {
    private int Id;
    private String Name;
    private String Email;

    public User(int id, String name, String email) {
        this.Id = id;
        this.Name = name;
        this.Email = email;
    }

    public int getId() { return Id; }
    public String getName() { return Name; }
    public String getEmail() { return Email; }
}
