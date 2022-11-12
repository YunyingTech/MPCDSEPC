package com.yytech.mpcdsepc.service.impl;

import com.yytech.mpcdsepc.entity.Tube;
import com.yytech.mpcdsepc.mapper.TubeMapper;
import com.yytech.mpcdsepc.service.TubeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class TubeServiceImpl implements TubeService {
    @Resource
    private TubeMapper tubeMapper;
    @Override
    public boolean insertTube(Tube tube) {
        return false;
    }
    @Override
    public int deleteTube(int id){
        return tubeMapper.delTubeByID(id);
    }

    @Override
    public int getRollBackTimes(int id){
        return tubeMapper.getRollBackTimes(id);
    }

    @Override
    public Tube getTubeById(int id){
        return tubeMapper.getTubeById(id);
    }
}
