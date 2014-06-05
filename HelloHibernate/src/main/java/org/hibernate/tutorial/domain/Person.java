package org.hibernate.tutorial.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by 908869 on 6/5/2014.
 */
public class Person {

    private Long id;

    private int age;

    private String firstName;

    private String lastName;

    private Set<Event> events = new HashSet<Event>();

    private Set<String> emailAddresses = new HashSet<String>();

   public Person() {
    }

    public Long getId() {
        return id;
    }

    private void setId(Long id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<Event> getEvents() {
        return events;
    }

    public void setEvents(Set<Event> events) {
        this.events = events;
    }

    public Set<String> getEmailAddresses() {
        return emailAddresses;
    }

    public void setEmailAddresses(Set<String> emailAddresses) {
        this.emailAddresses = emailAddresses;
    }
}
