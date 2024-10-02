package br.com.crud.crudpessoa.controller;

import br.com.crud.crudpessoa.dto.PessoaDTO;
import br.com.crud.crudpessoa.model.Pessoa;
import br.com.crud.crudpessoa.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    @PostMapping("/pessoas")
    public ResponseEntity<Pessoa> criarPessoa(@RequestBody PessoaDTO pessoaDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pessoaService.registrarPessoa(pessoaDTO));
    }

    @GetMapping("/pessoas/{id}")
    public ResponseEntity<Object> buscarPessoaporId(@PathVariable Integer id) {
        Pessoa pessoaEncontrada = pessoaService.buscarPessoaPorId(id);

        if (Objects.isNull((pessoaEncontrada))) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pessoa não encontrada");
        }

        return ResponseEntity.status(HttpStatus.OK).body(pessoaEncontrada);
    }

    @GetMapping("/pessoas")
    public ResponseEntity<List<Pessoa>> listarPessoas() {
        return ResponseEntity.status(HttpStatus.OK).body(pessoaService.listarPessoas());
    }

    @DeleteMapping("/pessoas/{id}")
    public ResponseEntity<String> deletarPessoa(@PathVariable Integer id) {
        String resposta = pessoaService.deletarPessoaPorId(id);

        if (Objects.isNull(resposta)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pessoa não encontrada");
        }

        return ResponseEntity.status(HttpStatus.OK).body(resposta);
    }

    @PutMapping("/pessoas/{id}")
    public ResponseEntity<Object> atualizarRegistroPessoa(@PathVariable Integer id, @RequestBody PessoaDTO pessoaDTO) {
        Pessoa pessoa = pessoaService.atualizarPessoaRegistro(id, pessoaDTO);

        if (Objects.isNull(pessoa)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pessoa não encontrada");
        }

        return ResponseEntity.status(HttpStatus.OK).body(pessoa);
    }
}
