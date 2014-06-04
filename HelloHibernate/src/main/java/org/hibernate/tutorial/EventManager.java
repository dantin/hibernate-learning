package org.hibernate.tutorial;

import org.hibernate.Session;
import org.hibernate.tutorial.domain.Event;
import org.hibernate.tutorial.util.HibernateUtil;

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
        }

//        HibernateUtil.getSessionFactory().close();
//        System.exit(0);
        HibernateUtil.close();
    }

    private void createAndStoreEvent(String title, Date theDate) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Event theEvent = new Event();
        theEvent.setTitle(title);
        theEvent.setDate(theDate);
        session.save(theEvent);

        session.getTransaction().commit();
    }

    private List<Event> listEvents() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<Event> result = session.createQuery("from Event").list();
        session.getTransaction().commit();
        return result;
    }
}
