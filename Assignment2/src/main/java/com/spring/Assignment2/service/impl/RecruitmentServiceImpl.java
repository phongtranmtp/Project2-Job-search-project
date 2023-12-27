package com.spring.Assignment2.service.impl;

import com.spring.Assignment2.dao.CategoryRepository;
import com.spring.Assignment2.dao.CompanyRepository;
import com.spring.Assignment2.dao.RecruitmentRepository;
import com.spring.Assignment2.entity.Category;
import com.spring.Assignment2.entity.Company;
import com.spring.Assignment2.entity.Recruitment;
import com.spring.Assignment2.model.RecruitmentDTO;
import com.spring.Assignment2.model.UserPrincipal;
import com.spring.Assignment2.service.RecruitmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
@Transactional
public class RecruitmentServiceImpl implements RecruitmentService {

    @Autowired
    RecruitmentRepository recruitmentRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    private CompanyRepository companyRepository;

    /* lấy ra danh sách bài đăng*/
    @Override
    public List<RecruitmentDTO> getRecuitments() {
        List<Recruitment> recruitments = recruitmentRepository.findAll();
        List<RecruitmentDTO> recruitmentDTOs = new ArrayList<>();
        for (Recruitment recruitment : recruitments) {
            recruitmentDTOs.add(convertRecruiment(recruitment));
        }
        return recruitmentDTOs;
    }

    /* lấy ra danh sách bài đăng và phân trang*/
    @Override
    public Page<Recruitment> getRecuitmentPages(Integer page) {
        Pageable pageable = PageRequest.of(page-1,5);
        Page<Recruitment> recruitments = recruitmentRepository.findAll(pageable);

        return recruitments;
    }

    /* thêm mới bài đăng*/
    @Override
    public void postJob(RecruitmentDTO recruitmentDTO) {
        Recruitment recruitment = new Recruitment();

        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = myDateObj.format(myFormatObj);

        recruitment.setId(recruitmentDTO.getId());
        recruitment.setAddress(recruitmentDTO.getAddress());
        recruitment.setCreatedAt(formattedDate);
        recruitment.setDescription(recruitmentDTO.getDescription());
        recruitment.setExperience(recruitmentDTO.getExperience());
        recruitment.setQuantity(recruitmentDTO.getQuantity());
        recruitment.setRankRe(recruitmentDTO.getRankRe());
        recruitment.setSalary(recruitmentDTO.getSalary());
        recruitment.setStatus(recruitmentDTO.getStatus());
        recruitment.setTitle(recruitmentDTO.getTitle());
        recruitment.setType(recruitmentDTO.getType());
        recruitment.setView(recruitmentDTO.getView());
        recruitment.setDeadline(recruitmentDTO.getDeadline());
        recruitment.setCategory(recruitmentDTO.getCategory());
        Category category = categoryRepository.findById(recruitmentDTO.getCategory().getId()).orElse(null);
        category.setNumberChoose(category.getNumberChoose() + 1);

        UserPrincipal currentUser = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        recruitment.setCompany(currentUser.getCompany());
        recruitment.setApplyPosts(recruitmentDTO.getApplyPosts());
        recruitmentRepository.save(recruitment);
        categoryRepository.save(category);

    }

    /* Chi tiết bài đăng*/
    @Override
    public RecruitmentDTO detail(int id) {
        Recruitment recruitment = recruitmentRepository.findById(id).orElse(null);
        RecruitmentDTO recruitmentDTO = convertRecruiment(recruitment);
        return recruitmentDTO;
    }

    /* Xóa bài đăng*/
    @Override
    public void deletePostJob(int id) {
        recruitmentRepository.deleteById(id);
    }

