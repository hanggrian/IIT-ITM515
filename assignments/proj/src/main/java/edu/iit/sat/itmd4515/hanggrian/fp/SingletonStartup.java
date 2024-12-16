package edu.iit.sat.itmd4515.hanggrian.fp;

import com.github.javafaker.Faker;
import edu.iit.sat.itmd4515.hanggrian.fp.db.Departments;
import edu.iit.sat.itmd4515.hanggrian.fp.db.Roles;
import edu.iit.sat.itmd4515.hanggrian.fp.db.Students;
import edu.iit.sat.itmd4515.hanggrian.fp.db.schemas.Department;
import edu.iit.sat.itmd4515.hanggrian.fp.db.schemas.Role;
import edu.iit.sat.itmd4515.hanggrian.fp.db.schemas.Student;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import jakarta.ejb.EJB;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.ejb.Timeout;
import jakarta.ejb.Timer;
import jakarta.ejb.TimerService;
import jakarta.inject.Inject;
import jakarta.security.enterprise.identitystore.PasswordHash;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The startup service to supply initial login credentials.
 */
@Singleton
@Startup
public class SingletonStartup {
    static final int STUDENT_COUNT = 10;
    static final String ROLE_ADMIN = "admin";
    static final String ROLE_USER = "user";

    private static final Logger LOGGER = LoggerFactory.getLogger(SingletonStartup.class);
    private static final String NON_ALPHABET_REGEX = "[^a-zA-Z]";
    private static final long TIMER_INTERVAL = 10000L;
    private static final String TIMER_TAG = "SingletonStartup#TIMER_TAG";

    @EJB Departments departments;
    @EJB Students students;
    @EJB Roles roles;
    @Inject PasswordHash hash;

    @Resource TimerService service;

    private Faker faker;
    private Random random;
    private Role userRole;

    @PostConstruct
    public void initialize() {
        LOGGER.info("Initializing startup service...");
        service.createTimer(TIMER_INTERVAL, TIMER_INTERVAL, TIMER_TAG);

        List<Department> collegeOfComputingDepartments =
            Arrays.asList(
                new Department.Builder()
                    .departmentName("Applied Mathematics")
                    .website("https://www.iit.edu/applied-math")
                    .email("amath@iit.edu")
                    .phoneNumber("3125678980")
                    .building("Rettaliata Engineering Center")
                    .description(
                        "<p>"
                            + "There is a growing demand for people whose undergraduate training "
                            + "emphasizes modern applied mathematics. These positions are "
                            + "typically interdisciplinary and focus on a combination of modeling, "
                            + "analysis, statistics, and computation. They are found in such "
                            + "diverse fields as financial services, pharmaceutical research, and "
                            + "municipal administration. Applied mathematicians are found "
                            + "everywhere in commerce, industry, and government where quantitative "
                            + "data is used to make decisions. Illinois Tech applied math "
                            + "graduates have found positions in insurance companies, research "
                            + "laboratories, and internet startups, among other places."
                            + "</p>"
                            + "<p>"
                            + "As part of Chicago’s only tech-focused university, Illinois Tech’s "
                            + "applied mathematics department has built strong connections within "
                            + "the city to provide "
                            + "<a href=\"https://www.iit.edu/applied-math/student-resources/applied"
                            + "-math-internships\">internship</a> "
                            + "and "
                            + "<a href=\"https://www.iit.edu/applied-math/student-resources/applied"
                            + "-math-careers\">employment</a> "
                            + "opportunities."
                            + "</p>"
                    ).build(),
                new Department.Builder()
                    .departmentName("Computer Science")
                    .website("https://www.iit.edu/computer-science")
                    .email("computerscience@iit.edu")
                    .phoneNumber("3125675150")
                    .building("Stuart Building")
                    .description(
                        "<p>"
                            + "Beyond the fundamentals, Illinois Tech offers focused study in many "
                            + "areas of computer science with courses in modern concepts including "
                            + "data mining, information security, artificial intelligence, and "
                            + "distributed computing. Formal specializations in information "
                            + "security and in information and knowledge management systems are "
                            + "offered for bachelor’s studies."
                            + "</p>"
                            + "<p>"
                            + "Gain a firm foundation in the fundamentals in the latest theories "
                            + "and skills at Chicago’s only tech-focused university."
                            + "</p>"
                    ).build(),
                new Department.Builder()
                    .departmentName("Information Technology and Management")
                    .website("https://www.iit.edu/itm")
                    .email("itmdept@iit.edu")
                    .phoneNumber("3125675290")
                    .building("Michael Paul Galvin Tower")
                    .description(
                        "<p>"
                            + "Apply classroom knowledge to solve real-life problems through a "
                            + "hands-on, reality-based approach to education. Learn to blend "
                            + "theoretical content and practical application through projects and "
                            + "laboratory-based instruction. With seven undergraduate and nine "
                            + "graduate specializations that target specific careers in "
                            + "information technology, cybersecurity, and computing, the "
                            + "Department of Information Technology and Management and its "
                            + "programs offer unparalleled breadth and depth."
                            + "</p>"
                    ).build()
            );
        if (departments.isEmpty()) {
            LOGGER.info("Empty departments, populating...");
            for (Department department : collegeOfComputingDepartments) {
                departments.insert(department);
            }
        }

        userRole = new Role.Builder().roleTitle(ROLE_USER).build();
        Role adminRole = new Role.Builder().roleTitle(ROLE_ADMIN).build();
        if (roles.isEmpty()) {
            LOGGER.info("Empty roles, populating...");
            roles.insert(userRole);
            roles.insert(adminRole);
        }

        if (students.isEmpty()) {
            LOGGER.info("Empty users, populating...");
            for (int i = 0; i < STUDENT_COUNT; i++) {
                students.insert(randomizeStudent(collegeOfComputingDepartments, userRole));
            }
            students.insert(
                new Student.Builder()
                    .studentId(ROLE_ADMIN)
                    .email(ROLE_ADMIN + "@iit.edu")
                    .password(hash.generate(ROLE_ADMIN.toCharArray()))
                    .roles(userRole, adminRole)
                    .build()
            );
        }

        LOGGER.info("Startup service finished.");
    }

    @Timeout
    public void repeat(Timer timer) {
        LOGGER.info(
            "{} initiated, next action is {}s away.",
            timer.getInfo(),
            TIMER_INTERVAL / 1000
        );

        students
            .selectAllExceptAdmin()
            .stream()
            .filter(
                student ->
                    student
                        .getRoles()
                        .stream()
                        .noneMatch(role -> role.getRoleTitle().equals(ROLE_USER))
            ).forEach(
                student -> {
                    student.getRoles().add(userRole);
                    students.update(student);
                }
            );
    }

    private Student randomizeStudent(List<Department> departments, Role role) {
        if (faker == null || random == null) {
            faker = new Faker();
            random = new Random();
        }
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String studentId =
            Character.toLowerCase(firstName.charAt(0))
                + lastName.toLowerCase().replaceAll(NON_ALPHABET_REGEX, "");
        return new Student.Builder()
            .firstName(firstName)
            .lastName(lastName)
            .studentId(studentId)
            .email(studentId + "@iit.edu")
            .password(hash.generate(studentId.toCharArray()))
            .department(departments.get(random.nextInt(departments.size())))
            .roles(role)
            .build();
    }
}
