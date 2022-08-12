package br.com.acrani.cadastroCliente.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteUpdateForm {

    @NotNull
    @Size(min = 3, max = 50)
    private String name;
    @NotNull
    private String phone;
    @NotNull
    @Email
    private String email;
}
