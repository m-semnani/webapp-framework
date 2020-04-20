package nl.linkit.productmngt.model;

import javax.persistence.*;

@Entity
@Table(name = "authority")
public class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer id;
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    @Enumerated(EnumType.STRING)
    public AuthorityType name;
    public AuthorityType getName() { return name; }
    public void setName(AuthorityType name) { this.name = name; }

    public Authority() {}

    public Authority(AuthorityType name) {
        this.name = name;
    }

}
