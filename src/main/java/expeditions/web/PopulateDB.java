package expeditions.web;

import expeditions.web.model.*;
import expeditions.web.service.TransactionManager;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by IntelliJ IDEA.
 * User: Hac
 * Date: 1/5/13
 * Time: 6:13 PM
 * To change this template use File | Settings | File Templates.
 */
public class PopulateDB {

    @Autowired
    TransactionManager manager;

    private boolean flag = false;

    private final ArrayList<String> mapInfo = new ArrayList<String>(Arrays.asList(
            "Grossglockner", "1880-10-09",
                "http://blog.wuokko.org/wp-content/uploads/2011/07/nordwest_routes_map_with_south_face_route.jpg",
            "Mont Blanc", "1791-08-08",
                "http://www.outdoorgear4u.co.uk/blog/wp-content/uploads/2011/02/chamonic-mont-blanc-map2.jpg",
            "Fagaras", "1770-01-16", "http://www.munte.utcluj.ro/Images/harti/creasta_fagaras.jpg",
            "Piatra Craiului", "1918-12-01", "src/piatraCraiului.jpg"
    ));

    private final String[] expeditionInfo = {"tura vacanta iarna an IV", "Austria, fata sudica, traseu Studlgrad",
                "Kals am Grossglockner", "Hielingblut", "1",
            "canionul Cioranga Mare", "Romania, dificultate 3A", "Zarnesti", "Zarnesti", "4"};

    private final String[] participantsInfo = {"Dimitrie Manole", "1986-12-28",
            "Andrei Holban", "1990-03-06",
            "Andreea Hodea", "1990-07-26",
            "Bogdan Sosonel", "1988-02-28"};

    private final String[] registriesInfo = {"1", "1", "2012-09-03", "2012-09-04",
        "1", "2", "2012-09-03", "2012-09-04",
        "1", "3", "2012-09-03", "2012-09-04",
        "1", "4", "2012-09-03", "2012-09-04",
        "2", "1", "2012-12-28", "2013-01-03",
        "2", "2", "2012-12-28", "2013-01-03",
        "2", "3", "2012-12-28", "2013-01-03"};

    /* String name, String phone, String coordinates, Date buildDate,
                          Integer mapId, String picUrl, Integer capacity, Double price */
    private final String[] hutsInfo = {"Studlhute", "", "2801m", "1798-08-19", "1", "", "201", "60",
        "Gouter", "", "3800m", "1850-09-01", "2", "", "50", "128",
        "Curmatura", "+40745454184", "1450m", "1958-07-12", "4", "http://img.carpati.org/cazare/cabane/curmatura3.jpg",
            "48", "30"};

    private ExpeditionMap createNewMap(String mountain, String date, String picPath) {
        ExpeditionMap map = null;
        try {
            Date issueDate = new Date(new SimpleDateFormat("yyyy-dd-MM").parse(date).getTime());
            map = new ExpeditionMap(mountain, issueDate, picPath);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        manager.addExpeditionMap(map);

        return map;
    }

    private Expedition createExpedition(String name, String description,
                                        String startVillage, String endVillage,
                                        String expMapId) {
        ExpeditionMap expMap = manager.getExpeditionMap(Integer.parseInt(expMapId));
        Expedition exp = new Expedition(name, description, startVillage, endVillage, expMap);

        manager.addExpedition(exp);

        return exp;
    }

    private Participant createParticipant(String name, String birthDate) {
        Participant participant = null;
        try {
            participant = new Participant(name,
                    new Date(new SimpleDateFormat("yyyy-MM-dd").parse(birthDate).getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        manager.addParticipant(participant);

        return participant;
    }

    private Registry createRegistry(String expId, String participantId,
                                    String startDate, String endDate)
    {
        Expedition exp = manager.getExpedition(Integer.parseInt(expId));
        Participant p = manager.getParticipant(Integer.parseInt(participantId));
        Registry reg = null;
        try {
            reg = new Registry(exp, p,
                    new Date(new SimpleDateFormat("yyyy-MM-dd").parse(startDate).getTime()),
                    new Date(new SimpleDateFormat("yyyy-MM-dd").parse(endDate).getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        manager.addRegistry(reg);

        return reg;
    }

    private Hut createHut(String name, String phone, String coordinates, String buildDate,
                          String mapId, String picUrl, String capacity, String price)
    {
        ExpeditionMap map = manager.getExpeditionMap(Integer.parseInt(mapId));
        Hut hut = null;
        try {
            hut = new Hut(name, phone, coordinates,
                    new Date(new SimpleDateFormat("yyyy-MM-dd").parse(buildDate).getTime()), map,
                    picUrl, Integer.parseInt(capacity), Double.parseDouble(price));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        manager.addHut(hut);

        return hut;
    }

    public PopulateDB() {

    }

    public void populate() {
        if (!flag) {
            for (int i = 0, n = mapInfo.size(); i < n; i += 3)
                createNewMap(mapInfo.get(i), mapInfo.get(i + 1), mapInfo.get(i + 2));

            for (int i = 0,n = expeditionInfo.length; i < n; i += 5) {
                createExpedition(expeditionInfo[i], expeditionInfo[i + 1],
                        expeditionInfo[i + 2], expeditionInfo[i + 3], expeditionInfo[i + 4]);
            }

            for (int i = 0, n = participantsInfo.length; i < n; i += 2) {
                createParticipant(participantsInfo[i], participantsInfo[i + 1]);
            }

            for (int i = 0, n = registriesInfo.length; i < n; i += 4) {
                createRegistry(registriesInfo[i], registriesInfo[i + 1],
                        registriesInfo[i + 2], registriesInfo[i + 3]);
            }

            for (int i = 0, n = hutsInfo.length; i < n; i += 8) {
                createHut(hutsInfo[i], hutsInfo[i + 1], hutsInfo[i + 2], hutsInfo[i + 3],
                        hutsInfo[i + 4], hutsInfo[i + 5], hutsInfo[i + 6], hutsInfo[i + 7]);
            }

            flag = true;
        }
    }
}
