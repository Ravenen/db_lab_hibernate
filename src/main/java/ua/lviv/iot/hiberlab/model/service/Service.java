package ua.lviv.iot.hiberlab.model.service;

import java.util.List;

public interface Service<T> {
  List<T> findAll();

  T findById(Integer id);

  T create(T entity);

  T update(Integer id, T entity);

  T delete(Integer id);
}
