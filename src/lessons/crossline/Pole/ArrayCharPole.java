package lessons.crossline.Pole;

import lessons.crossline.data.Point2d;

import java.util.Arrays;

public class ArrayCharPole extends AbstractPole {

    private char[][] pole;
    private boolean anobodyWin;
    private int POLE_SIZE = 0;
    private int lineSize = 0;

    public ArrayCharPole(int pole_size, int lineSize) {
        super(pole_size,lineSize) ;
        this.POLE_SIZE = pole_size;
        this.lineSize = lineSize;
        createPole();
    }

    // СЃРѕР·РґР°РЅРёРµ РёРіСЂРѕРІРѕРіРѕ РїРѕР»СЏ

    public void createPole() {
        pole = new char[POLE_SIZE][POLE_SIZE];
        for (int i = 0; i < POLE_SIZE; i++) {
            for (int j = 0; j < POLE_SIZE; j++) {
                pole[i][j] = '*';
            }
        }
    }

    // РµСЃС‚СЊ Р»Рё СЃРІРѕР±РѕРґРЅС‹Рµ РєР»РµС‚РєРё
    @Override
    public boolean hasCleanCell() {
        for (int i = 0; i < POLE_SIZE; i++) {
            for (int j = 0; j < POLE_SIZE; j++) {
                if (pole[i][j] == '*') {
                    return true;
                }
            }
        }
        return false;
    }

    // РїСЂРѕРІРµСЂРєР° РЅР° РєРѕСЂСЂРµРєС‚РЅРѕСЃС‚СЊ РєРѕРѕСЂРґРёРЅР°С‚
    @Override
    public boolean canMakeStep(Point2d step) {
        boolean isEmpty = true;
        int str = step.getY();
        int col = step.getX();

        // РџСЂРѕРІРµСЂРєР° РЅР° ...
        if (str < 0 || col < 0) {// ... РѕС‚СЂРёС†Р°С‚РµР»СЊРЅС‹Рµ РёРЅРґРµРєСЃС‹
            //System.out.println("РљРѕРѕСЂРґРёРЅР°С‚С‹ РЅРµ РјРѕРіСѓС‚ Р±С‹С‚СЊ РјРµРЅСЊС€Рµ РёР»Рё СЂР°РІРЅС‹ 0! ");
            isEmpty = false;
        } else if (str > pole[0].length - 1 || col > pole.length - 1) {// ... РІС‹С…РѕРґ Р·Р° РїСЂРµРґРµР»С‹ РїРѕР»СЏ
            //System.out.println("РљРѕРѕСЂРґРёРЅР°С‚С‹ РЅРµ РјРѕРіСѓС‚ Р±С‹С‚СЊ Р±РѕР»СЊС€Рµ СЂР°Р·РјРµСЂР° РёРіСЂРѕРІРѕРіРѕ РїРѕР»СЏ! ");
            isEmpty = false;
        } else if (pole[str][col] != '*') {// ... СЃРІРѕР±РѕРґРЅР°СЏ СЏС‡РµР№РєР°
            //System.out.println("РЇС‡РµР№РєР° Р·Р°РЅСЏС‚Р°! ");
            isEmpty = false;
        }

        return isEmpty;
    }

    @Override
    public void step(Point2d step, char aChar) {
        int str = step.getY();
        int col = step.getX();

        pole[str][col] = aChar;
    }

    // РіРѕСЂРёР·РѕРЅС‚Р°Р»СЊРЅС‹Р№ РїР°С‚С‚РµСЂРЅ
    public boolean lineHorizontal(char[][] arr, int sizePat, char aChar) {

        boolean isLine = false;
        int sizeLine;

        for (int i = 0; i < arr.length; i++) {
            sizeLine = 0;// РїСЂРё РїРµСЂРµС…РѕРґРµ РЅР° РґСЂСѓРіСѓСЋ СЃС‚СЂРѕРєСѓ - РѕР±РЅСѓР»РёС‚СЊ
            for (int j = 0; j < arr[0].length; j++) {// РІ РїСЂРµРґРµР»Р°С… РѕРґРЅРѕР№ СЃС‚СЂРѕРєРё
                if (arr[i][j] == aChar) {// РµСЃР»Рё "РЅР°С€Р°" СЏС‡РµР№РєР°
                    sizeLine++;// СѓРІРµР»РёС‡РёС‚СЊ СЃС‡РµС‚С‡РёРє
                } else sizeLine = 0;// РµСЃР»Рё РЅРµ "РЅР°С€Р°" СЏС‡РµР№РєР° - РѕР±РЅСѓР»РёС‚СЊ СЃС‡РµС‚С‡РёРє
                if (sizeLine == sizePat) break;// РµСЃР»Рё РґРѕСЃС‚РёРіР»Рё РЅСѓР¶РЅРѕРіРѕ СЂР°Р·РјРµСЂР° - РєРѕРЅРµС† С†РёРєР»Р°
            }
            if (sizeLine == sizePat) {// РµСЃР»Рё РґРѕСЃС‚РёРіР»Рё РЅСѓР¶РЅРѕРіРѕ СЂР°Р·РјРµСЂР°
                isLine = true;// РІРѕР·РІСЂР°С‚ РёР· С„СѓРЅРєС†РёРё
                break;// РєРѕРЅРµС† С†РёРєР»Р°
            }
        }

        return isLine;
    }

