package ra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ra.dto.ResumeDTO;
import ra.model.Resume;
import ra.service.ResumeService;

import javax.validation.Valid;
import java.util.List;

@Controller
public class ResumeController {

    @Autowired
    private ResumeService resumeService;

    // GET: Danh sách resume
    @GetMapping("resumes")
    public String listResumes(Model model) {
        List<Resume> resumes = resumeService.findAll();
        model.addAttribute("resumes", resumes);
        return "resume_list";
    }

    // GET: Form thêm mới resume
    @GetMapping("add")
    public String showAddForm(Model model) {
        model.addAttribute("resumeDTO", new ResumeDTO());
        return "resume_add";
    }

    // POST: Lưu resume mới
    @PostMapping("save")
    public String saveResume(@Valid @ModelAttribute("resumeDTO") ResumeDTO resumeDTO,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "resume_add";
        }
        Resume resume = mapToEntity(resumeDTO);
        resumeService.save(resume);
        return "redirect:/resumes";
    }

    // GET: Form chỉnh sửa resume theo id
    @GetMapping("edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Resume resume = resumeService.findById(id);
        if (resume == null) {
            return "redirect:/resumes";
        }
        ResumeDTO resumeDTO = mapToDTO(resume);
        model.addAttribute("resumeDTO", resumeDTO);
        return "resume_edit";
    }

    // POST: Cập nhật resume từ form chỉnh sửa
    @PostMapping("update")
    public String updateResume(@Valid @ModelAttribute("resumeDTO") ResumeDTO resumeDTO,
                               BindingResult result,
                               Model model) {
        if (result.hasErrors()) {
            return "resume_edit";
        }
        Resume resume = mapToEntity(resumeDTO);
        resumeService.update(resume);
        return "redirect:/resumes";
    }

    // GET: Xóa resume
    @GetMapping("delete/{id}")
    public String deleteResume(@PathVariable Long id) {
        resumeService.delete(id);
        return "redirect:/resumes";
    }

    // Helper: DTO → Entity
    private Resume mapToEntity(ResumeDTO dto) {
        Resume resume = new Resume();
        resume.setId(dto.getId());
        resume.setFullName(dto.getFullName());
        resume.setEmail(dto.getEmail());
        resume.setPhoneNumber(dto.getPhoneNumber());
        resume.setEducation(dto.getEducation());
        resume.setExperience(dto.getExperience());
        resume.setSkills(dto.getSkills());
        return resume;
    }

    // Helper: Entity → DTO
    private ResumeDTO mapToDTO(Resume resume) {
        ResumeDTO dto = new ResumeDTO();
        dto.setId(resume.getId());
        dto.setFullName(resume.getFullName());
        dto.setEmail(resume.getEmail());
        dto.setPhoneNumber(resume.getPhoneNumber());
        dto.setEducation(resume.getEducation());
        dto.setExperience(resume.getExperience());
        dto.setSkills(resume.getSkills());
        return dto;
    }
}
