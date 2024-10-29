package edu.iit.sat.itmd4515.hanggrian.fp;

import edu.iit.sat.itmd4515.hanggrian.fp.db.Languages;
import edu.iit.sat.itmd4515.hanggrian.fp.db.schemas.Language;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Unit tests in this project requires an {@code English}
 * {@link edu.iit.sat.itmd4515.hanggrian.fp.db.schemas.Language} record. If no such entry exist in
 * the database, this singleton startup executor will eagerly create it for the tests to pass.
 */
@Singleton
@Startup
public class SingletonStartup {
    private static final Logger LOGGER = LoggerFactory.getLogger(SingletonStartup.class);

    @EJB Languages languages;

    @PostConstruct
    public void initialize() {
        LOGGER.info("Initializing startup service...");

        if (!languages.isEmpty()) {
            LOGGER.info("Database established, skipped.");
            return;
        }

        LOGGER.info(
            "Database likely uninitialized! "
                + "Visit MySQL documentation for Sakila schema and data SQL scripts. "
                + "https://dev.mysql.com/doc/index-other.html"
        );

        Language language = new Language();
        language.setName("English");
        languages.insert(language);
    }
}
