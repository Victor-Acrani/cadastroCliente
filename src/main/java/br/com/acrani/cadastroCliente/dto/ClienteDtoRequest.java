package br.com.acrani.cadastroCliente.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDtoRequest {

    @NotNull
    @Size(min = 3, max = 50)
    private String name;
    @NotNull
    @CPF
    private String cpf;
    @NotNull
    private String phone;
    @NotNull
    @Email
    private String email;
}
