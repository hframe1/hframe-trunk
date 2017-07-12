import java.util.*;

/**
 * Created by zhangquanhong on 2016/5/5.
 */
public class Demo {

    public static void main(String[] args) {
        Character[][] charTable = new Character[][]{
                new Character[]{'t','h','i','s'},
                new Character[]{'w','a','t','s'},
                new Character[]{'o','a','h','g'},
                new Character[]{'f','g','d','t'},
        };

        fetch(charTable,new String[]{"this","two","fat","that"});
    }

    public static void fetch(Character[][] charTable , String[] words) {

        Map<Character, List<int[]>> charMap = new HashMap<Character, List<int[]>>();

        for (int i = 0; i < charTable.length; i++) {
            for (int j = 0; j < charTable[i].length; j++) {
                Character character = charTable[i][j];
                if(!charMap.containsKey(character)) {
                    charMap.put(character, new ArrayList<int[]>());
                }
                charMap.get(character).add(new int[]{i,j});
            }
        }

        for (String word : words) {
            char charA = word.charAt(0);
            char charB = word.charAt(1);

            List<int[]> positionA = charMap.get(charA);
            List<int[]> positionB = charMap.get(charB);

            flag : for (int[] intA : positionA) {
                for (int[] intB : positionB) {
                    if(Math.abs(intA[0]-intB[0]) <=1 && Math.abs(intA[1]-intB[1]) <=1) {
                        if(intA[0] == intB[0] && intA[1] == intB[1]) {
                            continue;
                        }

                        int x = intB[0]-intA[0];
                        int y =intB[1]-intA[1];

                        int[] end = get(charTable, word.toCharArray(), charA, intA, x, y);
                        if(end != null) {
                            intA[0]++;
                            intA[1]++;
                            end[0]++;
                            end[1]++;
                            System.out.println(word + "已算出，开始：" + Arrays.toString(intA) + "; 结束：" + Arrays.toString(end));
                            break flag;
                        }
                    }
                }
            }
        }

    }

    private static int[]  get(Character[][] charTable, char[] word, char charA, int[] intA, int x, int y) {

        int[] position= null;

        for (char char1 : word) {

            if(position == null) {
                position= new int[]{intA[0], intA[1]};
            }else {
                position[0] +=x;
                position[1] +=y;
            }

            if(position[0] >= 0 && position[0] < charTable.length && position[1] >= 0 && position[1] < charTable[0].length) {
                if(charTable[position[0]][position[1]] != char1) {
                    return null;
                }
            }else {
                return null;
            }
        }

        return position;
    }
}
