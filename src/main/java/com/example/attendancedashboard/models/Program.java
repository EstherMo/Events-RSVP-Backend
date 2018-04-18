package com.example.attendancedashboard.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

@Data
@AllArgsConstructor @NoArgsConstructor @Getter @Setter
@Entity @Table(name = "PROGRAMS")
public class Program {

    //@JsonIgnoreProperties("programs") //solve JSON recursive dependency
    private Set<Participant> participants;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @JsonIgnore
    @Access(AccessType.PROPERTY)
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "programs")
    @ModelAttribute("getParticipants")
    public Set<Participant> getParticipants() {
        return this.participants;
    }

    public void setParticipants(Set<Participant> participants) {
        this.participants = participants;
    }

    @Column(name = "EVENT_NAME ")
    private String eventName;

    @Column(name = "PROGRAM_DATE")
    private String programDate;

    @Column(name = "CATEGORY")
    private String category;

    @Column(name = "LOCATION")
    private String location;

    public Program(String name, String date, String category, String location) {
        this.id = id;
        this.eventName = name;
        this.programDate = date;
        this.category = category;
        this.location = location;
    }
}