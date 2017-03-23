package org.launchcode.controllers;

import org.launchcode.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;//for dictionay style values to use.
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Modified by  me.
 */
@Controller
@RequestMapping(value = "list")//url is expected to have /list
public class ListController {//a Controller; also handler

    static HashMap<String, String> columnChoices = new HashMap<>();

    public ListController () {
        columnChoices.put("core competency", "Skill");
        columnChoices.put("employer", "Employer");
        columnChoices.put("location", "Location");
        columnChoices.put("position type", "Position Type");
        columnChoices.put("all", "All");
    }

    @RequestMapping(value = "")
    public String list(Model model) {//a handler method.

        model.addAttribute("columns", columnChoices);//model object points columns to the columnChoices

        return "list"; //to list.html
    }

    @RequestMapping(value = "values")//directing values as part of the root path->/list
    // with ? in the url;which is an answer to the query.
    public String listColumnValues(Model model, @RequestParam String column) {//<-- a handler in the Controller.

        // When the user clicks on the links  employer, location, etc.,
        // they will be routed to the listColumnValues handler in the ListController controller, which looks
        // for this parameters (column=${column.key}. Also @RequestParam  allows to pass in the column such as employer
        //from the url-> /list/values?column=employer. Then 'employer' will be picked up by the column in @RequestParam String column)
        //and pass to the remainder of the method.
        if (column.equals("all")) {
            ArrayList<HashMap<String, String>> jobs = JobData.findAll();
            model.addAttribute("title", "All Jobs"); // model is a map, an object. key/value pair or dictionary. enables springboot to communicates with Thymeleaf.
            //works similar to model as a java.util.Map.
            model.addAttribute("jobs", jobs);
            return "list-jobs"; //presenting model objects to list-jobs.html. for it to process the info there.
        } else {
            ArrayList<String> items = JobData.findAll(column);
            model.addAttribute("title", "All " + columnChoices.get(column) + " Values");//key-> title, value-> added
            //dynamically to the model object  map.
            //All " + columnChoices.get(column) + " Values". <--this string is the value for the key -> title. added to the model object as String.
            model.addAttribute("column", column);// map. key/value pair or dictionary. enables springboot to communicates with Thymeleaf.
            model.addAttribute("items", items);//the key items in left points to the value in rite.
            return "list-column"; //presenting model info to list-column.html. for list-column.html to process the info there.
            //presenting objects to list-column.html. for it to process the info there.
        }

    }

    @RequestMapping(value = "jobs")
    public String listJobsByColumnAndValue(Model model,
            @RequestParam String column, @RequestParam String value) {

        ArrayList<HashMap<String, String>> jobs = JobData.findByColumnAndValue(column, value);
        model.addAttribute("title", "Jobs with " + columnChoices.get(column) + ": " + value); // heading to be used in the body of hte page.
        model.addAttribute("jobs", jobs);

        return "list-jobs"; //presenting model objects to list-jobs.html. for it to process info there.
    }
}
