package test;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.rockfintech.reas.xabank.MainApplication;
import com.rockfintech.reas.xabank.service.IXaLogService;
import com.rockfintech.reas.xabank.util.json.JsonUtils;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
//指定SpringBoot工程的Application启动类
//支持web项目
@WebAppConfiguration
@SpringBootTest(classes = MainApplication.class, webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class MybatisTest {

    @Autowired
    private IXaLogService xaLogService;

    @Test
    public void test1() throws Exception{
        String s = "{\"SYS_HEAD\":{\"seq_NO\":\"10991809080000000010\",\"zone_CODE\":\"65\",\"business_NO\":\"14201809080000000017\",\"service_CODE\":\"0110010001\",\"user_LANG\":\"CHINESE\",\"tran_TIMESTAMP\":\"155600319\",\"tran_MODE\":\"ONLINE\",\"branch_ID\":\"101\",\"user_ID\":\"9994\",\"tran_CODE\":\"01\",\"tran_DATE\":\"20180908\",\"terminal_CODE\":\"11111111\",\"channel_CODE\":\"1099\",\"company\":\"XA\",\"source_TYPE\":\"014\"},\"BODY\":{\"czbz\":\"1\",\"xzbz\":\"1\",\"xtgzh\":\"js000010\",\"zhdh\":\"111111\",\"dfzhdh\":\"658010100100018847\",\"ywlx\":\"76\",\"jdbj\":\"0\",\"jyje\":50002,\"tcrq\":\"20180908\",\"pzdh\":\"1\",\"zydh\":\"103\"}}";
        JSONObject jsonObject = JSONObject.parseObject(s);
        JSONObject SYS_HEAD = jsonObject.getJSONObject("SYS_HEAD");
        SYS_HEAD.remove("user_LANG");
        String str1 = JSONObject.toJSONString(SYS_HEAD).toUpperCase();
        JSONObject SYS_HEAD_NEW = JSONObject.parseObject(str1);
        JSONObject BODY = jsonObject.getJSONObject("BODY");

        BODY.put("jyje1","0.00");
        BODY.put("jyje2","0.00");
        BODY.put("xjxmdh","");
        BODY.put("qkfs","");
        BODY.put("mm","");

        //zhdh和dfzhdh两个值互换 暂时在这里调整
        String dfzhdhTemp = BODY.getString("dfzhdh");
        BODY.put("dfzhdh",BODY.getString("zhdh"));
        BODY.put("zhdh",dfzhdhTemp);

        JSONObject jsonNew = new JSONObject();
        jsonNew.put("SYS_HEAD",SYS_HEAD_NEW);
        jsonNew.put("BODY",BODY);

        String stemp = JSONObject.toJSONString(jsonNew);
        System.out.println(stemp);

        String sendData = String.format("%08d", stemp.length()) + stemp;//数字8位长度0补全

        System.out.println("------------------" + sendData);

        Map<String,Object> map = JsonUtils.readValue(s,Map.class);
        Map<String,Object> map1 = new HashMap<>();
        map1.put("chid","132123131");
        map1.put("acctNo","464676876");
        map1.put("reqMap",map);
//        System.out.println(map);
        xaLogService.notice(map1);
    }


}
