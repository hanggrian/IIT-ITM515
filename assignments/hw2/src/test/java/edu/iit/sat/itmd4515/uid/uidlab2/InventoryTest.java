package edu.iit.sat.itmd4515.uid.uidlab2;

import edu.iit.sat.itmd4515.uid.uidlab2.db.Inventories;
import edu.iit.sat.itmd4515.uid.uidlab2.db.schemas.Film;
import edu.iit.sat.itmd4515.uid.uidlab2.db.schemas.Inventory;
import edu.iit.sat.itmd4515.uid.uidlab2.db.schemas.Rental;
import edu.iit.sat.itmd4515.uid.uidlab2.db.schemas.Store;
import jakarta.validation.ConstraintViolation;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class InventoryTest extends DatabaseTest {
    @Test
    public void validPojo() {
        Inventory inventory = new Inventory();

        assertThat(validator.validate(inventory).stream().map(ConstraintViolation::getMessage))
            .containsExactly(
                "must not be null",
                "must not be null");
    }

    @Test
    public void invalidPojo() {
        Inventory inventory = new Inventory();
        inventory.setFilm(new Film());
        inventory.setStore(new Store());

        assertThat(validator.validate(inventory)).isEmpty();
    }

    @Test
    public void validDao() {
        Rental rental =
            session
                .createQuery("FROM Rental WHERE id= 1", Rental.class)
                .getSingleResult();
        Inventory inventory =
            Inventories.selectOneByFilm(
                session,
                rental.getStaff().getStore(),
                rental.getInventory().getFilm());
        assertEquals(rental.getInventory(), inventory);
    }
}
