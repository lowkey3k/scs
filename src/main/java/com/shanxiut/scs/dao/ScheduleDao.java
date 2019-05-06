package com.shanxiut.scs.dao;

import com.shanxiut.scs.entity.Schedule;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by hliu on 2019/3/9.
 */
@Repository
public interface ScheduleDao extends SuperDao<Schedule,Long>{


    @Query(value="select t.* from scs_schedule t JOIN scs_schedule_students t1 where t1.student_id = ?1 and t.id=t1.schedule_id",nativeQuery = true)
    List<Schedule> findSchedulesById(Long studentID);

    @Query(value="select t2.* from scs_teacher t1,scs_schedule t2 where t1.id=t2.teacher_id and t1.id=?1",nativeQuery = true)
    List<Schedule> findSchedulesByTeacherId(Long teacherID);


}
