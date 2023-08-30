package repository;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import userEntity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, BigInteger> {

	public UserEntity findTop1ByCpf(String cpf);
	public UserEntity findToplBEmail(String email);
	
}
//classe para encapsular o comportamento de armazenamento, recuperação e pesquisa que emula uma coleção de objetos.