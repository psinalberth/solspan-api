package ps.solspan.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name="USUARIO")
public class Usuario extends Entidade {
	
	private static final long serialVersionUID = 5224424896332715491L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID_USUARIO")
	private int id;
	
	@NotBlank
	@Column(name="NOME")
	private String nome;
	
	@NotBlank
	@Column(name="LOGIN")
	private String login;
	
	@NotBlank
	@Column(name="PASSWORD")
	private String password;

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
