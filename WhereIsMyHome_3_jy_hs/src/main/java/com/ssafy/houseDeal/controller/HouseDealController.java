package com.ssafy.houseDeal.controller;

import com.ssafy.houseDeal.model.HouseDealDto;
import com.ssafy.houseDeal.model.service.HouseDealService;

import lombok.AllArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/house")
@AllArgsConstructor
public class HouseDealController {

    private final HouseDealService houseDealService;

    @GetMapping
    public ResponseEntity<List<HouseDealDto>> getHouseDeals(@RequestParam Map<String, String> request) {
        List<HouseDealDto> houseDeals = houseDealService.getHouseDeals(request);
        return ResponseEntity.ok(houseDeals);
    }

}
