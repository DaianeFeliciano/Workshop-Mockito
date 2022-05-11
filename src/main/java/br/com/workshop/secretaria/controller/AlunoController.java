package br.com.workshop.secretaria.controller;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

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

import br.com.workshop.secretaria.dto.AlunoDto;
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
    public AlunoDto createAluno(@RequestBody @Valid AlunoDto alunoDto){
        System.out.println(alunoDto);
        return alunoDto;
    }

    @GetMapping("/nome")
    public AlunoDto findByNome(@PathVariable String nome){
        return null;
        //chamada da service vai aqui
    }

    @GetMapping
    public ResponseEntity<List<AlunoDto>> listAlunos(){
        return null;
        //chamada da service vai aqui
    }

    @DeleteMapping("/{matricula}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable UUID matricula){
        //chamada da service vai aqui
    }

    @PutMapping("/{matricula}")
    public AlunoDto updateById(@PathVariable UUID matricula, @RequestBody @Valid AlunoDto alunoDto){
        return alunoDto;
        //chamada da service aqui
    }



}
