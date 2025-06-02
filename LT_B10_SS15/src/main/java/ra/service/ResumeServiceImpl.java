package ra.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.model.Resume;
import ra.repository.ResumeRepo;

import java.util.List;

@Service
public class ResumeServiceImpl implements ResumeService {
    @Autowired
    private ResumeRepo resumeRepo;

    @Override
    public List<Resume> findAll() {
        return resumeRepo.findAll();
    }

    @Override
    public Resume findById(Long id) {
        return resumeRepo.findById(id);
    }

    @Override
    public void save(Resume resume) {
        resumeRepo.save(resume);
    }

    @Override
    public void update(Resume resume) {
        resumeRepo.update(resume);
    }

    @Override
    public void delete(Long id) {
        resumeRepo.delete(id);
    }
}
