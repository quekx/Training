package com.qkx.example.lc.achive.medium;

import java.util.*;

/**
 * @author kaixin
 * @since 2021-11-15 20:08
 */
//Given a string s containing an out-of-order English representation of digits 0
//-9, return the digits in ascending order.
//
//
// Example 1:
// Input: s = "owoztneoer"
//Output: "012"
// Example 2:
// Input: s = "fviefuro"
//Output: "45"
//
//
// Constraints:
//
//
// 1 <= s.length <= 10⁵
// s[i] is one of the characters ["e","g","f","i","h","o","n","s","r","u","t",
//"w","v","x","z"].
// s is guaranteed to be valid.
//
// Related Topics Hash Table Math String 👍 455 👎 1362


//leetcode submit region begin(Prohibit modification and deletion)
class No423 {

    public static void main(String[] args) {
        String s = "fviefurozerofiveonezero";
        System.out.println(new No423().originalDigits(s));
    }

    public String originalDigits(String s) {
        String[] words = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
        Map<Character, Map<Integer, Integer>> map = new HashMap<>();

        for (int i = 0; i < words.length; i++) {
            for (char c : words[i].toCharArray()) {
                Map<Integer, Integer> charMap = map.computeIfAbsent(c, character -> new HashMap<>());
                charMap.put(i, charMap.getOrDefault(i, 0) + 1);
            }
        }

        Map<Character, Integer> sCountMap = new HashMap<>();
        for (char c : s.toCharArray()) {
            sCountMap.put(c, sCountMap.getOrDefault(c, 0) + 1);
        }

        int[] result = new int[words.length];
        while (!map.isEmpty()) {
            Map.Entry<Character, Map<Integer, Integer>> single = null;
            for (Map.Entry<Character, Map<Integer, Integer>> element : map.entrySet()) {
                if (element.getValue().size() == 1) {
                    single = element;
                    break;
                }
            }
            if (single == null) {
                break;
            }

            char c = single.getKey();
            int i = single.getValue().keySet().iterator().next();
            int num = single.getValue().values().iterator().next();
            for (char subChar : words[i].toCharArray()) {
                map.get(subChar).remove(i);
            }

            Integer cCount = sCountMap.get(c);
            if (cCount == null || cCount == 0) {
                map.remove(c);
                continue;
            }

            // 确定i的个数
            result[i] = cCount / num;
            for (char subChar : words[i].toCharArray()) {
                sCountMap.put(subChar, sCountMap.get(subChar) - result[i]);
            }
        }

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i <= result.length - 1; i++) {
            if (result[i] > 0) {
                for (int k = 1; k <= result[i]; k++) {
                    builder.append(i);
                }
            }
        }
        return builder.toString();
    }

    /**
     * 回溯版本超时
     * 运行失败:
     * Time Limit Exceeded
     * 测试用例:"ertfsxvxttiorseenivsoiwosefeinoinwoioousiieihtfirrnioeenwiortwsefwnnniseoisontieiitnvsovthrenwfitfenoiwouwtrtnxneisieinovhuieenrveenoiuoireooieiuittefotnoeeftfsetooeniuvrsnvetwieetvoneeoiieeiiirhftnrntihenseveeorioriononnuhweixefoiotowxreossentetresvzoerfeonvhfoestveooxesintstvefewrifsneeexveefoxonvueituwoieefvofwrfistixeesooxoeweteortfevtsuffohnfruiwitnuxexrevrhfenwenofnzfvsiofeneesoshoefxoefieiieeoueofoivshvtrsoneenenwiexnifttfeoooetesnouewtneisfeenwiiwnvuntnwuesniffweeoretviftseeexeoetzntefnfoxesfforvrwrrhrewtegwsnvinotonitvxeerwooeefswxxtfixenwfxioevierefevinviwvxonfitouevwxnshiewuntwunniennvunvfnnxinovfsvohrresenffintfissstnrfwinsegrrnesentoitoieneiiinuenrtssgosetoufnniuesneseonesetnstiofieeosvoowooeueietrheiienstifweeueitioretiwefwvonsernsxrtteeiisofttwnorovenoisnrsoxseoeeoeeovnoweeowhfirovesxeutisnnviieneetvuxnreoenxinfnrvxntooeonroewxeoirxenfeeehietriuoofxiexwntotointhwvtnfwtnrieeofifinevsfxioninsvntenrhwiwovfenwfrffiofeiehuriieeeswooeofienuoei... (show balloon)
     *
     * @param s
     * @return
     */
    public String originalDigits2(String s) {
        int[] numCount = new int[10];
        String[] words = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};

        char[] arr = s.toCharArray();
        int[] charCount = new int[26];
        for (char c : arr) {
            charCount[c - 'a']++;
        }

        backTrace(words, numCount, charCount);
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i <= 9; i++) {
            if (numCount[i] > 0) {
                for (int k = 1; k <= numCount[i]; k++) {
                    builder.append(i);
                }
            }
        }
        return builder.toString();
    }

    private boolean backTrace(String[] words, int[] numCount, int[] charCount) {
        if (isOk(charCount)) {
            return true;
        }

        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            if (check(word, charCount)) {
                removeWord(word, charCount, numCount, i);
                if (backTrace(words, numCount, charCount)) {
                    return true;
                }
                rollbackWord(word, charCount, numCount, i);
            }
        }
        return false;
    }

    private void rollbackWord(String word, int[] charCount, int[] numCount, int i) {
        for (char c : word.toCharArray()) {
            charCount[c - 'a']++;
        }
        numCount[i]--;
    }

    private void removeWord(String word, int[] charCount, int[] numCount, int i) {
        for (char c : word.toCharArray()) {
            charCount[c - 'a']--;
        }
        numCount[i]++;
    }

    private boolean check(String word, int[] charCount) {
        for (char c : word.toCharArray()) {
            if (charCount[c - 'a'] == 0) {
                return false;
            }
        }
        return true;
    }

    private boolean isOk(int[] charCount) {
        for (int count : charCount) {
            if (count > 0) {
                return false;
            }
        }
        return true;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

