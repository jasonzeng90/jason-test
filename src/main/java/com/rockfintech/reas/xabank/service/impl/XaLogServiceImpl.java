package com.rockfintech.reas.xabank.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.rockfintech.reas.xabank.common.constant.Request;
import com.rockfintech.reas.xabank.common.emun.XaLogStatus;
import com.rockfintech.reas.xabank.entity.XaLog;
import com.rockfintech.reas.xabank.exception.BizException;
import com.rockfintech.reas.xabank.service.IXaLogService;
import com.rockfintech.reas.xabank.util.json.JsonUtils;
import com.sun.deploy.net.protocol.ProtocolType;
import com.sun.tools.javac.util.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.Socket;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

@Service
public class XaLogServiceImpl extends BaseService<XaLog> implements IXaLogService {
    @Autowired
    private IXaLogService xaLogService;




        @Override
    public void notice(Map<String, Object> map) throws Exception {
        XaLog xaLog = new XaLog();

        String chid = map.get("chid").toString();
        String acctNo = map.get("acctNo").toString();
        Map<String,Object> reqMap = (Map<String, Object>) map.get("reqMap");
        String xaLogId = UUID.randomUUID().toString().trim().replaceAll("-", "").toLowerCase();

        String sendJson;
        Socket socket = null;
        try {
            socket = new Socket(Request.XINAN_IP,Integer.valueOf(Request.XINAN_PORT));
            logger.info("客户端启动成功");
        } catch (Exception e) {
            logger.error("socket connect error", e);
        }

        OutputStream ops = null;
        BufferedReader br = null;
        try {
            ops = socket.getOutputStream();

            String mapStr = JsonUtils.writeValueAsString(reqMap);

            JSONObject mapObject = JSONObject.parseObject(mapStr);
            JSONObject sysHead = mapObject.getJSONObject("SYS_HEAD");
            sysHead.remove("user_LANG");
            String sysHeadStr = JSONObject.toJSONString(sysHead).toUpperCase();
            JSONObject sysHeadNew = JSONObject.parseObject(sysHeadStr);
            JSONObject BODY = mapObject.getJSONObject("BODY");

            BODY.put("jyje1","0.00");
            BODY.put("jyje2","0.00");
            BODY.put("xjxmdh","");
            BODY.put("qkfs","");
            BODY.put("mm","");
            BODY.put("pzdh","");
            BODY.put("zydh","036");

            //zhdh和dfzhdh两个值互换 暂时在这里调整 TODO
            String dfzhdhTemp = BODY.getString("dfzhdh");
            BODY.put("dfzhdh",BODY.getString("zhdh"));
            BODY.put("zhdh",dfzhdhTemp);

            JSONObject jsonNew = new JSONObject();
            jsonNew.put("SYS_HEAD",sysHeadNew);
            jsonNew.put("BODY",BODY);

            String stemp = JSONObject.toJSONString(jsonNew);
            logger.info("parseJSON字符串" + stemp);

            String sendData = String.format("%08d", stemp.length()) + stemp;//数字8位长度0补全


            logger.info("socket发送数据到新安 sendData="+sendData);

            xaLog.setXalogId(xaLogId);
            xaLog.setChid(chid);
            xaLog.setAcctNo(acctNo);
            xaLog.setReqMsg(sendData);
            xaLog.setReqTime(new Date());
            xaLog.setStatus(XaLogStatus.SEND.getStatus());
            xaLogService.insertSelective(xaLog);

            br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "GBK"));


            ops.write(sendData.getBytes("GBK"));
            ops.flush();
            socket.shutdownOutput();
            logger.info("发送报文完成");

            String str = null;
            StringBuffer sb = new StringBuffer("");
            while ((str = br.readLine()) != null) {
                sb.append(str);
                sb.append("\n");
            }
            String string = sb.toString();
            logger.info("读取返回报文" + string);
            String receiveJson = string.substring(0, string.length() - 1);

            logger.info("读取返回报文receiveJson" + receiveJson);
            br.close();
            ops.close();
            socket.close();

