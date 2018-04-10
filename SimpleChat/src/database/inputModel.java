package database;

public class inputModel {
	private String name;
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private String text;
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	private String date;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public  inputModel() {
		name = "";
		text = "";
		date="";
	}



}
