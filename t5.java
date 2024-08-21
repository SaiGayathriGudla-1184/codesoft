import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Course {
    private String code;
    private String title;
    private String description;
    private int capacity;
    private String schedule;
    private int availableSlots;

    public Course(String code, String title, String description, int capacity, String schedule) {
        this.code = code;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.schedule = schedule;
        this.availableSlots = capacity;
    }

    public String getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getSchedule() {
        return schedule;
    }

    public int getAvailableSlots() {
        return availableSlots;
    }

    public void registerStudent() {
        availableSlots--;
    }

    public void dropStudent() {
        availableSlots++;
    }
}

class Student {
    private String id;
    private String name;
    private ArrayList<Course> registeredCourses;

    public Student(String id, String name) {
        this.id = id;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Course> getRegisteredCourses() {
        return registeredCourses;
    }

    public void registerCourse(Course course) {
        registeredCourses.add(course);
        course.registerStudent();
    }

    public void dropCourse(Course course) {
        registeredCourses.remove(course);
        course.dropStudent();
    }
}

public class CourseRegistrationSystem {
    private Map<String, Course> courses;
    private Map<String, Student> students;

    public CourseRegistrationSystem() {
        this.courses = new HashMap<>();
        this.students = new HashMap<>();
    }

    public void addCourse(Course course) {
        courses.put(course.getCode(), course);
    }

    public void addStudent(Student student) {
        students.put(student.getId(), student);
    }

    public void displayCourseListing() {
        System.out.println("Available Courses:");
        for (Course course : courses.values()) {
            System.out.println(course.getCode() + " - " + course.getTitle() + " (" + course.getAvailableSlots() + "/" + course.getCapacity() + ")");
        }
    }

    public void registerStudentForCourse(String studentId, String courseId) {
    Student student = students.get(studentId);
    Course course = courses.get(courseId);
    if (student == null) {
        System.out.println("Invalid student ID.");
    } else if (course == null) {
        System.out.println("Invalid course code.");
    } else {
        student.registerCourse(course);
    }
}

  public void dropStudentFromCourse(String studentId, String courseId) {
    Student student = students.get(studentId);
    Course course = courses.get(courseId);
    if (student == null) {
        System.out.println("Invalid student ID.");
    } else if (course == null) {
        System.out.println("Invalid course code.");
    } else {
        student.dropCourse(course);
    }
}
    public static void main(String[] args) {
        CourseRegistrationSystem system = new CourseRegistrationSystem();

        // Add courses
        system.addCourse(new Course("CS101", "Introduction to Computer Science", "Introduction to computer science concepts", 30, "MWF 10:00am"));
        system.addCourse(new Course("CS202", "Data Structures", "Introduction to data structures and algorithms", 25, "TTh 2:00pm"));
        system.addCourse(new Course("CS303", "Computer Systems", "Introduction to computer systems and architecture", 20, "MWF 3:00pm"));

        // Add students
        system.addStudent(new Student("S101", "John Doe"));
        system.addStudent(new Student("S102", "Jane Doe"));

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Course Registration System");
            System.out.println("1. Display course listing");
            System.out.println("2. Register for a course");
            System.out.println("3. Drop a course");            System.out.println("4. Exit");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    system.displayCourseListing();
                    break;
                case 2:
                    System.out.print("Enter student ID: ");
                    String studentId = scanner.next();
                    System.out.print("Enter course code: ");
                    String courseId = scanner.next();
                    system.registerStudentForCourse(studentId, courseId);
                    break;
                case 3:
                    System.out.print("Enter student ID: ");
                    studentId = scanner.next();
                    System.out.print("Enter course code: ");
                    courseId = scanner.next();
                    system.dropStudentFromCourse(studentId, courseId);
                    break;
                case 4:
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
