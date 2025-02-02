package com.gestaoesportiva.api.controller;

import com.gestaoesportiva.api.domain.federacao.Federacao;
import com.gestaoesportiva.api.domain.federacao.DadosAtualizacaoFederacao;
import com.gestaoesportiva.api.domain.federacao.DadosCadastroFederacao;
import com.gestaoesportiva.api.domain.federacao.DadosListagemFederacao;
import com.gestaoesportiva.api.service.FederacaoService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/federacoes")
public class FederacaoController {

    @Autowired
    private FederacaoService federacaoService;

    @GetMapping
    public ResponseEntity<Page<DadosListagemFederacao>> listarUsuario(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        var pagina = federacaoService.listarFederacao(paginacao);
        return ResponseEntity.ok(pagina);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Federacao> criarFederacao(@RequestBody DadosCadastroFederacao dados) {
        try {
            Federacao novaFederacao = federacaoService.criarFederacao(dados);
            return ResponseEntity.ok(novaFederacao);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    @Transactional
    public ResponseEntity<Federacao> atualizarInformacoesFederacao(@RequestBody DadosAtualizacaoFederacao dados) {
        Federacao novaFederacao = federacaoService.atualizarFederacao(dados);
        return ResponseEntity.ok(novaFederacao);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> inativarFederacao(Long id) {
        try {
            federacaoService.inativarFederacao(id);
            return ResponseEntity.ok("Federação" + id + "inativada com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
        }
    }

    @PutMapping("/ativar")
    @Transactional
    public ResponseEntity<?> ativarFederacao(Long id) {
        try {
            federacaoService.ativarFederacao(id);
            return ResponseEntity.ok("Federação" + id + "ativado com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
        }
    }
}
