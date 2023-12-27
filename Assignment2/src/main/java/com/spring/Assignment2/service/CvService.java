package com.spring.Assignment2.service;

import com.spring.Assignment2.model.CvDTO;
import org.springframework.stereotype.Service;

public interface CvService {
    void updateCv(CvDTO cvDTO);

    CvDTO getCvByUserId(int id);

    void deleteCvById(Integer id);
}
