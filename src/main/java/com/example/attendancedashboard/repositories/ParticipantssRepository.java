package com.example.attendancedashboard.repositories;

import com.example.attendancedashboard.models.Participant;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ParticipantssRepository extends CrudRepository<Participant, Long> {

    List<Participant> findByProgramsId(long programId);

}