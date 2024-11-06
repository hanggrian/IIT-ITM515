package edu.iit.sat.itmd4515.hanggrian.fp;

import edu.iit.sat.itmd4515.hanggrian.fp.db.Languages;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;

public class LanguagesTest extends DaoTest {
    private Languages languages;

    @BeforeEach
    public void createDao() {
        languages = new Languages();
        languages.manager = entityManager;
    }

    @Test
    public void validDao() {
        assertThat(languages.selectByName("english").getName())
            .isEqualTo("English");
    }
}
