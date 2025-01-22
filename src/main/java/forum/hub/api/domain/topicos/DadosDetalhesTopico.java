package forum.hub.api.domain.topicos;

import java.time.LocalDateTime;

public record DadosDetalhesTopico (
        Long id,
        String titulo,
        String mensagem,
        LocalDateTime dataCriacao,
        String nomeAutor,
        String nomeCurso
) {
    public DadosDetalhesTopico (Topico topico) {
        this(topico.getId(),
                topico.getTitulo(),
                topico.getMensagem(),
                topico.getDataCriacao(),
                topico.getAutor().getUsername(),
                topico.getCurso());
    }
}
