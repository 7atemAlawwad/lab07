package com.example.lms.Service;

import com.example.lms.Model.Course;
import com.example.lms.Model.Enrollment;
import com.example.lms.Model.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final ArrayList<Student> list = new ArrayList<>();
    private final EnrollmentService enr;


    public ArrayList<Student> getAll() {
        return list;
    }

    public void add(Student s) {
        list.add(s);
    }

    public boolean update(String id, Student u) {
        for (int i = 0; i < list.size(); i++)
            if (list.get(i).getId().equals(id)) {
                list.set(i, u);
                return true;
            }
        return false;
    }

    public boolean delete(String id) {
        for (int i = 0; i < list.size(); i++)
            if (list.get(i).getId().equals(id)) {
                list.remove(i);
                enr.deleteByStudent(id);
                return true;
            }
        return false;
    }

    public ArrayList<Course> coursesOf(String sid, CourseService cs) {
        ArrayList<Course> r = new ArrayList<>();
        for (Enrollment e : enr.byStudent(sid)) {
            Course c = cs.getById(e.getCourseId());
            if (c != null) r.add(c);
        }
        return r;
    }

    public int countCourses(String sid) {
        return enr.byStudent(sid).size();
    }

    public boolean drop(String sid, String cid) {
        return enr.deleteByStudentAndCourse(sid, cid);
    }

    public boolean suspend(String sid) {
        for (Student s : list)
            if (s.getId().equals(sid)) {
                s.setActive(false);
                enr.deleteByStudent(sid);
                return true;
            }
        return false;
    }

    public Student getById(String id) {
        for (Student s : list)
            if (s.getId().equals(id)) return s;
        return null;
    }

}
