package ngo.friendship.projecus.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Zakaria Imtiaz
 */
@RestController
public class BaseController {    

    @RequestMapping(value = {"/"}, method = {RequestMethod.GET})
    public ModelAndView index() {
        return new ModelAndView("home");
    }    
}
