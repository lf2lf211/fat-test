package com.yuanbao.testttt.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.math.BigDecimal;
import java.util.HashMap;


@Mapper
public interface MemberMapper {

	/*
	 * 根據TOKEN 找出會員
	 */
	@Select(value = "SELECT  account,balance,name,headerUrl FROM member  WHERE token=#{token,jdbcType=VARCHAR}")
	HashMap<String, Object> getMemberInfo(@Param("token") String token);

	@Update(value = "UPDATE member SET  balance=balance-#{balance,jdbcType=INTEGER}  WHERE account=#{account,jdbcType=VARCHAR}")
	int updateBalanceForBet(@Param("balance") BigDecimal balance, @Param("account") String account);

	@Update(value = "UPDATE member SET  balance=balance+#{balance,jdbcType=INTEGER}  WHERE account=#{account,jdbcType=VARCHAR}")
	int updateBalanceForPayout(@Param("balance") BigDecimal balance, @Param("account") String account);
	
	@Update(value = "UPDATE member SET  balance=#{balance,jdbcType=INTEGER}  WHERE account=#{account,jdbcType=VARCHAR}")
	int updateBalanceForReset(@Param("balance") BigDecimal balance, @Param("account") String account);

}
