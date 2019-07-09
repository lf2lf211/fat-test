package com.yuanbao.testttt.service.imp;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;


@Service
public class SelfRacingScheduleUtil {


	// 冠亞二星
	public static String mappingResult_double(String gameResult) {
		String gameSet = "";
		String gameResultSerials[] = gameResult.split(",");
		String gameResult1 = gameResultSerials[0];// 第一名車號
		String gameResult2 = gameResultSerials[1];

		gameSet += gameResult1 + "_" + gameResult2;

		return gameSet;

	}

	// 定位膽
	public static String mappingResult_single(String gameResult) {
		String gameSet = "";
		String gameResultSerials[] = gameResult.split(",");
		gameSet += "first_" + gameResultSerials[0] + " ";
		gameSet += "second_" + gameResultSerials[1] + " ";

		gameSet += "third_" + gameResultSerials[2] + " ";
		gameSet += "fourth_" + gameResultSerials[3] + " ";
		gameSet += "fifth_" + gameResultSerials[4] + " ";
		gameSet += "sixth_" + gameResultSerials[5] + " ";
		gameSet += "seventh_" + gameResultSerials[6] + " ";
		gameSet += "eighth_" + gameResultSerials[7] + " ";
		gameSet += "ninth_" + gameResultSerials[8] + " ";
		gameSet += "tenth_" + gameResultSerials[9] + " ";
		return gameSet;
	}

	// 冠亞和
	public static String mappingResult_sum(String gameResult) {

		String gameResultSerials[] = gameResult.split(",");

		int sum = Integer.valueOf(gameResultSerials[0]) + Integer.valueOf(gameResultSerials[1]);

		String gameSet = "";

		// 大小
		if (sum < 12 && sum > 0) {
			gameSet += "sum_small ";
		} else if (sum >= 12 && sum < 20) {
			gameSet += "sum_big ";
		}
		// 單雙
		if (sum % 2 == 1) {
			gameSet += "sum_odd ";
		} else {
			gameSet += "sum_even ";
		}

		// 和值
		if (sum < 10) {
			gameSet += "sum_0" + sum + " ";
		} else {
			gameSet += "sum_" + sum + " ";
		}
		return gameSet;
	}

