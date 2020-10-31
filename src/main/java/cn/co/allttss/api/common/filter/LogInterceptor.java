package cn.co.allttss.api.common.filter;

/**
 * <p>日志拦截器</p>
 * 使用logmdcfilter实现了，所以不使用此方法
 */
//@Component
//public class LogInterceptor extends HandlerInterceptorAdapter {
//    private Logger log = LoggerFactory.getLogger(this.getClass());
//    /**
//     * 日志跟踪标识
//     */
//    private static final String TRACE_ID = "TRACE_ID";
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
//        //SESSIONID
//        String traceId = request.getSession().getId();
//        //Uid
//        //String traceId = UUID.randomUUID().toString();
//        if (StringUtils.isEmpty(MDC.get(TRACE_ID))) {
//            MDC.put(TRACE_ID, traceId);
//        }
//        return true;
//    }

//}