    // РґРёР°РіРѕРЅР°Р»СЊРЅС‹Р№ РїР°С‚С‚РµСЂРЅ "РїСЂСЏРјРѕР№"
    public boolean lineDiagonal_1(int sizePat, char aChar) {

        boolean isLine = false;

        // РЅР° СЃРєРѕР»СЊРєРёС… Р»РёРЅРёСЏС… РёРіСЂРѕРІРѕРіРѕ РїРѕР»СЏ РјРѕР¶РЅРѕ РІСЃС‚СЂРµС‚РёС‚СЊ РґРёР°РіРѕРЅР°Р»СЊРЅС‹Р№ РїР°С‚С‚РµСЂРЅ
        int magicNum = pole.length - sizePat; // РІР°Р¶РЅР°СЏ РїРµСЂРµРјРµРЅРЅР°СЏ РІ СЂР°СЃС‡РµС‚Р°С… :-)
        // "РјР°СЃСЃРёРІ РїСЂРѕРіСЂРµСЃСЃР°": РјР°СЃСЃРёРІ РґР»СЏ Р·Р°РїРёСЃРё РїСЂРѕРіСЂРµСЃСЃР° РєР°Р¶РґРѕР№ Р»РёРЅРёРё "РґРёР°РіРѕРЅР°Р»СЊРЅРѕРіРѕ С‡РёСЃР»Р°" СЃРј. РЅРёР¶Рµ
        int[][] pD1arr1 = new int[3][magicNum * 2 + 1];
        int numLine = -1;

        // Р·Р°РїРѕР»РЅСЏРµРј (РґР°РµРј РёРјСЏ) РїРµСЂРІСѓСЋ Р»РёРЅРёСЋ РјР°СЃСЃРёРІР° РїСЂРѕРіСЂРµСЃСЃР° "РґРёР°РіРѕРЅР°Р»СЊРЅС‹Рј С‡РёСЃР»РѕРј" СЃРј. РЅРёР¶Рµ
        for (int i = 0; i < pD1arr1[0].length; i++) {
            pD1arr1[0][i] = -1 * (magicNum) + i;
        }

        // Р”РёР°РіРѕРЅР°Р»СЊРЅС‹Р№ РїР°С‚С‚РµСЂРЅ "РїСЂСЏРјРѕР№"
        for (int i = 0; i < pole.length; i++) {
            for (int j = 0; j < pole[0].length; j++) {
                // РїСЂСЏРјРѕРµ "РґРёР°РіРѕРЅР°Р»СЊРЅРѕРµ С‡РёСЃР»Рѕ" СЏС‡РµР№РєРё: РїРѕРІС‚РѕСЂСЏРµС‚СЃСЏ С‚РѕР»СЊРєРѕ РїРѕ РґРёР°РіРѕРЅР°Р»СЏРј
                // 0 -1 -2
                // 1  0	-1
                // 2  1	 0
                int nDiag = i - j;
                // РµСЃР»Рё "РґРёР°РіРѕРЅР°Р»СЊРЅРѕРµ С‡РёСЃР»Рѕ" СЏС‡РµР№РєРё РёРіСЂРѕРІРѕРіРѕ РїРѕР»СЏ РЅР°С…РѕРґРёС‚СЃСЏ РІ РёСЃРєРѕРјРѕРј РґРёР°РїР°Р·РѕРЅРµ
                if (nDiag > -1 * (magicNum + 1) && nDiag < magicNum + 1) {
                    // РёС‰РµРј РѕРґРЅРѕРёРјРµРЅРЅСѓСЋ СЏС‡РµР№РєСѓ РІ "РјР°СЃСЃРёРІРµ РїСЂРѕРіСЂРµСЃСЃР°"
                    numLine = Arrays.binarySearch(pD1arr1[0], nDiag);
                    if (pole[i][j] == aChar) {// РµСЃР»Рё СЏС‡РµР№РєР° РёРіСЂРѕРІРѕРіРѕ РїРѕР»СЏ "РЅР°С€Р°"
                        // РїРѕ РЅР°Р№РґРµРЅРЅРѕРјСѓ Р°РґСЂРµСЃСѓ "РјР°СЃСЃРёРІР° РїСЂРѕРіСЂРµСЃСЃР°" СѓРІРµР»РёС‡РёРІР°РµРј Р·РЅР°С‡РµРЅРёРµ РЅР° 1
                        pD1arr1[1][numLine] = pD1arr1[1][numLine] + 1;
                    } else pD1arr1[1][numLine] = 0;// РµСЃР»Рё СЏС‡РµР№РєР° "РЅРµ РЅР°С€Р°"
                    // РµСЃР»Рё РІС‹РёРіСЂС‹С€ - РїСЂРµСЂС‹РІР°РЅРёРµ РІРЅСѓС‚СЂРµРЅРЅРµРіРѕ С†РёРєР»Р°
                    if (pD1arr1[1][numLine] == sizePat) break;
                }
            }
            // РµСЃР»Рё РІС‹РёРіСЂС‹С€ - РїСЂРµСЂС‹РІР°РЅРёРµ РІРЅРµС€РЅРµРіРѕ С†РёРєР»Р°
            if (pD1arr1[1][numLine] == sizePat) {
                isLine = true;
                break;
            }
        }

        return isLine;
    }

