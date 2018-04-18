package com.example.attendancedashboard.controllers;

import com.example.attendancedashboard.models.Participant;
import com.example.attendancedashboard.models.Program;
import com.example.attendancedashboard.repositories.ParticipantssRepository;
import com.example.attendancedashboard.repositories.ProgramsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@RestController
public class ParticipantsController {
    @Autowired
    private ParticipantssRepository psRepository;

    @Autowired
    private ProgramsRepository programsRepository;

    @GetMapping("/participants")
    public Iterable<Participant> findAllParticipants() {
        return psRepository.findAll(

        );
    }

    //find all participants for a specific program
    @GetMapping("/participants/program/{programId}")
    public Iterable<Participant> findParticipantsbyProgramId(@PathVariable long programId) {
        return psRepository.findByProgramsId(programId);
    }

    //set program info for a participant
    @PostMapping("/participants/{programId}/{participantId}")
    public void createNewProgramforParticipant(@PathVariable Long participantId, @PathVariable Long programId) {
        Participant thisParticipant = psRepository.findById(participantId).get();
        //System.out.println( "xxxxxxxxxxxxxxxxxxxPARTICIPANNNNNTTTTTTTTTTTTTT" + thisParticipant);
        Set<Program> programHash = new HashSet<>();
        //System.out.println( "yyyyyyyyyyyyyyPROGRAMHASSSHHHHHHHHHHHHH" + programHash);
        Program thisProgram = programsRepository.findById(programId).get();
        //System.out.println( "some program string" + thisProgram);

        programHash.add(thisProgram);
//        thisParticipant.setPrograms(programHash);
    }

    @GetMapping("/participants/{participantId}")
    public Optional<Participant> findParticipantById(@PathVariable Long participantId) {
        return psRepository.findById(participantId);
    }
    @DeleteMapping("/participants/{participantId}")
    public HttpStatus deleteParticipantById(@PathVariable Long participantId) {
        psRepository.deleteById(participantId);
        return HttpStatus.OK;
    }
    @PostMapping("/participants")
    public Participant createNewParticipant(@RequestBody Participant newParticipant) {
        return psRepository.save(newParticipant);
    }
    @PatchMapping("/participant/{participantId}")
    public Participant updateParticipantById(@PathVariable Long participantId, @RequestBody Participant userRequest) {

        Participant participantFromDb = psRepository.findById(participantId).get();

        participantFromDb.setFirstName(userRequest.getFirstName());
        participantFromDb.setLastName(userRequest.getLastName());
        participantFromDb.setEmailAddress(userRequest.getEmailAddress());

        return psRepository.save(participantFromDb);
    }

}