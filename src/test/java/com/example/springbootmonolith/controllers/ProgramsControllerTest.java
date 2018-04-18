package com.example.springbootmonolith.controllers;

        import com.example.attendancedashboard.controllers.ProgramsController;
        import com.example.attendancedashboard.models.Program;
        import com.example.attendancedashboard.repositories.ProgramsRepository;
        import com.fasterxml.jackson.databind.ObjectMapper;
        import org.junit.Before;

        import org.junit.Test;
        import org.junit.runner.RunWith;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
        import org.springframework.boot.test.mock.mockito.MockBean;

        import org.springframework.dao.EmptyResultDataAccessException;
        import org.springframework.test.context.junit4.SpringRunner;
        import org.springframework.test.web.servlet.MockMvc;
        import org.springframework.http.MediaType;



        import java.util.stream.Collectors;
        import java.util.stream.Stream;

        import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

        import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
        import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

        import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
        import static org.hamcrest.Matchers.*;
        import static org.mockito.Mockito.*;

        import static org.mockito.BDDMockito.given;
        import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@WebMvcTest(ProgramsController.class)
public class ProgramsControllerTest  {
    @Autowired
    private MockMvc mockMvc;
    private Program newProgram;
    private Program updatedSecondProgram;

    @Autowired
    private ObjectMapper jsonObjectMapper;


    @MockBean
    private ProgramsRepository mockProgramRepository;

    @Before
    public void setUp() {
        Program firstProgram = new Program(
                "event name",
                "sunday",
                "category",
                "location"
        );

        Program secondProgram = new Program(
                "second name",
                "second date",
                "second category",
                "second location"
        );
        newProgram = new Program(
                "new name",
                "new location",
                "new category",
                "new date"
        );

        updatedSecondProgram = new Program(
                "updated name",
                "updated location",
                "updated category",
                "updated date"
        );

        given(mockProgramRepository.save(updatedSecondProgram)).willReturn(updatedSecondProgram);

        given(mockProgramRepository.save(newProgram).willReturn(newProgram);

        Iterable<Program> mockRecords =
                Stream.of(firstProgram, secondProgram).collect(Collectors.toList());

        given(mockProgramRepository.findAll()).willReturn(mockPrograms);

        given(mockProgramRepository.findOne(1L)).willReturn(firstProgram);

        given(mockProgramRepository.findOne(4L)).willReturn(null);

        // Mock out Delete to return EmptyResultDataAccessException for missing user with ID of 4
        doAnswer(invocation -> {
            throw new EmptyResultDataAccessException("ERROR MESSAGE FROM MOCK!!!", 1234);
        }).when(mockProgramRepository).delete(4L);
    }

    @Test
    public void findAllPrograms_success_returnsStatusOK() throws Exception {

        this.mockMvc
                .perform(get("/"))
                .andExpect(status().isOk());
    }
    @Test
    public void findAllPrograms_success_returnAllProgramsAsJSON() throws Exception {

        this.mockMvc
                .perform(get("/"))
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void findAllPrograms_success_returnNameForEachProgram() throws Exception {

        this.mockMvc
                .perform(get("/"))
                .andExpect(jsonPath("$[0].name", is("event name")));
    }

    @Test
    public void findAllPrograms_success_returnCategoryForEachProgram() throws Exception {

        this.mockMvc
                .perform(get("/"))
                .andExpect(jsonPath("$[0].category", is("category")));
    }
    //happy path tests
    @Test
    public void findProgramById_success_returnsStatusOK() throws Exception {

        this.mockMvc
                .perform(get("/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void findRecordById_success_returnName() throws Exception {

        this.mockMvc
                .perform(get("/1"))
                .andExpect(jsonPath("$.name", is("event name")));
    }

    @Test
    public void findRecordById_success_returnCategory() throws Exception {

        this.mockMvc
                .perform(get("/1"))
                .andExpect(jsonPath("$.category", is("category")));
    }

    //unhappy path tests


    //make a MockMVC request for that bad ID, and expect that it gives us a "not found" status (404).

    @Test
    public void findProgramById_failure_programNotFoundReturns404() throws Exception {

        this.mockMvc
                .perform(get("/4"))
                .andExpect(status().isNotFound());
    }

    ///clearer error message

    @Test
    public void findProgramById_failure_programNotFoundReturnsNotFoundErrorMessage() throws Exception {

        this.mockMvc
                .perform(get("/4"))
                .andExpect(status().reason(containsString("Program with ID of 4 was not found!")));
    }

    //delete tests
    @Test
    public void deleteProgramById_success_returnsStatusOk() throws Exception {

        this.mockMvc
                .perform(delete("/1"))
                .andExpect(status().isOk());
    }


    //confirm method is actually deleting a program
    @Test
    public void deleteProgramById_success_deletesViaRepository() throws Exception {

        this.mockMvc.perform(delete("/1"));

        verify(mockProgramRepository, times(1)).delete(1L);
    }

    //unhappy path delete. when deleting a program thtat doesnt exist, throw this error-- EmptyResultDataAccessException.

    @Test
    public void deleteProgramById_failure_programNotFoundReturns404() throws Exception {

        this.mockMvc
                .perform(delete("/4"))
                .andExpect(status().isNotFound());
    }

    //create new programs, testing the post route

    @Test
    public void createProgram_success_returnsStatusOk() throws Exception {

        this.mockMvc
                .perform(
                        post("/")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonObjectMapper.writeValueAsString(newProgram))
                )
                .andExpect(status().isOk());
    }

    @Test
    public void createRecord_success_returnsName() throws Exception {

        this.mockMvc
                .perform(
                        post("/")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonObjectMapper.writeValueAsString(newProgram))
                )
                .andExpect(jsonPath("$.name", is("new name")));
    }

    @Test
    public void createProgram_success_returnsCategory() throws Exception {

        this.mockMvc
                .perform(
                        post("/")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonObjectMapper.writeValueAsString(newProgram))
                )
                .andExpect(jsonPath("$.category", is("new category")));
    }

    //test patch route
    @Test
    public void updateProgramById_success_returnsStatusOk() throws Exception {

        this.mockMvc
                .perform(
                        patch("/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonObjectMapper.writeValueAsString(updatedSecondProgram))
                )
                .andExpect(status().isOk());
    }

    @Test
    public void updateProgramById_success_returnsUpdatedName() throws Exception {

        this.mockMvc
                .perform(
                        patch("/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonObjectMapper.writeValueAsString(updatedSecondProgram))
                )
                .andExpect(jsonPath("$.name", is("updated name")));
    }

    @Test
    public void updateProgramById_success_returnsUpdatedCategory() throws Exception {

        this.mockMvc
                .perform(
                        patch("/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonObjectMapper.writeValueAsString(updatedSecondProgram))
                )
                .andExpect(jsonPath("$.category", is("updated category")));
    }
    //unhappy path for patch route
    @Test
    public void updateProgramById_failure_programNotFoundReturns404() throws Exception {

        this.mockMvc
                .perform(
                        patch("/4")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonObjectMapper.writeValueAsString(updatedSecondProgram))
                )
                .andExpect(status().isNotFound());
    }

    @Test
    public void updateProgramById_failure_programNotFoundReturnsNotFoundErrorMessage() throws Exception {

        this.mockMvc
                .perform(
                        patch("/4")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonObjectMapper.writeValueAsString(updatedSecondProgram))
                )
                .andExpect(status().reason(containsString("Program with ID of 4 was not found!")));
    }


}
