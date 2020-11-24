package ua.lviv.iot.hiberlab.model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import ua.lviv.iot.hiberlab.model.entity.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class SessionUtils implements AutoCloseable {

  private Session session;
  private Transaction transaction;

  public SessionUtils() {
    session = null;
    transaction = null;
  }

  public Session openSession() {
    session = this.getSession().openSession();
    return session;
  }

  public Session openSessionWithTransaction() {
    session = this.getSession().openSession();
    transaction = session.beginTransaction();
    return session;
  }

  public boolean commit() {
    if (transaction != null) {
      transaction.commit();
      transaction = null;
      return true;
    }
    return false;
  }

  private SessionFactory getSession() {
    Properties properties = new Properties();
    try (InputStream input = SessionUtils.class.getClassLoader().getResourceAsStream("config.properties")) {
      properties.load(input);
    } catch (IOException e) {
      e.printStackTrace();
    }
    Configuration configuration = new Configuration().configure();
    configuration.mergeProperties(properties);
    configuration.addAnnotatedClass(AddressEntity.class);
    configuration.addAnnotatedClass(CityEntity.class);
    configuration.addAnnotatedClass(CountryEntity.class);
    configuration.addAnnotatedClass(ManufacturerEntity.class);
    configuration.addAnnotatedClass(PostEntity.class);
    configuration.addAnnotatedClass(RegionEntity.class);
    configuration.addAnnotatedClass(ServiceTypeEntity.class);
    configuration.addAnnotatedClass(ServiceEntity.class);
    configuration.addAnnotatedClass(SexEntity.class);
    configuration.addAnnotatedClass(TerminalEntity.class);
    configuration.addAnnotatedClass(TerminalTypeEntity.class);
    configuration.addAnnotatedClass(WorkmanEntity.class);
    StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
            .applySettings(configuration.getProperties());
    return configuration.buildSessionFactory(builder.build());
  }

  @Override
  public void close() {
    try {
      if (transaction != null) {
        transaction.rollback();
      }
      if (session != null) {
        session.close();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
