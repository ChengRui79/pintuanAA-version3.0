package com.team.mange.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.team.mange.mapper.JoinRecordMapper;
import com.team.mange.entity.JoinRecord;
import com.team.mange.service.JoinRecordService;


@Service("joinRecordService")
public class JoinRecordServiceImpl extends ServiceImpl<JoinRecordMapper, JoinRecord> implements JoinRecordService {


}
