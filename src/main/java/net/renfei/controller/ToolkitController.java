package net.renfei.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

/**
 * <p>Title: ToolkitController</p>
 * <p>Description: </p>
 *
 * @author RenFei(i @ renfei.net)
 */
@Controller
@RequestMapping("/toolkit")
public class ToolkitController {
    @RequestMapping("{path}")
    public ModelAndView getOnePath(@PathVariable("path") String path) {
        RedirectView red = new RedirectView("/kitbox/" + path, true);
        red.setStatusCode(HttpStatus.MOVED_PERMANENTLY);
        return new ModelAndView(red);
    }

    @RequestMapping("{path1}/{path2}")
    public ModelAndView getOnePath(@PathVariable("path1") String path1, @PathVariable("path2") String path2) {
        RedirectView red = new RedirectView("/kitbox/" + path1 + "/" + path2, true);
        red.setStatusCode(HttpStatus.MOVED_PERMANENTLY);
        return new ModelAndView(red);
    }
}
