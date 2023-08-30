package serviceImplements;

import java.math.BigInteger;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import static java.util.Objects.nonNull;
import repository.UserRepository;
import service.UserService;
import userEntity.UserEntity;

@Service
public class UserServiceImplements implements UserService {

	@Autowired
	UserRepository userRepository;

	//criar a expection personalizada e verificar o uso do nonNull
	@Override
	public UserEntity criar(UserEntity userEntity) throws Exception {

		try {
			if ( nonNull(userRepository.findTop1ByCpf(userEntity.getCpf()))) {
				throw new Exception ("O CPF inserido já pertence a outro usuário");
			}
			
			if ( nonNull (userRepository.findToplBEmail(userEntity.getEmail()))) {
				throw new Exception ("O Email inserido já pertence a outro usuário");
			}
			
			return userRepository.save(userEntity);
			
		}catch (Exception e) {
			throw new Exception (e.getMessage());
			
		}
		
	}
	@Override
	public void delete(BigInteger idUser) throws Exception {
		this.buscarPorId(idUser);
		this.userRepository.deleteById(idUser);
	}
	
	@Override
	@Transactional
	public UserEntity editar(UserEntity userEntity) {
		return this.userRepository.save(userEntity);
	}
	
	@Override
	public UserEntity buscarPorId(BigInteger idUser) throws Exception{
		Optional
			.ofNullable(idUser)//ArgumentNotValidException
			.orElseThrow(() -> new Exception("ID não pode ser vazio ou 0"));
		
		return this.userRepository.findById(idUser)
			.orElseThrow(() -> new Exception("Usuário não encontrado"));
	}
}
