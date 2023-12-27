package com.spring.Assignment2.controller;

import com.spring.Assignment2.dao.*;
import com.spring.Assignment2.entity.CV;
import com.spring.Assignment2.entity.Company;
import com.spring.Assignment2.entity.Recruitment;
import com.spring.Assignment2.entity.User;
import com.spring.Assignment2.model.*;
import com.spring.Assignment2.service.*;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = -1)
public class APIController {

    @Autowired
    UserService userService;

    @Autowired
    CompanyServive companyServive;

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    CvService cvService;

    @Autowired
    CVRepository cvRepository;

    @Autowired
    ApplyPostRepository applyPostRepository;

    @Autowired
    ApplyPostService applyPostService;

    @Autowired
    RecruitmentRepository recruitmentRepository;

    @Autowired
    SaveJobService saveJobService;

    @Autowired
    FollowCompanyService followCompanyService;


    /* upload ảnh cho user*/
    @PostMapping("/user/upload/")
    public void upload(@ModelAttribute("userDTO") UserDTO userDTO) throws IOException {
        UserPrincipal currentUser = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        if (!userDTO.getFile().isEmpty()) {
            final String UPLOAD_FOLDER = "D:/images/";

            String filename = userDTO.getFile().getOriginalFilename();
            File newFile = new File(UPLOAD_FOLDER + filename);

            userDTO.getFile().transferTo(newFile);
            currentUser.setImage(filename);
            userDTO.setImage(filename); // save to db
        }
        userService.updateImage(userDTO);
    }

    /* upload ảnh cho công ty */
    @PostMapping("/user/upload-company/")
    public void uploadCompany(@ModelAttribute("companyDTO") CompanyDTO companyDTO) throws IOException {
        UserPrincipal currentUser = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        Company company = companyRepository.getcompanyByUserId(currentUser.getId());
        if (!companyDTO.getFile().isEmpty()) {
            final String UPLOAD_FOLDER = "D:/images/";

            String filename = companyDTO.getFile().getOriginalFilename();
            File newFile = new File(UPLOAD_FOLDER + filename);

            companyDTO.getFile().transferTo(newFile);
            companyDTO.setId(company.getId());
            companyDTO.setLogo(filename); // save to db
        }
        companyServive.updateLogo(companyDTO);
    }

    /* download ảnh */
    @GetMapping("/user/download")
    public void download(@RequestParam("filename") String filename, HttpServletResponse response) throws IOException {
        final String UPLOAD_FOLDER = "D:/images/";

        File file = new File(UPLOAD_FOLDER + filename);
        Files.copy(file.toPath(), response.getOutputStream());
    }

    /* upload CV cho user */
    @PostMapping("/user/uploadCv")
    public void uploadCv(@ModelAttribute("CvDTO") CvDTO cvDTO) throws IOException {
        UserPrincipal currentUser = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        CV cv = cvRepository.getCvByUserId(currentUser.getId());
        if (!cvDTO.getFile().isEmpty()) {
            final String UPLOAD_FOLDER = "D:/downloadcv/";

            String filename = cvDTO.getFile().getOriginalFilename();
            File newFile = new File(UPLOAD_FOLDER + filename);

            cvDTO.getFile().transferTo(newFile);
            cvDTO.setId(cv.getId());
            cvDTO.setFileName(filename); // save to db
        }
        cvService.updateCv(cvDTO);
    }

    /* upload CV cho user khi user đã cập nhập CV */
    @PostMapping("/user/apply-job1/")
    public String applyJob1(@ModelAttribute("applyDTO") ApplyDTO applyDTO) throws IOException {
        try {
            UserPrincipal currentUser = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication()
                    .getPrincipal();
            List<ApplyPostDTO> applyPostDTOs = applyPostService.getApplyPostByUserId(currentUser.getId());
            for (ApplyPostDTO applyPostDTO1 : applyPostDTOs) {
                if (applyPostDTO1.getRecruitment().getId() == applyDTO.getIdRe()){
                    return "exist";
                }
            }

            ApplyPostDTO applyPostDTO = new ApplyPostDTO();

            Recruitment recruitment = recruitmentRepository.findById(applyDTO.getIdRe()).orElse(null);
            applyPostDTO.setRecruitment(recruitment);
            applyPostDTO.setNameCv(currentUser.getCv().getFileName());
            applyPostDTO.setText(applyDTO.getText());

            User user = new User();
            user.setId(currentUser.getId());
            applyPostDTO.setUser(user);
            applyPostService.addApplyPost(applyPostDTO);

            return "true";
        } catch (Exception e) {
            return "false";
        }
    }

