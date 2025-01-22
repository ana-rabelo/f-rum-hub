package forum.hub.api.domain.topicos;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroTopico(
        @NotBlank(message = "{mensagem.obrigatoria}")
        String mensagem,

        @NotBlank(message = "{curso.obrigatorio}")
        String nomeCurso,

        @NotBlank(message = "{titulo.obrigatorio}")
        String titulo
) {
}