    @Override
    public void update(RecruitmentDTO recruitmentDTO) {
        Recruitment recruitment = recruitmentRepository.findById(recruitmentDTO.getId()).orElse(null);
        if (recruitment != null){
            recruitment.setId(recruitmentDTO.getId());
            recruitment.setAddress(recruitmentDTO.getAddress());
            recruitment.setCreatedAt(recruitmentDTO.getCreatedAt());
            recruitment.setDescription(recruitmentDTO.getDescription());
            recruitment.setExperience(recruitmentDTO.getExperience());
            recruitment.setQuantity(recruitmentDTO.getQuantity());
            recruitment.setRankRe(recruitmentDTO.getRankRe());
            recruitment.setSalary(recruitmentDTO.getSalary());
            recruitment.setStatus(recruitmentDTO.getStatus());
            recruitment.setTitle(recruitmentDTO.getTitle());
            recruitment.setType(recruitmentDTO.getType());
            recruitment.setView(recruitmentDTO.getView());
            recruitment.setDeadline(recruitmentDTO.getDeadline());

            Category category = categoryRepository.findById(recruitmentDTO.getCategory().getId()).orElse(null);
            Category category1 = recruitment.getCategory();
            if (category.getId() != category1.getId()){
                category.setNumberChoose(category.getNumberChoose() +1);
                category1.setNumberChoose(recruitment.getCategory().getNumberChoose() - 1);
            }
            recruitment.setCategory(recruitmentDTO.getCategory());
//            recruitment.setCompany(recruitmentDTO.getCompany());
            recruitment.setApplyPosts(recruitmentDTO.getApplyPosts());
            recruitmentRepository.save(recruitment);
            categoryRepository.save(category);
            categoryRepository.save(category1);

        }
    }

    @Override
    public List<RecruitmentDTO> getRecruimentByApplyPost() {
        List<Recruitment> recruitments = recruitmentRepository.getRecruimentByApplyPost();
        List<RecruitmentDTO> recruitmentDTOs = new ArrayList<>();
        for (Recruitment recruitment : recruitments) {
            recruitmentDTOs.add(convertRecruiment(recruitment));
        }
        return recruitmentDTOs;
    }

    /* Tìm kiếm theo tên công việc */
    @Override
    public Page<Recruitment> findWorks(String keySearch,Integer page) {
        Pageable pageable = PageRequest.of(page-1,5);
        Page<Recruitment> recruitments = recruitmentRepository.findWorks(keySearch,pageable);

        return recruitments;
    }

    /* Tìm kiếm theo tên công ty */
    @Override
    public Page<Recruitment> findByCompanyNameCompany(String keySearch, Integer page) {
        Pageable pageable = PageRequest.of(page-1,5);
        Page<Recruitment> recruitments = recruitmentRepository.findByCompanyNameCompany(keySearch,pageable);

        return recruitments;
    }

    /* Tìm kiếm theo địa chỉ công ty */
    @Override
    public Page<Recruitment> findByAddressWorks(String keySearch, Integer page) {
        Pageable pageable = PageRequest.of(page-1,5);
        Page<Recruitment> recruitments = recruitmentRepository.findByCompanyAddress(keySearch,pageable);

        return recruitments;
    }

    @Override
    public Page<Recruitment> getApplyRecruitmentPageByUserId(int id, Integer page) {
        Pageable pageable = PageRequest.of(page-1,5);
        Page<Recruitment> recruitments = recruitmentRepository.getApplyRecruitmentByUserId(id,pageable);

        return recruitments;
    }

    @Override
    public Page<Recruitment> getListRecruitmentByCompany(int id, Integer page) {
        Pageable pageable = PageRequest.of(page-1,5);
        Page<Recruitment> recruitments = recruitmentRepository.getListRecruitmentByCompany(id,pageable);

        return recruitments;
    }

    public RecruitmentDTO convertRecruiment(Recruitment recruitment){
        RecruitmentDTO recruitmentDTO = new RecruitmentDTO();

        recruitmentDTO.setId(recruitment.getId());
        recruitmentDTO.setAddress(recruitment.getAddress());
        recruitmentDTO.setCreatedAt(recruitment.getCreatedAt());
        recruitmentDTO.setDescription(recruitment.getDescription());
        recruitmentDTO.setExperience(recruitment.getExperience());
        recruitmentDTO.setQuantity(recruitment.getQuantity());
        recruitmentDTO.setRankRe(recruitment.getRankRe());
        recruitmentDTO.setSalary(recruitment.getSalary());
        recruitmentDTO.setStatus(recruitment.getStatus());
        recruitmentDTO.setTitle(recruitment.getTitle());
        recruitmentDTO.setType(recruitment.getType());
        recruitmentDTO.setView(recruitment.getView());
        recruitmentDTO.setDeadline(recruitment.getDeadline());
        recruitmentDTO.setCategory(recruitment.getCategory());
        recruitmentDTO.setCompany(recruitment.getCompany());
        recruitmentDTO.setApplyPosts(recruitment.getApplyPosts());
        return recruitmentDTO;
    }

}
