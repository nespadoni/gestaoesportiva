package com.gestaoesportiva.api.application.controller;

import com.gestaoesportiva.api.domain.entity.Campeonato;
import com.gestaoesportiva.api.application.dto.campeonato.DadosAtualizacaoCampeonato;
import com.gestaoesportiva.api.application.dto.campeonato.DadosCadastroCampeonato;
import com.gestaoesportiva.api.application.dto.campeonato.DadosListagemCampeonato;
import com.gestaoesportiva.api.domain.service.CampeonatoService;
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
@RequestMapping("/campeonatos")
@Tag(name = "Campeonatos", description = "Gerenciamento de Campeonatos")
public class CampeonatoController {

    @Autowired
    private CampeonatoService campeonatoService;

    @GetMapping
    @Operation(summary = "Lista todos os Campeonatos", description = "Retorna uma página com todos os campeonatos")
    public ResponseEntity<Page<DadosListagemCampeonato>> listarCampeonatos(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        var pagina = campeonatoService.listar(paginacao);
        return ResponseEntity.ok(pagina);
    }

    @PostMapping
    @Transactional
    @Operation(summary = "Adiciona um novo Campeonato", description = "Cadastra um novo Campeonato na lista")
    public ResponseEntity<Campeonato> criarCampeonato(@RequestBody DadosCadastroCampeonato dados) {
        try {
            Campeonato novoCampeonato = campeonatoService.cadastrar(dados);
            return ResponseEntity.ok(novoCampeonato);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    @Transactional
    @Operation(summary = "Atualizar informações do Campeonato", description = "Atualiza as informações de um Campeonato")
    public ResponseEntity<Campeonato> atualizarInformacoesCampeonato(@RequestBody DadosAtualizacaoCampeonato dados) {
        Campeonato novoCampeonato = campeonatoService.atualizar(dados);
        return ResponseEntity.ok(novoCampeonato);
    }

    @DeleteMapping("/{id}")
    @Transactional
    @Operation(summary = "Deletar Campeonato", description = "Retorna 'ativo' = false, tornando um campeonato inativo")
    public ResponseEntity<?> inativarCampeonato(@PathVariable Long id) {
        try {
            campeonatoService.inativar(id);
            return ResponseEntity.ok("Campeonato " + id + " inativado com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Campeonato não encontrado");
        }
    }

    @PutMapping("/ativar")
    @Transactional
    @Operation(summary = "Ativar Campeonato", description = "Retorna 'ativo' = true, tornando um campeonato ativo novamente")
    public ResponseEntity<?> ativarCampeonato(@RequestParam Long id) {
        try {
            campeonatoService.ativar(id);
            return ResponseEntity.ok("Campeonato " + id + " ativado com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Campeonato não encontrado");
        }
    }
}
