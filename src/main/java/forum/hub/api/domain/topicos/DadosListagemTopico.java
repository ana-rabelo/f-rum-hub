package forum.hub.api.domain.topicos;

public record DadosListagemTopico (
        String mensagem,
        String nomeCurso,
        String titulo
){
    public DadosListagemTopico (Topico topico) {
        this(topico.getMensagem(), topico.getCurso(), topico.getTitulo());
    }
}
