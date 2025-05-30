package com.qkx.example.lc.achive.hard;

/**
 * @author kaixin
 * @since 2021-10-19 11:33
 */
//Given an array nums which consists of non-negative integers and an integer m,
//you can split the array into m non-empty continuous subarrays.
//
// Write an algorithm to minimize the largest sum among these m subarrays.
//
//
// Example 1:
//
//
//Input: nums = [7,2,5,10,8], m = 2
//Output: 18
//Explanation:
//There are four ways to split nums into two subarrays.
//The best way is to split it into [7,2,5] and [10,8],
//where the largest sum among the two subarrays is only 18.
//
//
// Example 2:
//
//
//Input: nums = [1,2,3,4,5], m = 2
//Output: 9
//
//
// Example 3:
//
//
//Input: nums = [1,4,4], m = 3
//Output: 4
//
//
//
// Constraints:
//
//
// 1 <= nums.length <= 1000
// 0 <= nums[i] <= 10⁶
// 1 <= m <= min(50, nums.length)
//
// Related Topics Array Binary Search Dynamic Programming Greedy 👍 3576 👎 114


//leetcode submit region begin(Prohibit modification and deletion)
public class No410 {
    /**
     * 解答失败:
     * 测试用例:[3,2,3,1,2,4,5,5,6,7,7,8,2,3,1,1,1,10,11,5,6,2,4,7,8,5,6]
     * 20
     * 测试结果:8
     * 期望结果:11
     * <p>
     * 解答失败:
     * 测试用例:[7,2,5,10,8]
     * 2
     * 测试结果:24
     * 期望结果:18
     * <p>
     * 解答失败: 测试用例:[3,2,3,1,2,4,5,5,6,7,7,8,2,3,1,1,1,10,11,5,6,2,4,7,8,5,6] 20 测试结果:8 期望结果:11 stdout
     * <p>
     * 运行失败: Memory Limit Exceeded
     * 测试用例:[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,50,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,100,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,150,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,200,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,250,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,300,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,350,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,400,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,450,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,500,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,550,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,600,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,650,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,700,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,750,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,800,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,850,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,900,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,950,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]
     * 50
     * stdout:
     * [7,2,5,10,8] 2
     * @param args
     */
    public static void main(String[] args) {
        int[] nums = {7,2,5,10,8};
        int m = 2;
//        int[] nums =
//                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 50, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 100, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 150, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 200, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 250, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 300, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 350, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 400, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 450, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 500, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 550, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 600, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 650, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 700, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 750, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 800, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 850, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 900, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 950, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
//        int m = 50;
//        int[] nums =
//                {39396,75535,17610,81826,10343,69422,14335,9801,19955,99295,38101,24312,20341,69218,65487,38409,89920,17480,92688,71016,91144,51111,88996,24041,7190,78854,94001,80392,50540,48497,3153,43509,74239,48742,14946,5772,53828,15647,92326,93162,53714,24957,31602,1926,26875,35836,60646,69048,98012,92164,26077,14024,77649,67997,78341,18214,57985,44171,52842,55525,70084,74614,30378,77896,4212,99784,64496,46262,46399,8492,6978,30754,84811,36393,45151,6340,13544,98081,86997,95444,45237,20037,14037,53490,38646,17000,74471,44593,82428,74864,19016,54838,58668,11032,84860,99259,76070,30163,49073,71500,28207,64386,70747,48359,13511,82968,98839,15888,90781,2133,28037,46092,31757,44124,36338,44268,97854,93749,36361,2179,77441,85528,16717,97573,26685,84894,43169,59012,33270,86629,11359,28595,47766,21965,65394,87355,57990,57962,13864,37360,96847,52645,93474,88997,88822,40161,32062,12006,1952,54158,90362,40220,34257,84673,93803,9722,68847,78587,58552,66210,17038,64681,51612,38151,45862,7565,34865,22118,3179,81710,32238,494,86367,87808,94954,37642,1944,25980,23970,4818,89584,27863,10048,8073,33515,14971,75233,96927,47280,35223,61500,64730,30107,47455,64536,95560,50286,87337,7105,25961,31925,70002,46739,68103,70275,54251,34629,66383,76483,12288,70671,99958,80034,13642,24341,15929,59181,44814,79352,71087,54428,18788,73285,68203,25968,74869,11750,48436,99493,16714,7501,49344,3942,797,19437,66095,33367,88037,16700,22559,34724,7128,83307,46293,57333,94730,28051,55133,52280,16397,61754,41447,51182,54450,4157,62207,30813,90077,49368,36572,87523,89996,43369,14540,6448,51950,28665,87214,33545,85912,18754,70886,16646,71827,68529,99288,3294,42185,52304,71541,25942,22506,8910,73719,83262,12615,63150,96392,46289,79797,56970,22172,54358,11895,80255,51527,59,22591,16126,47403,21702,21654,30731,84466,46744,70066,22379,69028,5242,89976,10067,88510,90661,10597,67939,62942,38747,2934,91174,55875,10476,18975,75385,59127,51002,53840,96733,54994,2310,51944,91857,74269,32636,31301,25461,34167,50288,59316,64029,88498,54665,14288,29800,62385,93371,16246,5820,62258,1951,2920,48093,31781,98049,11136,93114,39263,70104,36356,90273,14790,35511,10473,27269,90261,46748,7875,70785,29114,35197,74539,25170,94676,91668,70436,9289,88109,48743,70069,54481,58092,60815,66278,65467,35089,3283,45671,65769,42535,29309,99118,3658,34335,35693,68280,61192,34210,37485,18878,3593,30740,35511,3814,21030,6306,66511,52145,31918,90336,29722,54525,18282,5266,59838,47867,29745,11037,54417,15356,23216,50610,12648,46034,21330,81758,44067,3453,53784,8866,373,21551,80679,97930,37269,96502,11843,52776,70726,73048,53663,426,4340,79144,37234,76898,36327,19869,51710,80170,17197,71294,46640,53032,48778,84951,73728,39584,48968,40581,5286,87553,9234,71765,12396,47211,26513,17436,11543,66703,51042,79709,81518,99022,57674,36347,7789,36730,53905,90066,62513,51427,81243,46684,64972,88637,94193,79082,38750,62313,49728,75999,14842,23500,5328,54499,11474,37112,35025,2619,31509,21142,17947,74544,18328,81018,23468,86927,39252,71442,14607,67312,83062,63658,76471,3479,74504,57442,23262,88930,71104,70179,22690,4753,56989,35209,93038,61268,74124,54177,76383,45454,50030,31690,44104,50299,65776,14730,28733,45098,97606,3496,167,67067,3866,5312,62448,38467,15829,46710,76473,49091,69653,44337,29725,56489,42984,28531,60449,4603,53116,333,36661,92545,37433,5503,51206,17112,91929,3841,91577,57429,30216,87156,95187,85566,62591,23717,64111,45833,43552,50266,87417,10200,43891,89379,23747,20494,87495,42853,66348,18389,40551,42566,86900,32310,41427,33121,77754,54335,72621,15805,1257,83941,20628,7539,19207,5371,87955,99785,52194,8441,84835,64762,62773,96928,53926,33461,10852,58832,20487,24325,12030,90420,67142,49419,76549,26707,66633,96190,13931,60538,69777,76489,81238,3703,62924,41317,75040,55449,65463,24450,88403,90391,54673,11272,59515,97298,22477,69252,54854,88438,28705,85722,36527,93378,15057,82883,51119,43150,32686,97169,81534,93366,8354,58910,87190,1669,99024,89131,53932,80643,83565,77430,62852,86602,86351,20248,99484,99610,24232,43297,53858,28368,1072,21948,62543,29218,15430,79178,83460,42288,46567,62948,16125,11399,90473,85725,30819,22185,7424,59822,82822,64693,77648,89251,28039,75026,16992,5185,19576,7815,77467,41194,59217,61657,75907,22034,44046,14137,19932,86280,73362,48552,40770,69424,25464,3628,69254,68662,37617,17664,11856,36391,30724,51221,68026,64866,47181,47606,59794,38866,37667,344,38729,41529,48959,52290,88360,82982,39548,34128,61787,78058,553,52390,25312,37114,45128,81242,31407,95570,66045,1190,44659,98532,96384,14237,53423,87793,84117,25039,25067,37402,78281,51124,34422,87766,1796,18376,49658,59386,76993,95644,97003,39833,8602,19411,52690,66169,5142,87400,4654,3146,26949,94882,79870,22139,65039,58974,48105,49020,29646,6802,43360,30271,90687,89959,78844,91073,62027,74762,17600,89525,84271,67172,20501,4186,80485,71569,31326,84056,44332,44517,98716,84751,75775,9412,12075,17561,45622,54833,47814,75903,63242,65429,94759,34778,59002,80836,93122,83916,72358,65067,47318,27829,90193,84500,97998,19806,82578,83386,45038,52692,13883,34346,74410,95084,50406,97094,29220,93059,99723,86132,25943,23498,54728,46123,80876,1330,69911,17618,69436,18403,65319,8291,80287,44823,17529,88146,85915,90747,81703,19569,19785,6405,56584,20351,3167,33104,32453,12010,59747,40481,48485,91354,64922,8521,79849,18178,32933,28741,70883,67936,95581,91960,81392,84728,16815,2531,83247,35302,45370,47649,88078,31649,90216,7494,38954,31020,35492,58777,91197,15435,11981,90859,52557,59098,82554,73359,32049,31012,70914,69312,97840,15859,28448,51918,27260,12484,67347,89859,97449,18455,28188,77258,7916,83616,57840,92155,59310,80682,48304,5410,70241,30873,3269,66928,87186,99735,99438,19026,78671,84083,67100,77968,44121,71496,32698,49522,42487,69067,25426,5562,21977,62143,92767,58263,70599,98185,20574,13616,80737,74722,60726,63051,46437,69650,81427,18118,53498,15706,10543,67537,35528,84448,90964,8074,89762,96395,43791,99016};
//        int m = 15;
//        System.out.println(nums.length);
        long start = System.nanoTime();
        System.out.println(new No410().splitArray(nums, m));
        System.out.println("cost: " + (System.nanoTime() - start) / 1000000 + " ms");
    }

