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
    List<Message> findByUserId(@Param("userId") long userId);

    /**
     * add one message.
     * 
     * @param msg msg
     * @return 1 if success otherwise 0
     */
    int insert(Message msg);

    /**
     * update group.
     * 
     * @param msg msg
     * @return 1 if success otherwise 0
     */
    int update(Message msg);

    /**
     * delete.
     * 
     * @param id id
     */
    void delete(int id);

}
