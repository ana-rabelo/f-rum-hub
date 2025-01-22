package forum.hub.api.controller;

import forum.hub.api.domain.topicos.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;
    @Autowired
    private TopicoService topicoService;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhesTopico> register(@RequestBody @Valid DadosCadastroTopico dados, UriComponentsBuilder uriBuilder){
        var topico = topicoService.criarTopico(dados);

        var uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhesTopico(topico));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemTopico>> list(@PageableDefault(size = 10, sort = {"mensagem"}) Pageable pagination){
        var page = topicoRepository.findAllByStatusTrue(pagination)
                .map(DadosListagemTopico::new);

        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhesTopico> detail(@PathVariable Long id) {
        var topico = topicoRepository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhesTopico(topico));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DadosDetalhesTopico> update(@PathVariable Long id, @RequestBody @Valid DadosAtualizacaoTopico dados){
        var topico = topicoRepository.getReferenceById(id);
        topico.atualiza(dados);

        return ResponseEntity.ok(new DadosDetalhesTopico(topico));
   }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable Long id){
        var topico = topicoRepository.getReferenceById(id);
        topico.deleta();

        return ResponseEntity.noContent().build();
    }
}
