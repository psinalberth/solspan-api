package ps.solspan.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@MappedSuperclass
public abstract class Entidade implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@NotBlank
	@Column(name="DATA_ULT_ALTERACAO", nullable=false)
	private Date dataAlteracao;
	
	@NotBlank
	@Column(name="USUARIO_ULT_ALTERACAO", length=20, nullable=false)
	private String usuarioAlteracao;
	
	@Version
	@Column(name="VERSAO", columnDefinition="TINYINT(3)", nullable=false)
	@NotNull
	private int versao;

	abstract int getId();

	public Date getDataAlteracao() {
		return dataAlteracao;
	}

	public void setDataAlteracao(Date dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}

	public String getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public void setUsuarioAlteracao(String usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}

	public int getVersao() {
		return versao;
	}

	public void setVersao(int versao) {
		this.versao = versao;
	}
}
