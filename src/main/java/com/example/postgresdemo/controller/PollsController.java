package com.example.postgresdemo.controller;

import com.example.postgresdemo.exception.ResourceNotFoundException;
import com.example.postgresdemo.model.Polls;
import com.example.postgresdemo.repository.PollsRepository;
import com.example.postgresdemo.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RestController
public class PollsController {

    @Autowired
    private PollsRepository pollsRepository;

    @GetMapping("/polls")
    public Page<Polls> getPolls(
            @RequestParam(value="name", required=false, defaultValue="") String name,
            @RequestParam(value="date", required=false, defaultValue="") Date date,
            @RequestParam(value="active", required=false, defaultValue="") Boolean active,
            @RequestParam(value="sort", required=true, defaultValue="") String Sort,
            Pageable pageable) {

            if (Sort == "name"){
                return (Page<Polls>) pollsRepository.findByByNameOrActiveOrDateStartOrderByNameAsc(name, active, date, pageable);
            }else{
                return (Page<Polls>) pollsRepository.findByByNameOrActiveOrDateStartOrderByDateStartAsc(name, active, date, pageable);
            }

    }



    @PostMapping("/polls")
    public Polls addPolls(@Valid @RequestBody Polls polls) {
        return pollsRepository.save(polls);
    }

    @PutMapping("/polls/{pollsId}")
    public Polls updatePolls(@PathVariable Long pollsId,
                               @Valid @RequestBody Polls pollsRequest) {

        return pollsRepository.findById(pollsId)
                .map(polls -> {
                    polls.setText(pollsRequest.getText());
                    return pollsRepository.save(polls);
                }).orElseThrow(() -> new ResourceNotFoundException("Polls not found with id " + pollsId));
    }

    @DeleteMapping("/polls/{pollsId}")
    public ResponseEntity<?> deletePolls(@PathVariable Long pollsId) {

        return pollsRepository.findById(pollsId)
                .map(polls -> {
                    pollsRepository.delete(polls);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Polls not found with id " + pollsId));

    }
}
