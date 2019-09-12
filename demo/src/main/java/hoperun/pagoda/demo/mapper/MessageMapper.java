package hoperun.pagoda.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import hoperun.pagoda.demo.entity.Message;

/**
 * message mapper.
 *
 * @author zhangxiqin
 *
 */
@Mapper
public interface MessageMapper {
    /**
     * find by user id.
     *
     * @param userId
     *            userId
     * @return message list
     */
    List<Message> findByUserId(final @Param("userId") long userId);

}
