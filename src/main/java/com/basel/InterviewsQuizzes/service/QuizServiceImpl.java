package com.basel.InterviewsQuizzes.service;

import com.basel.InterviewsQuizzes.exception.global.SortValueException;
import com.basel.InterviewsQuizzes.exception.question.QuestionNotFoundException;
import com.basel.InterviewsQuizzes.exception.quiz.QuestionDuplicatedException;
import com.basel.InterviewsQuizzes.exception.quiz.QuizNotFoundException;
import com.basel.InterviewsQuizzes.model.dto.QuestionDto;
import com.basel.InterviewsQuizzes.model.dto.QuizDto;
import com.basel.InterviewsQuizzes.model.entity.Question;
import com.basel.InterviewsQuizzes.model.entity.Quiz;
import com.basel.InterviewsQuizzes.model.mapper.QuestionMapper;
import com.basel.InterviewsQuizzes.model.mapper.QuizMapper;
import com.basel.InterviewsQuizzes.model.mapper.UpdateMapper;
import com.basel.InterviewsQuizzes.repository.QuizRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuizServiceImpl implements QuizService{

    private final QuizRepository quizRepository;
    private final QuizMapper quizMapper;
    private final QuestionMapper questionMapper;
    private final UpdateMapper updateMapper;
    private final QuestionService questionService;

    private static final Logger logger = LoggerFactory.getLogger(QuizServiceImpl.class);


    @Override
    public void addQuiz(QuizDto quizDto) {
        saveQuiz(quizMapper.quizDtoToQuiz(quizDto));
    }

    @Override
    public void addQuestionToQuiz(long questionId, int quizId) {
        Quiz quiz = checkQuizExistence(quizId);
        Question question = getQuestionFromQuestionService(questionId);
        if (!questionExistsInQuiz(question.getId(), quizId)) {
            quiz.getQuestions().add(question);
            saveQuiz(quiz);
        } else {
            throw new QuestionDuplicatedException("This question is already associated with the quiz.");
        }
    }

    @Override
    public void updateQuiz(QuizDto quizDto, int id) {
        Quiz quiz = checkQuizExistence(id);
        updateMapper.updateQuizFromDto(quizDto, quiz);
        saveQuiz(quiz);
    }

    @Override
    public void deleteQuiz(int id) {
        Quiz quiz = checkQuizExistence(id);
        quizRepository.delete(quiz);
    }

    @Override
    public void deleteQuestionFromQuiz(int quizId, long questionId) {
        Quiz quiz = checkQuizExistence(quizId);
        Question questionToDelete = getQuestionFromQuestionService(questionId);
        List<Question> questions = quiz.getQuestions();
        if (questions.removeIf(question -> question.getId() == questionToDelete.getId())) {
            saveQuiz(quiz);
        }else {
            throw new QuestionNotFoundException("This question is not associated with this quiz");
        }
    }

    @Override
    public QuizDto getQuizById(int id) {
        QuizDto quizDto = quizMapper.quizToQuizDto(checkQuizExistence(id));
        return quizDto;
    }

    @Override
    public double getTotalDegreesOfQuiz(int id) {
        return checkQuizExistence(id).getTotalDegrees();
    }

    @Override
    public Page<QuizDto> getAllQuizzes(int page, int size, String sortField, String sortOrder) {
        Page<Quiz> quizPage = quizRepository.findAll
                (createPageRequest(page, size, sortField, sortOrder));
        return quizPage.map(quizMapper::quizToQuizDto);
    }

    @Override
    public Page<QuizDto> searchByCategory(String category, int page, int size, String sortField, String sortOrder) {
        Page<Quiz> quizPage = quizRepository.findQuizByCategory
                (category, createPageRequest(page, size, sortField, sortOrder));
        return quizPage.map(quizMapper::quizToQuizDto);
    }

    @Override
    public Page<QuizDto> searchByTitle(String title, int page, int size, String sortField, String sortOrder) {
        Page<Quiz> quizPage = quizRepository.findQuizByTitleContaining
                (title, createPageRequest(page, size, sortField, sortOrder));
        return quizPage.map(quizMapper::quizToQuizDto);
    }

    private Quiz checkQuizExistence(int id){
        return quizRepository.findById(id)
                .orElseThrow(() -> new QuizNotFoundException("this quiz is not Exist"));
    }

    private void calculateQuizTotalDegree(Quiz quiz) {
        List<Question> questions = quiz.getQuestions();
        if (questions != null && !questions.isEmpty()) {
            double totalDegrees = questions.stream().mapToDouble(Question::getDegree).sum();
            quiz.setTotalDegrees(totalDegrees);
        } else {
            quiz.setTotalDegrees(0.0); // Or any default value you prefer when there are no questions
        }
    }


    private boolean questionExistsInQuiz(long questionId, int quizId) {
        return quizRepository.existsByIdAndQuestions_Id(quizId, questionId);
    }
    private void saveQuiz(Quiz quiz){
        calculateQuizTotalDegree(quiz);
        quizRepository.save(quiz);
        System.out.println("save");
    }

    private Question getQuestionFromQuestionService(long questionId){
        QuestionDto questionDto = questionService.getQuestionById(questionId);
        Question question = questionMapper.questionDtoToQuestion(questionDto);
        return question;
    }

    private Pageable createPageRequest(int page, int size, String sortField, String sortOrder){
        return PageRequest.of(page, size, getSort(sortField, sortOrder));
    }

    private Sort getSort(String sortField, String sortOrder) {
        if ("asc".equalsIgnoreCase(sortOrder)) {
            return Sort.by(sortField).ascending();
        } else if("desc".equalsIgnoreCase(sortOrder)){
            return Sort.by(sortField).descending();
        }else{
            throw new SortValueException("order by desc or asc");
        }
    }
}
