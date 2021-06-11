package com.miscorf.dao;

import com.miscorf.pojo.House;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HouseMapper {
    boolean addHouse(House house);
    List<House> allHouse();
    List<House> allUserHouse(@Param("user_name")String user_name);
    boolean deleteHouse(House house );
    List<House> querryHouseByName(@Param("begin_num") int begin_num, @Param("page_size") int page_size,@Param("house_user_name") String house_user_name);
    boolean updateHouse(House house);
    List<House> allHousePage(@Param("begin_num") int begin_num, @Param("page_size") int page_size, @Param("house_user_name") String house_user_name);
}