	// 大小單雙 龍虎
	public static String mappingResult_bigSmall(String gameResult) {
		String gameSet = "";

		String gameResultSerials[] = gameResult.split(",");

		int gameResult1 = Integer.valueOf(gameResultSerials[0]);// 第一名車號
		int gameResult2 = Integer.valueOf(gameResultSerials[1]);
		int gameResult3 = Integer.valueOf(gameResultSerials[2]);
		int gameResult4 = Integer.valueOf(gameResultSerials[3]);
		int gameResult5 = Integer.valueOf(gameResultSerials[4]);
		int gameResult6 = Integer.valueOf(gameResultSerials[5]);
		int gameResult7 = Integer.valueOf(gameResultSerials[6]);
		int gameResult8 = Integer.valueOf(gameResultSerials[7]);
		int gameResult9 = Integer.valueOf(gameResultSerials[8]);
		int gameResult10 = Integer.valueOf(gameResultSerials[9]);

		if (gameResult1 >= 6) {
			gameSet += "first_big ";
		} else if (gameResult1 < 6) {
			gameSet += "first_small ";
		}
		if (gameResult1 % 2 == 1) {
			gameSet += "first_odd ";
		} else {
			gameSet += "first_even ";
		}

		if (gameResult2 >= 6) {
			gameSet += "second_big ";
		} else if (gameResult2 < 6) {
			gameSet += "second_small ";
		}
		if (gameResult2 % 2 == 1) {
			gameSet += "second_odd ";
		} else {
			gameSet += "second_even ";
		}

		if (gameResult3 >= 6) {
			gameSet += "third_big ";
		} else if (gameResult3 < 6) {
			gameSet += "third_small ";
		}
		if (gameResult3 % 2 == 1) {
			gameSet += "third_odd ";
		} else {
			gameSet += "third_even ";
		}

		if (gameResult4 >= 6) {
			gameSet += "fourth_big ";
		} else if (gameResult4 < 6) {
			gameSet += "fourth_small ";
		}
		if (gameResult4 % 2 == 1) {
			gameSet += "fourth_odd ";
		} else {
			gameSet += "fourth_even ";
		}

		if (gameResult5 >= 6) {
			gameSet += "fifth_big ";
		} else if (gameResult5 < 6) {
			gameSet += "fifth_small ";
		}
		if (gameResult5 % 2 == 1) {
			gameSet += "fifth_odd ";
		} else {
			gameSet += "fifth_even ";
		}

		if (gameResult6 >= 6) {
			gameSet += "sixth_big ";
		} else if (gameResult6 < 6) {
			gameSet += "sixth_small ";
		}
		if (gameResult6 % 2 == 1) {
			gameSet += "sixth_odd ";
		} else {
			gameSet += "sixth_even";
		}

		if (gameResult7 >= 6) {
			gameSet += "seventh_big ";
		} else if (gameResult7 < 6) {
			gameSet += "seventh_small ";
		}
		if (gameResult7 % 2 == 1) {
			gameSet += "seventh_odd ";
		} else {
			gameSet += "seventh_even ";
		}

		if (gameResult8 >= 6) {
			gameSet += "eighth_big ";
		} else if (gameResult8 < 6) {
			gameSet += "eighth_small ";
		}
		if (gameResult8 % 2 == 1) {
			gameSet += "eighth_odd ";
		} else {
			gameSet += "eighth_even ";
		}

		if (gameResult9 >= 6) {
			gameSet += "ninth_big ";
		} else if (gameResult9 < 6) {
			gameSet += "ninth_small ";
		}
		if (gameResult9 % 2 == 1) {
			gameSet += "ninth_odd ";
		} else {
			gameSet += "ninth_even ";
		}

		if (gameResult10 >= 6) {
			gameSet += "tenth_big ";
		} else if (gameResult10 < 6) {
			gameSet += "tenth_small ";
		}
		if (gameResult10 % 2 == 1) {
			gameSet += "tenth_odd ";
		} else {
			gameSet += "tenth_even ";
		}

		if (gameResult1 > gameResult10) {
			gameSet += "first_D ";
		} else {
			gameSet += "first_T ";
		}

		if (gameResult2 > gameResult9) {
			gameSet += "second_D ";
		} else {
			gameSet += "second_T ";
		}
		if (gameResult3 > gameResult8) {
			gameSet += "third_D ";
		} else {
			gameSet += "third_T ";
		}

		if (gameResult4 > gameResult7) {
			gameSet += "fourth_D ";
		} else {
			gameSet += "fourth_T ";
		}

		if (gameResult5 > gameResult6) {
			gameSet += "fifth_D ";
		} else {
			gameSet += "fifth_T ";
		}

		return gameSet;
	}

	public static BigDecimal mappingRate_bigSmall(BigDecimal baseRate) {
		return BigDecimal.valueOf(2).multiply(baseRate).setScale(3, BigDecimal.ROUND_HALF_UP);
	}

	public static BigDecimal mappingRate_single(BigDecimal baseRate) {
		return BigDecimal.valueOf(10).multiply(baseRate).setScale(3, BigDecimal.ROUND_HALF_UP);
	}

	public static BigDecimal mappingRate_double(BigDecimal baseRate) {
		return BigDecimal.valueOf(42).multiply(baseRate).setScale(3, BigDecimal.ROUND_HALF_UP);
	}

	public static BigDecimal mappingRate_sum(String type,BigDecimal baseRate) {
		double rate = 0;
		if (type.equals("sum_03") || type.equals("sum_04") || type.equals("sum_18") || type.equals("sum_19")) {
			rate = 42;
		} else if (type.equals("sum_05") || type.equals("sum_06") || type.equals("sum_16") || type.equals("sum_17")) {
			rate = 21;
		} else if (type.equals("sum_14") || type.equals("sum_15") || type.equals("sum_07") || type.equals("sum_08")) {
			rate = 14;
		} else if (type.equals("sum_12") || type.equals("sum_13") || type.equals("sum_09") || type.equals("sum_10")) {
			rate = 10;
		} else if (type.equals("sum_11")) {
			rate = 8;
		} else if (type.equals("sum_small") || type.equals("sum_odd")) {
			rate = 1.686868687;
		} else if (type.equals("sum_even") || type.equals("sum_big")) {
			rate = 2.080808081;
		}
		return BigDecimal.valueOf(rate).multiply(baseRate).setScale(3, BigDecimal.ROUND_HALF_UP);
	}

}
