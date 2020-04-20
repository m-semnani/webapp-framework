package nl.linkit.productmngt.model;

import javax.persistence.*;

@Entity
@Table(name = "product")
public class Product {

	private long id;
	private String name;
	private int quantity;

	public Product() {}

	public Product(String name, int quantity) {
		this.name = name;
		this.quantity = quantity;
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

	@Column(name = "quantity", nullable = false)
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", quantity=" + quantity + "]";
	}
	
}
