package com.gestaoesportiva.api.controller;

import com.gestaoesportiva.api.domain.jogador.Jogador;
import com.gestaoesportiva.api.domain.jogador.DadosCadastroJogador;
import com.gestaoesportiva.api.domain.jogador.DadosAtualizacaoJogador;
import com.gestaoesportiva.api.domain.jogador.DadosListagemJogador;
import com.gestaoesportiva.api.service.JogadorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Jogadores", description = "Gerenciamento de Jogadores")
public class JogadorController {

    @Autowired
    private JogadorService jogadorService;

    @GetMapping
    @Operation(summary = "Lista todos os Jogadores", description = "Retorna uma página de Jogadores")
    public ResponseEntity<Page<DadosListagemJogador>> listarJogadores(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        var pagina = jogadorService.listar(paginacao);
        return ResponseEntity.ok(pagina);
    }

    @PostMapping
    @Transactional
    @Operation(summary = "Adiciona um novo Jogador", description = "Cadastra um novo jogador na lista")
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
    @Operation(summary = "Atualizar informações do Jogador", description = "Atualiza as informações de um Jogador")
    public ResponseEntity<Jogador> atualizarJogador(@RequestBody DadosAtualizacaoJogador dados) {
        Jogador jogadorAtualizado = jogadorService.atualizar(dados);
        return ResponseEntity.ok(jogadorAtualizado);
    }

    @DeleteMapping("/{id}")
    @Transactional
    @Operation(summary = "Deletar Jogador", description = "Inativar jogador na lista, ativo = false")
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
    @Operation(summary = "Ativar Jogador", description = "Ativar jogador na lista, ativo = true")
    public ResponseEntity<?> ativarJogador(@RequestParam Long id) {
        try {
            jogadorService.ativar(id);
            return ResponseEntity.ok("Jogador " + id + " ativado com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Jogador não encontrado");
        }
    }
}
