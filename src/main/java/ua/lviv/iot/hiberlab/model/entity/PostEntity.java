package ua.lviv.iot.hiberlab.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "post", schema = "pavliyk_3")
public class PostEntity {
    private Integer id;
    private String post;

    public PostEntity() {
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "post")
    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PostEntity that = (PostEntity) o;

        if (!id.equals(that.id)) return false;
        return post.equals(that.post);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + post.hashCode();
        return result;
    }
}
