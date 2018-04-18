package com.example.attendancedashboard.repositories;

import com.example.attendancedashboard.models.Program;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProgramsRepository extends CrudRepository<Program, Long> {

    List<Program> findByParticipantsId(long participantId);
}