package br.com.acrani.cadastroCliente.controllers;

import br.com.acrani.cadastroCliente.dto.ClienteDtoRequest;
import br.com.acrani.cadastroCliente.dto.ClienteDtoResponse;
import br.com.acrani.cadastroCliente.dto.ClienteUpdateForm;
import br.com.acrani.cadastroCliente.services.ClienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping
    public ResponseEntity<List<ClienteDtoResponse>> findAll(){

        List<ClienteDtoResponse> all = clienteService.findAll();
        return ResponseEntity.ok().body(all);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping("/buscacpf")
    public ResponseEntity<ClienteDtoResponse> findByCpf(@RequestParam(value = "cpf", required = true) String cpf){

        ClienteDtoResponse byCpf = clienteService.findByCpf(cpf);
        return ResponseEntity.ok().body(byCpf);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @PostMapping
    public ResponseEntity<ClienteDtoResponse> save(@RequestBody @Valid ClienteDtoRequest request){

        ClienteDtoResponse save = clienteService.save(request);
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/clientes/" + save.getCpf()).toUriString());
        return ResponseEntity.created(uri).body(save);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @PutMapping
    public ResponseEntity<ClienteDtoResponse> update(@RequestParam(value = "cpf", required = true) String cpf,
                                                     @RequestBody @Valid ClienteUpdateForm form){

        ClienteDtoResponse update = clienteService.update(cpf, form);
        return ResponseEntity.ok().body(update);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping
    public ResponseEntity<ClienteDtoResponse> delete(@RequestParam(value = "cpf", required = true) String cpf){
        clienteService.delete(cpf);
        return ResponseEntity.noContent().build();
    }

}
