package com.studentmgmt;

import com.studentmgmt.dao.StudentDAO;
import com.studentmgmt.dao.StudentDAOImpl;
import com.studentmgmt.model.Student;

import java.util.List;
import java.util.Scanner;

public class MainApp {
    static final Scanner in = new Scanner(System.in);
    static final StudentDAO dao = new StudentDAOImpl();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n=== Student Management ===");
            System.out.println("1) Add Student");
            System.out.println("2) View All");
            System.out.println("3) Update Student");
            System.out.println("4) Delete Student");
            System.out.println("5) Search by Name");
            System.out.println("0) Exit");
            System.out.print("Select: ");
            String choice = in.nextLine().trim();

            switch (choice) {
                case "1": add(); break;
                case "2": viewAll(); break;
                case "3": update(); break;
                case "4": delete(); break;
                case "5": search(); break;
                case "0": System.out.println("👋 Bye!"); return;
                default: System.out.println("❌ Invalid choice.");
            }
        }
    }

    private static void add() {
        System.out.print("Name: "); String name = in.nextLine().trim();
        int age = readInt("Age: ");
        System.out.print("Course: "); String course = in.nextLine().trim();

        if (name.isEmpty() || course.isEmpty() || age <= 0) {
            System.out.println("❌ Invalid input.");
            return;
        }
        int rows = dao.insert(new Student(name, age, course));
        System.out.println(rows == 1 ? "✅ Added" : "❌ Add failed");
    }

    private static void viewAll() {
        List<Student> list = dao.findAll();
        if (list.isEmpty()) { System.out.println("(no records)"); return; }
        list.forEach(System.out::println);
    }

    private static void update() {
        int id = readInt("ID to update: ");
        System.out.print("New Name: "); String name = in.nextLine().trim();
        int age = readInt("New Age: ");
        System.out.print("New Course: "); String course = in.nextLine().trim();

        int rows = dao.update(new Student(id, name, age, course));
        System.out.println(rows == 1 ? "✅ Updated" : "❌ Update failed (check ID)");
    }

    private static void delete() {
        int id = readInt("ID to delete: ");
        int rows = dao.delete(id);
        System.out.println(rows == 1 ? "✅ Deleted" : "❌ Delete failed (check ID)");
    }

    private static void search() {
        System.out.print("Name contains: ");
        String q = in.nextLine().trim();
        List<Student> list = dao.findByName(q);
        if (list.isEmpty()) { System.out.println("(no matches)"); return; }
        list.forEach(System.out::println);
    }

    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = in.nextLine().trim();
            try { return Integer.parseInt(s); }
            catch (NumberFormatException e) { System.out.println("Enter a valid number."); }
        }
    }
}
