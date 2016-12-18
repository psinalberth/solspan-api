package ps.solspan.util;

public class Path {
	
	private static final String ROOT_API = "/api/";
	
	public static class Usuario {
		
		static final String USUARIO = ROOT_API + "usuarios";
		
		public static final String INDEX = USUARIO + "/";
		public static final String ID = USUARIO + "/:id";
	}
	
	public static class Auth {
		
		public static final String LOGIN  = "/login";
		public static final String LOGOUT = "/logout";
	}
	
	public static class Solicitacao {
		
		static final String SOLICITACAO = ROOT_API + "solicitacoes";
		
		public static final String INDEX = SOLICITACAO + "/";
		public static final String ID = SOLICITACAO + "/:id";
	}
}
