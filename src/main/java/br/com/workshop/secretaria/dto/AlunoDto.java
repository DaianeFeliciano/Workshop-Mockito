package br.com.workshop.secretaria.dto;

import java.util.UUID;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AlunoDto {
    
    private UUID matricula;

    @NotNull
    @Size(min = 1, max = 200)
    private String escola;

    @NotNull
    @Size(min = 1, max = 200)
    private String nome;

    @NotNull
    @Size(max = 20)
    private String dataNascimento;

    @NotNull
    @Size(max = 20)
    private String serie;
}
