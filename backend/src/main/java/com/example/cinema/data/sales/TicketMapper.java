package com.example.cinema.data.sales;

import com.example.cinema.po.Ticket;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

/**
 * Created by liying on 2019/4/16.
 */
@Mapper
public interface TicketMapper {

    int insertTicket(Ticket ticket);

    int insertTickets(List<Ticket> tickets);

    void deleteTicket(int ticketId);

    void updateTicketState(@Param("ticketId") int ticketId, @Param("state") int state);

    List<Ticket> selectTicketsBySchedule(int scheduleId);

    Ticket selectTicketByScheduleIdAndSeat(@Param("scheduleId") int scheduleId, @Param("column") int columnIndex, @Param("row") int rowIndex);

    Ticket selectTicketById(int id);

    List<Ticket> selectTicketByUser(int userId);

    /**
     * 更新票的实际花费
     * @param ticketId
     * @param consume 票的实际花费
     */
    void updateTicketConsume(@Param("ticketId") int ticketId,@Param("consume") double consume);

    /**
     * 更新票的付款方式 1是会员卡，0是银行卡
     * @param ticketId
     * @param way
     */
    void updateTicketWay(@Param("ticketId") int ticketId,@Param("way") double way);

    /**
     * 按照用户id、排片id查找状态为0，即未付款的票
     * @param userId
     * @param scheduleId
     * @return
     */
    List<Ticket> selectTicketByUserScheduleAndState(@Param("userId") int userId,@Param("scheduleId")int scheduleId);
    /**
     * 删除失效过期的票
     */
    @Scheduled(cron = "0/1 * * * * ?")
    void cleanExpiredTicket();

    /**
     * 在退票的数据库插入一张已经退了的票
     * @param ticket
     * @return
     */
    int insertRefundedTicket(Ticket ticket);

    /**
     * 按照用户id查找所有的退票
     * @param userId
     * @return
     */
    List<Ticket> selectRefundedTicketByUser(int userId);
}

