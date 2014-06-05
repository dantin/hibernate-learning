package org.hibernate.tutorial;

import org.hibernate.Session;
import org.hibernate.tutorial.domain.Event;
import org.hibernate.tutorial.domain.Person;
import org.hibernate.tutorial.util.HibernateUtil;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by 908869 on 6/3/2014.
 */
public class EventManager {

    public static void main(String[] args) throws Exception {
        EventManager mgr = new EventManager();

        if (args[0].equals("store")) {
            mgr.createAndStoreEvent("My Event", new Date());
        } else if(args[0].equals("list")) {
            List<Event> events = mgr.listEvents();
            for(Event event : events) {
                System.out.println("Event: " + event.getTitle() + " Time: " + event.getDate());
            }
        } else if(args[0].equals("addpersontoevent")) {
            Long eventId = (Long) mgr.createAndStoreEvent("My Event", new Date());
            Long personId = (Long) mgr.createAndStorePerson("Foo", "Bar");
            mgr.addPersonToEvent(personId, eventId);
            mgr.addEmailToPerson(personId, "email1@test.com");
            mgr.addEmailToPerson(personId, "email2@test.com");
            System.out.println("Added person " + personId + " to event " + eventId);
        }

//        HibernateUtil.getSessionFactory().close();
//        System.exit(0);
        HibernateUtil.close();
    }

    private Serializable createAndStoreEvent(String title, Date theDate) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Event theEvent = new Event();
        theEvent.setTitle(title);
        theEvent.setDate(theDate);
        Serializable result = session.save(theEvent);

        session.getTransaction().commit();

        return result;
    }

    private Serializable createAndStorePerson(String firstName, String lastName) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Person person = new Person();
        person.setFirstName(firstName);
        person.setLastName(lastName);
        person.setAge(18);
        Serializable result = session.save(person);

        session.getTransaction().commit();

        return result;
    }

    private List<Event> listEvents() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<Event> result = session.createQuery("from Event").list();
        session.getTransaction().commit();
        return result;
    }

    private void addPersonToEvent(Long personId, Long eventId) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Person person = (Person) session.load(Person.class, personId);
        Event event = (Event) session.load(Event.class, eventId);
        person.getEvents().add(event);

        session.getTransaction().commit();
    }

    private void addEmailToPerson(Long personId, String emailAddress) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Person aPerson = (Person) session.load(Person.class, personId);
        // adding to the emailAddress collection might trigger a lazy load of the collection
        aPerson.getEmailAddresses().add(emailAddress);

        session.getTransaction().commit();
    }
}
