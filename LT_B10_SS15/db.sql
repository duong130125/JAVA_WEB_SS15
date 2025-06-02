create database lt_b10_ss15;
use lt_b10_ss15;

CREATE TABLE resumes
(
    id           BIGINT AUTO_INCREMENT PRIMARY KEY,
    full_name    VARCHAR(100) NOT NULL,
    email        VARCHAR(100) NOT NULL,
    phone_number VARCHAR(20)  NOT NULL,
    education    TEXT,
    experience   TEXT,
    skills       TEXT
);

DELIMITER //
-- Lấy tất cả
CREATE PROCEDURE get_all_resumes()
BEGIN
    SELECT * FROM resumes;
END;

-- Lấy theo ID
CREATE PROCEDURE get_resume_by_id(IN rid BIGINT)
BEGIN
    SELECT * FROM resumes WHERE id = rid;
END;

-- Thêm
CREATE PROCEDURE insert_resume(
    IN r_fullName VARCHAR(100),
    IN r_email VARCHAR(100),
    IN r_phone VARCHAR(20),
    IN r_education TEXT,
    IN r_experience TEXT,
    IN r_skills TEXT
)
BEGIN
    INSERT INTO resumes(full_name, email, phone_number, education, experience, skills)
    VALUES (r_fullName, r_email, r_phone, r_education, r_experience, r_skills);
END;

-- Cập nhật
CREATE PROCEDURE update_resume(
    IN r_id BIGINT,
    IN r_fullName VARCHAR(100),
    IN r_email VARCHAR(100),
    IN r_phone VARCHAR(20),
    IN r_education TEXT,
    IN r_experience TEXT,
    IN r_skills TEXT
)
BEGIN
    UPDATE resumes
    SET full_name = r_fullName, email = r_email, phone_number = r_phone,
        education = r_education, experience = r_experience, skills = r_skills
    WHERE id = r_id;
END;

-- Xoá
CREATE PROCEDURE delete_resume(IN r_id BIGINT)
BEGIN
    DELETE FROM resumes WHERE id = r_id;
END;

DELIMITER //
