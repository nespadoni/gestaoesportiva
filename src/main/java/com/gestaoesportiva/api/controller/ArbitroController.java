package com.gestaoesportiva.api.controller;

import com.gestaoesportiva.api.domain.arbitro.Arbitro;
import com.gestaoesportiva.api.domain.arbitro.DadosAtualizacaoArbitro;
import com.gestaoesportiva.api.domain.arbitro.DadosCadastroArbitro;
import com.gestaoesportiva.api.domain.arbitro.DadosListagemArbitro;
import com.gestaoesportiva.api.service.ArbitroService;
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
@RequestMapping("/arbitros")
@Tag(name = "Árbitros", description = "Gerenciamento de Árbitros")
public class ArbitroController {

    @Autowired
    private ArbitroService arbitroService;

    @GetMapping
    @Operation(summary = "Lista todos os Árbitros", description = "Retorna uma página com todos os árbitros")
    public ResponseEntity<Page<DadosListagemArbitro>> listarArbitros(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        var pagina = arbitroService.listar(paginacao);
        return ResponseEntity.ok(pagina);
    }

    @PostMapping
    @Transactional
    @Operation(summary = "Adiciona um novo Árbitro", description = "Cadastra um novo Árbitro na lista")
    public ResponseEntity<Arbitro> criarArbitro(@RequestBody DadosCadastroArbitro dados) {
        try {
            Arbitro novoArbitro = arbitroService.cadastrar(dados);
            return ResponseEntity.ok(novoArbitro);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    @Transactional
    @Operation(summary = "Atualizar informações do Árbitro", description = "Atualiza as informações de um Árbitro")
    public ResponseEntity<Arbitro> atualizarInformacoesArbitro(@RequestBody DadosAtualizacaoArbitro dados) {
        Arbitro novoArbitro = arbitroService.atualizar(dados);
        return ResponseEntity.ok(novoArbitro);
    }

    @DeleteMapping("/{id}")
    @Transactional
    @Operation(summary = "Deletar Árbitro", description = "Retorna 'ativo' = false, tornando um árbitro inativo")
    public ResponseEntity<?> inativarArbitro(@PathVariable Long id) {
        try {
            arbitroService.inativar(id);
            return ResponseEntity.ok("Árbitro " + id + " inativado com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Árbitro não encontrado");
        }
    }

    @PutMapping("/ativar")
    @Transactional
    @Operation(summary = "Ativar Árbitro", description = "Retorna 'ativo' = true, tornando um árbitro ativo novamente")
    public ResponseEntity<?> ativarArbitro(@RequestParam Long id) {
        try {
            arbitroService.ativar(id);
            return ResponseEntity.ok("Árbitro " + id + " ativado com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Árbitro não encontrado");
        }
    }
}
