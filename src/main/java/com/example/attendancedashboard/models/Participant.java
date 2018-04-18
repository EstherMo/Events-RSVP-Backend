package com.example.attendancedashboard.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Data
@AllArgsConstructor @NoArgsConstructor @Getter @Setter
@Entity @Table(name = "PARTICIPANTS")
public class Participant {

    //@JsonIgnoreProperties("participants")
    private Set<Program> programs;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Access(AccessType.PROPERTY)
    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinTable(name = "programs_participants", joinColumns = @JoinColumn(name = "participant_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "program_id", referencedColumnName = "id"))
    public Set<Program> getPrograms() {
        return this.programs;
    }
    public void setPrograms(Set<Program> programs) { this.programs = programs; }



    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "EMAIL_ADDRESS")
    private String emailAddress;

}