package edu.iit.sat.itmd4515.uid.uidlab3;

import edu.iit.sat.itmd4515.uid.uidlab3.db.Languages;
import edu.iit.sat.itmd4515.uid.uidlab3.db.schemas.Language;
import jakarta.validation.ConstraintViolation;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;

public class LanguageTest extends DatabaseTest {
    @Test
    public void invalidPojo() {
        Language language = new Language();
        language.setName(stringSized(21));

        assertThat(validator.validate(language).stream().map(ConstraintViolation::getMessage))
            .containsExactly(
                "must not be null",
                "size must be between 0 and 20"
            );
    }

    @Test
    public void validPojo() {
        Language language = new Language();
        language.setLanguageId((byte) 0);
        language.setName("");

        assertThat(validator.validate(language)).isEmpty();
    }

    @Test
    public void validDao() {
        assertThat(Languages.selectOneByName(entityManager, "english").getName())
            .isEqualTo("English");
    }
}
