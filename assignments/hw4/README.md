# [Lab 4](https://github.com/hanggrian/IIT-ITM515/blob/assets/assignments/hw4.pdf): ORM and JPA

This project makes use of Hibernate ORM using `EntityManager` connection
practiced in **Lab 3**. However, [Sakila Sample Database](https://dev.mysql.com/doc/sakila/en/)
has been replaced by custom implementation of CTA train station database.

- *Jakarta Persistence API (JPA)* in Hibernate ORM with `persistence.xml`.
- POJO and DAO validation in *Jupiter* unit tests.
- Single-window JavaFX app that displays train station and which line it belongs
  to.

## Discussion

> Paragraph that describes the business domain you have chosen to work with, and
  why.

My business domain of choice is the **Chicago Transit Authority (CTA)**. It
resonates with me as a student who commutes daily using the CTA train system
during my study at *Illinois Tech*. The CTA itself is a complex public
transportation service that likely has complex data structure and heavy
operations. This project reduces the complexity by focusing on the train station
and track relationship.

> Write a second paragraph answering the following questions: There is only one
  entity required for Lab 4, but what other entities from your business domain
  can you think of? How might they relate to one another? You can answer this in
  narrative form, or you can answer it with a database diagram. One of your
  midterm questions will be very similar, about the design of your FP, so this
  is to help get you started early.

Currently there are `station`, `track`, and `track_station` entities in the
first iteration of this CTA database. The first entities that should be
integrated next are `locomotive` and `wagon` to depict a train with a single
engine and many railcars.

I imagine the database should consider the consumer aspect of the CTA service
such as `passenger`, `trip` and `fare`. However, this would introduce an extra
layer of complexity since consumers can purchase a single trip or subscribe to a
daily, weekly or monthly pass. A solution to this is to register another entity
`pass` that manages starting and final subscription dates.

Additionally, a `staff` or `conductor` entity can be useful to oversee the train
operation and to validate elevated requests by user. However, with many of our
trains being automated, the term conductor who manually drives the train is
no longer relevant. Therefore, the `staff` entity will probably not be linked to
the `train` entity.

> Document in your README by adding code block(s) containing the output of your
  test classes. Clearly identify each operation and discuss what is being
  tested.

Using `testLogging` configuration in `build.gradle`, the test results can be
printed to console using the following command:

```sh
./gradlew test
```

The result is the pass/fail status preceded by the test class and function name.

```
> Task :test

StationTest > valid() PASSED

StationTest > invalid() PASSED

StationsTest > createAndDelete() PASSED

StationsTest > update() PASSED

StationsTest > read() PASSED

TrackStationsTest > read() PASSED

TrackTest > valid() PASSED

TrackTest > invalid() PASSED

TracksTest > createAndDelete() PASSED

TracksTest > read() PASSED
```

The test classes are separated into POJO validation (`StationTest`) and DAO JPA
connection (`StationsTest`). In POJO tests, I use `invalid()` method to assert
data objects initialized with default values while `valid()` is used to validate
edge cases such as `null` values and values nearing the limit. On the other
hand, DAO test method names reflect CRUD operations that are being tested.

## Building

Unlike *Lab 3* that is initialized with Payara and Servlet, this project is a
small JavaFX application that can be run with the following command:

```sh
./gradlew run
```

> **Note**
>
> The MySQL server must be running and the CTA database [schema](https://github.com/hanggrian/IIT-ITM515/blob/main/assignments/hw4/cta_schema.sql)
  and [data](https://github.com/hanggrian/IIT-ITM515/blob/main/assignments/hw4/cta_data.sql)
  must be imported.

## Diagrams

<img width="320" src="https://github.com/hanggrian/IIT-ITM515/raw/assets/assignments/hw4/diagram1.svg"><br><small>Diagram 1 &mdash; CTA station diagram</small>

## Screenshots

### Interface

<img width="480" src="https://github.com/hanggrian/IIT-ITM515/raw/assets/assignments/hw4/screenshot1_1.png"><br><small>Screenshot 1.1 &mdash; Main interface</small>

<img width="640" src="https://github.com/hanggrian/IIT-ITM515/raw/assets/assignments/hw4/screenshot1_2.png"><br><small>Screenshot 1.2 &mdash; Expanded state</small>

### CRUD Operations

<img width="480" src="https://github.com/hanggrian/IIT-ITM515/raw/assets/assignments/hw4/screenshot2_1.png"><br><small>Screenshot 2.1 &mdash; READ station</small>

Stations can be listed using **List All** action from the menu bar or context
menu. Inversely, the station list can also be used to list all tracks the
station belongs to.

<img width="320" src="https://github.com/hanggrian/IIT-ITM515/raw/assets/assignments/hw4/screenshot2_2.png"><br><small>Screenshot 2.2 &mdash; CREATE track station</small>

Upon clicking **Add to Track**, a dialog of list choice will appear requiring
the user to select a track to add the station to.

<img width="320" src="https://github.com/hanggrian/IIT-ITM515/raw/assets/assignments/hw4/screenshot2_3.png"><br><small>Screenshot 2.3 &mdash; UPDATE track</small>

The 24-hour status of track and elevator/parking feature of station can be
toggled using the **Toggle Status/Feature** action.

<img width="320" src="https://github.com/hanggrian/IIT-ITM515/raw/assets/assignments/hw4/screenshot2_4.png"><br><small>Screenshot 2.4 &mdash; DELETE station</small>

When deleting a station, the user will be prompted with a confirmation dialog
before the operation is executed.
