package forum.hub.api.domain.topicos;

import forum.hub.api.domain.user.User;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Table(name = "topicos")
@Entity(name = "Topico")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensagem;
    private LocalDateTime dataCriacao;
    private String curso;
    private Boolean status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autor_id")
    private User autor;

    public Topico(DadosCadastroTopico dados, User autor){
        this.titulo = dados.titulo();
        this.mensagem = dados.mensagem();
        this.dataCriacao = LocalDateTime.now();
        this.curso = dados.nomeCurso();
        this.status = true;
        this.autor = autor;
    }

    public void atualiza(DadosAtualizacaoTopico dados){
        if (dados.titulo() != null) {
            this.titulo = dados.titulo();
        }
        if (dados.mensagem() != null) {
            this.mensagem = dados.mensagem();
        }
    }

    public void deleta(){
        this.status = false;
    }
}