    /* upload CV cho user khi user nộp CV mới */
    @PostMapping("/user/apply-job/")
    public String applyJob(@ModelAttribute("applyDTO") ApplyDTO applyDTO) throws IOException {
        try {
            UserPrincipal currentUser = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication()
                    .getPrincipal();
            List<ApplyPostDTO> applyPostDTOs = applyPostService.getApplyPostByUserId(currentUser.getId());
            for (ApplyPostDTO applyPostDTO1 : applyPostDTOs) {
                if (applyPostDTO1.getRecruitment().getId() == applyDTO.getIdRe()){
                    return "exist";
                }
            }
            ApplyPostDTO applyPostDTO = new ApplyPostDTO();

            Recruitment recruitment = recruitmentRepository.findById(applyDTO.getIdRe()).orElse(null);
            applyPostDTO.setRecruitment(recruitment);
            applyPostDTO.setText(applyDTO.getText());

            User user = new User();
            user.setId(currentUser.getId());
            applyPostDTO.setUser(user);

            if (!applyDTO.getFile().isEmpty()) {
                final String UPLOAD_FOLDER = "D:/downloadcv/";

                String filename = applyDTO.getFile().getOriginalFilename();
                File newFile = new File(UPLOAD_FOLDER + filename);

                applyDTO.getFile().transferTo(newFile);
                applyPostDTO.setNameCv(filename); // save to db
            }
            applyPostService.addApplyPost(applyPostDTO);

            return "true";
        } catch (Exception e) {
            return "false";
        }
    }

    /* Lưu bài đăng */
    @PostMapping("/save-job/save/")
    public String saveJob(@ModelAttribute("saveDTO") SaveDTO saveDTO) throws IOException{
        try {
            UserPrincipal currentUser = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication()
                    .getPrincipal();
            List<SaveJobDTO> saveJobDTOs = saveJobService.getSaveJobByUserId(currentUser.getId());
            for (SaveJobDTO saveJobDTO1 : saveJobDTOs) {
                if (saveJobDTO1.getRecruitment().getId() == saveDTO.getIdRe()){
                    return "exist";
                }
            }

            SaveJobDTO saveJobDTO = new SaveJobDTO();

            Recruitment recruitment = recruitmentRepository.findById(saveDTO.getIdRe()).orElse(null);
            saveJobDTO.setRecruitment(recruitment);

            User user = new User();
            user.setId(currentUser.getId());
            saveJobDTO.setUser(user);
            saveJobService.addSaveJob(saveJobDTO);

            return "true";
        } catch (Exception e) {
            return "false";
        }
    }

    /* Theo dõi công ty*/
    @PostMapping("/user/follow-company/")
    public String followCompany(@ModelAttribute("followDTO") FollowDTO followDTO) throws IOException{
        try {
            UserPrincipal currentUser = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication()
                    .getPrincipal();
            List<FollowCompanyDTO> followCompanyDTOs = followCompanyService.getFollowCompanyByUserId(currentUser.getId());
            for (FollowCompanyDTO followCompanyDTO1 : followCompanyDTOs) {
                if (followCompanyDTO1.getCompany().getId() == followDTO.getIdCompany()){
                    return "exist";
                }
            }

            FollowCompanyDTO followCompanyDTO = new FollowCompanyDTO();

            Company company = companyRepository.findById(followDTO.getIdCompany()).orElse(null);
            followCompanyDTO.setCompany(company);

            User user = new User();
            user.setId(currentUser.getId());
            followCompanyDTO.setUser(user);
            followCompanyService.addfollowCompany(followCompanyDTO);

            return "true";
        } catch (Exception e) {
            return "false";
        }
    }
}
