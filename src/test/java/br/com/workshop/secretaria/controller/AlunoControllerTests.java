package br.com.workshop.secretaria.controller;

import br.com.workshop.secretaria.domain.Aluno;
import br.com.workshop.secretaria.service.AlunoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
class AlunoControllerTests {

    public static final Long MATRICULA   = 1L;
    public static final String ESCOLA    = "Objetivo";
    public static final String NOME      = "Daiane";
    public static final LocalDate DATA_NASCIMENTO = LocalDate.of(2002,
            2, 20);
    public static final String SERIE     = "3A-EM";
    public static final int INDEX = 0;

    @InjectMocks
    private AlunoController alunoController;

    @Mock
    private Aluno aluno;

    @Mock
    private AlunoService alunoService;

    @Mock
    private List<Aluno> listAluno;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startAluno();
    }

    @Test
    void createAluno() {
    }

    @Test
    void whenFindByNameThenReturnAnAlunoListByName() {
        when(alunoService.findByName(anyString())).thenReturn(listAluno);

        ResponseEntity<List<Aluno>> response = alunoController.findByNome(NOME);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(Aluno.class, response.getBody().get(INDEX).getClass());
        assertEquals(NOME, response.getBody().get(INDEX).getNome());
        assertEquals(MATRICULA, response.getBody().get(INDEX).getMatricula());
        assertEquals(ESCOLA, response.getBody().get(INDEX).getEscola());
        assertEquals(DATA_NASCIMENTO, response.getBody().get(INDEX).getDataNascimento());
        assertEquals(SERIE, response.getBody().get(INDEX).getSerie());

    }

    @Test
    void listAlunos() {
    }

    @Test
    void findById() {
    }

    @Test
    void deleteById() {
    }

    @Test
    void updateById() {
    }

    private void startAluno() {
        aluno = new Aluno(MATRICULA, ESCOLA,
                NOME, DATA_NASCIMENTO, SERIE);
        listAluno = List.of(aluno);
    }
}