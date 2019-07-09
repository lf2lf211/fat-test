package com.yuanbao.testttt.service.imp;

import com.yuanbao.testttt.mapper.BetDetailMapper;
import com.yuanbao.testttt.service.IChatApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Map;


@Service
public class TestApiService implements IChatApiService {

    @Value("${project.hostName}")
    private String hostName;

    @Autowired
    private BetDetailMapper betDetailMapper;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void test() {
        ArrayList<Map<String, Object>> betList = betDetailMapper.getBet();

        logger.info("開始");
        ArrayList<String> list = new ArrayList<String>();
        for (Map<String, Object> map : betList) {
            try {
                Double earn = Double.valueOf(map.get("earn").toString());
                Long betId = Long.valueOf(map.get("betId").toString());
                Long betDetailId = Long.valueOf(map.get("betDetailId").toString());

                String content = betDetailMapper.getBetDetailContent(betDetailId);
                String gameResult = betDetailMapper.getGameResultDetail(betId);
                Integer isWin = 1;

                if (SelfRacingScheduleUtil.mappingResult_bigSmall(gameResult).indexOf(content) > -1) {
                    // 大小單雙類別中獎
                } else if (SelfRacingScheduleUtil.mappingResult_sum(gameResult).indexOf(content) > -1) {
                    // 冠亞合類別中獎
                } else if (SelfRacingScheduleUtil.mappingResult_single(gameResult).indexOf(content) > -1) {
                    // 定位膽類別中獎
                } else if (SelfRacingScheduleUtil.mappingResult_double(gameResult).indexOf(content) > -1) {
                    // 冠亞二星類別中獎
                } else {
                    isWin = 0;
                }

                if (isWin == 0 && earn < 0) {// 此content 沒中獎且盈虧為-
                    continue;
                } else if (isWin == 1 && earn > 0) {// 此content 中獎且盈虧為+
                    continue;
                } else {
                    list.add("不正確   : betId : " + betId + ",betDetailId : " + betDetailId + ",earn : " + earn
                            + ",  result  :  " + gameResult + ",content :" + content);
                    logger.info("不正確   : betId : " + betId + ",betDetailId : " + betDetailId + ",earn : " + earn
                            + ",  result  :  " + gameResult + ",content :" + content);

                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        for (int i = 0; i < list.size(); i++) {
            logger.info(list.get(i));
        }

    }

    public void test2() {
        ArrayList<Map<String, Object>> list = betDetailMapper.getBetForGAA020685236();
//        ArrayList<Map<String, Object>> list = betDetailMapper.getBetDateil();
        int i = 0;
        for (Map<String, Object> map : list) {
            i = i + 1;
            if (i % 100 == 0) {
                logger.info("第" + i + "筆");
            }
            BigDecimal betRate = BigDecimal.valueOf(Integer.valueOf(map.get("betRate").toString()));
            betRate = betRate.divide(BigDecimal.valueOf(1000));
            String account = map.get("account").toString();
            Double betAmt = Double.valueOf(map.get("betAmt").toString());
            Long spProductInfoId = Long.valueOf(map.get("spProductInfoId").toString());

            BigDecimal earn = BigDecimal.valueOf(Integer.valueOf(map.get("earn").toString()));
            String content = map.get("value").toString();
            Long betId = Long.valueOf(map.get("betId").toString());
            Long betDetailId = Long.valueOf(map.get("betDetailId").toString());
            BigDecimal realEarn = BigDecimal.ZERO;
            String spMemberId = betDetailMapper.getSpId(spProductInfoId);
            String gameResult = betDetailMapper.getGameResultDetail(betId);

            if (SelfRacingScheduleUtil.mappingResult_bigSmall(gameResult).indexOf(content) > -1) {
                // 大小單雙類別中獎
                realEarn = SelfRacingScheduleUtil.mappingRate_bigSmall(betRate).multiply(BigDecimal.valueOf(betAmt))
                        .subtract(BigDecimal.valueOf(betAmt)).setScale(0, BigDecimal.ROUND_HALF_UP);

            } else if (SelfRacingScheduleUtil.mappingResult_sum(gameResult).indexOf(content) > -1) {
                // 冠亞合類別中獎
                realEarn = SelfRacingScheduleUtil.mappingRate_sum(content, betRate).multiply(BigDecimal.valueOf(betAmt))
                        .subtract(BigDecimal.valueOf(betAmt)).setScale(0, BigDecimal.ROUND_HALF_UP);
            } else if (SelfRacingScheduleUtil.mappingResult_single(gameResult).indexOf(content) > -1) {
                // 定位膽類別中獎
                realEarn = SelfRacingScheduleUtil.mappingRate_single(betRate).multiply(BigDecimal.valueOf(betAmt))
                        .subtract(BigDecimal.valueOf(betAmt)).setScale(0, BigDecimal.ROUND_HALF_UP);
            } else if (SelfRacingScheduleUtil.mappingResult_double(gameResult).indexOf(content) > -1) {
                // 冠亞二星類別中獎
                realEarn = SelfRacingScheduleUtil.mappingRate_double(betRate).multiply(BigDecimal.valueOf(betAmt))
                        .subtract(BigDecimal.valueOf(betAmt)).setScale(0, BigDecimal.ROUND_HALF_UP);
            } else {
                realEarn = BigDecimal.valueOf(betAmt).negate();
            }


            if (!(earn.intValue() == realEarn.intValue())) {
                logger.info(
                        "平台 : {}  ,  玩家 : {} ,  Earn : {}  , realEarn : {} , betAmt  : {} ,  betId :{} ,  betDetailId :  {}  ",
                        spMemberId, account, earn, realEarn, betAmt, betId, betDetailId);
            }


        }

    }

}
