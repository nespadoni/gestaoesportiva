package com.gestaoesportiva.api.application.controller;

import com.gestaoesportiva.api.domain.entity.Time;
import com.gestaoesportiva.api.application.dto.time.DadosCadastroTime;
import com.gestaoesportiva.api.application.dto.time.DadosAtualizacaoTime;
import com.gestaoesportiva.api.application.dto.time.DadosListagemTime;
import com.gestaoesportiva.api.domain.service.TimeService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/times")
public class TimeController {

    @Autowired
    private TimeService timeService;

    @GetMapping
    public ResponseEntity<Page<DadosListagemTime>> listarTimes(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        var pagina = timeService.get(paginacao);
        return ResponseEntity.ok(pagina);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Time> criarTime(@RequestBody DadosCadastroTime dados) {
        try {
            Time novoTime = timeService.create(dados);
            return ResponseEntity.ok(novoTime);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    @Transactional
    public ResponseEntity<Time> atualizarInformacoesTime(@RequestBody DadosAtualizacaoTime dados) {
        Time timeAtualizado = timeService.atualizar(dados);
        return ResponseEntity.ok(timeAtualizado);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> inativarTime(@PathVariable Long id) {
        try {
            timeService.inativar(id);
            return ResponseEntity.ok("Time " + id + " inativado com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Time não encontrado");
        }
    }

    @PutMapping("/ativar")
    @Transactional
    public ResponseEntity<?> ativarTime(@RequestParam Long id) {
        try {
            timeService.ativar(id);
            return ResponseEntity.ok("Time " + id + " ativado com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Time não encontrado");
        }
    }
}
