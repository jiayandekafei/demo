package hoperun.pagoda.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hoperun.pagoda.demo.bean.BaseResponse;
import hoperun.pagoda.demo.bean.DBInfoRequest;
import hoperun.pagoda.demo.bean.DBInfoResponse;
import hoperun.pagoda.demo.bean.DBSimiListResponse;
import hoperun.pagoda.demo.constant.Constant;
import hoperun.pagoda.demo.service.DBSimilarityService;
import hoperun.pagoda.demo.service.NotesDBService;
import io.swagger.annotations.ApiOperation;

/**
 * notes DB export Controller.
 *
 * @author zhangxiqin
 *
 */
@RestController
@RequestMapping("/notes/db")
public class NotesDBController {

    /**
     * Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(NotesDBController.class);

    /**
     * notes DB.
     */
    @Autowired
    private NotesDBService notesDBService;

    /**
     *DB Similarity sevice. 
     */
    @Autowired
    private DBSimilarityService dbSimiService;

    /**
    * export DB.
    * @param type 1 if export single DB otherwise 2
    * @param groupId target group id
    * @param dbName target db name(only for single export)
    * @return BaseResponse<String> 
    */
    @GetMapping("/export")
    @ApiOperation(value = "export notes DB")
    public BaseResponse<String> exportDB(@RequestParam final int type, @RequestParam final int groupId,
            @RequestParam(required = false) final String dbName) {
        final String method = "exportDB";

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(Constant.LOG_PATTERLN, method, "export notes DB started");
        }

        return notesDBService.export(type, groupId, dbName);
    }

    /**
     * retrieve DB similarity list by group.
     * @param request  request
     * @return BaseResponse<CustomerListResponse> customer list
     */
    @SuppressWarnings("unchecked")
    @GetMapping("/list")
    @ApiOperation(value = "retrieve DB list")
    public BaseResponse<DBInfoResponse> retrieveDBList(final DBInfoRequest request) {
        final String method = "retrieveDBList";

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(Constant.LOG_PATTERLN, method, "retrieve DB List started");
        }
        return BaseResponse.ok(notesDBService.retrieveDBInfo(request));
    }

    /**
     * DB similarity analyze.
     * @param type type
     * @param groupId groupId
     * @param hideExcept hideExcept
     * @param simiValue simiValue
     * @return BaseResponse<String>
     */
    @GetMapping("/analyze")
    @ApiOperation(value = "DB similarity analyze")
    public BaseResponse<String> genDBSimilarity(@RequestParam final int type, @RequestParam final int groupId, @RequestParam final boolean hideExcept,
            @RequestParam final double simiValue) {
        final String method = "analyzeSimilarity";

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(Constant.LOG_PATTERLN, method, "analyze similarity started");
        }
        return notesDBService.dbSimilarityAnalyze(type, groupId, hideExcept, simiValue);
    }

    /**
     * retrieve DB similarity list by group.
     * @param groupId  groupId
     * @return BaseResponse<CustomerListResponse> customer list
     */
    @SuppressWarnings("unchecked")
    @GetMapping("/simi/list")
    @ApiOperation(value = "retrieve DB Similarity list")
    public BaseResponse<DBSimiListResponse> retrieveDBSimiDataList(@RequestParam(required = false) final int groupId) {
        final String method = "retrieveDBSimiDataList";

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(Constant.LOG_PATTERLN, method, "retrieve DB similarity data started");
        }
        return BaseResponse.ok(notesDBService.retrieveDBSimiData(groupId));
    }

    /**
     * retrieve DB similarity list by group.
     * @param groupId  groupId
     * @return BaseResponse<CustomerListResponse> customer list
     */
    @SuppressWarnings("unchecked")
    @GetMapping("/simi/pie")
    @ApiOperation(value = "retrieve DB Similarity list")
    public BaseResponse<DBSimiListResponse> retrieveDBSimiPieData(@RequestParam(required = false) final int groupId) {
        final String method = "retrieveDBSimiPieData";

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(Constant.LOG_PATTERLN, method, "retrieve DB similarity pie data started");
        }
        return BaseResponse.ok(dbSimiService.getDBSimiDistributionData(groupId));
    }

    /**
     * retrieve DB similarity list by group.
     * @param dbName  dbName
     * @param groupId  groupId
     * @return BaseResponse<CustomerListResponse> customer list
     */
    @SuppressWarnings("unchecked")
    @GetMapping("/analyze/form")
    @ApiOperation(value = " generate form similarity data")
    public BaseResponse<String> genFormSimil(@RequestParam(required = false) final String dbName, @RequestParam(required = false) final int groupId) {
        final String method = "genFormSimil";

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(Constant.LOG_PATTERLN, method, "get Customer list started");
        }
        return BaseResponse.ok(notesDBService.formSimilarityAnalyze(dbName, groupId));
    }

    /**
     * retrieve DB similarity list by group.
     * @param dbName dbName
     * @return BaseResponse<CustomerListResponse> customer list
     */
    @SuppressWarnings("unchecked")
    @GetMapping("/form/simi/{dbName}")
    @ApiOperation(value = "retrieve form similarity data")
    public BaseResponse<DBSimiListResponse> retrieveFormSimilData(@PathVariable final String dbName) {
        final String method = "retrieveFormSimilData";

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(Constant.LOG_PATTERLN, method, "get Customer list started");
        }
        return BaseResponse.ok(notesDBService.retrieveFormSimiData(dbName));
    }
}
