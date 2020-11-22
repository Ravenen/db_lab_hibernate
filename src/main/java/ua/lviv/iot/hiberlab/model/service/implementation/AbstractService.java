package ua.lviv.iot.hiberlab.model.service.implementation;

import ua.lviv.iot.hiberlab.model.dataaccess.DataAccess;
import ua.lviv.iot.hiberlab.model.service.Service;

import java.util.List;

public class AbstractService<T> implements Service<T> {

    private DataAccess<T> dataAccess;

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
    public T update(T entity) {
        dataAccess.update(entity);
        return entity;
    }

    @Override
    public T delete(Integer id) {
        T entity = findById(id);
        dataAccess.delete(id);
        return entity;
    }
}
