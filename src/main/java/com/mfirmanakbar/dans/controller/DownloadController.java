package com.mfirmanakbar.dans.controller;

import com.mfirmanakbar.dans.response.PositionResponse;
import com.mfirmanakbar.dans.service.PositionService;
import com.mfirmanakbar.dans.utils.WriteDataToCSV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
public class DownloadController {

    @Autowired
    PositionService positionService;

    @GetMapping("/download/positions.csv")
    public void downloadCSV(HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; file=positions.csv");
        List<PositionResponse> positionResponses = (List<PositionResponse>) positionService.findAll().getBody();
        WriteDataToCSV.positionResponseToCsv(response.getWriter(), positionResponses);
    }

}
