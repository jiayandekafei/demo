package hoperun.pagoda.demo.constant;
/**
 * Constant class.
 * @author zhangxiqin
 *
 */
public final class Constant {

    /**
     * no patameter Constructors.
     */
    private Constant() {
        super();
    }

    /**
     * log pattern.
     */
    public static final String LOG_PATTERLN = "method :{},message :{}";
    /**
     * token type.
     */
    public static final String TOKEN_TYPE = "Bearer ";

    /**
     * success message.
     */
    public static final String SUCCESS_MESSAGE = "successfully";

    /**
     * aae transform result temp folder.
     */
    public static final String AAE_RESULT = "aaeResult";

    /**
     * aae transform result fileName.
     */
    public static final String AAE_RESULT_FILE_NAME = "transformResult";

    /**
     * file suffix.
     */
    public static final String FILE_SUFFIX_ZIP = ".zip";

    /*********************status code**********************************/
    /**
     * 401.
     */
    public static final int STATUS_CODE_401 = 401;
    /**
     * 403.
     */
    public static final int STATUS_CODE_403 = 403;
    /**
     * 404.
     */
    public static final int STATUS_CODE_404 = 404;

    /*********************number**********************************/
    /**
     * 2.
     */
    public static final int NUMBER_2 = 2;
    /**
     * 3.
     */
    public static final int NUMBER_3 = 3;
    /**
     * 32.
     */
    public static final int NUMBER_32 = 32;
    /**
     * 1000.
     */
    public static final int NUMBER_1000 = 1000;

    /**
     * server host regex.
     */
    public static final String IP_REGEX = "\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}";

    /**
     * execute status E；executing.
     */
    public static final String EXE_STATUS_E = "E";

    /**
     * execute status C；executing.
     */
    public static final String EXE_STATUS_C = "C";

    /*******************************user status*************************************************/
    /**
     * user status aprroved.
     */
    public static final String USER_STATUS_A = "A";
    /**
     * user status watting for approve.
     */
    public static final String USER_STATUS_W = "W";
    /**
     * user status rejected.
     */
    public static final String USER_STATUS_R = "R";
}
