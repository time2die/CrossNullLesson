package Homework;

/**
 * Created by Kireev on 29.05.2016.
 */

import java.io.*;
import java.util.*;


public class Main {
    public static void main(String[] args) throws IOException {

        ///    ReadInput ri = new ReadInput();

        File file = new File("D://text.txt");
        //FileReader reader = new FileReader(file);
        InputStreamReader isr = new InputStreamReader(new FileInputStream(file), "UTF-8");
        BufferedReader buf = new BufferedReader(new FileReader("D://Vojna i mir.txt"));

        String name;
        String line = buf.readLine();

        //Вывожу на экран количнство слов и количество символов task3 подсчитать количество уникальных слов в тексте
        while ((name = buf.readLine()) != null) {
            line += name + '\n';
        }

        System.out.print(name);
        {
            String[] a = line.split("[^A-Za-zА-Яа-я]+");
            //Ключ - слово , значение  - количество повторений
            HashMap<String, Integer> hm = new HashMap<String, Integer>();

            // Ищу повторяющиеся значения

            for (int i = 0; i < a.length; i++) {
                if (!hm.containsKey(a[i])) {
                    hm.put(a[i], 1);
                } else {
                    hm.put(a[i], hm.get(a[i]) + 1);
                    ;
                }
            }

            //Записываю данные в список для применения сортировки - вывод топа 10-ки повторений

            List<Map.Entry<String, Integer>> en = new ArrayList<Map.Entry<String, Integer>>(hm.entrySet());
            System.out.println("Количество слов");
            //Делаю перебор по списку при помощи итератора. Вывожу ключ и значение

            Iterator<Map.Entry<String, Integer>> itr = hm.entrySet().iterator();
            {
                while (itr.hasNext()) {
                    Map.Entry em = (Map.Entry) itr.next();
                    System.out.println("Key = " + em.getKey() + " " + "Value =  " + em.getValue() + "\n");
                }

            }
            // Сортировка значений по значению (value)
            System.out.println("sorted map Топ - 10 слова");
            Collections.sort(en, new Comparator<Map.Entry<String, Integer>>() {
                        @Override
                        public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                            int i1 = o1.getValue();
                            int i2 = o2.getValue();
                            if (i2 > i1) {
                                return 1;
                            } else {
                                if (i2 == i1)
                                    return 0;
                                else return -1;
                            }

                        }
                    }
            );
// 10 -ка часто встречающихся слов
            System.out.println(en.subList(0, 10));
        }

        // Символы
        System.out.println("Количество занков препинания");

        String[] a = line.split("[^-.?!,:;]+");
        HashMap<String, Integer> hm = new HashMap<>();
        for (int i = 0; i < a.length; i++) {
            if (!hm.containsKey(a[i])) {
                hm.put(a[i], 1);
            } else {
                hm.put(a[i], hm.get(a[i]) + 1);

            }
        }

        List<Map.Entry<String, Integer>> en = new ArrayList<Map.Entry<String, Integer>>(hm.entrySet());

        Iterator<Map.Entry<String, Integer>> itr = hm.entrySet().iterator();
        {
            while (itr.hasNext()) {
                System.out.println(itr.next());
            }
        }

        // Сортировка значений по значению (value)
        System.out.println("sorted map Топ - 10 знаки препинания ");
        Collections.sort(en, new Comparator<Map.Entry<String, Integer>>() {
                    @Override
                    public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                        int i1 = o1.getValue();
                        int i2 = o2.getValue();
                        if (i2 > i1) {
                            return 1;
                        } else {
                            if (i2 == i1)
                                return 0;
                            else return -1;
                        }

                    }
                }

        );
        System.out.println(en.subList(0, 5));
    }

}




