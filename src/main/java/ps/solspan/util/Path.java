package ps.solspan.util;

public class Path {
	
	private static final String ROOT_API = "/api/";
	
	public static final String LOGIN  = "/login";
	public static final String LOGOUT = "/logout";
	
	static final String USUARIO = ROOT_API + "usuarios";
	
	public static final String USUARIO_INDEX = USUARIO + "/";
	public static final String USUARIO_ID = USUARIO + "/:id";
	
	static final String SOLICITACAO = ROOT_API + "solicitacoes";
	
	public static final String SOLICITACAO_INDEX = SOLICITACAO + "/";
	public static final String SOLICITACAO_ID = SOLICITACAO + "/:id";
}
