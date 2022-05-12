package br.com.workshop.secretaria.controller;

import br.com.workshop.secretaria.domain.Aluno;
import br.com.workshop.secretaria.service.AlunoService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AlunoController.class)
public class AlunoControllerTest {

    public static final Long MATRICULA   = 1L;
    public static final String ESCOLA    = "Objetivo";
    public static final String NOME      = "Daiane";
    public static final LocalDate DATA_NASCIMENTO = LocalDate.of(2002, 2, 20);
    public static final String SERIE     = "3A-EM";
    public static final int INDEX = 0;


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private AlunoService service;

    @Mock
    private List<Aluno> listAluno;

    @Mock
    private Aluno aluno;

    @Mock
    private Aluno aluno2;

    @BeforeEach
    void setUp(){
        startAluno();
    }

    @Test
    void testandoMockMvc(){
        assertNotNull(mockMvc);
    }

    @Test
    void getAllTest() throws Exception {
        when(service.getAll())
                .thenReturn(listAluno);

        this.mockMvc.perform(get("/api/alunos")).andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", Matchers.is(2)))
                .andExpect(jsonPath("$[0].nome").value(NOME))
                .andExpect(jsonPath("$[0].matricula").value(MATRICULA));
    }

    @Test
    void whenFindByNameThenReturnAnAlunoListByName() throws Exception {
        when(service.findByName(NOME))
                .thenReturn(listAluno);

        this.mockMvc.perform(get("/api/alunos/nome/Daiane")).andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", Matchers.is(2)))
                .andExpect(jsonPath("$[0].nome").value(NOME))
                .andExpect(jsonPath("$[0].escola").value(ESCOLA))
                .andExpect(jsonPath("$[0].serie").value(SERIE));
    }

    private void startAluno() {
        aluno = new Aluno(MATRICULA, ESCOLA,
                NOME, DATA_NASCIMENTO, SERIE);
        aluno2 = new Aluno(2L, "Objetivo",
                "Pedro", LocalDate.of(2000,10,2), "3A - EM");
        listAluno = List.of(aluno,aluno2);
    }
}
