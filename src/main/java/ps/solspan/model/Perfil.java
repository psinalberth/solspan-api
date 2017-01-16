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
@Table(name="PERFIL")
public class Perfil extends Entidade {

	private static final long serialVersionUID = -4988665697252536062L;
	
	@Expose(serialize=true, deserialize=true)
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID_PERFIL")
	private int id;
	
	@Expose(serialize=true, deserialize=true)
	@NotBlank
	@Column(name="NOME", length=60, nullable=false)
	private String nome;
	
	@Expose(serialize=true, deserialize=true)
	@NotBlank
	@Column(name="DESCRICAO", length=100, nullable=false)
	private String descricao;

	@Override
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
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
