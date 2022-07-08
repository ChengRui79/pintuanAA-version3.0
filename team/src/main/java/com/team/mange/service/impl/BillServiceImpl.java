package com.team.mange.service.impl;

import com.team.mange.entity.Bill;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.team.mange.mapper.BillMapper;
import com.team.mange.service.BillService;


@Service("billService")
public class BillServiceImpl extends ServiceImpl<BillMapper, Bill> implements BillService {


}
