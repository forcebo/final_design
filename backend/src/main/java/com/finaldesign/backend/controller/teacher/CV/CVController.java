package com.finaldesign.backend.controller.teacher.CV;

import com.finaldesign.backend.pojo.CV;
import com.finaldesign.backend.pojo.Result;
import com.finaldesign.backend.service.teacher.CVService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CVController {
    @Autowired
    private CVService cvService;

    @PostMapping("/teacher/release/job/information/")
    private Result releaseJobInformation(@RequestBody CV cv) {
        return cvService.insertCV(cv);
    }
}
