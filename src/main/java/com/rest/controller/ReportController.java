package com.rest.controller;

import com.rest.dto.ReportResponse;
import com.rest.service.ReportService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reports")
@CrossOrigin("*")
public class ReportController {

    private final ReportService service;

    public ReportController(ReportService service) {
        this.service = service;
    }

    @GetMapping
    public ReportResponse getReport() {
        return service.getReport();
    }
}