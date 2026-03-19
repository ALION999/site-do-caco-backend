package com.caco.sitedocaco.controller.publicController;

import com.caco.sitedocaco.dto.response.ExamWithoutSubjectDTO;
import com.caco.sitedocaco.entity.exam.Exam;
import com.caco.sitedocaco.entity.exam.Subject;
import com.caco.sitedocaco.security.ratelimit.RateLimit;
import com.caco.sitedocaco.service.ExamService;
import com.caco.sitedocaco.service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public/exams")
@RequiredArgsConstructor
@RateLimit(capacity = 30, refillTokens = 30, refillPeriod = 1)
public class ExamController {

    private final SubjectService subjectService;
    private final ExamService examService;

    @GetMapping("/subjects")
    public ResponseEntity<Page<Subject>> getAllSubjects(@PageableDefault(size = 20, sort = "name") Pageable pageable) {
        Page<Subject> subjects = subjectService.getAllSubjects(pageable);
        return ResponseEntity.ok(subjects);
    }

    @GetMapping
    public ResponseEntity<Page<Exam>> getAllExams(@PageableDefault(size = 20, sort = "year") Pageable pageable) {
        Page<Exam> exams = examService.getAllExams(pageable);
        return ResponseEntity.ok(exams);
    }

    @GetMapping("/subject/{subjectCode}")
    public ResponseEntity<Page<ExamWithoutSubjectDTO>> getExamsBySubject(
            @PathVariable String subjectCode,
            @PageableDefault(size = 20, sort = "year") Pageable pageable) {
        Page<ExamWithoutSubjectDTO> exams = examService.getExamsBySubject(subjectCode, pageable);
        return ResponseEntity.ok(exams);
    }
}