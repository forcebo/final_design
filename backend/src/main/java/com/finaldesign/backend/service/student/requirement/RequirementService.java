package com.finaldesign.backend.service.student.requirement;

import com.finaldesign.backend.pojo.Requirement;
import com.finaldesign.backend.pojo.Result;
import org.springframework.stereotype.Service;

public interface RequirementService {
    Result insertRequirement(Requirement requirement);
    Result getAllRequirements(Integer page);
    Result examineYesById(Integer id);
    Result examineNotById(Integer id);
}
