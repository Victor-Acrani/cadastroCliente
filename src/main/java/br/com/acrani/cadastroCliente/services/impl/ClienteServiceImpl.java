package br.com.acrani.cadastroCliente.services.impl;

import br.com.acrani.cadastroCliente.dto.ClienteDtoRequest;
import br.com.acrani.cadastroCliente.dto.ClienteDtoResponse;
import br.com.acrani.cadastroCliente.dto.ClienteUpdateForm;
import br.com.acrani.cadastroCliente.models.Cliente;
import br.com.acrani.cadastroCliente.repositories.ClienteRepository;
import br.com.acrani.cadastroCliente.services.ClienteService;
import br.com.acrani.cadastroCliente.services.exceptions.DuplicatedDataException;
import br.com.acrani.cadastroCliente.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteServiceImpl(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public List<ClienteDtoResponse> findAll() {
        List<Cliente> all = clienteRepository.findAll();

        return all.stream().map(cliente -> {
            ClienteDtoResponse response = new ClienteDtoResponse();
            BeanUtils.copyProperties(cliente, response);
            return response;
        }).toList();
    }

    @Override
    public ClienteDtoResponse findByCpf(String cpf) {
        Cliente cliente = clienteRepository.findByCpf(cpf)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado. CPF: " + cpf));

        ClienteDtoResponse response = new ClienteDtoResponse();
        BeanUtils.copyProperties(cliente, response);
        return response;
    }

    @Override
    public ClienteDtoResponse save(ClienteDtoRequest request) {

        Optional<Cliente> byCpf = clienteRepository.findByCpf(request.getCpf());
        if (byCpf.isPresent()){
            throw new DuplicatedDataException("Cliente já cadastrado. CPF:" + request.getCpf());
        }

        Cliente cliente = new Cliente();
        BeanUtils.copyProperties(request, cliente);
        clienteRepository.save(cliente);

        ClienteDtoResponse response = new ClienteDtoResponse();
        BeanUtils.copyProperties(cliente, response);
        return response;
    }

    @Override
    public ClienteDtoResponse update(String cpf, ClienteUpdateForm form) {
        Cliente cliente = clienteRepository.findByCpf(cpf)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado. CPF: " + cpf));

        BeanUtils.copyProperties(form, cliente);
        ClienteDtoResponse response = new ClienteDtoResponse();
        BeanUtils.copyProperties(cliente, response);
        return response;
    }

    @Override
    public void delete(String cpf) {
        Optional<Cliente> byCpf = clienteRepository.findByCpf(cpf);
        if (byCpf.isPresent()){
            clienteRepository.deleteById(byCpf.get().getId());
        } else {
            throw new ResourceNotFoundException("Cliente não encontrado. CPF:" + cpf);
        }
    }
}
