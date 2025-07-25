package com.example.lms.Service;

import com.example.lms.Model.Course;
import com.example.lms.Model.Enrollment;
import com.example.lms.Model.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final ArrayList<Course> list = new ArrayList<>();
    private final EnrollmentService enr;
    private final StudentService stu;

    public ArrayList<Course> getAll() {
        return list;
    }

    public void add(Course c) {
        list.add(c);
    }

    public boolean update(String id, Course u) {
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
                enr.deleteByCourse(id);
                return true;
            }
        return false;
    }

    public ArrayList<Student> studentsIn(String cid) {
        ArrayList<Student> out = new ArrayList<>();
        for (Enrollment e : enr.byCourse(cid)) {
            Student s = stu.getById(e.getStudentId());
            if (s != null) out.add(s);
        }
        return out;
    }

    public double avgGrade(String cid) {
        double t = 0;
        int c = 0;
        for (Enrollment e : enr.byCourse(cid))
            if (e.getGrade() != null) {
                t += e.getGrade();
                c++;
            }
        return c == 0 ? 0 : t / c;
    }

    public boolean open(String cid) {
        for (Course c : list)
            if (c.getId().equals(cid)) {
                c.setClosed(false);
                return true;
            }
        return false;
    }

    public int remaining(String cid) {
        for (Course c : list)
            if (c.getId().equals(cid)) {
                return c.getCapacity() - enr.countByCourse(cid);
            }
        return -1;
    }

    public Course getById(String id) {
        for (Course c : list) if (c.getId().equals(id)) return c;
        return null;
    }

    public boolean exists(String id) {
        return getById(id) != null;
    }
}