    // РґРёР°РіРѕРЅР°Р»СЊРЅС‹Р№ РїР°С‚С‚РµСЂРЅ "РѕР±СЂР°С‚РЅС‹Р№"
    public boolean lineDiagonalAnother(int sizePat, char aChar) {

        boolean isLine = false;

        // РЅР° СЃРєРѕР»СЊРєРёС… Р»РёРЅРёСЏС… РёРіСЂРѕРІРѕРіРѕ РїРѕР»СЏ РјРѕР¶РЅРѕ РІСЃС‚СЂРµС‚РёС‚СЊ РґРёР°РіРѕРЅР°Р»СЊРЅС‹Р№ РїР°С‚С‚РµСЂРЅ
        int magicNum = pole.length - sizePat; // РІР°Р¶РЅР°СЏ РїРµСЂРµРјРµРЅРЅР°СЏ РІ СЂР°СЃС‡РµС‚Р°С… :-)
        // "РјР°СЃСЃРёРІ РїСЂРѕРіСЂРµСЃСЃР°": РјР°СЃСЃРёРІ РґР»СЏ Р·Р°РїРёСЃРё РїСЂРѕРіСЂРµСЃСЃР° РєР°Р¶РґРѕР№ Р»РёРЅРёРё "РґРёР°РіРѕРЅР°Р»СЊРЅРѕРіРѕ С‡РёСЃР»Р°" СЃРј. РЅРёР¶Рµ
        int[][] pD1arr1 = new int[2][magicNum * 2 + 1];
        int numLine = -1;

        // Р·Р°РїРѕР»РЅСЏРµРј (РґР°РµРј РёРјСЏ) РїРµСЂРІСѓСЋ Р»РёРЅРёСЋ РјР°СЃСЃРёРІР° РїСЂРѕРіСЂРµСЃСЃР° "РґРёР°РіРѕРЅР°Р»СЊРЅС‹Рј С‡РёСЃР»РѕРј" СЃРј. РЅРёР¶Рµ
        for (int i = 0; i < pD1arr1[0].length; i++) {
            pD1arr1[0][i] = -1 * (magicNum) + i;
        }

        // Р”РёР°РіРѕРЅР°Р»СЊРЅС‹Р№ РїР°С‚С‚РµСЂРЅ "РѕР±СЂР°С‚РЅС‹Р№"
        for (int i = 0; i < pole.length; i++) {
            for (int j = 0; j < pole[0].length; j++) {
                // РѕР±СЂР°С‚РЅРѕРµ "РґРёР°РіРѕРЅР°Р»СЊРЅРѕРµ С‡РёСЃР»Рѕ" СЏС‡РµР№РєРё: РїРѕРІС‚РѕСЂСЏРµС‚СЃСЏ С‚РѕР»СЊРєРѕ РїРѕ РґРёР°РіРѕРЅР°Р»СЏРј
                //2  1  0
                //1	 0 -1
                //0	-1 -2
                int nDiag = -1 * (i - (pole[0].length - 1)) - j;
                // РµСЃР»Рё "РґРёР°РіРѕРЅР°Р»СЊРЅРѕРµ С‡РёСЃР»Рѕ" СЏС‡РµР№РєРё РёРіСЂРѕРІРѕРіРѕ РїРѕР»СЏ РЅР°С…РѕРґРёС‚СЃСЏ РІ РёСЃРєРѕРјРѕРј РґРёР°РїР°Р·РѕРЅРµ
                if (nDiag > -1 * (magicNum + 1) && nDiag < magicNum + 1) {
                    // РёС‰РµРј РѕРґРЅРѕРёРјРµРЅРЅСѓСЋ СЏС‡РµР№РєСѓ РІ "РјР°СЃСЃРёРІРµ РїСЂРѕРіСЂРµСЃСЃР°"
                    numLine = Arrays.binarySearch(pD1arr1[0], nDiag);
                    if (pole[i][j] == aChar) {// РµСЃР»Рё СЏС‡РµР№РєР° "РЅР°С€Р°"
                        // РїРѕ РЅР°Р№РґРµРЅРЅРѕРјСѓ Р°РґСЂРµСЃСѓ "РјР°СЃСЃРёРІР° РїСЂРѕРіСЂРµСЃСЃР°" СѓРІРµР»РёС‡РёРІР°РµРј Р·РЅР°С‡РµРЅРёРµ РЅР° 1
                        pD1arr1[1][numLine] = pD1arr1[1][numLine] + 1;
                    } else pD1arr1[1][numLine] = 0;// РµСЃР»Рё СЏС‡РµР№РєР° "РЅРµ РЅР°С€Р°"
                    // РµСЃР»Рё РІС‹РёРіСЂС‹С€ - РїСЂРµСЂС‹РІР°РЅРёРµ РІРЅСѓС‚СЂРµРЅРЅРµРіРѕ С†РёРєР»Р°
                    if (pD1arr1[1][numLine] == sizePat) break;
                }
            }
            // РµСЃР»Рё РІС‹РёРіСЂС‹С€ - РїСЂРµСЂС‹РІР°РЅРёРµ РІРЅРµС€РЅРµРіРѕ С†РёРєР»Р°
            if (pD1arr1[1][numLine] == sizePat) {
                isLine = true;
                break;
            }
        }

        return isLine;
    }

