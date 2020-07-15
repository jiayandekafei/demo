package hoperun.pagoda.demo.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hoperun.pagoda.demo.bean.DBSimiPieResponse;
import hoperun.pagoda.demo.constant.Constant;
import hoperun.pagoda.demo.entity.DBSimiPieData;
import hoperun.pagoda.demo.entity.DBSimilarity;
import hoperun.pagoda.demo.mapper.DBSimilarityMapper;
import hoperun.pagoda.demo.service.DBSimilarityService;

/**
 * notes db service.
 * @author zhangxiqin
 *
 */
@Service
public class DBSimilarityServiceImpl implements DBSimilarityService {

    /**
     * Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(DBSimilarityServiceImpl.class);

    /**
     * DB similarity mapper.
     */
    @Autowired
    DBSimilarityMapper dbSimiMapper;

    /**
     * insert.
     */
    @Override
    @Transactional
    public void insert(final DBSimilarity dbSimilarity) {
        String method = "insert";
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(Constant.LOG_PATTERLN, method, "DB similarity data insert started");
        }
        dbSimiMapper.insert(dbSimilarity);
    }

    /**
     * get similarity by group.
     */
    @Override
    public List<DBSimilarity> getSimiDataByGroup(final int groupId) {
        String method = "getSimiDataByGroup";
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(Constant.LOG_PATTERLN, method, "similarity Data retrieve start");
        }
        return dbSimiMapper.findCateGoryDataByGroupId(groupId);
    }

    /**
     * delete similarity data by group.
     */
    @Override
    @Transactional
    public void deleteSimiDataByGroupId(final int groupId) {
        String method = "deleteSimiDataByGroupId";
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(Constant.LOG_PATTERLN, method, "DB similarity Data delete start");
        }
        dbSimiMapper.deleteByGroupId(groupId);
    }

    /**
     * get DB simi distribution data.
     */
    @Override
    public DBSimiPieResponse getDBSimiDistributionData(final int groupId) {
        List<DBSimilarity> data = getSimiDataByGroup(groupId);
        Set<String> categoryNo = data.stream().map(DBSimilarity::getCategoryNo).collect(Collectors.toSet());
        List<DBSimiPieData> pieData = new ArrayList<>();
        List<String> categoryDB = new ArrayList<>();
        String oldNo = "";
        for (int i = 0; i < data.size(); i++) {
            DBSimilarity dbSimi = data.get(i);
            String no = dbSimi.getCategoryNo();
            if (!no.equals(oldNo) && i != 0) {
                pieData.add(DBSimiPieData.builder().name(oldNo).dbName(categoryDB).value(data.get(i - 1).getDbSize()).build());
                categoryDB = new ArrayList<>();
            } else if (i == data.size() - 1) {
                categoryDB.add(dbSimi.getJpName());
                pieData.add(DBSimiPieData.builder().name(oldNo).dbName(categoryDB).value(data.get(i - 1).getDbSize()).build());
                categoryDB = new ArrayList<>();
            }
            categoryDB.add(dbSimi.getJpName());
            oldNo = no;
        }
        return DBSimiPieResponse.builder().categoryNo(categoryNo).dbSimiPie(pieData).build();
    }
}
