package ClassObj;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Agent implements Serializable {
	public String name;
	public String username;
	public String password;
	public String host;
	public int port;
	public String path;
	public String role;

	public Agent(String name, String username, String password, String host, int port, String path, String role) {
		this.name = name;
		this.username = username;
		this.password = password;
		this.host = host;
		this.port = port;
		this.path = path;
		this.role = role;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Agent(Agent user) {
		this.name = user.name;
		this.username = user.username;
		this.password = user.password;
		this.host = user.host;
		this.port = user.port;
		this.path = user.path;
		this.role = user.role;
	}

	@Override
	public String toString() {
		return "Agent [username=" + username + "]";
	}
	
}
