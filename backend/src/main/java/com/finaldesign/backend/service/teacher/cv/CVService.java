package com.finaldesign.backend.service.teacher.cv;

import com.finaldesign.backend.pojo.CV;
import com.finaldesign.backend.pojo.ConditionQueryTeacher;
import com.finaldesign.backend.pojo.Result;

public interface CVService {
    Result insertCV(CV cv);
    Result getJobInformation();
    Result multipleTablesAndCondition(Integer currentPage, ConditionQueryTeacher condition);
    Result getTeacherInfoById(Integer id);
    Result getTeacherNew();
    Result getAllCV(Integer page);
    Result examineYesById(Integer id);
    Result examineNotById(Integer id);
}
