package abai.kz.jpa.demo.repositories;

import abai.kz.jpa.demo.entities.Requests;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface RequestRepository extends JpaRepository<Requests, Long> {
    List<Requests> findAllByHandled(Boolean handled);
    List<Requests> findAllByUsernameContains(String username);
}
