package edu.iit.sat.itmd4515.hwijaya.lab2.db;

import edu.iit.sat.itmd4515.hwijaya.lab2.db.schemas.Actor;
import edu.iit.sat.itmd4515.hwijaya.lab2.db.schemas.Address;
import edu.iit.sat.itmd4515.hwijaya.lab2.db.schemas.Category;
import edu.iit.sat.itmd4515.hwijaya.lab2.db.schemas.City;
import edu.iit.sat.itmd4515.hwijaya.lab2.db.schemas.Country;
import edu.iit.sat.itmd4515.hwijaya.lab2.db.schemas.Customer;
import edu.iit.sat.itmd4515.hwijaya.lab2.db.schemas.Film;
import edu.iit.sat.itmd4515.hwijaya.lab2.db.schemas.FilmActor;
import edu.iit.sat.itmd4515.hwijaya.lab2.db.schemas.FilmCategory;
import edu.iit.sat.itmd4515.hwijaya.lab2.db.schemas.FilmText;
import edu.iit.sat.itmd4515.hwijaya.lab2.db.schemas.Inventory;
import edu.iit.sat.itmd4515.hwijaya.lab2.db.schemas.Language;
import edu.iit.sat.itmd4515.hwijaya.lab2.db.schemas.Payment;
import edu.iit.sat.itmd4515.hwijaya.lab2.db.schemas.Rental;
import edu.iit.sat.itmd4515.hwijaya.lab2.db.schemas.Staff;
import edu.iit.sat.itmd4515.hwijaya.lab2.db.schemas.Store;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Hibernate utility class to create entity manager and validator instances.
 */
public final class Databases {
    private Databases() {}

    private static final Logger LOGGER = LoggerFactory.getLogger(Databases.class);

    private static SessionFactory sessionFactory;
    private static ValidatorFactory validatorFactory;

    /**
     * Returns a session from existing or newly-created factory.
     */
    public static Session openSession() {
        if (sessionFactory != null) {
            return sessionFactory.openSession();
        }
        Configuration configuration =
            new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Actor.class)
                .addAnnotatedClass(Address.class)
                .addAnnotatedClass(Category.class)
                .addAnnotatedClass(City.class)
                .addAnnotatedClass(Country.class)
                .addAnnotatedClass(Customer.class)
                .addAnnotatedClass(Film.class)
                .addAnnotatedClass(FilmActor.class)
                .addAnnotatedClass(FilmCategory.class)
                .addAnnotatedClass(FilmText.class)
                .addAnnotatedClass(Inventory.class)
                .addAnnotatedClass(Language.class)
                .addAnnotatedClass(Payment.class)
                .addAnnotatedClass(Rental.class)
                .addAnnotatedClass(Staff.class)
                .addAnnotatedClass(Store.class);
        StandardServiceRegistryBuilder sb = new StandardServiceRegistryBuilder();
        sb.applySettings(configuration.getProperties());
        StandardServiceRegistry standardServiceRegistry = sb.build();
        sessionFactory = configuration.buildSessionFactory(standardServiceRegistry);
        for (Object a : sessionFactory.getMetamodel().getEntities()) {
            LOGGER.info(a.toString());
        }
        return sessionFactory.openSession();
    }

    /**
     * Returns a validator from existing or newly-created factory.
     */
    public static Validator getValidator() {
        if (validatorFactory != null) {
            return validatorFactory.getValidator();
        }
        validatorFactory = Validation.buildDefaultValidatorFactory();
        return validatorFactory.getValidator();
    }

    /**
     * Release database instances.
     */
    public static void close() {
        if (sessionFactory != null) {
            sessionFactory.close();
            sessionFactory = null;
        }
        if (validatorFactory == null) {
            return;
        }
        validatorFactory.close();
        validatorFactory = null;
    }
}
