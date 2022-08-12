package br.com.acrani.cadastroCliente.services;

import br.com.acrani.cadastroCliente.dto.ClienteDtoRequest;
import br.com.acrani.cadastroCliente.dto.ClienteDtoResponse;
import br.com.acrani.cadastroCliente.dto.ClienteUpdateForm;

import java.util.List;

public interface ClienteService {

    /**
     *
     * @return
     */
    List<ClienteDtoResponse> findAll();

    /**
     *
     * @param cpf
     * @return
     */
    ClienteDtoResponse findByCpf(String cpf);

    /**
     *
     * @param request
     * @return
     */
    ClienteDtoResponse save(ClienteDtoRequest request);

    /**
     *
     * @param form
     * @return
     */
    ClienteDtoResponse update(String cpf, ClienteUpdateForm form);

    /**
     *
     * @param cpf
     */
    void delete(String cpf);
}
