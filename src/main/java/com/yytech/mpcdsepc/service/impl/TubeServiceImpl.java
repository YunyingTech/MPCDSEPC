package com.yytech.mpcdsepc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yytech.mpcdsepc.entity.Tube;
import com.yytech.mpcdsepc.mapper.TubeMapper;
import com.yytech.mpcdsepc.service.TubeService;
import org.springframework.stereotype.Service;

@Service
public class TubeServiceImpl extends ServiceImpl<TubeMapper, Tube> implements TubeService {
}
