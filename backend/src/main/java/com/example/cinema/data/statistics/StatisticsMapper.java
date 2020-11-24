package com.example.cinema.data.statistics;

import com.example.cinema.po.AudiencePrice;
import com.example.cinema.po.Hall;
import com.example.cinema.po.MovieScheduleTime;
import com.example.cinema.po.MovieTotalBoxOffice;
import com.example.cinema.vo.HallVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @author fjj
 * @date 2019/4/16 1:43 PM
 */
@Mapper
public interface StatisticsMapper {
    /**
     * 查询date日期每部电影的排片次数 1
     * @param date
     * @return
     */
    List<MovieScheduleTime> selectMovieScheduleTimes(@Param("date") Date date, @Param("nextDate") Date nextDate);

    /**
     * 查询所有电影的总票房（包括已经下架的，降序排列） 1
     * @return
     */
    List<MovieTotalBoxOffice> selectMovieTotalBoxOffice();
    /**
     *  查询date日期每部电影的观众人数
     */
    List<MovieTotalBoxOffice> selectAudienceNum(@Param("date") Date date,@Param("nextDate") Date nextDate);


    /**
     *查询一定时间内的每部电影的总票房
     */
    List<MovieTotalBoxOffice> selectMovieBoxOfficeOnCertainDate(@Param("date") Date date,@Param("nextDate") Date nextDate);

    /**
     * 查询某天每个客户的购票金额 1
     * @param date
     * @param nextDate
     * @return
     */
    List<AudiencePrice> selectAudiencePrice(@Param("date") Date date, @Param("nextDate") Date nextDate);

    /**
     *查询每部电影某天的放映场数
     */
    List<Hall> selectTotalHalls();
}
