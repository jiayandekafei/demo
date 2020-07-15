package hoperun.pagoda.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import hoperun.pagoda.dxlAnalyse.quantity.entity.DBInfo;

/**
 * DB info mapper.
 *
 * @author zhangxiqin
 *
 */
@Mapper
public interface DBInfoMapper {
    /**
     * retrieve all .
     * 
     * @param groupId groupId
     * @return DBInfo Data
     */
    List<DBInfo> findAllDBByGroup(int groupId);

    /**
     * insert.
     * 
     * @param dbInfo DB Info
     */
    void insert(DBInfo dbInfo);

    /**
     * delete .
     * 
     * @param groupId groupId
     */
    void deleteByGroupId(int groupId);
}
