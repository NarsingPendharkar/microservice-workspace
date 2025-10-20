package org.school.studentms.service;

import org.school.studentms.dto.StudentDTO;
import org.school.studentms.entity.Student;
import org.school.studentms.exception.ResourceNotFoundException;
import org.school.studentms.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository repository;

    @Override
    public StudentDTO createStudent(StudentDTO dto) {
        Student student = mapToEntity(dto);
        Student saved = repository.save(student);
        return mapToDTO(saved);
    }

    @Override
    public StudentDTO getStudentById(Long id) {
        Student student = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));
        return mapToDTO(student);
    }

    @Override
    public List<StudentDTO> getAllStudents() {
        return repository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public StudentDTO updateStudent(Long id, StudentDTO dto) {
        Student existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));

        existing.setFirstName(dto.getFirstName());
        existing.setLastName(dto.getLastName());
        existing.setEmail(dto.getEmail());
        existing.setPhone(dto.getPhone());
        existing.setGrade(dto.getGrade());

        Student updated = repository.save(existing);
        return mapToDTO(updated);
    }

    @Override
    public void deleteStudent(Long id) {
        Student student = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));
        repository.delete(student);
    }

    // Utility Mappers
    private Student mapToEntity(StudentDTO dto) {
        return Student.builder()
                .id(dto.getId())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .phone(dto.getPhone())
                .grade(dto.getGrade())
                .build();
    }

    private StudentDTO mapToDTO(Student entity) {
        return StudentDTO.builder()
                .id(entity.getId())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .email(entity.getEmail())
                .phone(entity.getPhone())
                .grade(entity.getGrade())
                .build();
    }
}
