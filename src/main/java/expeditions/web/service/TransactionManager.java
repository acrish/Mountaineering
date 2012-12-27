package expeditions.web.service;

import expeditions.web.HibernateUtil;
import expeditions.web.model.Expedition;
import expeditions.web.model.ExpeditionMap;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.sql.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Hac
 * Date: 12/24/12
 * Time: 7:20 PM
 */
public class TransactionManager {

    public void addExpeditionMap(ExpeditionMap map) {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        Transaction tx = null;
        try {
            // create session
            tx = session.beginTransaction();
            session.save(map);
            tx.commit();
        } catch (Exception exp) {
            if (tx != null) {
                tx.rollback();
            }
            // close session
            session.close();
        } finally {
            if (session.isOpen()) {
                session.close();
            }
        }
    }

    public void addExpedition(Expedition expedition) {

        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        Transaction tx = null;
        try {
            // create session
            tx = session.beginTransaction();
            session.save(expedition);
            tx.commit();
        } catch (Exception exp) {
            if (tx != null) {
                tx.rollback();
            }
            // close session
            session.close();
        } finally {
            if (session.isOpen()) {
                session.close();
            }
        }
    }

    public List<Expedition> getAllExpeditions() {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        Transaction tx = null;
        List<Expedition> result = null;

        try {
            // create session
            tx = session.beginTransaction();
            result = session.createQuery(
                    "from Expedition").list();
            tx.commit();
        } catch (Exception exp) {
            if (tx != null) {
                tx.rollback();
            }
            // close session
            session.close();
        }
        if (session.isOpen()) {
            session.close();
        }

        return result;
    }

    public List<Expedition> getAllExpeditionMaps() {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        Transaction tx = null;
        List<Expedition> result = null;

        try {
            // create session
            tx = session.beginTransaction();
            result = session.createQuery(
                    "from ExpeditionMap").list();
            tx.commit();
        } catch (Exception exp) {
            if (tx != null) {
                tx.rollback();
            }
            // close session
            session.close();
        }
        if (session.isOpen()) {
            session.close();
        }

        return result;
    }
}
