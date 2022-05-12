package br.com.workshop.secretaria.controller;

import java.util.List;
import javax.validation.Valid;
import br.com.workshop.secretaria.domain.Aluno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<Aluno>> findByNome(@PathVariable String nome){
        return ResponseEntity.ok(alunoService.findByName(nome));
    }

    @GetMapping
    public ResponseEntity<List<Aluno>> listAlunos(){
        return ResponseEntity.ok(alunoService.getAll());
    }

    @GetMapping("/{matricula}")
    public ResponseEntity<Aluno> findById(@PathVariable Long matricula){
        return ResponseEntity.ok(alunoService.findById(matricula));
    }

    @DeleteMapping("/{matricula}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long matricula){
        alunoService.deleteAluno(matricula);
    }

    @PutMapping("/{matricula}")
    public ResponseEntity<Aluno> updateById(@PathVariable Long matricula, @RequestBody @Valid Aluno aluno){
        alunoService.updateAluno(matricula,aluno);
        return ResponseEntity.ok(aluno);
    }
}
