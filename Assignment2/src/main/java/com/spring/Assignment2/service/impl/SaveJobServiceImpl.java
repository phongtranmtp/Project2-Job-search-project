package com.spring.Assignment2.service.impl;

import com.spring.Assignment2.dao.SaveJobRepository;
import com.spring.Assignment2.entity.Recruitment;
import com.spring.Assignment2.entity.SaveJob;
import com.spring.Assignment2.model.SaveJobDTO;
import com.spring.Assignment2.service.SaveJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class SaveJobServiceImpl implements SaveJobService {
    @Autowired
    SaveJobRepository saveJobRepository;

    @Override
    public List<SaveJobDTO> getSaveJobByUserId(int id) {
        List<SaveJob> saveJobs = saveJobRepository.getSaveJobByUserId(id);
        List<SaveJobDTO> saveJobDTOs = new ArrayList<>();
        for (SaveJob saveJob : saveJobs) {
            saveJobDTOs.add(convertSaveJob(saveJob));
        }
        return saveJobDTOs;
    }

    @Override
    public void addSaveJob(SaveJobDTO saveJobDTO) {
        SaveJob saveJob = convertSaveJob1(saveJobDTO);
        saveJobRepository.save(saveJob);
    }

    /* hiển thị danh sách công việc đã lưu và phân trang*/
    @Override
    public Page<SaveJob> getSaveJobPages(int id,Integer page) {
        Pageable pageable = PageRequest.of(page-1,5);
        Page<SaveJob> saveJobs = saveJobRepository.getSaveJobByUserIdPages(id,pageable);

        return saveJobs;
    }

    public SaveJobDTO convertSaveJob(SaveJob saveJob){
        SaveJobDTO saveJobDTO = new SaveJobDTO();
        saveJobDTO.setId(saveJob.getId());
        saveJobDTO.setUser(saveJob.getUser());
        saveJobDTO.setRecruitment(saveJob.getRecruitment());
        return saveJobDTO;
    }

    public SaveJob convertSaveJob1(SaveJobDTO saveJobDTO){
        SaveJob saveJob = new SaveJob();
        saveJob.setUser(saveJobDTO.getUser());
        saveJob.setRecruitment(saveJobDTO.getRecruitment());
        return saveJob;
    }
}