    /**
     * 解答成功: 执行耗时:0 ms,击败了100.00% 的Java用户 内存消耗:40.2 MB,击败了84.59% 的Java用户
     */
    public int splitArray(int[] nums, int m) {
        if (nums == null || nums.length < m || m == 0) {
            return 0;
        }

        int max = 0, sum = 0;
        for (int num : nums) {
            max = Math.max(max, num);
            sum += num;
        }
        int l = max, r = sum;
        while (l < r) {
            int mid = l + ((r - l) >> 1);
            int splitNum = getSplitNum(nums, mid);
            if (splitNum == m) {
                // 切分的个数一致，不一定是最终答案，答案上边界为 mid, ans <= mid
                r = mid;
            } else if (splitNum > m) {
                // 切分的个数过多，说明数组和上限太小，答案下边界为 mid + 1, ans >= mid + 1
                l = mid + 1;
            } else {
                // 切分的个数过少，说明数组和上限太大，答案上边界为 mid - 1, ans <= mid - 1
                r = mid - 1;
            }
        }
        return l;
    }

    /**
     * 针对输入的 sum，最多把数组nums切割成 x 个数组，每个数组的和 <= sum
     * 返回 x
     */
    private int getSplitNum(int[] nums, int target) {
        int sum = nums[0];
        int count = 0;
        for (int i = 1; i < nums.length; i++) {
            if (sum + nums[i] > target) {
                count++;
                sum = nums[i];
            } else {
                sum += nums[i];
            }
        }
        return count + 1;
    }

