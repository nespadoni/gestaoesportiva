package com.gestaoesportiva.api.application.controller;

import com.gestaoesportiva.api.application.dto.jogo.DadosAtualizacaoJogo;
import com.gestaoesportiva.api.application.dto.jogo.DadosCadastroJogo;
import com.gestaoesportiva.api.application.dto.jogo.DadosListagemJogo;
import com.gestaoesportiva.api.domain.entity.Jogo;
import com.gestaoesportiva.api.domain.service.JogoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/jogos")
@Tag(name = "Jogos", description = "Gerenciamento de Jogos")
@RequiredArgsConstructor
public class JogoController {

    private final JogoService jogoService;

    @GetMapping
    @Operation(summary = "Lista todos os Jogos", description = "Retorna uma página com todos os jogos")
    public ResponseEntity<Page<DadosListagemJogo>> listarJogos(@PageableDefault(size = 10, sort = {"data"}) Pageable paginacao) {
        var pagina = jogoService.listarJogos(paginacao);
        return ResponseEntity.ok(pagina);
    }

    @PostMapping
    @Operation(summary = "Cria um novo Jogo", description = "Cadastra um novo jogo com os dados fornecidos")
    public ResponseEntity<Jogo> criarJogo(@RequestBody DadosCadastroJogo dados) {
        try {
            Jogo novoJogo = jogoService.criarJogo(dados);
            return ResponseEntity.ok(novoJogo);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza um Jogo existente", description = "Atualiza os dados de um jogo por ID")
    public ResponseEntity<Jogo> atualizarJogo(@RequestBody DadosAtualizacaoJogo dadosAtualizados) {
        Jogo jogo = jogoService.atualizarJogo(dadosAtualizados);
        return ResponseEntity.ok(jogo);
    }

    @DeleteMapping("/deletar/{id}")
    @Operation(summary = "Deleta um Jogo", description = "Remove um jogo existente por ID")
    public ResponseEntity<?> deletarJogo(@PathVariable Long id) {
        try {
            jogoService.inativar(id);
            return ResponseEntity.ok("Jogo " + id + " deletado com sucesso");
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/finalizar/{id}")
    @Operation(summary = "Finaliza um Jogo", description = "Finaliza um jogo existente por ID")
    public ResponseEntity<?> finalizarJogo(@PathVariable Long id) {
        try {
            jogoService.finalizar(id);
            return ResponseEntity.ok("Jogo " + id + " finalizado com sucesso");
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/ativar/{id}")
    @Operation(summary = "Ativa um Jogo", description = "Ativa um jogo existente por ID")
    public ResponseEntity<?> ativarJogo(@PathVariable Long id) {
        try {
            jogoService.ativar(id);
            return ResponseEntity.ok("Campeonato " + id + " ativado com sucesso");
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    
    
}
