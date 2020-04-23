package nl.linkit.productmngt.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;

@Entity
@Table(name = "product")
public class Product {

	private long id;
	private String name;
	private int quantity;
	private AppUser appUser;
	@Transient
	@JsonInclude()
	private long ownerId;
	@Transient
	@JsonInclude()
	private String ownerName;

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

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "user_id", nullable = false)
	@JsonIgnore
	public AppUser getAppUser() { return appUser; }
	public void setAppUser(AppUser appUser) { this.appUser = appUser; }

	@Transient
	@JsonInclude()
	public long getOwnerId() {
		return ownerId;
	}
	@Transient
	@JsonInclude()
	public void setOwnerId(long ownerId) {
		this.ownerId = ownerId;
	}
	@Transient
	@JsonInclude()
	public String getOwnerName() {
		return ownerName;
	}
	@Transient
	@JsonInclude()
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", quantity=" + quantity + "]";
	}
	
}
