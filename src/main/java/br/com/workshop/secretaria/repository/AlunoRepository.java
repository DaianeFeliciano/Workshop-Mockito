package br.com.workshop.secretaria.repository;

import br.com.workshop.secretaria.domain.Aluno;
import jdk.jfr.Enabled;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@EnableJpaRepositories
public interface AlunoRepository extends JpaRepository<Aluno, UUID> {

    public Aluno findAllByNomeContainingIgnoreCase(String name);

}