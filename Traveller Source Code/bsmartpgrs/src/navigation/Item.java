package navigation;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Item implements Serializable 
{
	private static final long serialVersionUID = 1L;
	
	private String name;
    private String text;
    private String image;
   
    private String[] packages;
    
    public String[] getPackages() {
        return packages;
    }

    public void setPackages(String[] packages) {
        this.packages = packages;
    }

    public boolean include() {
        if (packages == null) {
            return true;
        }

        boolean invert = false;
        boolean match = false;


        for (String packageName : packages) {
            String name = packageName;
            if (name.charAt(0) == '!') {
                invert = true;
                name = name.substring(1);
            }
            
            if (name.equals("jsp")) {
                match = true;
            }
        }
        
        return (!invert && match) || (invert && !match);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
    
    public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	} 
}
