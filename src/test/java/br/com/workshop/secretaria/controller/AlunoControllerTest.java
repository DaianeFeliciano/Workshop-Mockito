package br.com.workshop.secretaria.controller;

import br.com.workshop.secretaria.domain.Aluno;
import br.com.workshop.secretaria.service.AlunoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AlunoController.class)
public class AlunoControllerTest {

    public static final Long MATRICULA   = 1L;
    public static final String ESCOLA    = "Objetivo";
    public static final String NOME      = "Daiane";
    public static final String SERIE     = "3A-EM";
    public static final int INDEX = 0;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AlunoService service;

    private List<Aluno> listAluno;

    private Aluno aluno;

    @BeforeEach
    void setUp(){
        startAluno();
    }

    @Test
    void testandoMockMvc(){
        assertNotNull(mockMvc);
    }

    @Test
    void createAlunoControllerTest() throws Exception{
        when(service.createAluno(aluno)).thenReturn(aluno);
        mockMvc.perform(post("/api/alunos")
                        .contentType(MediaType.APPLICATION_JSON).content(asJsonString(aluno)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.matricula").value(MATRICULA))
                .andExpect(jsonPath("$.nome").value(NOME))
                .andExpect(jsonPath("$.escola").value(ESCOLA))
                .andExpect(jsonPath("$.serie").value(SERIE));
    }

    @Test
    void getAllAlunosControllerTest() throws Exception {
        when(service.getAll())
                .thenReturn(listAluno);

        mockMvc.perform(get("/api/alunos")).andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", Matchers.is(1)))
                .andExpect(jsonPath("$[0].matricula").value(MATRICULA))
                .andExpect(jsonPath("$[0].nome").value(NOME))
                .andExpect(jsonPath("$[0].escola").value(ESCOLA))
                .andExpect(jsonPath("$[0].serie").value(SERIE));
    }

    @Test
    void whenFindByNameThenReturnAnAlunoListByName() throws Exception {
        when(service.findByName(NOME))
                .thenReturn(listAluno);

        mockMvc.perform(get("/api/alunos/nome/{nome}",NOME)).andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", Matchers.is(1)))
                .andExpect(jsonPath("$[0].matricula").value(MATRICULA))
                .andExpect(jsonPath("$[0].nome").value(NOME))
                .andExpect(jsonPath("$[0].escola").value(ESCOLA))
                .andExpect(jsonPath("$[0].serie").value(SERIE));
    }

    @Test
    void findByIdControllerTest() throws Exception{
        when(service.findById(anyLong())).thenReturn(aluno);
        mockMvc.perform(get("/api/alunos/{matricula}",MATRICULA)).andExpect(status().isOk())
                .andExpect(jsonPath("$.matricula").value(MATRICULA))
                .andExpect(jsonPath("$.nome").value(NOME))
                .andExpect(jsonPath("$.escola").value(ESCOLA))
                .andExpect(jsonPath("$.serie").value(SERIE));
    }

    @Test
    void deleteAlunoControllerTest() throws Exception {
        mockMvc.perform(delete("/api/alunos/{matricula}",MATRICULA))
                .andExpect(status().isNoContent());
    }

    @Test
    void updateByIdTest() throws Exception{
        mockMvc.perform(put("/api/alunos/{matricula}",MATRICULA)
                        .contentType(MediaType.APPLICATION_JSON).content(asJsonString(aluno)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.matricula").value(MATRICULA))
                .andExpect(jsonPath("$.nome").value(NOME))
                .andExpect(jsonPath("$.escola").value(ESCOLA))
                .andExpect(jsonPath("$.serie").value(SERIE));
    }

    private void startAluno() {
        aluno = new Aluno(MATRICULA, ESCOLA,
                NOME, SERIE);
        listAluno = List.of(aluno);
    }

    public static String asJsonString(final Aluno aluno) {
        try {
            return new ObjectMapper().writeValueAsString(aluno);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
