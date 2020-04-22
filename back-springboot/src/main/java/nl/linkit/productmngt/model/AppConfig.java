package nl.linkit.productmngt.model;

import javax.persistence.*;

@Entity
@Table(name = "app_config")
public class AppConfig {

	public enum Config {
		LACK, EXCESS
	}

	private long id;
	private String name;
	private String value;

	public AppConfig() {}

	public AppConfig(String name, String value) {
		this.name = name;
		this.value = value;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	@Column(name = "name", nullable = false)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "value", nullable = false)
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value =  value;
	}

}
