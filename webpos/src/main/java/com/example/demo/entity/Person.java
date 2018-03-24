package com.example.demo.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
 
 
@Entity
@Table(name = "PERSON")
public class Person {
    private int id;
    private String name;
    private byte[] photo;
 
    public Person() {
    }
 
    public Person(String name) {
        this.name = name;
    }
 
    @Id
    @Column(name = "PERSON_ID")
    @GeneratedValue
    public int getId() {
        return id;
    }
 
    public byte[] getPhoto() {
        return photo;
    }
 
    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setId(int id) {
		this.id = id;
	}
 
    // other getters and setters
}