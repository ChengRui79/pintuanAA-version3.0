package com.team.mange.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.team.mange.mapper.TeamMapper;
import com.team.mange.entity.Team;
import com.team.mange.service.TeamService;


@Service("teamService")
public class TeamServiceImpl extends ServiceImpl<TeamMapper, Team> implements TeamService {


}
