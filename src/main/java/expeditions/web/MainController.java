package expeditions.web;

import expeditions.web.model.*;
import expeditions.web.service.LatestExp;
import expeditions.web.service.TransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.beans.PropertyEditorSupport;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Hac
 * Date: 12/27/12
 * Time: 9:58 AM
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class MainController {
    @Autowired
    private TransactionManager transactionManager;
    @Autowired
    private PopulateDB populator;

    public MainController() {
    }

    @RequestMapping("/hello.htm")
    public ModelAndView init() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("hello");
        populator.populate();

        return mav;
    }

    @RequestMapping("/latestExpeditions.htm")
    public ModelAndView latestExpeditons() {

        ModelAndView mav = new ModelAndView();
        mav.setViewName("latestExpProcedure");
        List<LatestExp> latests = transactionManager.getLatest();
        mav.addObject("latests", latests);


        return mav;
    }

    @RequestMapping(value = "/display_tables.htm")
    public ModelAndView displayTables() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("display_tables");

        List<ExpeditionMap> maps = transactionManager.getAllExpeditionMaps();
        mav.addObject("maps", maps);

        mav.addObject("expeditions", transactionManager.getAllExpeditions());

        mav.addObject("participants", transactionManager.getAllParticipants());

        mav.addObject("registries", transactionManager.getAllRegistries());

        mav.addObject("huts", transactionManager.getAllHuts());

        return mav;
    }

    @RequestMapping(value = "/remove.htm")
    public ModelAndView removeById(@RequestParam Hut hut) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("hello");
        System.out.println("**************" + hut.getId());
        transactionManager.removeHut(hut);

        return mav;
    }

    @RequestMapping(value = "/addExpedition.htm")
    public ModelAndView addExpedition() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("addExpedition");
        Expedition exp = new Expedition();
        mav.addObject("newExp", exp);
        mav.addObject("maps", transactionManager.getAllExpeditionMaps());

        return mav;
    }

    @RequestMapping(value =  "/saveExpedition.htm", method = RequestMethod.POST)
    public ModelAndView saveExpedition(@ModelAttribute("newExp") Expedition exp) {
        ExpeditionMap expMap = transactionManager.getExpeditionMapByName(exp.getMountainName().substring(0,
                exp.getMountainName().indexOf(" - ")));
        System.err.println(expMap.getMountain());
        exp.setExpMap(expMap);
        transactionManager.addExpedition(exp);

        ModelAndView mav = new ModelAndView();
        mav.setViewName("hello");

        return mav;
    }

    @InitBinder
    public void dateBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        binder.registerCustomEditor(Date.class,
            new PropertyEditorSupport() {
                public void setAsText(final String text) {
                    System.err.println(text);
                    DateFormat DOB = new SimpleDateFormat("MM/dd/yyyy");
                    java.sql.Date convertedDate = null;
                    try {
                        convertedDate = new java.sql.Date(DOB.parse(text).getTime());
                        setValue(convertedDate);
                    } catch (ParseException e) {
                        DOB = new SimpleDateFormat("yyyy-MM-dd");
                        try {
                            convertedDate = new Date(DOB.parse(text).getTime());
                            setValue(convertedDate);
                        } catch (ParseException e1) {
                        }
                    }
                }
            });
    }

    @InitBinder
    public void participantBinder(WebDataBinder binder) {

        binder.registerCustomEditor(Participant.class,
            new PropertyEditorSupport() {
                public void setAsText(final String text) {
                    setValue(transactionManager.getParticipant(Integer.parseInt(
                            text.substring(0, text.indexOf(")")))));
                }
            });
    }

    @InitBinder
    public void expeditionBinder(WebDataBinder binder) {

        binder.registerCustomEditor(Expedition.class,
            new PropertyEditorSupport() {
                public void setAsText(final String text) {
                    setValue(transactionManager.getExpedition(Integer.parseInt(
                            text.substring(0, text.indexOf(")")))));
                }
            });
    }

    @InitBinder
    public void mapBinder(WebDataBinder binder) {

        binder.registerCustomEditor(ExpeditionMap.class,
            new PropertyEditorSupport() {
                public void setAsText(final String text) {
                    setValue(transactionManager.getExpeditionMap(Integer.parseInt(
                            text.substring(0, text.indexOf(")")))));
                }
            });
    }

    @RequestMapping(value =  "/addParticipant.htm", method = RequestMethod.GET)
    public ModelAndView addParticipant() {
        Participant participant = new Participant();

        ModelAndView mav = new ModelAndView();
        mav.setViewName("newParticipant");
        mav.addObject("newParticipant", participant);

        return mav;
    }

    @RequestMapping(value =  "/saveParticipant.htm", method = RequestMethod.POST)
    public ModelAndView saveParticipant(@ModelAttribute("newParticipant") Participant p) {

        transactionManager.addParticipant(p);

        ModelAndView mav = new ModelAndView();
        mav.setViewName("hello");

        return mav;
    }

    @RequestMapping(value =  "/addRegistry.htm", method = RequestMethod.GET)
    public ModelAndView addRegistry() {
        Registry reg = new Registry();

        ModelAndView mav = new ModelAndView();
        mav.setViewName("addRegistry");
        mav.addObject("newReg", reg);
        mav.addObject("expeditions", transactionManager.getAllExpeditions());
        mav.addObject("participants", transactionManager.getAllParticipants());

        return mav;
    }

    @RequestMapping(value =  "/saveRegistry.htm", method = RequestMethod.POST)
    public ModelAndView saveRegistry(@ModelAttribute("newReg") Registry reg) {

        transactionManager.addRegistry(reg);

        ModelAndView mav = new ModelAndView();
        mav.setViewName("hello");

        return mav;
    }

    @RequestMapping(value =  "/addHut.htm", method = RequestMethod.GET)
    public ModelAndView addHut() {
        Hut hut = new Hut();

        ModelAndView mav = new ModelAndView();
        mav.setViewName("addHut");
        mav.addObject("newHut", hut);
        mav.addObject("maps", transactionManager.getAllExpeditionMaps());

        return mav;
    }

    @RequestMapping(value =  "/saveHut.htm", method = RequestMethod.POST)
    public ModelAndView saveHut(@ModelAttribute("newHut") Hut hut) {

        transactionManager.addHut(hut);

        ModelAndView mav = new ModelAndView();
        mav.setViewName("hello");

        return mav;
    }

    @RequestMapping(value =  "/addMap.htm", method = RequestMethod.GET)
    public ModelAndView addMap() {
        ExpeditionMap map = new ExpeditionMap();

        ModelAndView mav = new ModelAndView();
        mav.setViewName("addMap");
        mav.addObject("newMap", map);

        return mav;
    }

    @RequestMapping(value =  "/editMap.htm", method = RequestMethod.GET)
    public ModelAndView editMap(@RequestParam ExpeditionMap map) {

        ModelAndView mav = new ModelAndView();
        mav.setViewName("editMap");
        mav.addObject("newMap", map);

        return mav;
    }

    @RequestMapping(value =  "/saveMap.htm", method = RequestMethod.POST)
    public ModelAndView saveMap(@ModelAttribute("newMap") ExpeditionMap map, @RequestParam String flag) {
        System.out.println(map.getId() + " " + flag);
        if (flag.isEmpty())
            transactionManager.addExpeditionMap(map);
        else
            transactionManager.updateExpeditionMap(map);

        ModelAndView mav = new ModelAndView();
        mav.setViewName("hello");

        return mav;
    }

    @RequestMapping(value = "/aggregations.htm")
    public ModelAndView getAggregations() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("reports");
        /*mav.addObject("report1", transactionManager.getSomeHuts());*/

        return mav;
    }
}
