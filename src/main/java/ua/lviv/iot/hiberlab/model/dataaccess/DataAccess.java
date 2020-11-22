package ua.lviv.iot.hiberlab.model.dataaccess;

public interface DataAccess<T> {
    void add(T entity);

    void delete(Integer id);

    T get(Integer id);

    void update(T entity);
}
