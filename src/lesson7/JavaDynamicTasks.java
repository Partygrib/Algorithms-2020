package lesson7;

import kotlin.NotImplementedError;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings("unused")
public class JavaDynamicTasks {
    /**
     * Наибольшая общая подпоследовательность.
     * Средняя
     *
     * Дано две строки, например "nematode knowledge" и "empty bottle".
     * Найти их самую длинную общую подпоследовательность -- в примере это "emt ole".
     * Подпоследовательность отличается от подстроки тем, что её символы не обязаны идти подряд
     * (но по-прежнему должны быть расположены в исходной строке в том же порядке).
     * Если общей подпоследовательности нет, вернуть пустую строку.
     * Если есть несколько самых длинных общих подпоследовательностей, вернуть любую из них.
     * При сравнении подстрок, регистр символов *имеет* значение.
     */
    public static String longestCommonSubSequence(String first, String second) {
        char [] x = new char[first.length() + 1];
        char [] y = new char[second.length() + 1];
        x[0] = 'c';
        y[0] = 'e';
        for (int i = 1; i < x.length; i++) {
            x[i] = first.charAt(i - 1);
        }
        for (int i = 1; i < y.length; i++) {
            y[i] = second.charAt(i - 1);
        }
        int[][] m = new int[x.length][y.length];
        ArrayList<Character> list = new ArrayList<>();
        for (int i = 0; i < x.length; i++) {
            for (int j = 0; j < y.length; j++) {
                if (i == 0 || j == 0) m[i][j] = 0;
                else {
                    if (y[j] == x[i]) {
                        m[i][j] = m[i - 1][j - 1] + 1;
                    }
                    else m[i][j] = Math.max(m[i - 1][j], m[i][j - 1]);
                }
            }
        }
        int ix = x.length - 1;
        int jy = y.length - 1;
        while (ix > 0 && jy > 0) {
            if (x[ix] == y[jy]) {
                list.add(x[ix]);
                ix--;
                jy--;
            }
            else {
                if (m[ix - 1][jy] > m[ix][jy - 1]) ix--;
                else jy--;
            }
        }
        Collections.reverse(list);
        StringBuilder sb = new StringBuilder();
        for (Character ch: list) {
            sb.append(ch);
        }
        return sb.toString();
    }
    //Трудоемкость T = O(N * M)
    //Ресурсоемкость R = O(N * M)

    /**
     * Наибольшая возрастающая подпоследовательность
     * Сложная
     *
     * Дан список целых чисел, например, [2 8 5 9 12 6].
     * Найти в нём самую длинную возрастающую подпоследовательность.
     * Элементы подпоследовательности не обязаны идти подряд,
     * но должны быть расположены в исходном списке в том же порядке.
     * Если самых длинных возрастающих подпоследовательностей несколько (как в примере),
     * то вернуть ту, в которой числа расположены раньше (приоритет имеют первые числа).
     * В примере ответами являются 2, 8, 9, 12 или 2, 5, 9, 12 -- выбираем первую из них.
     */
    public static List<Integer> longestIncreasingSubSequence(List<Integer> list) {
        if (list.isEmpty()) return list;
        int n = list.size();
        int[] a = new int[n];
        int[] b = new int[n];
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            a[i] = 1;
            b[i] = -1;
            for (int j = 0; j < n; j++) {
                if (list.get(j) < list.get(i) && a[j] + 1 > a[i]) {
                    a[i] = a[j] + 1;
                    b[i] = j;
                }
            }
        }
        int last = 0;
        int length = 0;
        for (int i = 0; i < n; i++) {
            if (a[i] > length) {
                last = i;
                length = a[i];
            }
        }
        while (last != -1) {
            result.add(list.get(last));
            last = b[last];
        }
        Collections.reverse(result);
        return result;
    }
    //Трудоемкость T = O(N^2)
    //Ресурсоемкость R = O(N)

    /**
     * Самый короткий маршрут на прямоугольном поле.
     * Средняя
     *
     * В файле с именем inputName задано прямоугольное поле:
     *
     * 0 2 3 2 4 1
     * 1 5 3 4 6 2
     * 2 6 2 5 1 3
     * 1 4 3 2 6 2
     * 4 2 3 1 5 0
     *
     * Можно совершать шаги длиной в одну клетку вправо, вниз или по диагонали вправо-вниз.
     * В каждой клетке записано некоторое натуральное число или нуль.
     * Необходимо попасть из верхней левой клетки в правую нижнюю.
     * Вес маршрута вычисляется как сумма чисел со всех посещенных клеток.
     * Необходимо найти маршрут с минимальным весом и вернуть этот минимальный вес.
     *
     * Здесь ответ 2 + 3 + 4 + 1 + 2 = 12
     */
    public static int shortestPathOnField(String inputName) {
        throw new NotImplementedError();
    }

    // Задачу "Максимальное независимое множество вершин в графе без циклов"
    // смотрите в уроке 5
}
