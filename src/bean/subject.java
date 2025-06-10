package bean;

import java.io.Serializable;

public class subject implements Serializable {

	private String cd;
	private String name;
	private School school;

	public String getCd() {
		return cd;
	}
	public void setCd(String cd) {
		this.name = cd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public School getschool() {
		return school;
	}
	public void setschool(School school) {
		this.school = school;
	}

}
