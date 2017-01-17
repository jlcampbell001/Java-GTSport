package backEnd.general.owners;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * The repository for the owner table.
 * @author Jonathan
 *
 */
@Repository
public interface OwnerRepository extends JpaRepository<Owner, String> {

	/**
	 * Returns an owner that is the passed owner name.
	 * 
	 * @param ownerName
	 *            - the owner name to look for
	 * @return - the owner object found or null if not found
	 */
	@Query("select own from Owner own where own.ownerName = :ownerName")
	Owner findByName(@Param("ownerName") String ownerName);

	/**
	 * Returns the owner that is the default owner.
	 * 
	 * @return - the owner object found or null if not found
	 */
	@Query("select own from Owner own where own.defaultOwner = true")
	Owner findDefaultOwner();

	/**
	 * Returns the highest value primary key in the owner table.
	 * 
	 * @return - the highest primary key
	 */
	@Query("select max(own.primaryKey) as maxkey from Owner own")
	String getMaxKey();

	/**
	 * Returns a list of default owners.
	 * 
	 * @return - the list of owner objects found to be set as the default owner
	 */
	@Query("select own from Owner own where own.defaultOwner = true")
	List<Owner> findAllDefaultOwners();
}