    public int splitArray3(int[] nums, int m) {
        if (nums == null || nums.length < m || m == 0) {
            return 0;
        }

        int len = nums.length;
        int[][][] dp = new int[len][len][m + 1];
        for (int start = 0; start <= len - 1; start++) {
            dp[start][start][1] = nums[start];
            for (int end = start + 1; end <= len - 1; end++) {
                dp[start][end][1] = dp[start][end - 1][1] + nums[end];
            }
        }

        long count = 0;
        for (int i = 2; i <= m; i++) {
            for (int start = 0; start <= len - i; start++) {
                for (int end = start + i - 1; end <= len - 1; end++) {
                    int res = Integer.MAX_VALUE;
                    // 计算[start, end]
                    int k = start;
                    int j = end + 1 - i;
                    while (k <= j) {
                        count++;
                        int mid = (k + j) / 2;
                        int sx = dp[start][mid][1];
                        int sn = dp[mid + 1][end][i - 1];
                        if (sx > sn) {
                            res = Math.min(res, sx);
                            j = mid - 1;
                        } else {
                            res = Math.min(res, sn);
                            k = mid + 1;
                        }
                    }
                    dp[start][end][i] = res;
                }
            }
        }
        System.out.println("cal count >> " + count);
        return dp[0][len - 1][m];
    }

