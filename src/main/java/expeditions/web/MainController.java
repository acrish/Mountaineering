package expeditions.web;

import expeditions.web.model.Expedition;
import expeditions.web.model.ExpeditionMap;
import expeditions.web.service.TransactionManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by IntelliJ IDEA.
 * User: Hac
 * Date: 12/27/12
 * Time: 9:58 AM
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class MainController {
    private TransactionManager transactionManager = new TransactionManager();

    @RequestMapping("hello.htm")
    public ModelAndView init() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("hello");
        mav.addObject("tables", Arrays.asList(new String[]{"Expeditions", "Participants", "Registries"}));

        return mav;
    }

    @RequestMapping(value = "display_tables.htm")
    public ModelAndView displayTables() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("display_tables");

        ExpeditionMap map = new ExpeditionMap("Grosslockner", new Date(1990, 7, 26), "some_map_url");
        /*map.setExpeditions(new ArrayList<Expedition>());
        map.getExpeditions().add(expedition);*/
        transactionManager.addExpeditionMap(map);
        mav.addObject("maps", transactionManager.getAllExpeditionMaps());


        Expedition expedition = new Expedition("tura Iarna 2012", "Grosslockner Austria Zelmatt");
        /*expedition.setExpMap(map);*/
        transactionManager.addExpedition(expedition);

        mav.addObject("expeditions", transactionManager.getAllExpeditions());

        return mav;
    }
}
