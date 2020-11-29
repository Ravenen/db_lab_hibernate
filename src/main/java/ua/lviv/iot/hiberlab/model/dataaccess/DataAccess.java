package ua.lviv.iot.hiberlab.model.dataaccess;

import java.util.List;

public interface DataAccess<T> {
  void add(T entity);

  void delete(Integer id);

  T get(Integer id);

  List<T> getAll();

  void update(T entity);
}
