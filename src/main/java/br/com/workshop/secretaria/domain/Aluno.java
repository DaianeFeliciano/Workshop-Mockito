package br.com.workshop.secretaria.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Aluno {

    private String escola;

    private UUID matricula;

    private String nome;

    private LocalDate dataNascimento;

    private String serie;
}
