package service;

import java.math.BigInteger;

import org.springframework.stereotype.Service;

import userEntity.UserEntity;

@Service
public interface UserService {

	public UserEntity criar (UserEntity userEntity) throws Exception;
	public void delete(BigInteger idUser) throws Exception;
	public UserEntity editar(UserEntity userEntity);
	public UserEntity buscarPorId (BigInteger idUser) throws Exception;
}
