package com.assignment.boundfour.project;

import com.assignment.boundfour.project.dto.ProjectInfoResponse;
import com.assignment.boundfour.project.dto.ProjectModifyRequest;
import com.assignment.boundfour.project.dto.ProjectSaveRequest;
import com.assignment.boundfour.user.Plan;
import com.assignment.boundfour.user.PlanRepository;
import com.assignment.boundfour.user.User;
import com.assignment.boundfour.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    private final UserRepository userRepository;

    public Long save(ProjectSaveRequest request) {
        Project project = request.toEntity();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        long projectCount = this.projectRepository.countByUserUsername(username);
        User user = this.userRepository.findByUsername(username)
                        .orElseThrow(() -> new RuntimeException("유저 없음"));
        project.setOwner(user);

        Plan plan = user.getPlan();
        if (projectCount >= plan.getLimit()) {
            throw new RuntimeException(plan.getName() + " PLAN은 " + plan.getLimit() + "개 제한.");
        }

        return this.projectRepository.save(project).getId();
    }

    public ProjectInfoResponse info(Long id) {
        try {
            Project project = this.projectRepository.findById(id)
                    .orElseThrow();

            return ProjectInfoResponse.fromEntity(project);
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    public void modify(Long id, ProjectModifyRequest request) {
        Project project = this.projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("프로젝트 없음."));

        project.modify(request.managerId(), request.status(), request.deadline());
        this.projectRepository.save(project);
    }

    public void delete(Long id) {
        this.projectRepository.deleteById(id);
    }
}
