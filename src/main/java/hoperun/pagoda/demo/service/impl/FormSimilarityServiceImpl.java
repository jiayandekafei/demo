package hoperun.pagoda.demo.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hoperun.pagoda.demo.constant.Constant;
import hoperun.pagoda.demo.entity.FormSimilarity;
import hoperun.pagoda.demo.mapper.FormSimilarityMapper;
import hoperun.pagoda.demo.service.FormSimilarityService;

/**
 * notes db service.
 * @author zhangxiqin
 *
 */
@Service
public class FormSimilarityServiceImpl implements FormSimilarityService {

    /**
     * Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(FormSimilarityServiceImpl.class);

    /**
     * DB similarity mapper.
     */
    @Autowired
    FormSimilarityMapper formSimiMapper;

    /**
     * insert.
     */
    @Override
    @Transactional
    public void insert(final FormSimilarity formSimilarity) {
        String method = "insert";
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(Constant.LOG_PATTERLN, method, "form similarity data insert started");
        }
        formSimiMapper.insert(formSimilarity);
    }

    /**
     * get similarity by group.
     */
    @Override
    public List<FormSimilarity> getSimiDataByName(final String dbName) {
        String method = "findFormSimiDataByDbName";
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(Constant.LOG_PATTERLN, method, "from similarity Data retrieve start");
        }
        return formSimiMapper.findFormSimiDataByDbName(dbName);
    }

    /**
     * delete similarity data by DB name.
     */
    @Override
    @Transactional
    public void deleteByDbName(final String dbName) {
        String method = "deleteByDbName";
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(Constant.LOG_PATTERLN, method, "similarity Data delete start");
        }
        formSimiMapper.deleteByDBName(dbName);
    }

}
