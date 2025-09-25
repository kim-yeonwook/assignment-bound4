package com.assignment.boundfour.project;

import com.assignment.boundfour.project.dto.ProjectInfoResponse;
import com.assignment.boundfour.project.dto.ProjectModifyRequest;
import com.assignment.boundfour.project.dto.ProjectSaveRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/projects")
@RestController
public class ProjectController {

    private final ProjectService projectService;

    @PostMapping
    public ResponseEntity<?> save(@RequestBody ProjectSaveRequest request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(this.projectService.save(request));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('A', 'D')")
    public ResponseEntity<?> info(@PathVariable Long id) {
        ProjectInfoResponse response = this.projectService.info(id);

        if (response == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('A', 'B')")
    public ResponseEntity<?> modify(@PathVariable Long id, @RequestBody ProjectModifyRequest request) {
        this.projectService.modify(id, request);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('A', 'B', 'C')")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        this.projectService.delete(id);

        return ResponseEntity.ok().build();
    }
}
