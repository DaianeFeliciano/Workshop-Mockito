package br.com.workshop.secretaria.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "Alunos")
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, name = "Matricula", unique = true)
    private UUID matricula;

    @Column(nullable = false, name = "Escola")
    private String escola;

    @Column(nullable = false, name = "Nome")
    private String nome;

    @Column(nullable = false, name = "DataDeNascimento")
    private LocalDate dataNascimento;

    @Column(nullable = false, name = "Serie")
    private String serie;
}
