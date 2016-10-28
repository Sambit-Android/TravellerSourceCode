package navigation;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Widget extends Item implements Serializable  {
	
	private static final long serialVersionUID = 1L;
	
	private Example[] items;

	public Example[] getItems() {
        return items;
    }

    public void setItems(Example[] items) {
        this.items = items;
    }
}
