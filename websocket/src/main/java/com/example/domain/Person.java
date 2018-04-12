package com.example.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import java.io.Serializable;

@Entity
@NamedQuery(name="Person.withNameAndAddressNamedQuery",query = "select p from Person p where p.name = ?1 and p.address = ?2 ")
public class Person implements Serializable{

    private String name ;

    private Integer age ;

    private String address;

    @Id
    @GeneratedValue
    private  Long id ;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Person(Long id , String name, Integer age, String address) {
        this.name = name;
        this.age = age;
        this.address = address;
        this.id = id;
    }
    public Person(){}
}
