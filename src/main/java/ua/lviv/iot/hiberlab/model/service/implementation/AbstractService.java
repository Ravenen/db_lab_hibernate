package ua.lviv.iot.hiberlab.model.service.implementation;

import ua.lviv.iot.hiberlab.model.dataaccess.DataAccess;
import ua.lviv.iot.hiberlab.model.service.Service;

import java.util.List;

public abstract class AbstractService<T> implements Service<T> {

    private final DataAccess<T> dataAccess;

    public AbstractService(DataAccess<T> dataAccess) {
        this.dataAccess = dataAccess;
    }

    @Override
    public List<T> findAll() {
        return dataAccess.getAll();
    }

    @Override
    public T findById(Integer id) {
        return dataAccess.get(id);
    }

    @Override
    public T create(T entity) {
        dataAccess.add(entity);
        return entity;
    }

    @Override
    public T update(Integer id, T entity) {
        T oldEntity = findById(id);
        dataAccess.update(entity);
        return oldEntity;
    }

    @Override
    public T delete(Integer id) {
        T entity = findById(id);
        dataAccess.delete(id);
        return entity;
    }
}
