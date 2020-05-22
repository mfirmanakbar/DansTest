package com.mfirmanakbar.dans.controller;

import com.mfirmanakbar.dans.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PositionController {

    @Autowired
    PositionService positionService;

    @RequestMapping(value = "/positions", method = RequestMethod.GET)
    public ResponseEntity<?> positions() {
        return positionService.findAll();
    }

    @RequestMapping(value = "/positions/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> positionsById(@PathVariable String id) {
        return positionService.findById(id);
    }

}
