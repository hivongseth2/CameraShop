package com.camera.projectcamera.api;

import com.camera.projectcamera.dto.newDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NewAPI {

    @PostMapping("/new")
    public newDTO createNew(@RequestBody newDTO model) {

        return model;

    }
}
