package br.com.workshop.secretaria.controller;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import br.com.workshop.secretaria.domain.Aluno;
import br.com.workshop.secretaria.dto.AlunoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.workshop.secretaria.service.AlunoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/alunos")
public class AlunoController {
    
    @Autowired
    private AlunoService alunoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Aluno createAluno(@RequestBody @Valid Aluno aluno){
        return alunoService.createAluno(aluno);
    }

    @GetMapping("/api/alunos/{nome}")
    public Aluno findByNome(@PathVariable String nome){
        return alunoService.findByName(nome);
        //chamada da service vai aqui
    }

    @GetMapping
    public ResponseEntity<List<Aluno>> listAlunos(){
        return null;
        //chamada da service vai aqui
    }

    @DeleteMapping("/{matricula}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable UUID matricula){
        //chamada da service vai aqui
    }

    @PutMapping("/{matricula}")
    public Aluno updateById(@PathVariable UUID matricula, @RequestBody @Valid Aluno aluno){
        return aluno;
        //chamada da service aqui
    }



}
