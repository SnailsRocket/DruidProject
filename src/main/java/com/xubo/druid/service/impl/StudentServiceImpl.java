package com.xubo.druid.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xubo.druid.mapper.StudentMapper;
import com.xubo.druid.service.StudentService;
import org.springframework.stereotype.Service;
import com.xubo.druid.entity.domain.Student;

/**
 *
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student>
    implements StudentService {

}




