package com.yuanbao.testttt.controller;

import static com.yuanbao.testttt.vo.ResponseCode.SUCESS;

import com.yuanbao.testttt.service.imp.DeleteSPService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yuanbao.testttt.controller.base.DefaultController;
import com.yuanbao.testttt.service.imp.TestApiService;
import com.yuanbao.testttt.vo.ServerResponse;


@RestController
@RequestMapping(value = "/inner-api")
public class TestApiController extends DefaultController {

    @Autowired
    @Qualifier("testApiService")
    TestApiService testApiService;
    @Autowired
    @Qualifier("deleteSPService")
    DeleteSPService deleteSPService;

    private static final Logger log = LoggerFactory.getLogger(TestApiService.class);

    // 測試用
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public ResponseEntity<ServerResponse> testAccount() {

        try {
            log.info("進入");

            testApiService.test();
            log.info("結束");

            return new ResponseEntity<ServerResponse>(ServerResponse.create(SUCESS.getRetCode()), headers,
                    HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping(value = "/test2", method = RequestMethod.GET)
    public ResponseEntity<ServerResponse> testAccount2() {

        try {
            log.info("進入");

            testApiService.test2();
            log.info("結束");

            return new ResponseEntity<ServerResponse>(ServerResponse.create(SUCESS.getRetCode()), headers,
                    HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping(value = "/deleteSpMember/{spMemberId}", method = RequestMethod.GET)
    public ResponseEntity<ServerResponse> deleteSpMember(@PathVariable(value = "spMemberId") Long spMemberId) {

        try {
            log.info("進入");
            deleteSPService.delectSpMember(spMemberId);
            log.info("結束");
            return new ResponseEntity<ServerResponse>(ServerResponse.create(SUCESS.getRetCode()), headers,
                    HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
