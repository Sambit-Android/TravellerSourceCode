package navigation;

import java.io.Serializable;

public class ThirdLevel extends Item implements Serializable 
{
	
	private static final long serialVersionUID = 1L;
	
	private String module;
	private String name;
    private String url;    
    private String role;
    
	public String getModule() {
		return module;
	}
	public void setModule(String module) {
		this.module = module;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
   
    
    
}
