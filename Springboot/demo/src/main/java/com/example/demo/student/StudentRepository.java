/* Esta es el Data Access Layer*
Se quiere utilizar esta interfaz con el servicio StudentService*/
package com.example.demo.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/*El repositorio trabaja con un tipo de objeto (Student) y el tipo de ID (en este caso el id es de tipo long)
*
* When getting an Optional return type, we're likely to check if the value is missing, leading to fewer
* NullPointerExceptions in the applications. However, the Optional type isn't suitable in all places.

 */
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query("SELECT s FROM Student s WHERE s.email = ?1")
    Optional<Student> findStudentByEmail(String email);
}
