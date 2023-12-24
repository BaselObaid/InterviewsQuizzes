package com.basel.InterviewsQuizzes.controller;

import com.basel.InterviewsQuizzes.model.dto.QuizDto;
import com.basel.InterviewsQuizzes.repository.QuizRepository;
import com.basel.InterviewsQuizzes.service.QuizService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("quizzes")
@RequiredArgsConstructor
public class QuizController {

    private final QuizService quizService;
    private final QuizRepository quizRepository;

    @PostMapping("/")
    public ResponseEntity addNewQuiz(@RequestBody @Valid QuizDto quizDto){
        quizService.addQuiz(quizDto);
        return ResponseEntity.status(201).build();
    }

    @PatchMapping("/quiz/{quizId}")
    public ResponseEntity addQuestionsToQuiz(@PathVariable int quizId, @RequestParam long questionId){
        quizService.addQuestionToQuiz(questionId, quizId);
        return ResponseEntity.status(200).build();
    }

    @PatchMapping("/quiz/delete/{quizId}")
    public ResponseEntity deleteQuestionFromQuiz(@PathVariable int quizId, @RequestParam long questionId) {
        quizService.deleteQuestionFromQuiz(quizId, questionId);
        return ResponseEntity.status(200).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity updateQuiz(@RequestBody @Valid QuizDto quizDto, @PathVariable int id){
        quizService.updateQuiz(quizDto, id);
        return ResponseEntity.status(200).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteQuiz(@PathVariable int id){
        quizService.deleteQuiz(id);
        return ResponseEntity.status(200).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity getQuizById(@PathVariable int id){
        return ResponseEntity.status(200).body(quizService.getQuizById(id));
    }

    @GetMapping("/totalDegrees/{id}")
    public ResponseEntity getTotalDegreesOfQuiz(@PathVariable int id){
        return ResponseEntity.status(200).body(quizService.getTotalDegreesOfQuiz(id));
    }

    @GetMapping()
    public ResponseEntity getAllQuizzes(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortField,
            @RequestParam(defaultValue = "asc") String sortOrder
    ){
        return ResponseEntity.status(200).body(quizService.getAllQuizzes(page, size, sortField, sortOrder));
    }

    @GetMapping("/quiz/category")
    public ResponseEntity getQuizByCategory(
            @NotBlank @RequestParam @Valid String category,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortField,
            @RequestParam(defaultValue = "asc") String sortOrder
    ){
        return ResponseEntity.status(200).body(quizService.searchByCategory(category, page, size, sortField, sortOrder));
    }

    @GetMapping("/quiz/title")
    public ResponseEntity getQuizByTitle(
            @NotBlank @RequestParam String title,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortField,
            @RequestParam(defaultValue = "asc") String sortOrder
    ){
        return ResponseEntity.status(200).body(quizService.searchByTitle(title, page, size, sortField, sortOrder));
    }

}
