package com.example.attendancedashboard.controllers;

import com.example.attendancedashboard.models.Program;
import com.example.attendancedashboard.repositories.ParticipantssRepository;
import com.example.attendancedashboard.repositories.ProgramsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class ProgramsController {

    @Autowired
    private ProgramsRepository programsRepository;

    @Autowired
    ParticipantssRepository psRepository;


    @GetMapping("/programs")
    public Iterable<Program> findAllprograms() {
        return programsRepository.findAll(

        );
    }
    //get all participants for a program
    @GetMapping("/programs/participant/{participantId}")
    public Iterable<Program> findProgramsbyParticipantId(@PathVariable long participantId) {
        return programsRepository.findByParticipantsId(participantId);
    }

//    //add a participant to a program
//    @PostMapping("/programs/participant")
//    public Program createNewParticipantForProgram(@RequestBody Program newParticipant) {
//        return programsRepository.save(newParticipant);
//    }


    @GetMapping("/programs/{programId}")
    public Optional<Program> findProgramById(@PathVariable Long programId) {
        return programsRepository.findById(programId);
    }
    @DeleteMapping ("/programs/{programId}")
    public HttpStatus deleteProgramById(@PathVariable Long programId) {
        programsRepository.deleteById(programId);
        return HttpStatus.OK;
    }
    @PostMapping("/programs")
    public Program createNewProgram(@RequestBody Program newProgram) {
        return programsRepository.save(newProgram);
    }

    @PatchMapping("/programs/{programId}")
    public Program updateProgramById(@PathVariable Long programId, @RequestBody Program userRequest) {

        Program programFromDb = programsRepository.findById(programId).get();

        programFromDb.setEventName(userRequest.getEventName());
        programFromDb.setProgramDate(userRequest.getProgramDate());
        programFromDb.setCategory(userRequest.getCategory());

        return programsRepository.save(programFromDb);
    }

}