package com.camera.projectcamera.model.request;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class CustomerRequest {
    private Long personId;
    String firstName;
    String lastName;
    String phone;
    String email;
    String street;
    String city;
    String status;
    String userName;
    String passWord;
    Long roleId;
}
