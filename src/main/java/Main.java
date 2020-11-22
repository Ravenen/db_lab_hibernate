import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ua.lviv.iot.hiberlab.model.dataaccess.DataAccess;
import ua.lviv.iot.hiberlab.model.dataaccess.implementation.CountryDataAccess;
import ua.lviv.iot.hiberlab.model.entity.CountryEntity;


public class Main {
//    private static final SessionFactory ourSessionFactory;

//    static {
//        try {
//            Configuration configuration = new Configuration();
//            configuration.configure();
//
//            ourSessionFactory = configuration.buildSessionFactory();
//        } catch (Throwable ex) {
//            throw new ExceptionInInitializerError(ex);
//        }
//    }

//    public static Session getSession() throws HibernateException {
//        return ourSessionFactory.openSession();
//    }

    public static void main(final String[] args) throws Exception {
//        final Session session = getSession();
//        try {
//            System.out.println("querying all the managed entities...");
//            final Metamodel metamodel = session.getSessionFactory().getMetamodel();
//            for (EntityType<?> entityType : metamodel.getEntities()) {
//                final String entityName = entityType.getName();
//                final Query query = session.createQuery("from " + entityName);
//                System.out.println("executing: " + query.getQueryString());
//                for (Object o : query.list()) {
//                    System.out.println("  " + o);
//                }
//            }
//        } finally {
//            session.close();
//        }
//        DataAccess<CountryEntity> countryDao = new CountryDataAccess();
////        CountryEntity country = new CountryEntity();
////        country.setName("Uganda");
//        System.out.println(countryDao.get((long) 1));
////        countryDao.add(country);
        System.out.println("TEST++++++++++++++++++++++++++");
        DataAccess<CountryEntity> dao = new CountryDataAccess();
        CountryEntity countryEntity = new CountryEntity();
        countryEntity.setName("Uganda");
//        dao.add(countryEntity);
        System.out.println(dao.get(11));
    }
}