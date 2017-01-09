package backEnd.general.owners;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, String> {

	@Query("select own from Owner own where own.ownerName = :ownerName")
	Owner findByName(@Param("ownerName") String ownerName);

	@Query("select own from Owner own where own.defaultOwner = true")
	Owner findDefaultOwner();
	
	@Query("select max(own.primaryKey) as maxkey from Owner own")
	String getMaxKey();

}
