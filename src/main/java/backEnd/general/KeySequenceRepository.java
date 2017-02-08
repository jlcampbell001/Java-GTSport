package backEnd.general;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The key sequence repository.
 *
 * @author jonathan
 */
@Repository
public interface KeySequenceRepository extends JpaRepository<KeySequence, String> {
}
