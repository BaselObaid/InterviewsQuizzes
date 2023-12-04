package com.basel.InterviewsQuizzes.service;

import com.basel.InterviewsQuizzes.exception.QuestionNotFoundException;
import com.basel.InterviewsQuizzes.model.dto.QuestionDto;
import com.basel.InterviewsQuizzes.model.entity.Question;
import com.basel.InterviewsQuizzes.model.mapper.QuestionMapper;
import com.basel.InterviewsQuizzes.model.pojo.Difficulty;
import com.basel.InterviewsQuizzes.model.pojo.Option;
import com.basel.InterviewsQuizzes.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService{

    private final QuestionRepository questionRepository;
    private final QuestionMapper questionMapper;

    @Override
    @Transactional
    public void AddQuestion(QuestionDto questionDto) {
        Question newQuestion = questionMapper.questionDtoToQuestion(questionDto);
        questionRepository.save(newQuestion);
    }

    @Override
    @Transactional
    public void deleteQuestion(long id) {
        Question question = checkQuestionExistence(id);
        questionRepository.delete(question);
    }

    @Override
    @Transactional
    public void updateQuestion(QuestionDto questionDto, long id) {
        Question question = checkQuestionExistence(id);
        questionMapper.updateQuestionFromDto(questionDto, question);
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
    public QuestionDto getQuestionByQuestionText(String qText) {
        return null;
    }

    @Override
    public List<QuestionDto> getQuestionsByDifficulty(Difficulty difficulty) {
        List<Question> questions = questionRepository.findQuestionByDifficulty(difficulty);
            return questionMapper.ListQuestionsToListQuestionDto(questions);
    }

    @Override
    @Transactional
    public void updateOptionsInQuestion(Option options, long id) {
        Question question = checkQuestionExistence(id);
        question.setOptions(options);
        questionRepository.save(question);
    }


    private Question checkQuestionExistence(long id) {
        return questionRepository.findById(id)
                .orElseThrow(() -> new QuestionNotFoundException("This question does not exist!"));
    }
}
