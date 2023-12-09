package com.basel.InterviewsQuizzes.service;

import com.basel.InterviewsQuizzes.exception.QuestionAssociatedWithQuizException;
import com.basel.InterviewsQuizzes.exception.QuestionNotFoundException;
import com.basel.InterviewsQuizzes.model.dto.QuestionDto;
import com.basel.InterviewsQuizzes.model.entity.Question;
import com.basel.InterviewsQuizzes.model.mapper.QuestionMapper;
import com.basel.InterviewsQuizzes.model.mapper.UpdateMapper;
import com.basel.InterviewsQuizzes.model.pojo.Option;
import com.basel.InterviewsQuizzes.repository.QuestionRepository;
import com.basel.InterviewsQuizzes.validation.DifficultyValidationAnnotation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService{

    private final QuestionRepository questionRepository;
    private final QuestionMapper questionMapper;
    private final UpdateMapper updateQuestionMapper;


    @Override
    @Transactional
    public void AddQuestion(QuestionDto questionDto) {
        Question newQuestion = questionMapper.questionDtoToQuestion(questionDto);
        questionRepository.save(newQuestion);
    }

    @Override
    @Transactional
    public void deleteQuestion(long id){
            checkQuestionExistence(id);
            checkQuestionInQuiz(id);
            questionRepository.deleteById(id);
    }


    @Override
    @Transactional
    public void updateQuestion(QuestionDto questionDto, long id) {
        Question question = checkQuestionExistence(id);
        updateQuestionMapper.updateQuestionFromDto(questionDto, question);
        questionRepository.save(question);
    }

    @Override
    @Transactional
    public void updateOptionsInQuestion(Option options, long id) {
        Question question = checkQuestionExistence(id);
        question.setOptions(options);
        questionRepository.save(question);
    }

    @Override
    public List<QuestionDto> getAllQuestions() {
        return questionMapper.ListQuestionsToListQuestionDto(questionRepository.findAll());
    }

    @Override
    public QuestionDto getQuestionById(long id) {
        Question question = checkQuestionExistence(id);
        return questionMapper.questionToQuestionDto(question);
    }

    @Override
    public List<QuestionDto> getQuestionByQuestionText( String qText) {
        List<Question> questions = questionRepository.findByQuestionTextContaining(qText);
        List<QuestionDto> questionDtos = questionMapper.ListQuestionsToListQuestionDto(questions);
        return questionDtos;
    }

    @Override
    public List<QuestionDto> getQuestionsByDifficulty( String difficulty) {
        List<Question> questions = questionRepository.findQuestionByDifficulty(difficulty);
        List<QuestionDto> questionDtos = questionMapper.ListQuestionsToListQuestionDto(questions);
        return questionDtos;
    }




    private Question checkQuestionExistence(long id) {
        return questionRepository.findById(id)
                .orElseThrow(() -> new QuestionNotFoundException("This question does not exist!"));
    }

    private void checkQuestionInQuiz(long id){
        Long qId = questionRepository.checkQuestionFromQuizzes(id);
        if(qId != null){
            throw new QuestionAssociatedWithQuizException("Question is associated with a quiz and cannot be deleted");
        }
    }



}
