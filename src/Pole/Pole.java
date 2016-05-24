package Pole;

import data.Point2d;

/**
 * Created by time2die on 16.05.2016.
 */
public interface Pole {
    // РµСЃС‚СЊ Р»Рё СЃРІРѕР±РѕРґРЅС‹Рµ РєР»РµС‚РєРё
    boolean hasCleanCell();

    // РїСЂРѕРІРµСЂРєР° РЅР° РєРѕСЂСЂРµРєС‚РЅРѕСЃС‚СЊ РєРѕРѕСЂРґРёРЅР°С‚
    boolean canMakeStep(Point2d step);

    void step(Point2d step, char aChar);

    // РїРѕР±РµРґРёС‚РµР»СЊ
    boolean isAnobodyWin(char aChar);

    // РїРµС‡Р°С‚СЊ РёРіСЂРѕРІРѕРіРѕ РїРѕР»СЏ РІ РєРѕРЅСЃРѕР»Рё
    void printPole();
}
