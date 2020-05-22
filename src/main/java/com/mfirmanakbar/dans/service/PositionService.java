package com.mfirmanakbar.dans.service;

import org.springframework.http.ResponseEntity;

public interface PositionService {
    ResponseEntity<?> findAll();

    ResponseEntity<?> findById(String id);

    ResponseEntity<?> downloadAll();
}
