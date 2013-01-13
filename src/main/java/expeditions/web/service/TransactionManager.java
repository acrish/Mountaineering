package expeditions.web.service;

import expeditions.web.HibernateUtil;
import expeditions.web.model.*;
import org.hibernate.Query;
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
    SessionFactory sf = HibernateUtil.getSessionFactory();

    public List executeAQuery(String query) {
        Session session = sf.openSession();
        org.hibernate.Query q = null;
        List result = null;
        try {
            q = session.createSQLQuery(query);
            result = q.list();
        } catch (Exception exp) {
            exp.printStackTrace();
        } finally {
            if (session.isOpen()) {
                session.close();
            }
        }

        return result;
    }

    public List getSomeHuts() {
        String query = "select m.mountain Mountain,m.issue_date Map_Issue_Date, h.name Hut, h.build_date Build_Date " +
                "from huts h right join expeditionmaps m on h.build_date > date_sub(curdate(), interval 300 year) " +
                "and m.map_id=h.map_id";
        return executeAQuery(query);
    }

    public List getFastestExpedition() {
        String query = "select p.name Participant, p.birth_date Birth_Date, e.expname Expedition, " +
                " m.mountain Mountain, min(datediff(r.end_date, r.start_date)) Days " +
                "from expeditionmaps m, expeditions e, registries r, participants p " +
                "where e.map_id=m.map_id and r.registry_id=p.participant_id " +
                "and m.mountain='Piatra Craiului' GROUP BY p.name;";
        return executeAQuery(query);
    }

    public List getGrossglocknerParticipants() {
        String query = "select p.*, e.* from participants p, expeditions e, registries r " +
                "where r.participant_id=p.participant_id and e.expid=r.expedition_id;";
        return executeAQuery(query);
    }

    public void getLatestExpeditions() {

        Session session = sf.openSession();
        org.hibernate.Query query = session.createSQLQuery(
            "call latestExpeditions();");
        try {
            query.list();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public List<LatestExp> getLatest() {
        Session session = sf.openSession();
        Transaction tx = null;
        List<LatestExp> result = null;

        try {
            // create session
            tx = session.beginTransaction();
            result = session.createQuery(
                    "from LatestExp").list();
            result = result.subList(0, result.size() - 1);
            tx.commit();
        } catch (Exception exp) {
            System.err.println("Error when retrieving latest expeditions!!!\n\t" + exp);
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

    public void add(Object obj) {

        Session session = sf.openSession();
        Transaction tx = null;
        try {
            // create session
            tx = session.beginTransaction();
            session.save(obj);
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

    public void addHut(Hut hut) {
        add(hut);
    }

    public List<Hut> getAllHuts() {

        Session session = sf.openSession();
        Transaction tx = null;
        List<Hut> result = null;

        try {
            // create session
            tx = session.beginTransaction();
            result = session.createQuery(
                    "from Hut").list();
            tx.commit();
        } catch (Exception exp) {
            System.err.println("Error when retrieving huts!!!\n\t" + exp);
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

    public void addExpeditionMap(ExpeditionMap map) {

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

    public void updateExpeditionMap(ExpeditionMap map) {

        Session session = sf.openSession();
        Transaction tx = null;
        try {
            // create session
            tx = session.beginTransaction();
            String updateQ_template = "UPDATE expeditionmaps SET mountain=\"%s\", issue_date=\"%s\", picture_url=\"%s\"" +
                    "where map_id=%d";
            Query q = session.createSQLQuery(String.format(updateQ_template, map.getMountain(), map.getIssueDate(),
                    map.getPicUrl(), map.getId()));
            q.executeUpdate();
            tx.commit();
        } catch (Exception exp) {
            System.out.println(exp);
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

    public ExpeditionMap getExpeditionMap(int id) {

        Session session = sf.openSession();
        Transaction tx = null;
        ExpeditionMap result = null;

        try {
            // create session
            tx = session.beginTransaction();
            result = (ExpeditionMap)session.createQuery(
                    "from ExpeditionMap where id="+id).uniqueResult();
            tx.commit();
        } catch (Exception exp) {
            System.err.println("Error when retrieving expedition map " + id + "!!!\n\t" + exp);
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

    public void removeHut(Hut obj) {

        Session session = sf.openSession();
        Transaction tx = null;
        try {
            // create session
            tx = session.beginTransaction();
            String deleteQ_template = "delete from huts where id=%d";
            Query q = session.createSQLQuery(String.format(deleteQ_template, obj.getId()));
            q.executeUpdate();
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
