package com.spring.Assignment2.service;

import com.spring.Assignment2.entity.SaveJob;
import com.spring.Assignment2.model.SaveJobDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface SaveJobService {
    List<SaveJobDTO> getSaveJobByUserId(int id);

    void addSaveJob(SaveJobDTO saveJobDTO);

    Page<SaveJob> getSaveJobPages(int id, Integer page);
}
