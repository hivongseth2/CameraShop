package com.camera.projectcamera.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class MessageError {
    private Integer errorCode;
    private String message;
}
