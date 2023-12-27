package com.spring.Assignment2.service.impl;

import com.spring.Assignment2.dao.CVRepository;
import com.spring.Assignment2.dao.UserRepository;
import com.spring.Assignment2.entity.CV;
import com.spring.Assignment2.entity.User;
import com.spring.Assignment2.model.CvDTO;
import com.spring.Assignment2.service.CvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CvServiceImpl implements CvService {
    @Autowired
    CVRepository cvRepository;

    @Autowired
    UserRepository userRepository;

    /* Câ nhập Cv */
    @Override
    public void updateCv(CvDTO cvDTO) {
        CV cv = cvRepository.findById(cvDTO.getId()).orElse(null);
        if (cv != null){
            cv.setFileName(cvDTO.getFileName());
            cvRepository.save(cv);
        }
    }
    /* lấy ra CV theo user id */
    @Override
    public CvDTO getCvByUserId(int id) {
        CV cv = cvRepository.getCvByUserId(id);
        User user = userRepository.findById(id).orElse(null);
        if (cv == null){
            if (user.getRole().getId() == 1){
                cv = new CV();
                cv.setUser(user);
                cvRepository.save(cv);
            } else {
                return null;
            }
        }
        cv = cvRepository.getCvByUserId(user.getId());
        user.setCv(cv);
        CvDTO cvDTO = convertCv(cv);
        return cvDTO;
    }

    /* Xóa Cv */
    @Override
    public void deleteCvById(Integer id) {
        CV cv = cvRepository.findById(id).orElse(null);
        if (cv != null){
            cv.setFileName(null);
            cvRepository.save(cv);
        }
    }

    public CvDTO convertCv(CV cv) {
        CvDTO cvDTO = new CvDTO();
        cvDTO.setId(cv.getId());
        cvDTO.setFileName(cv.getFileName());
        cvDTO.setUser(cv.getUser());
        return cvDTO;
    }
}