    /**
     * dp[start][end][i] (0 <= start <= end <= len - 1, 1 <= i <= m)
     * 代表 num[start, end] 数组分成 i 个子序列的结果
     * dp[start][end][i] = min(dp[start][start + x][y], dp[start + x + 1][end][i - y])
     *
     * @param nums
     * @param m
     * @return
     */
    public int splitArray2(int[] nums, int m) {
        if (nums == null || nums.length < m || m == 0) {
            return 0;
        }

        int len = nums.length;
        int[][][] dp = new int[len][len][m + 1];
        for (int start = 0; start <= len - 1; start++) {
            dp[start][start][1] = nums[start];
            for (int end = start + 1; end <= len - 1; end++) {
                dp[start][end][1] = dp[start][end - 1][1] + nums[end];
            }
        }

        long count = 0;
        for (int i = 2; i <= m; i++) {
            for (int start = 0; start <= len - 1; start++) {
                for (int end = start + i - 1; end <= len - 1; end++) {
                    int res = Integer.MAX_VALUE;
                    for (int mid = start; mid <= end + 1 - i; mid++) {
                        // 这个地方随着mid增大
                        // sx单调递增、sn单调递减
                        // 可用二分
                        count++;
                        int sx = dp[start][mid][1];
                        int sn = dp[mid + 1][end][i - 1]; // 这个要保证 end - mid >= i - 1
                        if (sx > sn) {
                            res = Math.min(res, sx);
                            break;
                        } else {
                            res = Math.min(res, sn);
                        }
                    }
                    dp[start][end][i] = res;
                }
            }
        }
        System.out.println("cal count >> " + count);
        return dp[0][len - 1][m];
    }

    public int f(int[] nums, int m) {
        /* Question says that, if m = 2 and array is [7,2,5,10,8], we can divide the array
       in 2 pieces in number of ways
       Case 1: [7] [2,5,10,8]
       Case 2 : [7,2] [5,10,8]
       Case 3 : [7,2,5] [10,8]
       Case 4 : [7,2,5,10] [8]

       Now, we should calulate the sum of the subarrays formed in each case
       Case 1 : 7   25
       Case 2 : 9   23
       Case 3 : 14  18
       Case 4 : 24  8

       Now, the largest sum among each case
       Case 1 : 25
       Case 2 : 23
       Case 3 : 18
       Case 4 : 24

       And minimum of all the cases,
       18 --> our final answer
    */

    /* Approach : The maximum pieces we can do of an array is equal to the size of the array
       And, the minimum would be just one

       If m = length of array, our final answer would be the largest element in the array
       If m = 1, our final answer is the sum of the array

       After, getting the range, we can think of applying binary search - to find the most optimal solution
    */


        int start = 0;
        int end = 0;

        for (int i = 0; i < nums.length; i++) {
            start = Math.max(start, nums[i]);
            end = end + nums[i];
        }

        //BINARY SEARCH
        while (start < end) {
            int mid = start + (end - start) / 2;

            //Considering mid as our answer, now we have to calulate that how many pieces do we get when we divide the array in such a way that mid is the max sum

            int sum = 0;
            int pieces = 1;

            for (int i = 0; i < nums.length; i++) {
                if ((sum + nums[i]) > mid) {
                    //As soon as the sum exceeds from the maximum (mid) value, we have to put it in new subarray

                    sum = nums[i];  //we start taking the sum from here now
                    pieces++;   //A subarray has been created
                } else {
                    sum = sum + nums[i];    //Adding the sum in the same array
                }
            }

            if (pieces > m) {
                //If the number of pieces made are increasing the pieces that are alloted to us, we must increase our max sum so the value of pieces is less

                start = mid + 1;
            } else {
                end = mid;
            }
        }

        //As the above loop will break when start == end, so we can return either of them
        return end;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

