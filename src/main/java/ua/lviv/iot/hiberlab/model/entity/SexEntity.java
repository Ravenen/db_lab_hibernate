package ua.lviv.iot.hiberlab.model.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sex", schema = "pavliyk_3")
public class SexEntity {
  private Integer id;
  private String sex;

  public SexEntity() {
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
  @Column(name = "sex")
  public String getSex() {
    return sex;
  }

  public void setSex(String sex) {
    this.sex = sex;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    SexEntity sexEntity = (SexEntity) o;

    if (!id.equals(sexEntity.id)) {
      return false;
    }
    return sex.equals(sexEntity.sex);
  }

  @Override
  public int hashCode() {
    int result = id.hashCode();
    result = 31 * result + sex.hashCode();
    return result;
  }
}
