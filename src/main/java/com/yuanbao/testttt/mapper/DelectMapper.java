package com.yuanbao.testttt.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;


@Mapper
public interface DelectMapper {

    @Select(value = "SELECT id FROM `spProductInfo` where spMemberId  = #{spMemberId,jdbcType=INTEGER} ")
    ArrayList<Long> getDeleteSpProductInfoId(@Param("spMemberId") Long spMemberId);

    @Select(value = "SELECT id   FROM `spProductDailyRecord` WHERE spProductInfoId =  #{spProductInfoId,jdbcType=INTEGER}")
    ArrayList<Long> getDeleteSpProductDailyRecordId(@Param("spProductInfoId") Long spProductInfoId);

    @Select(value = "SELECT id FROM `spProductRateInfo` WHERE  spProductInfoId= #{spProductInfoId,jdbcType=INTEGER}")
    ArrayList<Long> getDeleteSpProductRateInfoId(@Param("spProductInfoId") Long spProductInfoId);

    @Select(value = "SELECT id FROM `chatRoom` WHERE   spProductInfoId= #{spProductInfoId,jdbcType=INTEGER}")
    ArrayList<Long> getDeletechatRoomId(@Param("spProductInfoId") Long spProductInfoId);

    @Select(value = " SELECT id FROM `chatGameResult` where chatRoomId=#{chatRoomId,jdbcType=INTEGER}")
    ArrayList<Long> getDeletechatGameResultId(@Param("chatRoomId") Long chatRoomId);

    @Select(value = "  SELECT id FROM `bet`  WHERE spProductInfoId =#{spProductInfoId,jdbcType=INTEGER}")
    ArrayList<Long> getDeletebetId(@Param("spProductInfoId") Long spProductInfoId);

    @Select(value = "   SELECT id FROM `betDetail`  where betId =#{betId,jdbcType=INTEGER}")
    ArrayList<Long> getDeletebetDetailId(@Param("betId") Long betId);

    @Select(value = " SELECT id FROM `gameResult` where spMemberId=#{spMemberId,jdbcType=INTEGER}")
    ArrayList<Long> getDeletegameResultId(@Param("spMemberId") Long spMemberId);


    @Delete(value = "DELETE FROM `spApiEncryptInfo` WHERE `spApiInfoId` IN(SELECT id FROM `spApiInfo` WHERE spMemberId =#{spMemberId,jdbcType=INTEGER}) ")
    void deleteSpApiEncryptInfo(@Param("spMemberId") Long spMemberId);

    @Delete(value = " DELETE FROM `spApiInfo` WHERE spMemberId  =#{spMemberId,jdbcType=INTEGER} ")
    void deleteSpApiInfo(@Param("spMemberId") Long spMemberId);


    @Delete(value = "DELETE FROM `spProductConfig`   WHERE spProductInfoId = #{spProductInfoId,jdbcType=INTEGER}")
    void deleteSpProductConfig(@Param("spProductInfoId") Long spProductInfoId);

    @Delete(value = "  DELETE FROM `spProductDailyRecordDetail`   WHERE spProductDailyRecordId =#{spProductDailyRecordId,jdbcType=INTEGER} ")
    void deleteSpProductDailyRecordDetail(@Param("spProductDailyRecordId") Long spProductDailyRecordId);

    @Delete(value = "DELETE  FROM `spProductRateHistoryInfo` WHERE spProductRateInfoId =#{spProductRateInfoId,jdbcType=INTEGER} ")
    void deleteSpProductRateHistoryInfo(@Param("spProductRateInfoId") Long spProductRateInfoId);

    @Delete(value = " DELETE FROM `spProductDailySum` WHERE  spProductInfoId  =#{spProductInfoId,jdbcType=INTEGER} ")
    void deleteSpProductDailySum(@Param("spProductInfoId") Long spProductInfoId);

    @Delete(value = " DELETE FROM `spProductRateInfo` WHERE  spProductInfoId  =#{spProductInfoId,jdbcType=INTEGER} ")
    void deletespProductRateInfo(@Param("spProductInfoId") Long spProductInfoId);

    @Delete(value = " DELETE FROM `spProductDailyRecord` WHERE  spProductInfoId  =#{spProductInfoId,jdbcType=INTEGER} ")
    void deletespProductDailyRecord(@Param("spProductInfoId") Long spProductInfoId);


    @Delete(value = " DELETE  FROM `chatGameResultDetail`where chatGameResultId =#{chatGameResultId,jdbcType=INTEGER} ")
    void deletechatGameResultDetail(@Param("chatGameResultId") Long chatGameResultId);

    @Delete(value = " DELETE  FROM `chatGameResultApplyDealer` where  chatGameResultId  =#{chatGameResultId,jdbcType=INTEGER} ")
    void deletechatGameResultApplyDealer(@Param("chatGameResultId") Long chatGameResultId);

    @Delete(value = " DELETE  FROM `chatGameResult` where id =#{id,jdbcType=INTEGER} ")
    void deletechatGameResult(@Param("id") Long chatGameResultId);

    @Delete(value = "  DELETE  FROM `chatRoom` WHERE  id  =#{id,jdbcType=INTEGER} ")
    void deletechatRoom(@Param("id") Long chatRoomId);

    @Delete(value = "DELETE  FROM `betDetailContent` where betDetailId =#{betDetailId,jdbcType=INTEGER} ")
    void deletebetDetailContent(@Param("betDetailId") Long betDetailId);

    @Delete(value = "   DELETE  FROM `betDetail`  where  id  =#{id,jdbcType=INTEGER} ")
    void deletebetDetail(@Param("id") Long betDetailId);

    @Delete(value = "  DELETE  FROM `betMultiple` where betId  =#{betId,jdbcType=INTEGER} ")
    void deletebetMultiple(@Param("betId") Long betId);

    @Delete(value = "   DELETE  FROM `payout` where betId  =#{betId,jdbcType=INTEGER} ")
    void deletepayout(@Param("betId") Long betId);

    @Delete(value = "    DELETE FROM `bet`  WHERE id  =#{id,jdbcType=INTEGER} ")
    void deletebet(@Param("id") Long betId);


    @Delete(value = "  DELETE  FROM `preGameResult` where  spProductInfoId   =#{spProductInfoId,jdbcType=INTEGER} ")
    void deletepreGameResult(@Param("spProductInfoId") Long spProductInfoId);

    @Delete(value = " DELETE  FROM `gameOdds` where gameResultId   =#{gameResultId,jdbcType=INTEGER} ")
    void deletegameOdds(@Param("gameResultId") Long gameResultId);

    @Delete(value = "  DELETE FROM `gameResultDetail` where gameResultId   =#{gameResultId,jdbcType=INTEGER} ")
    void deletegameResultDetail(@Param("gameResultId") Long gameResultId);

    @Delete(value = "  DELETE  FROM `gameResult` where id    =#{id,jdbcType=INTEGER} ")
    void deletegameResult(@Param("id") Long gameResultId);

    @Delete(value = "    DELETE FROM `spProductInfo`   WHERE id   =#{spProductInfoId,jdbcType=INTEGER} ")
    void deletespProductInfo(@Param("spProductInfoId") Long spProductInfoId);

    @Delete(value = "    DELETE FROM `spMember`   WHERE id   =#{spMemberId,jdbcType=INTEGER} ")
    void deletespMember(@Param("spMemberId") Long spMemberId);

}
