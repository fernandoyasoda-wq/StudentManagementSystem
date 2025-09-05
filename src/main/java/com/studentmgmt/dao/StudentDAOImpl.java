package com.studentmgmt.dao;

import com.studentmgmt.db.DBConnection;
import com.studentmgmt.model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAOImpl implements StudentDAO {

    @Override
    public int insert(Student s) {
        String sql = "INSERT INTO students(name, age, course) VALUES(?,?,?)";
        try (PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql)) {
            ps.setString(1, s.getName());
            ps.setInt(2, s.getAge());
            ps.setString(3, s.getCourse());
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Insert failed: " + e.getMessage());
            return 0;
        }
    }

    @Override
    public List<Student> findAll() {
        String sql = "SELECT id, name, age, course FROM students ORDER BY id";
        List<Student> list = new ArrayList<>();
        try (PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new Student(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getString("course")
                ));
            }
        } catch (SQLException e) {
            System.err.println("FindAll failed: " + e.getMessage());
        }
        return list;
    }

    @Override
    public int update(Student s) {
        String sql = "UPDATE students SET name=?, age=?, course=? WHERE id=?";
        try (PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql)) {
            ps.setString(1, s.getName());
            ps.setInt(2, s.getAge());
            ps.setString(3, s.getCourse());
            ps.setInt(4, s.getId());
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Update failed: " + e.getMessage());
            return 0;
        }
    }

    @Override
    public int delete(int id) {
        String sql = "DELETE FROM students WHERE id=?";
        try (PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Delete failed: " + e.getMessage());
            return 0;
        }
    }

    @Override
    public List<Student> findByName(String part) {
        String sql = "SELECT id, name, age, course FROM students WHERE name LIKE ? ORDER BY id";
        List<Student> list = new ArrayList<>();
        try (PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql)) {
            ps.setString(1, "%" + part + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(new Student(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getInt("age"),
                            rs.getString("course")
                    ));
                }
            }
        } catch (SQLException e) {
            System.err.println("Search failed: " + e.getMessage());
        }
        return list;
    }
}
