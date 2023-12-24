package com.basel.InterviewsQuizzes.controller;

import com.basel.InterviewsQuizzes.model.dto.QuestionDto;
import com.basel.InterviewsQuizzes.model.pojo.Option;
import com.basel.InterviewsQuizzes.service.QuestionService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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

    @PatchMapping("/{id}/degree")
    public ResponseEntity updateQuestionDegree(@RequestParam double degree, @PathVariable long id){
        questionService.updateQuestionDegree(degree, id);
        return ResponseEntity.status(200).build();
    }

    @PatchMapping("/{id}/option")
    public ResponseEntity updateOptionsInQuestion(@RequestBody Option option, @PathVariable long id){
        questionService.updateOptionsInQuestion(option, id);
        return ResponseEntity.status(200).build();
    }

    @GetMapping()
    public ResponseEntity<List<QuestionDto>> getAllQuestions(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortField,
            @RequestParam(defaultValue = "asc") String sortOrder
    ){
        return new ResponseEntity(questionService.getAllQuestions(page, size, sortField, sortOrder), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity getQuestionById(@PathVariable long id){
        return new ResponseEntity(questionService.getQuestionById(id),HttpStatus.OK);
    }

    @GetMapping("/search/text")
    public ResponseEntity searchQuestions(
            @NotBlank @RequestParam String text,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortField,
            @RequestParam(defaultValue = "asc") String sortOrder
            ){
        return new ResponseEntity(questionService.getQuestionByQuestionText(text, page, size, sortField, sortOrder), HttpStatus.OK);
    }

    @GetMapping("/search/difficulty")
    public ResponseEntity searchQuestionsByDifficulty(
            @NotBlank @RequestParam String difficulty,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortField,
            @RequestParam(defaultValue = "asc") String sortOrder
            ){
        Page<QuestionDto> questions = questionService.getQuestionsByDifficulty(difficulty.trim(), page, size, sortField, sortOrder);
        return new ResponseEntity(questions, HttpStatus.OK);
    }


//    private Map<String, Object> createSuccessResponseWithData(Object data, String message,boolean status) {
//        return Map.of(
//                "success", status,
//                "data", data,
//                "message", message
//        );
//    }
}



