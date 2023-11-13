package com.camera.projectcamera.controllers;

import com.camera.projectcamera.entity.Properties;
import com.camera.projectcamera.model.MessageError;
import com.camera.projectcamera.service.PropertiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/properties")
public class PropertiesController {

    @Autowired
    private PropertiesService propertiesService;

    @PostMapping("/add")
    public ResponseEntity<?> addProperties(@RequestBody Properties properties){
        propertiesService.addProperties(properties);
        if(properties==null){
            ResponseEntity.badRequest().body(new MessageError(400, "create error"));
        }

        return ResponseEntity.ok(properties);
    }
    @PutMapping("update/{propertyId}")
    public ResponseEntity<Void> updateProperties(@PathVariable Long propertyId, @RequestBody Properties properties){
        propertiesService.updateProperties(propertyId, properties);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("delete/{propertyId}")
    public ResponseEntity<Void> deleteProperties(@PathVariable Long propertyId){
        propertiesService.deleteProperties(propertyId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/get")
    public Properties getProperty(@RequestParam Long propertiesId){
        return propertiesService.getProperty(propertiesId);
    }

    @GetMapping
    public List<Properties> getProperties(){
        return propertiesService.getProperties();
    }


}
