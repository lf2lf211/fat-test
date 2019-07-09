package com.yuanbao.testttt.service.imp;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import com.yuanbao.testttt.conifg.TestInfo;
import com.yuanbao.testttt.mapper.MemberMapper;
import com.yuanbao.testttt.mapper.dataModel.MemberModel;
import com.yuanbao.testttt.service.ITestService;
import com.yuanbao.testttt.util.Md5Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;


@Service
public class TestSer implements ITestService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MemberMapper memberMapper;

    public String test() {


        return memberMapper.getMemberInfo("888xx").get("name").toString();
    }

    @Override
    public JSONObject getBalance(String token, String sign) throws IOException {

        Map<String, Object> paraMap = new TreeMap<String, Object>();
        HashMap<String, Object> map = new HashMap<String, Object>();
        paraMap.put("token", token);
        String mySign = Md5Util.md5(paraMap, TestInfo.MD5KEY, "utf-8");
        if (sign.equals(mySign)) {
            MemberModel memberModel = new MemberModel();
            map = memberMapper.getMemberInfo(token);
            BigDecimal balance = BigDecimal.valueOf(Long.valueOf(map.get("balance").toString()));
            memberModel.setBalance(balance);
            map.clear();
            map.put("retCode", "0");
            map.put("data", memberModel);
        } else {
            map.put("retCode", "SIGN_FAILS");
        }
        JSONObject json = new JSONObject(map);
        return json;
    }

    @Override
    public Map<String, Object> getLoginInfo(String token, String sign) throws IOException {
        Map<String, Object> paraMap = new TreeMap<String, Object>();
        HashMap<String, Object> map = new HashMap<String, Object>();
        paraMap.put("token", token);
        String mySign = Md5Util.md5(paraMap, TestInfo.MD5KEY, "utf-8");
        if (sign.equals(mySign)) {

            MemberModel memberModel = new MemberModel();
            map = memberMapper.getMemberInfo(token);
            String account = map.get("account").toString();
            BigDecimal balance = BigDecimal.valueOf(Long.valueOf(map.get("balance").toString()));
            String name = map.get("name").toString();
            String headerUrl = map.get("headerUrl").toString();

            // 驗簽成功就校驗餘額，低於一千補為一千
            // if(balance.compareTo(new BigDecimal("100000"))==-1){
            // memberMapper.updateBalanceForReset(new BigDecimal("100000"), account);
            // balance = new BigDecimal("100000");
            // }

            memberModel.setAccount(account);
            memberModel.setBalance(balance);
            memberModel.setHeaderUrl(headerUrl);
            memberModel.setName(name);
            map.clear();
            map.put("retCode", "0");
            map.put("data", memberModel);
        } else {
            map.put("retCode", "SIGN_FAILS");
        }

        return map;

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public JSONObject updateBalance(String data) throws IOException, InterruptedException {
        HashMap<String, Object> map = new HashMap<String, Object>();
        JSONObject dataJson = JSON.parseObject(data);
        Map<String, Object> paraMap = new TreeMap<String, Object>();

        // 动态历遍集合中的值
        for (Map.Entry<String, Object> entry : dataJson.entrySet()) {
            paraMap.put(entry.getKey(), entry.getValue());
        }

        String action = (String) paraMap.get("action");
        String token = (String) paraMap.get("token");
        String sign = (String) paraMap.get("sign");
        BigDecimal amt = new BigDecimal((String) paraMap.get("amt"));
        paraMap.remove("sign");

        try {

            String mySign = Md5Util.md5(paraMap, TestInfo.MD5KEY, "utf-8");
            // 先 較驗簽名
            if (sign.equals(mySign)) {
                MemberModel memberModel = new MemberModel();
                // 找出會員資料
                map = memberMapper.getMemberInfo(token);
                BigDecimal balance = BigDecimal.valueOf(Long.valueOf(map.get("balance").toString()));
                String account = map.get("account").toString();

                if (action.equals("bet") && balance.compareTo(amt) >= 0) {
//					int i = (int) (Math.random() * 2) + 1;
//					if (i == 1) {
                    if (memberMapper.updateBalanceForBet(amt, account) != 1) {
                        throw new Exception("bet更新會員錯誤");
                    }

                    map = memberMapper.getMemberInfo(token);
                    balance = BigDecimal.valueOf(Long.valueOf(map.get("balance").toString()));
                    memberModel.setBalance(balance);
                    map.clear();
                    map.put("retCode", "0");
                    map.put("data", memberModel);
                    // } else {
                    // Thread.sleep(7200);
                    // memberMapper.updateBalanceForBet(amt, account);
                    // map = memberMapper.getMemberInfo(token);
                    // balance = BigDecimal.valueOf(Long.valueOf(map.get("balance").toString()));
                    // memberModel.setBalance(balance);
                    // map.clear();
                    // map.put("retCode", "0");
                    // map.put("data", memberModel);
                    // }

                } else if (action.equals("payout")) {
                    if (memberMapper.updateBalanceForPayout(amt, account) != 1) {
                        throw new Exception("payout更新會員錯誤");
                    }

                    map = memberMapper.getMemberInfo(token);
                    balance = BigDecimal.valueOf(Long.valueOf(map.get("balance").toString()));
                    memberModel.setBalance(balance);
                    map.clear();
                    map.put("retCode", "0");
                    map.put("data", memberModel);
                } else if (action.equals("applyDealerRollBack")) {// 還錢
                    memberMapper.updateBalanceForPayout(amt, account);
                    map = memberMapper.getMemberInfo(token);
                    balance = BigDecimal.valueOf(Long.valueOf(map.get("balance").toString()));
                    memberModel.setBalance(balance);
                    map.clear();
                    map.put("retCode", "0");
                    map.put("data", memberModel);
                } else if (action.equals("applyDealer") && balance.compareTo(amt) >= 0) {// 扣錢
                    memberMapper.updateBalanceForBet(amt, account);
                    map = memberMapper.getMemberInfo(token);
                    balance = BigDecimal.valueOf(Long.valueOf(map.get("balance").toString()));
                    memberModel.setBalance(balance);
                    map.clear();
                    map.put("retCode", "0");
                    map.put("data", memberModel);
                } else if (action.equals("betRollback") && balance.compareTo(amt) >= 0) {// +錢
                    memberMapper.updateBalanceForPayout(amt, account);
                    map = memberMapper.getMemberInfo(token);
                    balance = BigDecimal.valueOf(Long.valueOf(map.get("balance").toString()));
                    memberModel.setBalance(balance);
                    map.clear();
                    map.put("retCode", "0");
                    map.put("data", memberModel);
                } else {
                    map.clear();
                    map.put("retCode", "UPDATE_BALANCE_FAILS");
                }

            } else {
                map.put("retCode", "SIGN_FAILS");
            }
            JSONObject json = new JSONObject();
            json = new JSONObject(map);
            System.out.println("測試平台餘額更新出參:" + json);

            return json;

        } catch (Exception e) {
            e.printStackTrace();
            map.clear();
            map.put("retCode", "UPDATE_BALANCE_FAILS");
            JSONObject json = new JSONObject();
            json = new JSONObject(map);
            return json;
        }

    }

}
