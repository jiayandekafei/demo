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
     * 32.
     */
    public static final int NUMBER_32 = 32;
    /**
     * 1000.
     */
    public static final int NUMBER_1000 = 1000;
}
