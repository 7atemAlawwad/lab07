package com.example.lms.Service;

import com.example.lms.Model.Enrollment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class EnrollmentService {

    private final ArrayList<Enrollment> list = new ArrayList<>();

    public ArrayList<Enrollment> getAll() {
        return list;
    }

    public void add(Enrollment e) {
        list.add(e);
    }

    public boolean update(String id, Enrollment u) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId().equals(id)) {
                list.set(i, u);
                return true;
            }
        }
        return false;
    }

    public boolean delete(String id) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId().equals(id)) {
                list.remove(i);
                return true;
            }
        }
        return false;
    }

    // Logic helpers --------------------------------
    public ArrayList<Enrollment> byStudent(String sid) {
        ArrayList<Enrollment> r = new ArrayList<>();
        for (Enrollment e : list) if (e.getStudentId().equals(sid)) r.add(e);
        return r;
    }

    public ArrayList<Enrollment> byCourse(String cid) {
        ArrayList<Enrollment> r = new ArrayList<>();
        for (Enrollment e : list) if (e.getCourseId().equals(cid)) r.add(e);
        return r;
    }

    public int countByCourse(String cid) {
        int c = 0;
        for (Enrollment e : list) if (e.getCourseId().equals(cid)) c++;
        return c;
    }

    public void deleteByStudent(String sid) {
        for (int i = 0; i < list.size(); i++)
            if (list.get(i).getStudentId().equals(sid)) {
                list.remove(i);
                i--;
            }
    }

    public void deleteByCourse(String cid) {
        for (int i = 0; i < list.size(); i++)
            if (list.get(i).getCourseId().equals(cid)) {
                list.remove(i);
                i--;
            }
    }

    public boolean deleteByStudentAndCourse(String sid, String cid) {
        for (int i = 0; i < list.size(); i++) {
            Enrollment e = list.get(i);
            if (e.getStudentId().equals(sid) && e.getCourseId().equals(cid)) {
                list.remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean setGrade(String id, Double g) {
        for (Enrollment e : list)
            if (e.getId().equals(id)) {
                e.setGrade(g);
                return true;
            }
        return false;
    }
}
