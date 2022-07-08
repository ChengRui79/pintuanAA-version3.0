package com.team.mange.service.impl;

import com.team.mange.entity.CostHis;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.team.mange.mapper.CostHisMapper;
import com.team.mange.service.CostHisService;


@Service("costHisService")
public class CostHisServiceImpl extends ServiceImpl<CostHisMapper, CostHis> implements CostHisService {


}
