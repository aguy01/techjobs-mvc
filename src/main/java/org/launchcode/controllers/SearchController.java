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



    @RequestMapping(value = "results")
    public String results(Model model, @RequestParam String searchType, @RequestParam String searchTerm) {


            model.addAttribute("columns", ListController.columnChoices);//model prepare objects coloumns to point at coloumnChoices, serves as a key.


            ArrayList<HashMap<String, String>> jobsByVal = JobData.findByValue(searchTerm);// searchTerm is evaluated and assigned.

            model.addAttribute("size",jobsByVal.size());
            model.addAttribute("jobs", jobsByVal);


        return "search"; //presenting model objects to search.html for it to process.

   }







}
