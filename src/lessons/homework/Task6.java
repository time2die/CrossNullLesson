package lessons.homework;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Задание
 * используя метод readText класса ReadInput выможете получить текс Войны и Мир
 * - посчитать частотность повторения всех слов и вывести на экран топ 10
 * - посчитать частотность повторения всех символов и вывести на экран топ 5
 * - подсчитать количество уникальных слов в тексте
 */

public class Task6 {

    public void start(){
        final List<String> book = ReadInput.readText();
        //мапы для подсчета количества слов и букв. Коллекция и счетчик готовы для многопоточности
        final Map<Object, AtomicLong> wordsMap = new ConcurrentHashMap<>();
        final Map<Object, AtomicLong> charsMap = new ConcurrentHashMap<>();
        //Что считать словом не ясно, буду считать все что попадает под паттерн ниже, но в него попадет много однобуквенного мусора

        final Pattern pattern = Pattern.compile("([^а-яёА-ЯЁa-zA-Z])");

        book.parallelStream().forEach(row -> {
            //разбиваю строку на "слова" по табу, пробелу.
            final String[] stringList= row.split("\\p{P}?[\\s\\t]+");

            //добываю слова
            Arrays.stream(stringList).map(String::toLowerCase).filter(word -> !word.equals("") && word.length() >= 3).forEach(word -> { //не уверен по поводу преобразований здесь. Ну и таки убрал слова меньше 3 букв
                wordsMap.putIfAbsent(word, new AtomicLong(0)); //если ключ не существует, то создать
                wordsMap.get(word).incrementAndGet(); //увеличить счетчик и получить его для сравнения в топе
            });

            //добываю символы
            row.toLowerCase().chars().mapToObj(value -> (char)value).forEach(value -> {
                charsMap.putIfAbsent(value, new AtomicLong(0)); //если ключ не существует, то создать
                charsMap.get(value).incrementAndGet(); //увеличить счетчик
            });

        });
        System.out.println("Top 10 words: "+getSortedStream(wordsMap).sequential().limit(10).collect(Collectors.toList()));
        System.out.println("Top 5 chars: "+getSortedStream(charsMap).sequential().limit(5).collect(Collectors.toList()));
        System.out.println("Uniq words: "+wordsMap.size());
        //для интереса какие слова употреблялись всего один раз
        System.out.println("Single use words:"+getSortedStream(wordsMap).filter(entry -> entry.getValue().get()==1).count());
    }

    private static Stream<Map.Entry<Object, AtomicLong>> getSortedStream(Map<Object, AtomicLong> map) {
        Stream<Map.Entry<Object, AtomicLong>> st = map.entrySet().parallelStream();
        return st.sorted( Map.Entry.comparingByValue((value1, value2)-> ((Long) value2.get()).compareTo(value1.get())) );
    }
}
