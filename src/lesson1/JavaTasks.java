package lesson1;

import kotlin.NotImplementedError;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

@SuppressWarnings("unused")
public class JavaTasks {
    /**
     * Сортировка времён
     *
     * Простая
     * (Модифицированная задача с сайта acmp.ru)
     *
     * Во входном файле с именем inputName содержатся моменты времени в формате ЧЧ:ММ:СС AM/PM,
     * каждый на отдельной строке. См. статью википедии "12-часовой формат времени".
     *
     * Пример:
     *
     * 01:15:19 PM
     * 07:26:57 AM
     * 10:00:03 AM
     * 07:56:14 PM
     * 01:15:19 PM
     * 12:40:31 AM
     *
     * Отсортировать моменты времени по возрастанию и вывести их в выходной файл с именем outputName,
     * сохраняя формат ЧЧ:ММ:СС AM/PM. Одинаковые моменты времени выводить друг за другом. Пример:
     *
     * 12:40:31 AM
     * 07:26:57 AM
     * 10:00:03 AM
     * 01:15:19 PM
     * 01:15:19 PM
     * 07:56:14 PM
     *
     * В случае обнаружения неверного формата файла бросить любое исключение.
     */
    static public void sortTimes(String inputName, String outputName) throws IOException {
        if (!inputName.endsWith(".txt")) throw new IllegalArgumentException();
        List<String> am = new ArrayList<>();
        List<String> pm = new ArrayList<>();
        List<String> am12 = new ArrayList<>();
        List<String> pm12 = new ArrayList<>();
        final Path path = Paths.get(inputName);
        BufferedReader reader = Files.newBufferedReader(path);
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("12")) {
                    if (line.contains("AM")) am12.add(line);
                    else pm12.add(line);
                } else {
                    if (line.contains("AM")) am.add(line);
                    else pm.add(line);
                }
            }
        Collections.sort(am);
        Collections.sort(pm);
        Collections.sort(am12);
        Collections.sort(pm12);
        am12.addAll(am);
        am12.addAll(pm12);
        am12.addAll(pm);
        FileWriter writer = new FileWriter(outputName);
            for (String text : am12) {
                writer.write(text + System.lineSeparator());
            }
        writer.close();
    }
    //Трудоемкость T = O(N*lg(N))
    //Ресурсоемкость R = O(N)

    /**
     * Сортировка адресов
     *
     * Средняя
     *
     * Во входном файле с именем inputName содержатся фамилии и имена жителей города с указанием улицы и номера дома,
     * где они прописаны. Пример:
     *
     * Петров Иван - Железнодорожная 3
     * Сидоров Петр - Садовая 5
     * Иванов Алексей - Железнодорожная 7
     * Сидорова Мария - Садовая 5
     * Иванов Михаил - Железнодорожная 7
     *
     * Людей в городе может быть до миллиона.
     *
     * Вывести записи в выходной файл outputName,
     * упорядоченными по названию улицы (по алфавиту) и номеру дома (по возрастанию).
     * Людей, живущих в одном доме, выводить через запятую по алфавиту (вначале по фамилии, потом по имени). Пример:
     *
     * Железнодорожная 3 - Петров Иван
     * Железнодорожная 7 - Иванов Алексей, Иванов Михаил
     * Садовая 5 - Сидоров Петр, Сидорова Мария
     *
     * В случае обнаружения неверного формата файла бросить любое исключение.
     */
    static public void sortAddresses(String inputName, String outputName) {throw new NotImplementedError();}

    /**
     * Сортировка температур
     *
     * Средняя
     * (Модифицированная задача с сайта acmp.ru)
     *
     * Во входном файле заданы температуры различных участков абстрактной планеты с точностью до десятых градуса.
     * Температуры могут изменяться в диапазоне от -273.0 до +500.0.
     * Например:
     *
     * 24.7
     * -12.6
     * 121.3
     * -98.4
     * 99.5
     * -12.6
     * 11.0
     *
     * Количество строк в файле может достигать ста миллионов.
     * Вывести строки в выходной файл, отсортировав их по возрастанию температуры.
     * Повторяющиеся строки сохранить. Например:
     *
     * -98.4
     * -12.6
     * -12.6
     * 11.0
     * 24.7
     * 99.5
     * 121.3
     */
    static public void sortTemperatures(String inputName, String outputName) throws IOException {
        if (!inputName.endsWith(".txt")) throw new IllegalArgumentException();
        final Path path = Paths.get(inputName);
        List<String> minus = new ArrayList<>();
        List<String> plus = new ArrayList<>();
        BufferedReader reader = Files.newBufferedReader(path);
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("-")) minus.add(line);
                else plus.add(line);
            }
        int[] finalMinus = Sorts.countingSort(helper1(minus), 2730);
        int[] finalPlus = Sorts.countingSort(helper1(plus), 5000);
        for (int i = 0; i < finalMinus.length / 2; i++) {
            int tmp = finalMinus[i];
            finalMinus[i] = finalMinus[minus.size() - i - 1];
            finalMinus[minus.size() - i - 1] = tmp;
        }
        FileWriter writer = new FileWriter(outputName);
            for (int t : finalMinus) {
                writer.write("-" + ((double) t) / 10 + System.lineSeparator());
            }
            for (int t : finalPlus) {
                writer.write(((double) t) / 10 + System.lineSeparator());
            }
            writer.close();
    }
    //Трудоемкость T = O(N + K)
    //Ресурсоемкость R = O(K), где K - диапазон

    private static int[] helper1(List<String> in){
        int[] out = new int[in.size()];
        for (int i = 0; i < in.size(); i++) {
            double a;
            String cur = in.get(i);
            String[] yo = cur.split("\\.");
            int first = Math.abs(Integer.parseInt(yo[0]));
            int second = Integer.parseInt(yo[1]);
            a = ((double) first + ((double) second) / 10) * 10;
            out[i] = (int) a;
        }
        return out;
    }

    /**
     * Сортировка последовательности
     *
     * Средняя
     * (Задача взята с сайта acmp.ru)
     *
     * В файле задана последовательность из n целых положительных чисел, каждое в своей строке, например:
     *
     * 1
     * 2
     * 3
     * 2
     * 3
     * 1
     * 2
     *
     * Необходимо найти число, которое встречается в этой последовательности наибольшее количество раз,
     * а если таких чисел несколько, то найти минимальное из них,
     * и после этого переместить все такие числа в конец заданной последовательности.
     * Порядок расположения остальных чисел должен остаться без изменения.
     *
     * 1
     * 3
     * 3
     * 1
     * 2
     * 2
     * 2
     */
    static public void sortSequence(String inputName, String outputName) throws IOException {
        if (!inputName.endsWith(".txt")) throw new IllegalArgumentException();
        final Path path = Paths.get(inputName);
        List<Integer> numbers = new ArrayList<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        int min = 0;
        int k = 1;
        BufferedReader reader = Files.newBufferedReader(path);
            String line;
            while ((line = reader.readLine()) != null) {
                numbers.add(Integer.parseInt(line));
            }
        for (int number : numbers) {
            if (map.get(number) != null) {
                int s = map.get(number);
                s++;
                map.put(number, s);
                if ((s > k) || (s == k && number < min)) {
                    k = s;
                    min = number;
                }
            } else map.put(number, 1);
        }
        final int finalMin = min;
        numbers.removeIf(number -> (number == finalMin));
        FileWriter writer = new FileWriter(outputName);
            for (int number : numbers) {
                writer.write(number + System.lineSeparator());
            }
            for (int i = 0; i < k; i++) {
                writer.write(finalMin + System.lineSeparator());
            }
            writer.close();
    }
    //Трудоемкость T = O(N)
    //Ресурсоемкость R = O(N)

    /**
     * Соединить два отсортированных массива в один
     *
     * Простая
     *
     * Задан отсортированный массив first и второй массив second,
     * первые first.size ячеек которого содержат null, а остальные ячейки также отсортированы.
     * Соединить оба массива в массиве second так, чтобы он оказался отсортирован. Пример:
     *
     * first = [4 9 15 20 28]
     * second = [null null null null null 1 3 9 13 18 23]
     *
     * Результат: second = [1 3 4 9 9 13 15 20 23 28]
     */
    static <T extends Comparable<T>> void mergeArrays(T[] first, T[] second) {
        throw new NotImplementedError();
    }
}
