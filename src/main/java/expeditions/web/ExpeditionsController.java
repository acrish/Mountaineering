package expeditions.web;


import expeditions.web.model.Expedition;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: acrish
 * Date: 12/9/12
 * Time: 8:57 PM
 */
@Controller
public class ExpeditionsController {

    protected final Log logger = LogFactory.getLog(getClass());

    @RequestMapping("/hello.htm")
    public ModelAndView helloWorld() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("hello");
        mav.addObject("message", "Hello World!");

        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        Transaction tx = null;
        try {
            // create session
            tx = session.beginTransaction();
            Expedition expedition = new Expedition("Tura dec", "Grosslockner Austria 7 zile");
            session.save(expedition);
            tx.commit();
        } catch (Exception exp) {
            tx.rollback();
            // close session
            session.close();
        }
        if (session.isOpen()) {
            List<Expedition> expeditions = session.createQuery(
                    "from Expedition").list();
            for (Expedition ex : expeditions)
                System.out.println("\t" + ex.getName());
            session.close();
        }

        return mav;
    }
}
