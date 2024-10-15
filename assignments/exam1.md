# [Mid exam](https://github.com/hanggrian/IIT-ITM515/blob/assets/assignments/exam1.pdf)

> There are several questions listed below. You must answer the FP Design
  question. In addition, pick **two** of the other questions to answer. You do
  not need to answer all the questions. You will have **three** total responses
  including the required FP Design question and the two questions you select.
>
> Each response should be essay format and 1-2 pages in length **at most.** Some
  of the questions allow you to follow an exercise, lab or tutorial, but you
  must also include the 1-2 page response as an answer.

## Problem 1: FP Design

> **You must answer this FP question.**
>
> **If you are not sure how to create a diagram, you might find the reverse
  engineering features of MySQL Workbench very helpful.**
>
> In our labs, you are designing and developing your own domain model that will
  carry forward into the Final Project.
>
> You should base your domain model on a particular business area or problem
  that you are interested in. In all subsequent labs we will build layers on top
  of this model with EJB and JSF or related technologies. *I strongly encourage
  you to design your own domain model, but if you are stuck, please ask
  questions on GitHub Discussions and we will help you with ideas.*
>
> To receive full credit on this question, you are required to discuss your
  ideas and design. Make appropriate use of diagrams if it aids your
  description.
>
> 1.  You must have at least four entities in your **business domain.**
> 1.  Entities must support at least three relationships between one another
      (`OneToMany`, `ManyToMany`, etc), and at least one must be bi-directional.
> 1.  Your idea must support the logical concept of multiple types of users or
      roles. We must consider this now so we can introduce security in later
      projects. Do not use "User" and "Group" or "Role" as entities in your
      **business domain.** We will extend our business domain with standard
      security entities as part of a subsequent lab. In other words, we will
      introduce a **security domain.** For this answer, your idea must support
      the concept of multiple types of users.
>
> An example could be an online marketplace. At a high level, Entities could
  include Products, Customers, Orders and Suppliers. A Supplier can supply many
  Products, and a given Product can be supplied by many Suppliers. An Order
  belongs to one Customer, but a Customer can make many Orders. An Order can be
  for many Products, and a given Product can be on many Orders.
>
> When considering this example as a full stack web application, roles could
  include customers and suppliers (buyers and sellers). A customer/buyer might
  login to the online marketplace to place a new order for a particular product,
  to check the status of an existing order, or to update their contact and
  payment information. A supplier/seller might login to the online marketplace
  to list a new product for sale, or to process orders that have been placed by
  customers. In other words, it is the same online web application, but the
  functionality depends on the type of user, or what role(s) a given user has.
>
> Your answer to this question is non-binding &ndash; you can always change your
  design before the final project. It is very important, however, that you are
  thinking about this design early in the semester and not at the last minute.
  To receive full credit for this question, please make sure to:
>
> 1.  Discuss your Entities. Include a diagram.
> 1.  Discuss how your Entities relate to one another, including any use of JPA
      inheritance strategies (if any).
> 1.  Discuss what functionality you will expose in your FP web application, and
      how that functionality relates to your multiple roles/security. In other
      words, who can do what?

