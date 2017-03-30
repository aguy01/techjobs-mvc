package org.launchcode.controllers;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.launchcode.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * Modified by me.
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", ListController.columnChoices);
        return "search";
    }

    // TODO #1 - Create handler to process search request and display results

    private static ArrayList<HashMap<String, String>> jobsBySearch = new ArrayList<HashMap<String, String>>();

    @RequestMapping(value = "results")
    public String results(Model model, @RequestParam String searchType, String searchTerm) {

        model.addAttribute("columns", ListController.columnChoices);//model prepare objects coloumns to point at coloumnChoices, serves as a key.


        if (searchType.equals("all")) {//if all selected.
            jobsBySearch = JobData.findByValue(searchTerm);
            model.addAttribute("size", jobsBySearch.size());
            model.addAttribute("jobs", jobsBySearch);
        } else {//other than 'all' selected.
            jobsBySearch = JobData.findByColumnAndValue(searchType, searchTerm);
            model.addAttribute("size", jobsBySearch.size());
            model.addAttribute("jobs", jobsBySearch);
        }

        return "search"; //presenting model objects to search.html for it to process.

    }

}