            xaLog = new XaLog();
            xaLog.setXalogId(xaLogId);
            xaLog.setResMsg(receiveJson);
            xaLog.setResTime(new Date());
            xaLog.setStatus(XaLogStatus.SUCCESS.getStatus());

        } catch (Exception e) {
            logger.error("socket连接完成,发送请求时异常:" + e.getMessage());
            xaLog = new XaLog();
            xaLog.setXalogId(xaLogId);
            xaLog.setStatus(XaLogStatus.ERROR.getStatus());
            xaLog.setReMsg(e.getMessage());

            throw new BizException();
        }finally {
            if(ops!=null){
                ops.close();
            }
            if(br != null){
                br.close();
            }
            if(socket != null){
                socket.close();
            }
            try {
                xaLogService.updateByPrimaryKeySelective(xaLog);
            }catch (Exception e3){
                logger.error("更新通讯日志异常",e3);
            }
        }


    }


//    public  void socketTzg(Map map) throws BizException, IOException {
//        XaLog xaLog = new XaLog();
//
//        String chid = map.get("chid").toString();
//        String acctNo = map.get("acctNo").toString();
//        Map<String,Object> reqMap = (Map<String, Object>) map.get("reqMap");
//        String xaLogId = UUID.randomUUID().toString().trim().replaceAll("-", "").toLowerCase();
//
//        String sendJson;
//        Socket socket = null;
//        try {
//            socket = new Socket(Request.XINAN_IP,Integer.valueOf(Request.XINAN_PORT));
//            logger.info("客户端启动成功");
//        } catch (Exception e) {
//            logger.error("socket connect error", e);
//        }
//
//        OutputStream ops = null;
//        BufferedReader br = null;
//        try {
//            ops = socket.getOutputStream();
//
//            sendJson = JsonUtils.writeValueAsString(reqMap);
//            String sendData = String.format("%08d", sendJson.length()) + sendJson;//数字8位长度0补全
//            logger.info("socket发送数据到新安 sendData="+sendData);
//
//            xaLog.setXalogId(xaLogId);
//            xaLog.setChid(chid);
//            xaLog.setAcctNo(acctNo);
//            xaLog.setReqMsg(sendData);
//            xaLog.setReqTime(new Date());
//            xaLog.setStatus(XaLogStatus.SEND.getStatus());
//            xaLogService.insertSelective(xaLog);
//
//            br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "GBK"));
//
//
//            ops.write(sendData.getBytes("GBK"));
//            ops.flush();
//            socket.shutdownOutput();
//            logger.info("发送报文完成");
//
//            String str = null;
//            StringBuffer sb = new StringBuffer("");
//            while ((str = br.readLine()) != null) {
//                sb.append(str);
//                sb.append("\n");
//            }
//            String string = sb.toString();
//            String receiveJson = string.substring(0, string.length() - 1);
//
//            logger.info("读取返回报文receiveJson" + receiveJson);
//            br.close();
//            ops.close();
//            socket.close();
//
//            xaLog = new XaLog();
//            xaLog.setXalogId(xaLogId);
//            xaLog.setResMsg(receiveJson);
//            xaLog.setResTime(new Date());
//            xaLog.setStatus(XaLogStatus.SUCCESS.getStatus());
//
//        } catch (IOException e) {
//            logger.error("socket连接完成,发送请求时异常:" + e.getMessage());
//            xaLog = new XaLog();
//            xaLog.setXalogId(xaLogId);
//            xaLog.setStatus(XaLogStatus.ERROR.getStatus());
//            xaLog.setReMsg(e.getMessage());
//
//            throw new BizException();
//        }finally {
//            if(ops!=null){
//                ops.close();
//            }
//            if(br != null){
//               br.close();
//            }
//            if(socket != null){
//                socket.close();
//            }
//            try {
//                xaLogService.updateByPrimaryKeySelective(xaLog);
//            }catch (Exception e3){
//                logger.error("更新通讯日志异常",e3);
//            }
//        }
//
//    }

}