The material used in this solution is based on [Lab 4](https://github.com/hanggrian/IIT-ITM515/tree/main/assignments/hw4/)
and further refined in [Lab 5](https://github.com/hanggrian/IIT-ITM515/tree/main/assignments/hw5/)
regarding **Chicago Transit Authority (CTA)** train system. Having to travel
daily to *Illinois Tech* using CTA, the topic of public transportation is of
huge interest to me. According to locals,  CTA is currently on the path of
decline with frequent service disruptions.<sup>[\[1\]]</sup> But coming from a
country with a severely lacking infrastructure, the CTA transit system is still
better and I am glad to have this experience.

### Entities

<img width="640" src="https://github.com/hanggrian/IIT-ITM515/raw/assets/assignments/hw5/diagram1.svg">

[View SQL schema](https://github.com/hanggrian/IIT-ITM515/blob/main/assignments/hw5/cta_schema.sql)
/ [sample data](https://github.com/hanggrian/IIT-ITM515/blob/main/assignments/hw5/cta_data.sql)

The database schema starts with **Station** where passengers board trains and
**Track** or line color of the train. A station may have additional features
such as elevator access and parking area as illustrated in the ['L' map](https://www.transitchicago.com/assets/1/6/ctamap_Lsystem.png). The **TrackStation** table is a junction that represents a
station that can belong to more than one track.

The next entity is **Train,** a car unit with a locomotive engine pulling other
**Cars,** a unit carrying passengers. They are connected to **TrainCar** which
suggests a trainset contains multiple cars but only one locomotive. A train is
assigned to a track because it cannot exist on a different line color.

**Passenger** and **Conductor** are considered in the future submission.
However, they fit the description of user domain roles and will not be
demonstrated at the moment. Additionally, records of commuter travel can be kept
in **Trip** along with the fare amount and a link to the user entity. A customer
may decide to pay monthly subscription fees for unlimited rides rather than
paying a fixed amount for each trip. To support this behavior, a trip entity can
be modified to include a payment type column or introduce a new entity **Pass.**

**Alert** is a message that is temporarily displayed on the stations and
broadcasted to the trains. This feature is invaluable in the event of an
emergency, although us passengers are more likely to see it when there is a
service disruption.

### Relationships

The connections between are defined using JPA annotations `@OneToMany`
(*track station* & *train car*), `@ManyToOne` (*station*, *track* & *car*)
and `@OneToOne` (*train*). `@ManyToMany` relationship is not drawn in the
diagram, but is declared in POJO classes to join opposite columns in the
junction table.

```java
@Entity
@Table(name = "track")
public class Track {
    @ManyToMany
    @JoinTable(
        name = "track_station",
        joinColumns = @JoinColumn(name = "track_color"),
        inverseJoinColumns = @JoinColumn(name = "station_name")
    )
    private Set<Station> stations = new HashSet<>();

    // other columns
}
```

I plan to utilize JPA inheritance strategies to share common attributes between
entities. For instance, a *train* can have empty seats resembling a *car*.
Another example would be a *passenger* and *conductor* extending a *user*
entity. There is also a plan to use Jakarta `@Embedded` annotation to
inject a primary key into the target entity.

```java
@Entity
@Table(name = "train_car")
public class TrainCar {
    @EmbeddedId
    private TrainCarId id;

    // other columns
}

@Embeddable
public class TrainCarId {
    @Column(name = "train_id", nullable = false)
    private Integer trainId;

    @Column(name = "car_no", length = 4, nullable = false)
    private String carNo;

    // encapsulation methods
}
```

### Functionality

I imagine my frontend web application to be an administrative dashboard for CTA
employees to configure the transportation routes and train formations. To assign
a trainset to a track, the user must select an existing formation or create a
new one using a single *train* and multiple *cars*. When a train is operable, it
stops at each *station* posted on the track line color. A station may have
multiple tracks for *passengers* to transfer between line colors. The other
responsibilities of CTA employees involve announcing *alerts* and updating
station information such as coordinates and extra facilities.

Besides the transportation management, the web application could provide a
financial report of the fares collected from passenger trip fares. Finally, the
web application could potentially support the ability to produce simple
statistics of track line occupancy, station foot traffic and passenger payment
method preferences.

### Checklist

- Implemented entities:
  - [x] *Track*
  - [x] *Station*
  - [x] *TrackStation*
  - [x] *Train*
  - [x] *Car*
  - [x] *TrainCar*
  - [ ] *Passenger*
  - [ ] *Trip*
  - [ ] *Pass*
  - [ ] *Conductor*
  - [ ] *Alert*
- Features that are subject to change:
  - [ ] Include CTA bus system, will not add many tables but massively
        change the structure of existing tables.
  - [ ] Support Ventra subscription membership with weekly and/or monthly
        payment, likely adding 1-2 tables.

## Problem 2: Jakarta EE Specification and Platform

> Discuss the Jakarta EE specification and platform. In your words, what does
  "enterprise" mean in the context of application development? What industries
  are commonly thought of as entrerprise? Discuss the role of application
  servers as they relate to the platform, and what role the GlassFish
  application server plays. Discuss the different Jakarta EE APIs and
  specifications. What is a specification, and how does it relate to an
  implementation? Discuss how Jakarta EE relates to Java SE. Discuss the JCP and
  its relationship to the JESP (Jakarta EE specification process).

Jakarta specifications are Eclipse Foundation's fork of [Oracle's Java EE](https://www.oracle.com/java/technologies/java-ee-glance.html), which itself is an extended version of
[Java SE](https://www.oracle.com/java/technologies/java-se-glance.html) with
server-side Application Programming Interfaces (APIs). These APIs are best
suited to create web-based applications that require security layers, improved
scalability and non-blocking concurrency. To the best of my knowledge, the
modifications made by Eclipse Foundation are related to the proprietary
licensing of Java EE, forcing an independent entity like Eclipse to replace
Oracle dependencies with open-source alternatives.<sup>[\[2\]]</sup>

### What is "enterprise"?

This term has always been a point of contention for me. In the context of
backend development, "enterprise" could mean a large-scale application with
hardened security, high availability and other features not found in a
"standard" application. I presume this is the intended expression of enterprise
in the Java EE and Jakarta EE brand names.

On the other hand, "enterprise" could refer to paid services in the context of
application licensing and support, as seen in [Red Hat Enterprise Linux (RHEL)](https://www.redhat.com/en/store/linux-platforms). This is in direct contrast to the term "community" which
implies free software without commercial backing, such as [MySQL Community Edition](https://www.mysql.com/products/community/). It could be confusing at times to differentiate between
two meanings of "enterprise", for example, [Payara Platform Community Edition](https://www.payara.fish/downloads/payara-platform-community-edition/)
is a free software that can be used to develop *Jakarta Enterprise Edition*
applications, whereas [Payara Platform Enterprise Edition](https://www.payara.fish/downloads/payara-platform-enterprise-edition/) is subscription-based.

### GlassFish role

While Jakarta EE is a tool to develop applications in the form of Java codebase,
a server to host the application is necessary for clients to interact with the
application. GlassFish middleware deploys the compiled Java code to an address
and keeps it alive for the duration of its service. There are several other
prominent application servers such as [Apache Tomcat](https://tomcat.apache.org/)
and [Eclipse Jetty](https://jetty.org/index.html). Nevertheless, GlassFish
remains the most popular because of its roots in Sun Microsystems, Oracle and
now Eclipse Foundation.

### Jakarta EE APIs

Jakarta EE promotes modularity by separating the APIs into different Maven
artifacts that can be imported using a project management tool like Maven or
Gradle. The specifications themselves are vast, covering a wide range of
interests such as **Jakarta Messaging (JMS),** a communication protocol,
**Jakarta Contexts and Dependency Injection (CDI),** providing support for
dependency injection patterns, and many more. Below are common APIs used in the
previous labs:

- **Jakarta Persistence API (JPA)** &ndash; responsible for mapping SQL entries
  to Java objects.
- **Jakarta Transactions API (JTA)** &ndash; ensures atomic transactions in
  database operations using the *Hibernate Query Language (HQL)*.
- **Jakarta Validation** &ndash; checks the validity of each Java property
  before processing.

### Development governance

The **Java Community Process (JCP)** is an establishment overseeing the
development progress of Java technologies to make sure that additions and fixes
conform to the technical specifications voted by the executive committees.<sup>[\[3\]]</sup>
Compared to the JCP standards, Eclipse's **Jakarta EE Specification Process
(JESP)** offers a more transparent environment and permissive licensing to
consume or distribute the work. Without Oracle's influence on the JESP body, the
community is able to move faster and adapt to newer concepts and libraries.

## Problem 3: Persistence Layer

> Compare and contrast the JDBC and JPA persistence technologies. Consider
  concepts like relationship mapping, CRUD operations, and inheritance in your
  comparison. What are the different Java APIs that relate to persistence, and
  how do the specifications relate toimplementation? How does Hibernate relate
  to JPA? What is *EclipseLink*? How do the EntityManager and JPQL relate to
  JDBC and SQL? How does the life cycle of an Entity relate to a POJO with JDBC?

**Java Database Connectivity (JDBC)** is a connection interface that allows Java
code to communicate with a database and interact with its schemas, usually
maintained by the database's official vendor. In contrast, **Java Persistence
API (JPA)** is a higher-level abstraction layer that creates Java object
instance representations of the queried database entries. To achieve this
Object-Relational Mapping (ORM) pattern, JPA accepts user-tagged annotations,
XML metadata and establish a connection to the database using JDBC.

### What is *EclipseLink*?

In my limited understanding, EclipseLink is a full-fledged database management
framework that complies with open standards such as JPA. The framework is
similar to Hibernate in that it provides a one-stop solution for data access
but may not be compatible with other frameworks. On the contrary, the JPA
itself is a lightweight library that can be integrated into any existing
technology stack.

### EntityManager lifecycle

The `EntityManager` is a procedure declared by the JPA specification to open a
session, commit a transaction and is preferred over the domain implementation of
JDBC like Hibernate's `SessionFactory`. The communication between the
`EntityManager` and the host database is performed using the JPQL query language
or any superset of JPQL like Hibernate's HQL. Its lifecycle begins when the
`EntityManagerFactory` generates a new manager instance, which in turn creates a
new session that is closed upon completion.

## Problem 4: Presentation Layer and MVC

> Compare and contrast Java presentation technologies, such as JSP, JSTL and EL.
  Include a discussion of Servlets in your response, and the presentation (or
  other) capabilities of a Servlet. How does a Servlet fit into the MVC pattern?
  For that matter, what is the MVC pattern? What Java technologies relate to
  what MVC components? How can Jakarta EE services such as validation be
  integrated into technologies related to views and presentation?

Having implemented the persistence layer and the business logic in the backend,
the presentation layer is the front end with user interface components a person
can interact with. This is achievable with **JavaServer Pages (JSP),** an HTML
language with dynamic content powered by Java, **JavaServer Pages Standard Tag
Library (JSTL)**, custom tags with shorter syntax, and **Expression Language
(EL)**, direct binding to Java attributes in the presentation.<sup>[\[4\]]</sup>

### MVC pattern

Since each component of the web application is modular (persistence layer,
business logic, presentation layer), the **Model-View-Controller (MVC)** pattern
can be applied to encourage reusability and isolate the component
responsibilities. This approach is entirely optional but is widely adopted in
the industry as a good practice. Java technologies bring several tools to
implement the MVC pattern: **JPA or JTA** as the model, **JSP, JSLT or EL**
as the view and **Servlets** as the controller.

### Jakarta EE services

The Jakarta EE services, namely **Jakarta Validation,** are integrated by
tagging the model attributes with a validation check such as `@NotNull`, `@Size`
and `@Max`. The validation is evaluated by the persistence layer in the
controller component. Finally, the result is displayed in the human-readable
format in the view component.

## References

1.  [Bosman, J. (2020, May 22). *New York Times: Chicago Is Tired of Waiting for Trains, and Thinks It Knows Who’s to Blame.*](https://www.nytimes.com/2024/05/22/us/chicago-cta-dorval-carter.html)
1.  [Tijms, A. (2020, February 27). *Oracle Blog: Transition from Java EE to Jakarta EE.*](https://blogs.oracle.com/javamagazine/post/transition-from-java-ee-to-jakarta-ee/)
1.  [Chuyko, D. (2023, September 23). *BellSoft Blog: Java Community Process (JCP): Shaping the future of Java.*](https://bell-sw.com/blog/java-community-process-jcp-shaping-the-future-of-java/)
1.  [Schalk, C. (2004, July). *Oracle: A Quick Overview of JSTL, EL and how it's used in Oracle ADF.*](https://www.oracle.com/application-development/technologies/jdeveloper/jstl-el-adf.html)

[\[1\]]: https://www.nytimes.com/2024/05/22/us/chicago-cta-dorval-carter.html
[\[2\]]: https://blogs.oracle.com/javamagazine/post/transition-from-java-ee-to-jakarta-ee/
[\[3\]]: https://bell-sw.com/blog/java-community-process-jcp-shaping-the-future-of-java/
[\[4\]]: https://www.oracle.com/application-development/technologies/jdeveloper/jstl-el-adf.html
