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
    public static final int INDEX = 0;


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
    void whenFindByNameThenReturnAnAlunoListByName() {
        when(alunoRepository.findAllByNomeContainingIgnoreCase(anyString()))
                            .thenReturn(listAluno);
                            List<Aluno> response = alunoService.findByName(NOME);

        assertNotNull(response);
        assertEquals(Aluno.class, response.get(INDEX).getClass());
        assertEquals(1, response.size());
        assertEquals(NOME, response.get(INDEX).getNome());
        assertEquals(MATRICULA, response.get(INDEX).getMatricula());
        assertEquals(ESCOLA, response.get(INDEX).getEscola());
        assertEquals(DATA_NASCIMENTO, response.get(INDEX).getDataNascimento());
        assertEquals(SERIE, response.get(INDEX).getSerie());
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