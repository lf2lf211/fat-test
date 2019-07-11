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


    public JSONObject getBalance(String token, String sign) throws IOException {
        return null;
    }

    public Map<String, Object> getLoginInfo(String token, String sign) throws IOException {
        return null;
    }

    public JSONObject updateBalance(String data) throws IOException, InterruptedException {
        return null;
    }
}
