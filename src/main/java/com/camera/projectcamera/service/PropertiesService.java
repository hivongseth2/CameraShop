package com.camera.projectcamera.service;

import com.camera.projectcamera.entity.Properties;

import java.util.List;

public interface PropertiesService {
    void addProperties(Properties properties);

    void updateProperties(Long propertyId, Properties properties);

    void deleteProperties(Long propertyId);

    Properties getProperty(Long propertiesId);

    List<Properties> getProperties();
}
