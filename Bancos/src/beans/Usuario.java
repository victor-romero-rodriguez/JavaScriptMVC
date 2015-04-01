package beans;

public class Usuario {
	private String login;
	private String pwd;
	
	public Usuario() {}
			
	public Usuario(String login, String pwd) {		
		this.login = login;
		this.pwd = pwd;		
	}

	public String getLogin() { return login; }

	public String getPwd() { return pwd; }
	
	public void setLogin(String login) { 
		this.login = login; 
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((pwd == null) ? 0 : pwd.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (pwd == null) {
			if (other.pwd != null)
				return false;
		} else if (!pwd.equals(other.pwd))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Usuario [login=" + login + ", pwd=" + pwd + "]";
	}
	
}
