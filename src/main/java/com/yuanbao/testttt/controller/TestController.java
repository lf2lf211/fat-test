package com.yuanbao.testttt.controller;

import com.yuanbao.testttt.controller.base.DefaultController;
import com.yuanbao.testttt.service.dto.api.DataReqDto;
import com.yuanbao.testttt.service.imp.TestSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;


@RestController
public class TestController extends DefaultController {

    @Autowired
    @Qualifier("testSer")
    TestSer testSer;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test() {

        return testSer.test();
    }

    /**
     * <p>enter(GET)-取得遊戲資訊</p>
     *
     * @throws IOException
     * @user Eric修義 2018年5月2日 上午11:09:59
     */
    @RequestMapping(value = "/getBalanceUrl", method = RequestMethod.POST)
    public String getBalanceUrl(@RequestBody DataReqDto dataReqDto) throws IOException {
        return testSer.getBalance(dataReqDto.getToken(), dataReqDto.getSign()).toString();
    }

    /**
     * <p>enter(GET)-取得遊戲資訊</p>
     *
     * @throws IOException
     * @user Eric修義 2018年5月2日 上午11:09:59
     */
    @RequestMapping(value = "/getLoginInfoUrl", method = RequestMethod.POST)
    public Map<String, Object> getLoginInfoUrl(@RequestBody DataReqDto dataReqDto) throws IOException {
        return testSer.getLoginInfo(dataReqDto.getToken(), dataReqDto.getSign());
    }

    /**
     * <p>enter(GET)-取得遊戲資訊</p>
     *
     * @throws IOException
     * @user Eric修義 2018年5月2日 上午11:09:59
     */
    @RequestMapping(value = "/updateBalanceUrl", method = RequestMethod.POST)
    public String updateBalanceUrl(@RequestBody String data) throws IOException, InterruptedException {

        System.out.println("測試平台餘額更新進參:" + data);

        return testSer.updateBalance(data).toString();

    }
}
