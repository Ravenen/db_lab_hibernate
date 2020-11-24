package ua.lviv.iot.hiberlab.model.dataaccess.implementation;

import org.hibernate.Session;
import ua.lviv.iot.hiberlab.model.SessionUtils;
import ua.lviv.iot.hiberlab.model.dataaccess.DataAccess;

import java.util.List;

public abstract class AbstractDataAccess<T> implements DataAccess<T> {

  private final Class<T> entityClass;
  private final SessionUtils sessionUtils;

  public AbstractDataAccess(Class<T> entityClass) {
    this.entityClass = entityClass;
    this.sessionUtils = new SessionUtils();
  }

  @Override
  public void add(T entity) {
    try (sessionUtils) {
      Session currentSession = sessionUtils.openSessionWithTransaction();
      currentSession.saveOrUpdate(entity);
      sessionUtils.commit();
    }
  }

  @Override
  public void delete(Integer id) {
    try (sessionUtils) {
      T entity = get(id);
      Session currentSession = sessionUtils.openSessionWithTransaction();
      currentSession.delete(entity);
      sessionUtils.commit();
    }
  }

  @Override
  public T get(Integer id) {
    T entity;
    try (sessionUtils) {
      entity = sessionUtils.openSession().get(entityClass, id);
    }
    return entity;
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<T> getAll() {
    List<T> entityList;
    try (sessionUtils) {
      Session session = sessionUtils.openSession();
      entityList = session.createQuery("from " + entityClass.getName()).getResultList();
    }
    return entityList;
  }

  @Override
  public void update(T entity) {
    try (sessionUtils) {
      sessionUtils.openSessionWithTransaction().merge(entity);
      sessionUtils.commit();
    }
  }
}
