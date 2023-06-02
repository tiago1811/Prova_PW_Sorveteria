package tads.ufrn.sorveteria.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tads.ufrn.sorveteria.errors.ErrorsMessages;

import java.sql.Date;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "sorvete")
public class Sorvete {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = ErrorsMessages.ERRO_NOME)
    @Size(min = 5)
    String nome;

    @NotBlank(message = ErrorsMessages.ERRO_SABOR)
    @Size(min = 5)
    String sabor;

    @NotBlank(message = ErrorsMessages.ERRO_COBERTURA)
    String cobertura;

    @NotBlank(message = ErrorsMessages.ERRO_ACOMPANHAMENTOS)
    @Size(min = 5)
    String acompanhamentos;

    @NotBlank(message = ErrorsMessages.ERRO_PRECO)
    String preco;

    String imagemUri;

    Date deleted;
}