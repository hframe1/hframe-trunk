import com.hframework.common.util.RegexUtils;
import org.apache.commons.httpclient.util.DateUtil;

import java.util.*;

/**
 * Created by zhangquanhong on 2016/6/22.
 */
public class Test {
    public static void main(String[] args) {
        Date date = new Date();
        DateUtil.formatDate(date, "yyyy");
        System.out.println(date + DateUtil.formatDate(date,""));

        String[] vars =null;// RegexUtils.find("log_info=='还本' || log_info=='提前还款本金' || log_info=='回报本金' || log_info=='付息' || log_info=='提前还款利息' || log_info=='回报利息' || log_info=='投资返利' || log_info=='注册返利' || log_info=='机构返利' || log_info=='充值' || log_info=='系统充值' || log_info=='管理员充值' || log_info=='提现申请'".replaceAll("'[^']*'", ""), "[a-zA-Z]+[a-zA-Z0-9._]*");
        vars = (String[]) (new HashSet(Arrays.asList(vars))).toArray(new String[0]);
        System.out.println(Arrays.toString(vars));
    }
}
