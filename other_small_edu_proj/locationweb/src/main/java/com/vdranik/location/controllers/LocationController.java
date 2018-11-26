package com.vdranik.location.controllers;

import com.vdranik.location.entities.Location;
import com.vdranik.location.repos.LocationRepository;
import com.vdranik.location.service.LocationService;
import com.vdranik.location.util.EmailUtil;
import com.vdranik.location.util.ReportUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import javax.servlet.ServletContext;

@Controller
public class LocationController {

  @Autowired
  LocationService locationService;
  @Autowired
  EmailUtil emailUtil;
  @Autowired
  LocationRepository repository;
  @Autowired
  ReportUtil reportUtil;
  @Autowired
  ServletContext sc;

  @RequestMapping("/showCreate")
  public String showCreate(){
    return "createLocation";
  }

  @RequestMapping("/saveLoc")
  public String saveLocation(@ModelAttribute("location") Location location, ModelMap modelMap){
    Location locationSaved = locationService.saveLocation(location);
    String msg = "Location saved with id: " + locationSaved.getId();
    modelMap.addAttribute("msg", msg);
//    emailUtil.sendEmail("misterfaust@gmail.com", "Location Saved",
//        "Location save saccessfully and about to return a response");
    return "createLocation";
  }

  @RequestMapping("/displayLocations")
  public String displayLocations(ModelMap modelMap){
    List<Location> locations = locationService.getAllLocations();
    modelMap.addAttribute("locations", locations);
    return "displayLocations";
  }

  @RequestMapping("/deleteLocation")
  public String displayLocations(@RequestParam("id") int id, ModelMap modelMap){
    //locationService.getLocationById(id).ifPresent(it -> locationService.deleteLocation(it));

    Location location = new Location();
    location.setId(id);
    locationService.deleteLocation(location);

    List<Location> locations = locationService.getAllLocations();
    modelMap.addAttribute("locations", locations);
    return "displayLocations";
  }

  @RequestMapping("/showUpdate")
  public String showUpdate(@RequestParam("id") int id, ModelMap modelMap){
    Location location = locationService.getLocationById(id).get();
    modelMap.addAttribute("location", location);
    return "updateLocation";
  }

  @RequestMapping("/updateLoc")
  public String updateLocation(@ModelAttribute("location") Location location, ModelMap modelMap){
    locationService.updateLocation(location);
    List<Location> locations = locationService.getAllLocations();
    modelMap.addAttribute("locations", locations);
    return "displayLocations";
  }

  @RequestMapping("/generateReport")
  public String generateReport(){
    String path = sc.getRealPath("/");
    List<Object[]> data = repository.findTypeAndTypeCount();
    reportUtil.generatePieChart(path, data);
    return "report";
  }

}

