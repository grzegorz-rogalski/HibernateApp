package javahive.api;

import javahive.domain.Student;
import javahive.infrastruktura.Finder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by m on 29.04.14.
 */

@Component
@Transactional
public class StudenciApi {
    @Inject
    private Finder finder;
    public List<Student> getListaWszystkichStudentow(){
        return finder.findAll(Student.class);
    }
}
