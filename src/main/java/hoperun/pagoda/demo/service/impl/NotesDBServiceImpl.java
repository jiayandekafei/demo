package hoperun.pagoda.demo.service.impl;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hoperun.pagoda.demo.bean.BaseResponse;
import hoperun.pagoda.demo.bean.DBInfoRequest;
import hoperun.pagoda.demo.bean.DBInfoResponse;
import hoperun.pagoda.demo.bean.DBSimiListResponse;
import hoperun.pagoda.demo.bean.FormSimiListResponse;
import hoperun.pagoda.demo.constant.Constant;
import hoperun.pagoda.demo.entity.DBSimilarity;
import hoperun.pagoda.demo.entity.ExeStatus;
import hoperun.pagoda.demo.entity.FormSimilarity;
import hoperun.pagoda.demo.entity.Group;
import hoperun.pagoda.demo.exception.BusinessException;
import hoperun.pagoda.demo.exception.ResultCode;
import hoperun.pagoda.demo.mapper.AgentInfoMapper;
import hoperun.pagoda.demo.mapper.DBInfoMapper;
import hoperun.pagoda.demo.mapper.FormInfoMapper;
import hoperun.pagoda.demo.mapper.ViewInfoMapper;
import hoperun.pagoda.demo.service.DBSimilarityService;
import hoperun.pagoda.demo.service.ExeStatusService;
import hoperun.pagoda.demo.service.FormSimilarityService;
import hoperun.pagoda.demo.service.GroupService;
import hoperun.pagoda.demo.service.NotesDBService;
import hoperun.pagoda.demo.utils.DBUtils;
import hoperun.pagoda.diiopanalyse.export.api.ExportDominoserverData;
import hoperun.pagoda.diiopanalyse.export.dto.ExportDataDto;
import hoperun.pagoda.dxlAnalyse.constant.SimiConstants;
import hoperun.pagoda.dxlAnalyse.quantity.entity.AgentInfo;
import hoperun.pagoda.dxlAnalyse.quantity.entity.DBInfo;
import hoperun.pagoda.dxlAnalyse.quantity.entity.FormInfo;
import hoperun.pagoda.dxlAnalyse.quantity.entity.StatisticsResult;
import hoperun.pagoda.dxlAnalyse.quantity.entity.ViewInfo;
import hoperun.pagoda.dxlAnalyse.similarity.FormEntity;
import hoperun.pagoda.dxlAnalyse.similarity.SimilarityCalculate;
import hoperun.pagoda.dxlAnalyse.similarity.XmlContentAnalyse;
import hoperun.pagoda.notesutils.NotesUtils;
import lotus.domino.NotesException;

/**
 * notes db service.
 * @author zhangxiqin
 *
 */
@Service
public class NotesDBServiceImpl implements NotesDBService {

    /**
     * Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(NotesDBServiceImpl.class);

    /**
     * group service.
     */
    @Autowired
    GroupService groupService;

    /**
     * DB similarity service.
     */
    @Autowired
    DBSimilarityService dbSimiService;

    /**
     * form similarity service.
     */
    @Autowired
    FormSimilarityService formSimiService;

    /**
     * execute status service.
     */
    @Autowired
    ExeStatusService exeStatusService;

    /**
     * DB info mapper.
     */
    @Autowired
    DBInfoMapper dbInfoMapper;

    /**
     * Form info mapper.
     */
    @Autowired
    FormInfoMapper formInfoMapper;

    /**
     * view info mapper.
     */
    @Autowired
    ViewInfoMapper viewInfoMapper;
    /**
     * agent info mapper.
     */
    @Autowired
    AgentInfoMapper agentInfoMapper;

