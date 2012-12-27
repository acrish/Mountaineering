package expeditions.web;


import com.sun.org.apache.xpath.internal.operations.Mod;
import expeditions.web.model.Expedition;
import expeditions.web.service.TransactionManager;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Arrays;
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

}
