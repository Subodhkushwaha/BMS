package com.cfs.BMS.Controller;


import com.cfs.BMS.dto.DashboardResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AdminController {


        private final com.cfs.BMS.service.AdminService adminService;

        // Dashboard
        @GetMapping("/dashboard")
        public ResponseEntity<DashboardResponse> dashboard() {
            return ResponseEntity.ok(adminService.getDashboard());
        }

        // ================= Movies =================

        @PostMapping("/movies")
        public ResponseEntity<com.cfs.BMS.entity.Movie> addMovie(@RequestBody com.cfs.BMS.entity.Movie movie) {
            return ResponseEntity.ok(adminService.addMovie(movie));
        }

        @PutMapping("/movies/{id}")
        public ResponseEntity<com.cfs.BMS.entity.Movie> updateMovie(
                @PathVariable Long id,
                @RequestBody com.cfs.BMS.entity.Movie movie) {

            return ResponseEntity.ok(adminService.updateMovie(id, movie));
        }

        @DeleteMapping("/movies/{id}")
        public ResponseEntity<String> deleteMovie(@PathVariable Long id) {

            adminService.deleteMovie(id);

            return ResponseEntity.ok("Movie Deleted Successfully");
        }

        // ================= Theater =================

        @PostMapping("/theaters")
        public ResponseEntity<com.cfs.BMS.entity.Theater> addTheater(@RequestBody com.cfs.BMS.entity.Theater theater) {

            return ResponseEntity.ok(adminService.addTheater(theater));
        }

        @PutMapping("/theaters/{id}")
        public ResponseEntity<com.cfs.BMS.entity.Theater> updateTheater(
                @PathVariable Long id,
                @RequestBody com.cfs.BMS.entity.Theater theater) {

            return ResponseEntity.ok(adminService.updateTheater(id, theater));
        }

        @DeleteMapping("/theaters/{id}")
        public ResponseEntity<String> deleteTheater(@PathVariable Long id) {

            adminService.deleteTheater(id);

            return ResponseEntity.ok("Theater Deleted");
        }

        // ================= Shows =================

        @PostMapping("/shows")
        public ResponseEntity<com.cfs.BMS.entity.Show> addShow(@RequestBody com.cfs.BMS.entity.Show show) {

            return ResponseEntity.ok(adminService.addShow(show));
        }

        @PutMapping("/shows/{id}")
        public ResponseEntity<com.cfs.BMS.entity.Show> updateShow(
                @PathVariable Long id,
                @RequestBody com.cfs.BMS.entity.Show show) {

            return ResponseEntity.ok(adminService.updateShow(id, show));
        }

        @DeleteMapping("/shows/{id}")
        public ResponseEntity<String> deleteShow(@PathVariable Long id) {

            adminService.deleteShow(id);

            return ResponseEntity.ok("Show Deleted");
        }
    }
