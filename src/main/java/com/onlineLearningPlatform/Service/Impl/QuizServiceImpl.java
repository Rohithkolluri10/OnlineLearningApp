package com.onlineLearningPlatform.Service.Impl;

import com.onlineLearningPlatform.Service.QuizService;
import com.onlineLearningPlatform.dto.AnswerDto;
import com.onlineLearningPlatform.dto.QuestionDto;
import com.onlineLearningPlatform.dto.QuizCreateDto;
import com.onlineLearningPlatform.dto.ResponseDto;
import com.onlineLearningPlatform.model.Answer;
import com.onlineLearningPlatform.model.Course;
import com.onlineLearningPlatform.model.Question;
import com.onlineLearningPlatform.model.Quiz;
import com.onlineLearningPlatform.repository.AnswerRespository;
import com.onlineLearningPlatform.repository.CourseRepository;
import com.onlineLearningPlatform.repository.QuestionRepository;
import com.onlineLearningPlatform.repository.QuizRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class QuizServiceImpl implements QuizService {
    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private QuizRespository quizRespository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerRespository answerRespository;

    @Override
    public ResponseDto createQuiz(QuizCreateDto quizCreateDto) {
        Course course = courseRepository.findById(quizCreateDto.getId()).orElseThrow(
                () -> new RuntimeException("Couldnt find the Course for the mentioned Id:" + quizCreateDto.getId())
        );
        Quiz quiz = new Quiz();
        quiz.setName(quizCreateDto.getName());
        quiz.setCourse(course);
        Set<Question> questions = new HashSet<>();
        for (QuestionDto questionDto : quizCreateDto.getQuestionDtos()) {
            Question question = new Question();
            Set<Answer> answers = new HashSet<>();
            for (AnswerDto answerDto : questionDto.getAnswerDtos()) {
                Answer answer = new Answer();
                answer.setQuestion(question);
                answer.setAnswertext(answerDto.getAnswerText());
                answer.setCorrect(answerDto.isCorrect());
                answers.add(answer);
            }
            question.setQuiz(quiz);
            question.setAnswers(answers);
            questions.add(question);
        }
        quiz.setQuestions(questions);
        quizRespository.save(quiz);
        return new ResponseDto(true, "Quiz has been added");
    }
}
