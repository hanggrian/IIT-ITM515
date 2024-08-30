package edu.iit.sat.itmd4515.uid.uidlab2.db;

import edu.iit.sat.itmd4515.uid.uidlab2.db.schemas.Actor;
import edu.iit.sat.itmd4515.uid.uidlab2.db.schemas.Address;
import edu.iit.sat.itmd4515.uid.uidlab2.db.schemas.Category;
import edu.iit.sat.itmd4515.uid.uidlab2.db.schemas.City;
import edu.iit.sat.itmd4515.uid.uidlab2.db.schemas.Country;
import edu.iit.sat.itmd4515.uid.uidlab2.db.schemas.Customer;
import edu.iit.sat.itmd4515.uid.uidlab2.db.schemas.Film;
import edu.iit.sat.itmd4515.uid.uidlab2.db.schemas.FilmActor;
import edu.iit.sat.itmd4515.uid.uidlab2.db.schemas.FilmCategory;
import edu.iit.sat.itmd4515.uid.uidlab2.db.schemas.FilmText;
import edu.iit.sat.itmd4515.uid.uidlab2.db.schemas.Inventory;
import edu.iit.sat.itmd4515.uid.uidlab2.db.schemas.Language;
import edu.iit.sat.itmd4515.uid.uidlab2.db.schemas.Payment;
import edu.iit.sat.itmd4515.uid.uidlab2.db.schemas.Rental;
import edu.iit.sat.itmd4515.uid.uidlab2.db.schemas.Staff;
import edu.iit.sat.itmd4515.uid.uidlab2.db.schemas.Store;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Hibernate utility class to create session using static factory pattern.
 */
public final class Databases {
    private Databases() {}

    private static final Logger LOGGER = LoggerFactory.getLogger(Databases.class);
    private static final SessionFactory SESSION_FACTORY;

    public static Session open() {
        return SESSION_FACTORY.openSession();
    }

    static {
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
        SESSION_FACTORY = configuration.buildSessionFactory(standardServiceRegistry);
        for (Object a : SESSION_FACTORY.getMetamodel().getEntities()) {
            LOGGER.info(a.toString());
        }
    }
}