    /**
     * export DB info.
     * @param type 1 if export single DB otherwise 2
     * @param groupId target group id
     * @param dbName target db name(only for single export)
     */
    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public BaseResponse<String> export(final int type, final int groupId, final String dbName) {
        String method = "export";
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(Constant.LOG_PATTERLN, method, "export DB started");
        }
        // get group info
        Group group = Optional.ofNullable(groupService.findByGrupId(groupId)).orElse(new Group());
        ExportDataDto param = DBUtils.genExportParameter(group);
        try {
            // update execute status as executing
            exeStatusService.updateStatus(ExeStatus.builder().groupId(groupId).type(1).status(Constant.EXE_STATUS_E).build());
            // export one DB
            if (1 == type) {
                param.setDatabaseName(param.getNsfPath().concat(File.separator).concat(dbName));
                ExportDominoserverData.exportDataByDominoserver(param);
            } else {
                // get export all DB
                StatisticsResult result = ExportDominoserverData.exportAllDataByDominoserver(param);
                // add data into database
                List<hoperun.pagoda.dxlAnalyse.quantity.entity.DBInfo> dbInfo = result.getDbs();
                // save data to db
                saveDBInfo(dbInfo, group.getGroupId());
            }
            // update execute status as executing
            exeStatusService.updateStatus(ExeStatus.builder().groupId(groupId).type(1).status(Constant.EXE_STATUS_C).build());
        } catch (Exception e) {
            e.printStackTrace();
            // update execute status as executing
            exeStatusService.updateStatus(ExeStatus.builder().groupId(groupId).type(1).status(Constant.EXE_STATUS_C).build());
            LOGGER.error(Constant.LOG_PATTERLN, method, e);
            return BaseResponse.failure(ResultCode.SERVER_ERROR, e.getMessage());
        }
        return BaseResponse.ok();
    }

    /**
     * analyse DB similarity.
     */
    @SuppressWarnings("unchecked")
    @Override
    public BaseResponse<String> dbSimilarityAnalyze(final int type, final int groupId, final boolean hideExcept, final double simiValue) {
        String method = "dbSimilarityAnalyze";
        // get group info
        Group group = Optional.ofNullable(groupService.findByGrupId(groupId)).orElse(new Group());
        ExportDataDto param = DBUtils.genExportParameter(group);
        param.setInputPath(group.getExportResultPath());
        SimilarityCalculate calcutor = new SimilarityCalculate();
        XmlContentAnalyse xmlContentAnalyse = new XmlContentAnalyse();
        Map<String, List<String>> compareForms = new HashMap<>();
        // get DB map
        try {
            // update execute status as executing
            exeStatusService.updateStatus(ExeStatus.builder().groupId(groupId).type(2).status(Constant.EXE_STATUS_E).build());
            if (1 == type) {
                xmlContentAnalyse.getDBMap(param.getInputPath(), "manifest.properties", compareForms);
            } else {
                calcutor.getAllCompareForm(param.getInputPath(), compareForms);
            }
            List<List<FormEntity>> dbSimiDataList = calcutor.generateSimilarity(compareForms, true);
            // delete current group's DB data
            dbSimiService.deleteSimiDataByGroupId(groupId);
            // save similarity data into db
            saveDBSimiData(dbSimiDataList, param, groupId);
            // update execute status as completed
            exeStatusService.updateStatus(ExeStatus.builder().groupId(groupId).type(2).status(Constant.EXE_STATUS_C).build());
        } catch (Exception e) {
            // update execute status as completed.
            exeStatusService.updateStatus(ExeStatus.builder().groupId(groupId).type(2).status(Constant.EXE_STATUS_C).build());
            LOGGER.error(Constant.LOG_PATTERLN, method, e);
            return BaseResponse.failure(ResultCode.SERVER_ERROR, e.getMessage());
        }
        return BaseResponse.ok();
    }

    /**
     * Save DB similarity data into DB.
     * @param dbSimiDataList similarity data list.
     * @param param export parameter.
     * @param groupId groupId.
     */
    private void saveDBSimiData(final List<List<FormEntity>> dbSimiDataList, final ExportDataDto param, final int groupId) {
        try {
            Map<String, String> dBpathDBNameMap = NotesUtils.getDBpathDBNameMap(param.getNotesHost(), param.getUserName(), param.getPassword(),
                    param.getNsfPath(), false);
            List<List<FormEntity>> hasCategoryList = dbSimiDataList.stream().filter(list -> list.size() > 1).sorted((o1, o2) -> o2.size() - o1.size())
                    .collect(Collectors.toList());
            List<List<FormEntity>> noCategoryList = dbSimiDataList.stream().filter(list -> list.size() == 1).collect(Collectors.toList());
            saveHasCategoryData(groupId, dBpathDBNameMap, hasCategoryList);
            saveNoCategoryData(groupId, dBpathDBNameMap, noCategoryList);
        } catch (NotesException nex) {
            throw new BusinessException(BaseResponse.failure(ResultCode.SERVER_ERROR, nex.getMessage()));
        }
    }

    /**
     * save DB(has similarity category) similarity data.
     * @param groupId groupId
     * @param dBpathDBNameMap dBpathDBNameMap
     * @param hasCategoryList DBList
     */
    private void saveHasCategoryData(final int groupId, final Map<String, String> dBpathDBNameMap, final List<List<FormEntity>> hasCategoryList) {
        List<String> currentStrList = SimiConstants.CHAR_LIST;
        List<String> currentNumList = SimiConstants.NUMBER_LIST;
        for (int i = 0; i < hasCategoryList.size(); i++) {
            List<FormEntity> categoryList = hasCategoryList.get(i);
            if (currentNumList.isEmpty()) {
                currentNumList = SimiConstants.NUMBER_LIST;
                currentStrList = currentStrList.subList(1, currentStrList.size());
            }
            String categoryNo = currentStrList.isEmpty() ? String.valueOf(i + 1) : currentStrList.get(0).concat(currentNumList.get(0));
            currentNumList = currentNumList.subList(1, currentNumList.size());
            for (int j = 0; j < categoryList.size(); j++) {
                String dbName = categoryList.get(j).getDbFileName();
                String mainForm = categoryList.get(j).getName();
                int dbSize = categoryList.size();
                int size = j == 0 ? categoryList.size() : 0;
                DBSimilarity data = DBSimilarity.builder().groupId(groupId).categoryNo(categoryNo).enName(dbName)
                        .jpName(null == dBpathDBNameMap.get(dbName) ? "" : dBpathDBNameMap.get(dbName)).mainForm(mainForm).categorySize(size)
                        .dbSize(dbSize).build();
                dbSimiService.insert(data);
            }
        }
    }

    /**
     * save DB(has no similarity category) similarity data.
     * @param groupId groupId
     * @param dBpathDBNameMap dBpathDBNameMap
     * @param noCategoryList DBList
     */
    private void saveNoCategoryData(final int groupId, final Map<String, String> dBpathDBNameMap, final List<List<FormEntity>> noCategoryList) {
        for (int i = 0; i < noCategoryList.size(); i++) {
            int size = i == 0 ? noCategoryList.size() : 0;
            List<FormEntity> categoryList = noCategoryList.get(i);
            String dbName = categoryList.get(0).getDbFileName();
            String mainForm = categoryList.get(0).getName();
            int dbSize = noCategoryList.size();
            DBSimilarity data = DBSimilarity.builder().groupId(groupId).categoryNo("無").enName(dbName)
                    .jpName(null == dBpathDBNameMap.get(dbName) ? "" : dBpathDBNameMap.get(dbName)).mainForm(mainForm).dbSize(dbSize)
                    .categorySize(size).build();
            dbSimiService.insert(data);
        }
    }

    /**
     * analyse form similarity.
     */
    @SuppressWarnings("unchecked")
    @Override
    public BaseResponse<String> formSimilarityAnalyze(final String dbName, final int groupId) {
        String method = "formSimilarityAnalyze";
        // get group info
        Group group = Optional.ofNullable(groupService.findByGrupId(groupId)).orElse(new Group());
        ExportDataDto param = DBUtils.genExportParameter(group);
        param.setInputPath(group.getExportResultPath().concat(File.separator).concat(group.getNotesDBPath()));
        SimilarityCalculate calcutor = new SimilarityCalculate();
        XmlContentAnalyse xmlContentAnalyse = new XmlContentAnalyse();
        Map<String, Map<String, List<String>>> forms = new HashMap<>();
        // get DB map
        try {
            xmlContentAnalyse.getAllForms(param.getInputPath(), forms);
            Map<String, List<List<FormEntity>>> formSimiData = calcutor.generateFormSimilarity(forms);
            // delete current group's DB data
            formSimiService.deleteByDbName(dbName);
            // save similarity data into db
            saveFormSimiData(formSimiData);
        } catch (Exception e) {
            LOGGER.error(Constant.LOG_PATTERLN, method, e);
            return BaseResponse.failure(ResultCode.SERVER_ERROR, e.getMessage());
        }
        return BaseResponse.ok();
    }

    /**
     * Save DB similarity data into DB.
     * @param formSimiData formSimiData.
     */
    private void saveFormSimiData(final Map<String, List<List<FormEntity>>> formSimiData) {
        for (Entry<String, List<List<FormEntity>>> simiData : formSimiData.entrySet()) {
            saveFormSimiDataBYDB(simiData.getKey(), simiData.getValue());
        }
    }

    /**
     * save form similarity data by DB.
     * @param dbName DB Name
     * @param simiData simiData
     */
    private void saveFormSimiDataBYDB(final String dbName, final List<List<FormEntity>> simiData) {
        int dbCategoryNum = simiData.size();
        for (int i = 0; i < simiData.size(); i++) {
            List<FormEntity> category = simiData.get(i);
            int simiNo = i + 1;
            for (int j = 0; j < category.size(); j++) {
                FormEntity form = category.get(j);
                int formCategoryNumber = j == 0 ? category.size() : 0;
                FormSimilarity formSimi = FormSimilarity.builder().dbName(dbName).allCategory(dbCategoryNum).categoryNo(simiNo)
                        .categorySize(formCategoryNumber).formName(form.getName()).fieldNum(form.getFieldNumber()).codeNum(form.getCodeNumber())
                        .build();
                formSimiService.insert(formSimi);
                dbCategoryNum = 0;
            }
        }
    }

    /**
     * retrieve DB similarity data by group id.
     */
    @Override
    public DBSimiListResponse retrieveDBSimiData(final int groupId) {
        return new DBSimiListResponse(dbSimiService.getSimiDataByGroup(groupId));
    }

    /**
     * retrieve Form similarity data by name.
     */
    @Override
    public FormSimiListResponse retrieveFormSimiData(final String dbName) {
        return new FormSimiListResponse(formSimiService.getSimiDataByName(dbName));
    }

    /**
    * retrieve DB info.
    */
    @Override
    public DBInfoResponse retrieveDBInfo(final DBInfoRequest request) {
        List<DBInfo> dbList = dbInfoMapper.findAllDBByGroup(request.getGroupId());
        int size = dbList.size();
        // filter by pageNo and limit
        dbList = dbList.stream().skip((request.getPageNo() - 1) * (long) request.getPageSize()).limit(request.getPageSize())
                .collect(Collectors.toList());
        return new DBInfoResponse(dbList, size);
    }

    /**
     * Save dbInfo.
     * @param dbInfo db info.
     * @param groupId Group id
     */
    @Transactional
    public void saveDBInfo(final List<DBInfo> dbInfo, final int groupId) {
        // delte
        dbInfoMapper.deleteByGroupId(groupId);
        dbInfo.forEach(db -> {
            db.setGroupId(groupId);
            dbInfoMapper.insert(db);
            // save form info
            saveFormInfo(db.getDbFileName(), db.getForms());
            // save view info
            saveViewInfo(db.getDbFileName(), db.getViews());
            // save agent info
            saveAgentInfo(db.getDbFileName(), db.getAgents());
            // save simlarity info
            this.saveFormSimiDataBYDB(db.getDbFileName(), db.getFormSimiData());
        });
    }

    /**
     * Save dbInfo.
     * @param dbFileName db name of English.
     * @param forms form list
     */
    @Transactional
    public void saveFormInfo(final String dbFileName, final List<FormInfo> forms) {
        // delete by db name
        formInfoMapper.deleteByDBName(dbFileName);
        forms.forEach(form -> {
            form.setDbName(dbFileName);
            // save form info
            formInfoMapper.insert(form);
        });
    }

    /**
     * Save view info.
     * @param dbFileName db name of English.
     * @param views view list
     */
    @Transactional
    public void saveViewInfo(final String dbFileName, final List<ViewInfo> views) {
        // delete by db name
        viewInfoMapper.deleteByDBName(dbFileName);
        views.forEach(view -> {
            view.setDbName(dbFileName);
            // save form info
            viewInfoMapper.insert(view);
        });
    }

    /**
     * Save agent info.
     * @param dbFileName db name of English.
     * @param agents agent list
     */
    @Transactional
    public void saveAgentInfo(final String dbFileName, final List<AgentInfo> agents) {
        // delete by db name
        agentInfoMapper.deleteByDBName(dbFileName);
        agents.forEach(agent -> {
            agent.setDbName(dbFileName);
            // save form info
            agentInfoMapper.insert(agent);
        });
    }

}
