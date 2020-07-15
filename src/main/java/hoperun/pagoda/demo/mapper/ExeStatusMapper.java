package hoperun.pagoda.demo.mapper;

import org.apache.ibatis.annotations.Mapper;

import hoperun.pagoda.demo.entity.ExeStatus;

/**
 * Execute status mapper.
 *
 * @author zhangxiqin
 *
 */
@Mapper
public interface ExeStatusMapper {
    /**
     * retrieve execute status by group and type .
     * 
     * @param groupId groupId
     * @param type type
     * @return ExeStatus Data
     */
    ExeStatus findByTypeAndGroup(int groupId, int type);

    /**
     * insert.
     * 
     * @param exeStatus ExeStatus
     */
    void insert(ExeStatus exeStatus);

    /**
     * update.
     * 
     * @param exeStatus ExeStatus
     */
    void update(ExeStatus exeStatus);

}
