package com.basel.InterviewsQuizzes.controller;

import com.basel.InterviewsQuizzes.model.dto.QuestionDto;
import com.basel.InterviewsQuizzes.model.pojo.Option;
import com.basel.InterviewsQuizzes.service.QuestionService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("questions")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;

    @PostMapping("/")
    public ResponseEntity addQuestion(@RequestBody @Valid QuestionDto questionDto){
        questionService.AddQuestion(questionDto);
        return ResponseEntity.status(201).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteQuestionById(@PathVariable long id) {
            questionService.deleteQuestion(id);
            return ResponseEntity.status(200).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity updateQuestionById(@RequestBody @Valid QuestionDto questionDto, @PathVariable long id){
        questionService.updateQuestion(questionDto, id);
        return ResponseEntity.status(200).build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity updateOptionsInQuestion(@RequestBody Option option, @PathVariable long id){
        questionService.updateOptionsInQuestion(option, id);
        return ResponseEntity.status(200).build();
    }

    @GetMapping()
    public ResponseEntity<List<QuestionDto>> getAllQuestions(){
        return new ResponseEntity<>(questionService.getAllQuestions(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity getQuestionById(@PathVariable long id){
        return new ResponseEntity(questionService.getQuestionById(id),HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity searchQuestions(@NotBlank @RequestParam String text){
        return new ResponseEntity(questionService.getQuestionByQuestionText(text), HttpStatus.OK);
    }

    @GetMapping("/search/difficulty")
    public ResponseEntity searchQuestionsByDifficulty( @RequestParam String difficulty){
        return new ResponseEntity(questionService.getQuestionsByDifficulty(difficulty.trim()), HttpStatus.OK);
    }

}



