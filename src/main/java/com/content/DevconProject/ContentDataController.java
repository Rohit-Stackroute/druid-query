package com.content.DevconProject;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.Map;

@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1")
public class ContentDataController {
    @Autowired
    IContentDataService iservice = new IContentDataService();

    @GetMapping("/content")
    public Map getUserDetails(@RequestParam(value = "objectid", required = false, defaultValue = "0") String contentId) {
       Map receivedContent = null;
        try {
            receivedContent = iservice.getContentDetails(contentId);
           return receivedContent;
       }
       catch(Exception exception){
           log.error("Exception caught in controller",exception.getMessage());
       }
        return receivedContent;
    }
}
