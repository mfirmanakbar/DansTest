package com.mfirmanakbar.dans.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mfirmanakbar.dans.response.PositionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.IOException;
import java.util.List;

@Service
public class PositionServiceImpl implements PositionService {

    private String positionBaseUrl = "https://jobs.github.com/positions";

    @Override
    public ResponseEntity<?> findAll() {
        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl = positionBaseUrl + ".json";
        ResponseEntity<String> response = restTemplate.getForEntity(fooResourceUrl, String.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            ObjectMapper objectMapper = new ObjectMapper();
            String positionsJson = response.getBody();
            try {
                List<PositionResponse> positionsResponse = objectMapper.readValue(
                        positionsJson,
                        new TypeReference<List<PositionResponse>>() {
                        });

                return new ResponseEntity<>(positionsResponse, HttpStatus.OK);
            } catch (IOException e) {
                e.printStackTrace();
                return new ResponseEntity<>("Error when mapping position", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity<>("Data not found", HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<?> findById(String id) {
        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl = positionBaseUrl + "/" + id + ".json";
        ResponseEntity<String> response = restTemplate.getForEntity(fooResourceUrl, String.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            ObjectMapper objectMapper = new ObjectMapper();
            String positionJson = response.getBody();
            try {
                PositionResponse positionResponse = objectMapper.readValue(positionJson, PositionResponse.class);
                return new ResponseEntity<>(positionResponse, HttpStatus.OK);
            } catch (IOException e) {
                e.printStackTrace();
                return new ResponseEntity<>("Error when mapping position", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity<>("Data not found", HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<?> downloadAll() {
        return new ResponseEntity<>("Data not found", HttpStatus.NOT_FOUND);
    }
}