    // С‚СЂР°РЅСЃРїРѕРЅРёСЂРѕРІР°РЅРёРµ
    public char[][] transArray() {
        char[][] outArr = new char[pole[0].length][pole.length];

        for (int i = 0; i < pole[0].length; i++) {
            for (int j = 0; j < pole.length; j++) {
                outArr[i][j] = pole[j][i];
            }
        }
        return outArr;
    }

    // РїРѕР±РµРґРёС‚РµР»СЊ
    @Override
    public boolean isAnobodyWin(char aChar) {

        int pattern = 0;

        // Р”РёР°РіРѕРЅР°Р»СЊРЅС‹Р№ РїР°С‚С‚РµСЂРЅ "РїСЂСЏРјРѕР№"
        if (lineDiagonal_1(lineSize, aChar)) {
            pattern = 1;
        }

        // Р”РёР°РіРѕРЅР°Р»СЊРЅС‹Р№ РїР°С‚С‚РµСЂРЅ "РѕР±СЂР°С‚РЅС‹Р№"
        if (lineDiagonalAnother(lineSize, aChar)) {
            pattern = 2;
        }

        // Р“РѕСЂРёР·РѕРЅС‚Р°Р»СЊРЅС‹Р№ РїР°С‚С‚РµСЂРЅ
        if (lineHorizontal(pole, lineSize, aChar)) {
            pattern = 3;
        }

        // Р’РµСЂС‚РёРєР°Р»СЊРЅС‹Р№ РїР°С‚С‚РµСЂРЅ
        char[][] boardT = transArray();
        if (lineHorizontal(boardT, lineSize, aChar)) {
            pattern = 4;
        }

        if (pattern > 0) anobodyWin = true;

        return anobodyWin;
    }

    @Override
    protected char getCell(Point2d coordinate) {
        if(canMakeStep(coordinate)) {
            return pole[coordinate.getX()][coordinate.getY()];
        }
        return '0' ;
    }

    // РїРµС‡Р°С‚СЊ РёРіСЂРѕРІРѕРіРѕ РїРѕР»СЏ РІ РєРѕРЅСЃРѕР»Рё
    @Override
    public void printPole() {

        int strLength = pole.length;
        int colLength = pole[0].length;

        for (int i = 0; i < strLength + 1; i++) {
            System.out.println();
            for (int j = 0; j < colLength + 1; j++) {

                if (i == 0 && j == 0) {// РЅРµ Р·Р°РїРѕР»РЅСЏРµРјС‹Р№ СѓРіРѕР»
                    System.out.printf("%5s", "");
                } else if (i == 0) {// РЅСѓРјРµСЂР°С†РёСЏ СЃС‚РѕР»Р±С†РѕРІ
                    System.out.printf("%5s", j);
                } else if (j == 0) {// РЅСѓРјРµСЂР°С†РёСЏ СЃС‚СЂРѕРє
                    System.out.printf("%5s", i);
                } else {
                    System.out.printf("%5s", pole[i - 1][j - 1]);
                }
            }
            System.out.println();
        }
        System.out.print("\n\n");
    }
}
