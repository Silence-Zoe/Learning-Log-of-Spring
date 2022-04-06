package com.silence.mapper;

import com.silence.DO.LoginTicketDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface LoginTicketMapper {

    int save(LoginTicketDO loginTicket);

    LoginTicketDO getByTicket(@Param("ticket") String ticket);

    int updateStatusByTicket(@Param("ticket") String ticket, @Param("status") Integer status);
}
