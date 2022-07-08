package com.team.mange.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.team.mange.mapper.MessageMapper;
import com.team.mange.entity.Message;
import com.team.mange.service.MessageService;


@Service("messageService")
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements MessageService {


}
