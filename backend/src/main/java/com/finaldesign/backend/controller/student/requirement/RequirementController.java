package com.finaldesign.backend.controller.student.requirement;

import com.finaldesign.backend.pojo.Requirement;
import com.finaldesign.backend.pojo.Result;
import com.finaldesign.backend.service.student.requirement.RequirementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class RequirementController {
    @Autowired
    private RequirementService requirementService;

    @PostMapping("/student/release/requirement/")
    public Result releaseRequirement(@RequestBody Requirement requirement) {
        return requirementService.insertRequirement(requirement);
    }
}
