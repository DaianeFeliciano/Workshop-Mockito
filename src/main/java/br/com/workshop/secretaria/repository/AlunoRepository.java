package br.com.workshop.secretaria.repository;

import br.com.workshop.secretaria.domain.Aluno;
import jdk.jfr.Enabled;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, UUID> {

    public List<Aluno> findAllByNomeContainingIgnoreCase(@Param("")String nome);

}