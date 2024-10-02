package br.com.crud.crudpessoa.service;

import br.com.crud.crudpessoa.dto.PessoaDTO;
import br.com.crud.crudpessoa.model.Pessoa;
import br.com.crud.crudpessoa.repository.PessoaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class PessoaService {

    @Autowired
    PessoaRepository pessoaRepository;

    public Pessoa registrarPessoa(PessoaDTO pessoaDTO) {
        Pessoa pessoa = new Pessoa();

        BeanUtils.copyProperties(pessoaDTO, pessoa);

        return pessoaRepository.save(pessoa);
    }

    public List<Pessoa> listarPessoas() {
        return pessoaRepository.findAll();
    }

    public Pessoa buscarPessoaPorId(Integer id) {
        return pessoaRepository.findById(id).orElse(null);
    }

    public String deletarPessoaPorId(Integer id) {
        Pessoa pessoaEncontrada = this.buscarPessoaPorId(id);

        if (Objects.isNull(pessoaEncontrada)) {
            return null;
        }

        pessoaRepository.delete(pessoaEncontrada);
        return "Pessoa deletada com sucesso";
    }

    public Pessoa atualizarPessoaRegistro(Integer id, PessoaDTO pessoaDTO) {
        Pessoa pessoaEncontrada = this.buscarPessoaPorId(id);

        if (Objects.isNull(pessoaEncontrada)) {
            return null;
        }

        Pessoa pessoa = pessoaEncontrada;

        BeanUtils.copyProperties(pessoaDTO, pessoa);

        return pessoaRepository.save(pessoa);
    }
}
