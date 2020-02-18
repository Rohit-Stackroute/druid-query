package com.content.DevconProject;

import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Map;

@Slf4j
@Service
public class ContentDataService extends IContentDataService {
//localhost:8080/api/v1/content?content_id=do_31292081216965017612465
    public Map getContentDetails(String contentId) throws IOException, ParseException{
        try {
            System.out.println("content"+contentId);
            File file = ResourceUtils.getFile("classpath:contentdata.json");
            String content = new String(Files.readAllBytes(file.toPath()));
            JSONParser parser = new JSONParser();
            JSONArray array = (JSONArray) parser.parse(content);
            log.info("array is " + array);
            for (int i = 0; i < array.size(); i++) {
            Map  contentDetails =  (Map) array.get(i);
                if (contentDetails.get("objectid").equals(contentId)) {
                    return contentDetails;
                }
                else{
                    log.info("Content not found");
                }
            }
        }
    catch(FileNotFoundException e){
                log.error("The file is not found",e.getMessage());
            }
        catch(Exception e){
            e.printStackTrace();
                log.error("The exception was thrown at service layer",e.getMessage());
            }
        return null;
    }
}
