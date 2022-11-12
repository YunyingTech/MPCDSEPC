package com.yytech.mpcdsepc.mapper;

import com.yytech.mpcdsepc.entity.Tube;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Mapper
@Repository
public interface TubeMapper {
    List<Tube> getAllTubes();
    Date getTubeCreateDateByID(Integer id);
    Integer getTubeCreatorIDByID(Integer id);
    Integer getTubeLastModifierIDByID(Integer id);

    int updateTubeCreateDateByID(Tube tube);
    int updateTubeCreatorByID(Tube tube);
    int updateTubeLastModifierIDByID(Tube tube);

    int addTube(Tube tube);

    int delTubeByID(Integer tubeId);

}
