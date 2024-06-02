package com.finaldesign.backend.controller.student.requirement;

import com.finaldesign.backend.pojo.Requirement;
import com.finaldesign.backend.pojo.Result;
import com.finaldesign.backend.service.student.requirement.RequirementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class RequirementController {
    @Autowired
    private RequirementService requirementService;

    @PostMapping("/student/release/requirement/")
    public Result releaseRequirement(@RequestBody Requirement requirement) {
        return requirementService.insertRequirement(requirement);
    }

    @GetMapping("/admin/get/all/requirement/")
    public Result getAllRequirements(@RequestParam Map<String, String> data) {
        Integer page = Integer.parseInt(data.get("page"));
        if (page == null || page <= 0) {
            return Result.fail("获取家教需求失败");
        }
        return requirementService.getAllRequirements(page);
    }

    @PostMapping("/admin/examine/requirement/yes/{id}/")
    public Result examineYesById(@PathVariable Integer id) {
        return requirementService.examineYesById(id);
    }

    @PostMapping("/admin/examine/requirement/not/{id}/")
    public Result examineNotById(@PathVariable Integer id) {
        return requirementService.examineNotById(id);
    }
}
