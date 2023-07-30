/**
 *
 */
package fr.gamedev.question.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import fr.gamedev.question.data.User;

import javax.transaction.Transactional;

/**
 * @author djer1
 *
 */
@RepositoryRestResource(collectionResourceRel = "user", path = "user")
public interface UserRepository extends PagingAndSortingRepository<User, Long> {

    List<User> findByLastName(@Param("name") String name);

    @Override
    @Transactional
    void delete(User user);

    @Override
    @Transactional
    void deleteById(Long id);

}
