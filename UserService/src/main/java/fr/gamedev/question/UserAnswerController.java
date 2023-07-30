package fr.gamedev.question;

import fr.gamedev.question.data.User;
import fr.gamedev.question.data.UserAnswer;
import fr.gamedev.question.repository.UserAnswerRepository;
import fr.gamedev.question.repository.UserRepository;
import org.springframework.hateoas.Link;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserAnswerController {
    UserAnswerRepository userAnswerRepository;
    UserRepository userRepository;
    UserAnswerController(UserAnswerRepository userAnswerRepository, UserRepository userRepository) {
        this.userAnswerRepository = userAnswerRepository;
        this.userRepository = userRepository;
    }
    @GetMapping("/next-question-by-user/{userId}")
    public UserAnswer getQuestionByUser(@PathVariable final long userId) {
        User user = userRepository.findById(userId).orElseThrow();
        UserAnswer userAnswer = userAnswerRepository.findUserAnswerByUserAndPointsIsNull(user);
        Link selfLink = new Link("http://localhost:8080/answer/"+userAnswer.getAnswerId()+"/question");
        userAnswer.add(selfLink);
        return userAnswer;
    }

    @GetMapping("/userAnswer/")
    public UserAnswer getUserAnswerByUserAndAnswer(@RequestParam final long userId, @RequestParam final long answerId) {
        User user = userRepository.findById(userId).orElseThrow();

        UserAnswer userAnswer = userAnswerRepository.findUserAnswerByUserAndAnswerId(user, answerId);
        return userAnswer;
    }

    @PostMapping("/userAnswer/{id}/points/{points}")
    public UserAnswer patchUserAnswer(@PathVariable final long id, @PathVariable final Integer points) {
        UserAnswer userAnswer = userAnswerRepository.findById(id).orElseThrow();
        userAnswer.setPoints(points);
        return userAnswerRepository.save(userAnswer);
    }
}
