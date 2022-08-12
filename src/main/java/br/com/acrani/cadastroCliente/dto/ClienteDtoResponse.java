package br.com.acrani.cadastroCliente.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDtoResponse {

    private String name;
    private String cpf;
    private String phone;
    private String email;
}
