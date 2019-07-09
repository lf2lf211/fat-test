package com.yuanbao.testttt.service.imp;

import com.yuanbao.testttt.mapper.BetDetailMapper;
import com.yuanbao.testttt.mapper.DelectMapper;
import com.yuanbao.testttt.service.IChatApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Map;


@Service
public class DeleteSPService {

    @Value("${project.hostName}")
    private String hostName;

    @Autowired
    private DelectMapper delectMapper;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Transactional(rollbackFor = Exception.class)
    public void delectSpMember(Long spMemberId) {
        Long[] list = {spMemberId};
//        Long[] list = {6L, 7L, 8L, 9L, 10L, 11L, 12L, 13L, 31L, 32L, 59L, 14L, 17L, 15L, 21L, 18L, 20L, 61L, 62L};

        logger.info("開始[{}]", spMemberId);
        delectMapper.deleteSpApiEncryptInfo(spMemberId);
        delectMapper.deleteSpApiInfo(spMemberId);
        ArrayList<Long> spProductInfoIdList = delectMapper.getDeleteSpProductInfoId(spMemberId);
        logger.info("spProductInfoIdList     [{}]", spProductInfoIdList);

        for (int j = 0; j < spProductInfoIdList.size(); j++) {
            Long spProductInfoId = spProductInfoIdList.get(j);
            logger.info("開始spProductInfoId[{}]", spProductInfoId);

            ArrayList<Long> betIdList = delectMapper.getDeletebetId(spProductInfoId);
            logger.info("betIdList     [{}]", betIdList);

            for (int k = 0; k < betIdList.size(); k++) {
                Long betId = betIdList.get(k);
                logger.info("開始betId[{}]", betId);

                ArrayList<Long> betDetailIdList = delectMapper.getDeletebetDetailId(betId);
                for (int l = 0; l < betDetailIdList.size(); l++) {
                    Long betDetailId = betDetailIdList.get(l);
                    delectMapper.deletebetDetailContent(betDetailId);
                    delectMapper.deletebetDetail(betDetailId);
                }
                delectMapper.deletebetMultiple(betId);
                delectMapper.deletepayout(betId);
                delectMapper.deletebet(betId);

            }

            //------------------------------------
            delectMapper.deleteSpProductConfig(spProductInfoId);
            delectMapper.deletepreGameResult(spProductInfoId);
            //------------------------------------

            ArrayList<Long> chatRoomIdList = delectMapper.getDeletechatRoomId(spProductInfoId);
            logger.info("chatRoomIdList    [{}]", chatRoomIdList);

            for (int k = 0; k < chatRoomIdList.size(); k++) {
                Long chatRoomId = chatRoomIdList.get(k);
                logger.info("開始chatRoomId[{}]", chatRoomId);

                ArrayList<Long> chatGameResultIdList = delectMapper.getDeletechatGameResultId(chatRoomId);
                logger.info("chatGameResultIdList    [{}]", chatGameResultIdList);

                for (int l = 0; l < chatGameResultIdList.size(); l++) {
                    Long chatGameResultId = chatGameResultIdList.get(l);
                    logger.info("開始chatGameResultId[{}]", chatGameResultId);

                    delectMapper.deletechatGameResultApplyDealer(chatGameResultId);
                    delectMapper.deletechatGameResultDetail(chatGameResultId);
                    delectMapper.deletechatGameResult(chatGameResultId);
                }


                delectMapper.deletechatRoom(chatRoomId);

            }

//            ArrayList<Long> gameResultIdList = delectMapper.getDeletegameResultId(spMemberId);
//            logger.info("gameResultIdList[{}]", gameResultIdList);
//
//            for (int l = 0; l < gameResultIdList.size(); l++) {
//                Long gameResultId = gameResultIdList.get(l);
//                logger.info("開始gameResultId[{}]", gameResultId);
//
//                delectMapper.deletegameOdds(gameResultId);
//                delectMapper.deletegameResultDetail(gameResultId);
//                delectMapper.deletegameResult(gameResultId);
//            }

            ArrayList<Long> spProductDailyRecordIdList = delectMapper.getDeleteSpProductDailyRecordId(spProductInfoId);
            for (int k = 0; k < spProductDailyRecordIdList.size(); k++) {
                Long spProductDailyRecordId = spProductDailyRecordIdList.get(k);
                delectMapper.deleteSpProductDailyRecordDetail(spProductDailyRecordId);
            }
            ArrayList<Long> spProductRateInfoIdList = delectMapper.getDeleteSpProductRateInfoId(spProductInfoId);
            for (int k = 0; k < spProductRateInfoIdList.size(); k++) {
                Long spProductRateInfoId = spProductRateInfoIdList.get(k);
                delectMapper.deleteSpProductRateHistoryInfo(spProductRateInfoId);
            }

            delectMapper.deletespProductDailyRecord(spProductInfoId);
            delectMapper.deleteSpProductDailySum(spProductInfoId);
            delectMapper.deletespProductRateInfo(spProductInfoId);
            delectMapper.deletespProductInfo(spProductInfoId);

        }

        delectMapper.deletespMember(spMemberId);
        logger.info("刪完:[{}]", spMemberId);
    }

}
