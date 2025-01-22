package forum.hub.api.domain.topicos;

import forum.hub.api.domain.user.User;
import forum.hub.api.domain.user.UserRepository;
import forum.hub.api.infra.security.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopicoService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TopicoRepository topicoRepository;

    public Topico criarTopico(DadosCadastroTopico dados) {
        String username = SecurityUtils.getLoggedInUsername();
        User autor = (User) userRepository.findByLogin(username);
        Topico topico = new Topico(dados, autor);
        return topicoRepository.save(topico);
    }
}