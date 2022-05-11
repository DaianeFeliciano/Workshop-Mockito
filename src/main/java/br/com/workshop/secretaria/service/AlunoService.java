package br.com.workshop.secretaria.service;

import br.com.workshop.secretaria.domain.Aluno;
import br.com.workshop.secretaria.dto.AlunoDto;
import br.com.workshop.secretaria.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AlunoService {

    @Autowired
    AlunoRepository alunoRepository;

    public Aluno createAluno(Aluno aluno){
        Aluno createdAluno = alunoRepository.save(aluno);
        return createdAluno;
    }

    public Aluno findByName(String nome) {
        Aluno aluno = alunoRepository.findAllByNomeContainingIgnoreCase(nome);
        return aluno;
    }

    
}
