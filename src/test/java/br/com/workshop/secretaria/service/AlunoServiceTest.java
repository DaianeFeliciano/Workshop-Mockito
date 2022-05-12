package br.com.workshop.secretaria.service;

import br.com.workshop.secretaria.AplicationConfigTest;
import br.com.workshop.secretaria.domain.Aluno;
import br.com.workshop.secretaria.repository.AlunoRepository;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.anyLong;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AlunoServiceTest {

    public static final Long MATRICULA   = 1L;
    public static final String ESCOLA    = "Objetivo";
    public static final String NOME      = "Daiane";
    public static final LocalDate DATA_NASCIMENTO = LocalDate.of(2002,
            2, 20);
    public static final String SERIE     = "3A-EM";
    public static final int INDEX = 0;

    @BeforeEach
        // antes realiza um trecho de código
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startAluno();
    }
    @Mock
    private List<Aluno> listAluno;

    @Mock
    private Aluno aluno;

    @Mock
    private Aluno aluno2;

    @Mock
    private AlunoRepository repository;

    @InjectMocks
    private AlunoService service;

    @Captor
    private ArgumentCaptor<Aluno> captor;


    private Optional<Aluno> alunoOptional;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        startAluno();
    }

    @Test
    void testFindById(){
        when(repository.findById(anyLong())).thenReturn(alunoOptional);

        Aluno response = service.findById(matricula);

        assertNotNull(response);
        assertEquals(Aluno.class, response.getClass());
        assertEquals(matricula, response.getMatricula());
        assertEquals(escola, response.getEscola());
        assertEquals(nome, response.getNome());
        assertEquals(dataNascimento, response.getDataNascimento());
        assertEquals(serie, response.getSerie());

    }

    @Test
    void testCreateAluno(){
        Aluno aluno = new Aluno();
        aluno.setMatricula(1L);
        aluno.setNome("Daiana");
        aluno.setEscola("Alub");
        aluno.setDataNascimento(LocalDate.of(2002,2,20));
        aluno.setSerie("3A - EM");

        when(repository.save(any())).thenReturn(aluno);

        service.createAluno(aluno);

        Mockito.verify(repository).save(captor.capture());

        Aluno alunoCreated = captor.getValue();
        assertEquals(aluno.getMatricula(), alunoCreated.getMatricula());
        assertEquals(aluno.getNome(), alunoCreated.getNome());
        assertEquals(aluno.getEscola(), alunoCreated.getEscola());
        assertEquals(aluno.getSerie(), alunoCreated.getSerie());
        assertEquals(aluno.getDataNascimento(), alunoCreated.getDataNascimento());
    }

    @Test
    void testDeleteAluno() {
        doNothing().when(repository).deleteById(anyLong());
        service.deleteAluno(MATRICULA);
        verify(repository, times(1)).deleteById(anyLong());
    }


    @Test
    void getAll() {

        when(repository.findAll()).thenReturn(listAluno);
        List<Aluno> alunoList = service.getAll();
        assertNotNull(alunoList.get(INDEX));
    }

    private void startAluno() {

         aluno = new Aluno(MATRICULA, ESCOLA,
                NOME, DATA_NASCIMENTO, SERIE);
         aluno2 = new Aluno(2L, "Objetivo",
                 "Pedro", LocalDate.of(2000,10,2), "3A - EM");

         listAluno = List.of(aluno,aluno2);

    }

    private void startAluno(){
        alunoOptional = Optional.of(new Aluno( matricula, escola, nome, dataNascimento, serie));
    }


    @Test
    void testeUpdateAluno() {
        Aluno aluno = new Aluno();
        Long matricula = 1L;
        aluno.setMatricula(1L);
        aluno.setNome("Daiana");
        aluno.setEscola("Alub");
        aluno.setDataNascimento(LocalDate.of(2002, 2, 20));
        aluno.setSerie("2A - EM");

        // buscando matricula
        when(repository.findById(anyLong())).thenReturn(Optional.of(aluno));
        // setando alterações
        String novaSerie = "3A - EM";
        aluno.setSerie(novaSerie);
        // salvando (metodo create dentro da service)
        when(repository.save(any())).thenReturn(aluno);

        service.updateAluno(matricula, aluno);

        // verifica/captura qual a matricula do repository.findById
        Mockito.verify(repository).findById(captorMatricula.capture());
        // verifica/captura os dados salvos com o repository.save
        Mockito.verify(repository).save(captor.capture());

        // comparações
        Aluno alunoUpdated = captor.getValue();
        Long matriculaCaptured = captorMatricula.getValue();
        assertEquals(matricula, matriculaCaptured);
        assertEquals(novaSerie, alunoUpdated.getSerie());
    }
}