package com.finaldesign.backend.service.teacher;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.finaldesign.backend.pojo.CV;
import com.finaldesign.backend.pojo.ConditionQueryTeacher;
import com.finaldesign.backend.pojo.Result;
import com.finaldesign.backend.pojo.TeacherInfo;

public interface CVService {
    Result insertCV(CV cv);
    Result getJobInformation();
    Result multipleTablesAndCondition(Integer currentPage, ConditionQueryTeacher condition);
}
