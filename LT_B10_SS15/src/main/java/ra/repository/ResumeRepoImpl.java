package ra.repository;

import org.springframework.stereotype.Repository;
import ra.model.Resume;
import ra.utils.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ResumeRepoImpl implements ResumeRepo {
    @Override
    public List<Resume> findAll() {
        List<Resume> resumes = new ArrayList<>();
        Connection conn = null;
        CallableStatement callSt = null;

        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call get_all_resumes()}");
            ResultSet rs = callSt.executeQuery();

            while (rs.next()) {
                Resume r = new Resume();
                r.setId(rs.getLong("id"));
                r.setFullName(rs.getString("full_name"));
                r.setEmail(rs.getString("email"));
                r.setPhoneNumber(rs.getString("phone_number"));
                r.setEducation(rs.getString("education"));
                r.setExperience(rs.getString("experience"));
                r.setSkills(rs.getString("skills"));
                resumes.add(r);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return resumes;
    }

    @Override
    public Resume findById(Long id) {
        Resume resume = null;
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call get_resume_by_id(?)}");
            callSt.setLong(1, id);
            ResultSet rs = callSt.executeQuery();
            if (rs.next()) {
                resume = new Resume();
                resume.setId(rs.getLong("id"));
                resume.setFullName(rs.getString("full_name"));
                resume.setEmail(rs.getString("email"));
                resume.setPhoneNumber(rs.getString("phone_number"));
                resume.setEducation(rs.getString("education"));
                resume.setExperience(rs.getString("experience"));
                resume.setSkills(rs.getString("skills"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return resume;
    }

    @Override
    public void save(Resume resume) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call insert_resume(?,?,?,?,?,?)}");
            callSt.setString(1, resume.getFullName());
            callSt.setString(2, resume.getEmail());
            callSt.setString(3, resume.getPhoneNumber());
            callSt.setString(4, resume.getEducation());
            callSt.setString(5, resume.getExperience());
            callSt.setString(6, resume.getSkills());
            callSt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
    }

    @Override
    public void update(Resume resume) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call update_resume(?,?,?,?,?,?,?)}");
            callSt.setLong(1, resume.getId());
            callSt.setString(2, resume.getFullName());
            callSt.setString(3, resume.getEmail());
            callSt.setString(4, resume.getPhoneNumber());
            callSt.setString(5, resume.getEducation());
            callSt.setString(6, resume.getExperience());
            callSt.setString(7, resume.getSkills());
            callSt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
    }

    @Override
    public void delete(Long id) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call delete_resume(?)}");
            callSt.setLong(1, id);
            callSt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
    }
}