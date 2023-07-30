package fr.gamedev.question.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import fr.gamedev.question.data.User;
import fr.gamedev.question.data.UserAnswer;

/**
 * @author djer13
 *
 */
@RepositoryRestResource(collectionResourceRel = "userAnswer", path = "userAnswer")
public interface UserAnswerRepository extends PagingAndSortingRepository<UserAnswer, Long> {

    Optional<UserAnswer> findTopByAnswerIdAndUserAndPointsNotNullAndPointsIsGreaterThanOrderByPoints(
            Long questionId, User user, int greaterThan);

    /*@Query("SELECT ua.answer.question FROM UserAnswer ua " + "WHERE ua.points IS NULL "
            + "and ua.user.login = :userLogin " + "and ROWNUM = 1 ")*/
    UserAnswer findFirstByUserLoginAndPointsIsNull(String userLogin);

    @Query("SELECT ua FROM UserAnswer ua " + "WHERE ua.points IS NULL " + "and ua.user.login = :userLogin ")
    List<UserAnswer> findAskedQuestionsByUserId(String userLogin);

    UserAnswer findUserAnswerByUserAndPointsIsNull(User user);

    UserAnswer findUserAnswerByUserAndAnswerId(User user, Long answerId);


}
