package com.yytech.mpcdsepc.service;

import com.yytech.mpcdsepc.entity.Tube;
import org.springframework.stereotype.Service;

@Service
public interface TubeService {
    boolean insertTube(Tube tube);
    int deleteTube(int tubeId);
}
