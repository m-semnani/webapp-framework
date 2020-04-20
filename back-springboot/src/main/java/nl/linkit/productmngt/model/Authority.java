package nl.linkit.productmngt.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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

//    @ManyToMany(mappedBy = "authorities")
//    public Set<AppUser> posts = new HashSet<>();
//    public Set<AppUser> getPosts() { return posts; }
//    public void setPosts(Set<AppUser> posts) { this.posts = posts; }

    public Authority() {}

    public Authority(AuthorityType name) {
        this.name = name;
    }

}
