package com.basel.InterviewsQuizzes.service;

import com.basel.InterviewsQuizzes.exception.question.QuestionAssociatedWithQuizException;
import com.basel.InterviewsQuizzes.exception.question.QuestionNotFoundException;
import com.basel.InterviewsQuizzes.exception.global.SortValueException;
import com.basel.InterviewsQuizzes.model.dto.QuestionDto;
import com.basel.InterviewsQuizzes.model.entity.Question;
import com.basel.InterviewsQuizzes.model.mapper.QuestionMapper;
import com.basel.InterviewsQuizzes.model.mapper.UpdateMapper;
import com.basel.InterviewsQuizzes.model.pojo.Option;
import com.basel.InterviewsQuizzes.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public void updateQuestionDegree(double degree, long id){
        Question question = checkQuestionExistence(id);
        question.setDegree(degree);
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
    public Page<QuestionDto> getAllQuestions(int page, int size, String sortField, String sortOrder) {
        Page<Question> questionsPage = questionRepository.findAll
                (createPageRequest(page, size, sortField, sortOrder));
        return questionsPage.map(questionMapper::questionToQuestionDto);
    }

    @Override
    public QuestionDto getQuestionById(long id) {
        Question question = checkQuestionExistence(id);
        return questionMapper.questionToQuestionDto(question);
    }

    @Override
    public Page<QuestionDto> getQuestionByQuestionText( String qText, int page, int size, String sortField, String sortOrder) {
        Page<Question> questionsPage = questionRepository.findByQuestionTextContaining
                (qText, createPageRequest(page, size, sortField, sortOrder));
        return questionsPage.map(questionMapper::questionToQuestionDto);
    }

    @Override
    public Page<QuestionDto> getQuestionsByDifficulty(String difficulty, int page, int size, String sortField, String sortOrder) {
        Page<Question> questionPage = questionRepository.findQuestionByDifficulty
                    (difficulty, createPageRequest(page, size, sortField, sortOrder));
        return questionPage.map(questionMapper::questionToQuestionDto);
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
