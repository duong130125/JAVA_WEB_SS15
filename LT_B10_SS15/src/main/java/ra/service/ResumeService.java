package ra.service;

import ra.model.Resume;

import java.util.List;

public interface ResumeService {
    List<Resume> findAll();
    Resume findById(Long id);
    void save(Resume resume);
    void update(Resume resume);
    void delete(Long id);
}
