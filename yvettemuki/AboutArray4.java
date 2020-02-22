import java.util.*;

public class AboutArray3 {

    // 1122 数组的相对排序
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        int currentIndex = 0;
        for (int i = 0; i < arr2.length; i++) {
            for (int j = currentIndex; j < arr1.length; j++) {
                if (arr1[j] == arr2[i]) {
                    int temp = arr1[j];
                    arr1[j] = arr1[currentIndex];
                    arr1[currentIndex] = temp;
                    currentIndex++;
                }
            }
        }
        Arrays.sort(arr1, currentIndex, arr1.length);
        return arr1;
    }

    // 1160 拼写单词
    public int countCharacters(String[] words, String chars) {
        int[] charArr = new int[30];
        for (int i = 0; i < chars.length(); i++) {
            charArr[chars.charAt(i) - 'a']++;
        }
        int totalNum = 0;
        for (int i = 0; i < words.length; i++) {
            int[] tempCharArr = charArr.clone();
            boolean isOneWordFinished = true;
            for (int j = 0; j < words[i].length(); j++) {
                char letter = words[i].charAt(j);
                if (tempCharArr[letter - 'a'] > 0) {
                    tempCharArr[letter - 'a']--;
                } else {
                    isOneWordFinished = false;
                    break;
                }
            }
            if (isOneWordFinished) {
                totalNum += words[i].length();
            }
        }
        return totalNum;
    }

    // 1002 查找常用字符串
    public List<String> commonChars(String[] A) {
        int[] charArr = new int[30];
        List<String> resList = new ArrayList<>();
        for (int i = 0; i < A[0].length(); i++) {
            charArr[A[0].charAt(i) - 'a']++;
        }
        for (int i = 1; i < A.length; i++) {
            int[] tempArr = new int[30];
            for (int j = 0; j < A[i].length(); j++) {
                tempArr[A[i].charAt(j) - 'a']++;
            }
            for (int k = 0; k < 30; k++) {
                if (charArr[k] > tempArr[k]) {
                    charArr[k] = tempArr[k];
                }
            }
        }
        for (int i = 0; i < charArr.length; i++) {
            for (int j = 0; j < charArr[i]; j++) {
                String letter = (char)((int)'a' + i)+"";
                resList.add(letter);
            }
        }
        return resList;
    }

    // 950 按递增顺序显示卡牌
    public int[] deckRevealedIncreasing(int[] deck) {
        Arrays.sort(deck);
        int deckLens = deck.length;
        int[] resArr = new int[deckLens];
        resArr[0] = deck[0];
        int repeatIndex = 0;
        for (int i = 0; i < deck.length; i++) {
            if (i == 0) {
                continue;
            }
            boolean canFill = false;
            for (;;repeatIndex++) {
                if (resArr[repeatIndex % deckLens] != 0) {
                    continue;
                }
                if (canFill == false) {
                    canFill = true;
                    continue;
                }
                resArr[repeatIndex % deckLens] = deck[i];
                break;
            }
        }
        return resArr;

    }


}
