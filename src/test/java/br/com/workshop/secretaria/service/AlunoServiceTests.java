package br.com.workshop.secretaria.service;

import br.com.workshop.secretaria.domain.Aluno;
import br.com.workshop.secretaria.repository.AlunoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
class AlunoServiceTests {

    public static final Long MATRICULA   = 1L;
    public static final String ESCOLA    = "Objetivo";
    public static final String NOME      = "Daiane";
    public static final LocalDate DATA_NASCIMENTO = LocalDate.of(2002,
            2, 20);
    public static final String SERIE     = "3A-EM";


    @InjectMocks // gera uma instacia real e os demais Mock, vai mockar mesmo
    private AlunoService alunoService;

    @Mock
    private AlunoRepository alunoRepository;

    @Mock
    private Aluno aluno;

    @Mock
    private List<Aluno> listAluno;

    @BeforeEach // antes realiza um trecho de código
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startAluno();
    }

    @Test
    void createAluno() {
    }

    @Test
    void whenFindByNameThenReturnAnAlunoInstance() {
        when(alunoRepository.findAllByNomeContainingIgnoreCase(anyString()))
                            .thenReturn(listAluno);
                            List<Aluno> response = alunoService.findByName(NOME);

        assertNotNull(response);
        assertEquals(listAluno, response);
        assertEquals(NOME, response.get(0).getNome());
        assertEquals(MATRICULA, response.get(0).getMatricula());
        assertEquals(ESCOLA, response.get(0).getEscola());
        assertEquals(DATA_NASCIMENTO, response.get(0).getDataNascimento());
        assertEquals(SERIE, response.get(0).getSerie());
    }

    @Test
    void getAll() {
    }

    @Test
    void findById() {
    }

    @Test
    void deleteAluno() {
    }

    @Test
    void updateAluno() {
    }

    private void startAluno() {
        aluno = new Aluno(MATRICULA, ESCOLA,
                NOME, DATA_NASCIMENTO, SERIE);
        listAluno = List.of(aluno);
    }
}