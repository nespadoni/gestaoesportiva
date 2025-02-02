package com.gestaoesportiva.api.controller;

import com.gestaoesportiva.api.domain.jogador.Jogador;
import com.gestaoesportiva.api.domain.jogador.DadosCadastroJogador;
import com.gestaoesportiva.api.domain.jogador.DadosAtualizacaoJogador;
import com.gestaoesportiva.api.domain.jogador.DadosListagemJogador;
import com.gestaoesportiva.api.service.JogadorService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/jogadores")
public class JogadorController {

    @Autowired
    private JogadorService jogadorService;

    @GetMapping
    public ResponseEntity<Page<DadosListagemJogador>> listarJogadores(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        var pagina = jogadorService.listar(paginacao);
        return ResponseEntity.ok(pagina);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Jogador> criarJogador(@RequestBody DadosCadastroJogador dados) {
        try {
            Jogador novoJogador = jogadorService.criarJogador(dados);
            return ResponseEntity.status(HttpStatus.CREATED).body(novoJogador);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    @Transactional
    public ResponseEntity<Jogador> atualizarInformacoesJogador(@RequestBody DadosAtualizacaoJogador dados) {
        Jogador jogadorAtualizado = jogadorService.atualizar(dados);
        return ResponseEntity.ok(jogadorAtualizado);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> inativarJogador(@PathVariable Long id) {
        try {
            jogadorService.inativar(id);
            return ResponseEntity.ok("Jogador " + id + " inativado com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Jogador não encontrado");
        }
    }

    @PutMapping("/ativar")
    @Transactional
    public ResponseEntity<?> ativarJogador(@RequestParam Long id) {
        try {
            jogadorService.ativar(id);
            return ResponseEntity.ok("Jogador " + id + " ativado com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Jogador não encontrado");
        }
    }
}
