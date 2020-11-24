package ua.lviv.iot.hiberlab.controller;

import java.util.List;

public interface Controller<T> {
    List<T> findAll();

    T findById(Integer id);

    T create(T entity);

    T update(Integer id, T entity);

    T delete(Integer id);
}
