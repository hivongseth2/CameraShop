package com.camera.projectcamera.service.impl;


import com.camera.projectcamera.entity.Properties;
import com.camera.projectcamera.repository.PropertiesRepository;
import com.camera.projectcamera.service.PropertiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class PropertiesServiceImpl implements PropertiesService {

    @Autowired
    private PropertiesRepository propertiesRepository;
    @Override
    public void addProperties(Properties properties) {
        propertiesRepository.save(properties);
    }

    @Override
    public void updateProperties(Long propertyId, Properties properties) {
        propertiesRepository
                .findById(propertyId)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Không thể thực hiện thao tác cập nhật với mặt hàng có id" + propertyId));
        properties.setPropertyId(propertyId);
        propertiesRepository.save(properties);
    }

    @Override
    public void deleteProperties(Long propertyId) {
        Properties properties = propertiesRepository
                .findById(propertyId)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Không thể thực hiện thao tác cập nhật với mặt hàng có id" + propertyId));
        propertiesRepository.delete(properties);
    }

    @Override
    public Properties getProperty(Long propertiesId) {
        Properties properties = propertiesRepository
                .findById(propertiesId)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Không thể thực hiện thao tác cập nhật với mặt hàng có id" + propertiesId));
            return  properties;
    }

    @Override
    public List<Properties> getProperties() {
        return propertiesRepository.findAll();
    }




}
