package com.cfs.BMS.Controller;


import com.cfs.BMS.dto.TheaterRequest;
import com.cfs.BMS.entity.Theater;
import com.cfs.BMS.service.TheaterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/theaters")
@RequiredArgsConstructor
public class TheaterController {

    private final TheaterService theaterService;

    //post add

    @GetMapping
    public ResponseEntity<List<Theater>> getAllTheaters()
    {
        return ResponseEntity.ok(theaterService.getAllTheaters());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Theater> getTheaterById(@PathVariable Long id)
    {
        return ResponseEntity.ok(theaterService.getTheaterById(id));
    }

    @GetMapping("/city/{cityId}")
    public ResponseEntity<List<Theater>> getTheaterByCity(@PathVariable Long cityId)
    {
        return ResponseEntity.ok(theaterService.getTheaterByCity(cityId));
    }

    @PostMapping
    public ResponseEntity<Theater> addTheater(@RequestBody TheaterRequest request) {
        return ResponseEntity.ok(theaterService.addTheater(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Theater> updateTheater(@PathVariable Long id,
                                                 @RequestBody TheaterRequest request) {
        return ResponseEntity.ok(theaterService.updateTheater(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTheater(@PathVariable Long id) {
        theaterService.deleteTheater(id);
        return ResponseEntity.ok("Theater deleted successfully");
    }


}