package br.com.retro.locadorajogos.repository;

import br.com.retro.locadorajogos.domain.Jogo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JogoRepository extends JpaRepository<Jogo, Long> {

}
