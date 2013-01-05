package expeditions.web.service;

import expeditions.web.HibernateUtil;
import expeditions.web.model.Expedition;
import expeditions.web.model.ExpeditionMap;
import expeditions.web.model.Participant;
import expeditions.web.model.Registry;
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

    public void remove(Object obj) {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        Transaction tx = null;
        try {
            // create session
            tx = session.beginTransaction();
            session.delete(obj);
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
            System.err.println("Error when saving expedition " + expedition.getName() + "!!!\n\t"
                    + exp);
        } finally {
            if (session.isOpen()) {
                session.close();
            }
        }
    }

    public Expedition getExpedition(int id) {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        Transaction tx = null;
        Expedition result = null;

        try {
            // create session
            tx = session.beginTransaction();
            result = (Expedition)session.createQuery(
                    "from Expedition where id="+id).uniqueResult();
            tx.commit();
        } catch (Exception exp) {
            System.err.println("Error when retrieving expedition" + id + "!!!\n\t" + exp);
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

    public List<ExpeditionMap> getAllExpeditionMaps() {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        Transaction tx = null;
        List<ExpeditionMap> result = null;

        try {
            // create session
            tx = session.beginTransaction();
            result = session.createQuery(
                    "from ExpeditionMap").list();
            tx.commit();
        } catch (Exception exp) {
            System.err.println("Error when retrieving maps!!!\n\t" + exp);
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

    public ExpeditionMap getExpeditionMapByName(String name) {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        Transaction tx = null;
        ExpeditionMap result = null;

        try {
            // create session
            tx = session.beginTransaction();
            result = (ExpeditionMap)session.createQuery(
                    "from ExpeditionMap where mountain = '" + name + "'").list().get(0);
            tx.commit();
        } catch (Exception exp) {
            System.err.println("Error when retrieving map of mountain " + name + "!!!\n\t" + exp);
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

    public void addParticipant(Participant p) {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        Transaction tx = null;
        try {
            // create session
            tx = session.beginTransaction();
            session.save(p);
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

    public Participant getParticipant(int id) {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        Transaction tx = null;
        Participant result = null;

        try {
            // create session
            tx = session.beginTransaction();
            result = (Participant)session.createQuery(
                    "from Participant where id="+id).uniqueResult();
            tx.commit();
        } catch (Exception exp) {
            System.err.println("Error when retrieving participants!!!\n\t" + exp);
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

    public List<Registry> getAllParticipants() {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        Transaction tx = null;
        List<Registry> result = null;

        try {
            // create session
            tx = session.beginTransaction();
            result = session.createQuery(
                    "from Participant").list();
            tx.commit();
        } catch (Exception exp) {
            System.err.println("Error when retrieving participants!!!\n\t" + exp);
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

    public void addRegistry(Registry reg) {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        Transaction tx = null;
        try {
            // create session
            tx = session.beginTransaction();
            session.save(reg);
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

    public List<ExpeditionMap> getAllRegistries() {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        Transaction tx = null;
        List<ExpeditionMap> result = null;

        try {
            // create session
            tx = session.beginTransaction();
            result = session.createQuery(
                    "from Registry").list();
            tx.commit();
        } catch (Exception exp) {
            System.err.println("Error when retrieving registry!!!\n\t" + exp);
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
