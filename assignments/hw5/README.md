# [Lab 5](https://github.com/hanggrian/IIT-ITM515/blob/assets/assignments/hw5.pdf): JPA Relationships

This project is a continuation of **Lab 4** considering that it expands on the
existing CTA database accessed via `EntityManager` and JPA connection.
Additionally, the main JavaFX application is also an improved version instead of
a rewrite.

- Introduces public transportation aspect of CTA business domain with new
  entities **Train**, **Car** and **Train Car**.
- `OneToOne` and `ManyToOne` relationships between entities as illustrated in
  the UML diagram below.
- Consistent testing of POJOs and DAOs using *Jupiter* unit tests. However, not
  all entity columns are being utilized in the main application.

## Discussion

> You must have at least four total entities in your **business domain**. You
  may have as many as you want, but at least four.
>
>
> > Remember, your final project must support the logical concept of multiple
    types of users or roles, but _**do not**_ create these as entities. We must
    consider this now so we can introduce security in later projects, and we
    will use the JEE security framework to introduce these security entities
    later. Likewise, do not create Admin as an entity. **Stay focused on
    entities within your domain.**

The new CTA schema consists of 6 entities:

- **Track** &mdash; A railcar route often characterized by its line color.
- **Station** &mdash; A pickup location for passengers along a track.
- **Track Station** &mdash; A junction table between *track* and *station*,
  representing a station that can belong in more than one track.
- **Train** &mdash; A locomotive vehicle pulling cars.
- **Car** &mdash; A carriage or wagon carrying passengers.
- **Train Car** &mdash; A junction table between *train* and *car*, representing
  a trainset containing multiple cars but only one locomotive.

In the user management aspect, there are planned entities for **Passenger** and
**Conductor** or **Staff**. Alternatively, the user and admin roles can be
combined into a single entity with a `role` or `is_staff` column.

## Building

Just like the previous lab, executing the program requires the MySQL server to
be running and the CTA database [schema](https://github.com/hanggrian/IIT-ITM515/blob/main/assignments/hw5/cta_schema.sql)
and [data](https://github.com/hanggrian/IIT-ITM515/blob/main/assignments/hw5/cta_data.sql)
to be imported. Afterwards, the program can be executed using the following
command:

```sh
./gradlew run
```

## Diagram

<img width="480" src="https://github.com/hanggrian/IIT-ITM515/raw/assets/assignments/hw5/diagram1.svg"><br><small>Diagram 1 &mdash; CTA station & train diagram</small>

## Screenshots

### Interface

<img width="480" src="https://github.com/hanggrian/IIT-ITM515/raw/assets/assignments/hw5/screenshot1_1.png"><br><small>Screenshot 1.1 &mdash; Main interface</small>

<img width="640" src="https://github.com/hanggrian/IIT-ITM515/raw/assets/assignments/hw5/screenshot1_2.png"><br><small>Screenshot 1.2 &mdash; Expanded state</small>

### CRUD Operations

<img width="320" src="https://github.com/hanggrian/IIT-ITM515/raw/assets/assignments/hw5/screenshot2_1.png"><br><small>Screenshot 2.1 &mdash; Add to Track</small>

CRUD operations to register a station to a track has been improved to filter out
stations that are already assigned to a track. In this example, **Green** and
**Blue** lines are excluded from the dialog list because they are existing
tracks of **Clark/Lake** station.

<img width="320" src="https://github.com/hanggrian/IIT-ITM515/raw/assets/assignments/hw5/screenshot2_2.png"><br><small>Screenshot 2.2 &mdash; Remove from Track</small>

Inversely, the list of stations that can be removed from a track is also
guaranteed to exist in the track's record. This method reduces the overhead of
checking the database for invalid operations.

### Input Restrictions

<img width="320" src="https://github.com/hanggrian/IIT-ITM515/raw/assets/assignments/hw5/screenshot3_1.png"><br><small>Screenshot 3.1 &mdash; Change Seat Count</small>

User can update the seat count of a railcar by entering a number in a text input
dialog. The confirmation button will be disabled until a non-null, non-negative
and non-decimal number is entered.

<img width="480" src="https://github.com/hanggrian/IIT-ITM515/raw/assets/assignments/hw5/screenshot3_2.png"><br><small>Screenshot 3.2 &mdash; Change Serial Number</small>

In the instance of updating locomotive serial number, the confirmation button is
enabled by default because the column accepts `null` values. However, it is
disabled when the input is longer than 20 characters per the schema requirement.
