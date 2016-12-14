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
	@Column(name="DATA_ULT_ALTERACAO")
	private Date dataAlteracao;
	
	@NotBlank
	@Column(name="USUARIO_ULT_ALTERACAO")
	private String usuarioAlteracao;
	
	@Version
	@Column(name="VERSAO")
	@NotNull
	private int versao;

	abstract int getId();
}
