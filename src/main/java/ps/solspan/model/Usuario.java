package ps.solspan.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;

import com.google.gson.annotations.Expose;

@Entity
@Table(name="USUARIO")
public class Usuario extends Entidade {
	
	private static final long serialVersionUID = 5224424896332715491L;
	
	@Expose(serialize=false, deserialize=true)
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID_USUARIO")
	private int id;
	
	@Expose(serialize=true, deserialize=true)
	@NotBlank
	@Column(name="NOME", length=60, nullable=false)
	private String nome;
	
	@Expose(serialize=true, deserialize=true)
	@NotBlank
	@Column(name="LOGIN", length=20, nullable=false, unique=true)
	private String login;
	
	@Expose(serialize=false, deserialize=true)
	@NotBlank
	@Column(name="SENHA", length=128, nullable=false)
	private String senha;
	
	@Expose(serialize=false, deserialize=true)
	@NotBlank
	@Column(name="SALT", length=64, nullable=false)
	private String salt;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public String getSalt() {
		return salt;
	}
	
	public void setSalt(String salt) {
		this.salt = salt;
	}
}
