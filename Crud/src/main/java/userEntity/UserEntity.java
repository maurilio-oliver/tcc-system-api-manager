package userEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;
import org.hibernate.validator.constraints.br.CPF;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class UserEntity {
	
	@Id
	@GeneratedValue (strategy = GenerationType.SEQUENCE)
	private BigInteger idUsuario;
	
	@NotBlank(message = "Nome não pode estar vazio, insira um nome.")
	@Size(min = 3, max = 100, message = "O nome precisa ter entre 3 a 100 caracteres")
	@Column(length = 100) //validar no banco
	private String nome;
	
	@Email
	@NotBlank
	private String email;
	
	@CPF
	@NotBlank
	private String cpf;
	
	@Past //data
	@NotNull
	
	private Date dtNascimento;
	private String endereco;
	private String bairro;
	private String  cep;
	
	@Positive
	private int numero;
	
	private String complemento;
	private String referencia;
	
	
	//separar em uma classe posteriormente todos os getters e setters
	public BigInteger getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(BigInteger idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public Date getDtNascimento() {
		return dtNascimento;
	}
	public void setDtNascimento(Date dtNascimento) {
		this.dtNascimento = dtNascimento;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	public String getReferencia() {
		return referencia;
	}
	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	
	
}
