package lesson6;

import kotlin.NotImplementedError;

import java.util.*;

@SuppressWarnings("unused")
public class JavaGraphTasks {
    /**
     * Эйлеров цикл.
     * Средняя
     *
     * Дан граф (получатель). Найти по нему любой Эйлеров цикл.
     * Если в графе нет Эйлеровых циклов, вернуть пустой список.
     * Соседние дуги в списке-результате должны быть инцидентны друг другу,
     * а первая дуга в списке инцидентна последней.
     * Длина списка, если он не пуст, должна быть равна количеству дуг в графе.
     * Веса дуг никак не учитываются.
     *
     * Пример:
     *
     *      G -- H
     *      |    |
     * A -- B -- C -- D
     * |    |    |    |
     * E    F -- I    |
     * |              |
     * J ------------ K
     *
     * Вариант ответа: A, E, J, K, D, C, H, G, B, C, I, F, B, A
     *
     * Справка: Эйлеров цикл -- это цикл, проходящий через все рёбра
     * связного графа ровно по одному разу
     */
    public static List<Graph.Edge> findEulerLoop(Graph graph) {
        ArrayList<Graph.Edge> result = new ArrayList<>();
        ArrayList<Graph.Vertex> listV = new ArrayList<>();
        ArrayDeque<Graph.Vertex> stack = new ArrayDeque<>();
        Graph.Vertex x = null;
        Set<Graph.Edge> setE = graph.getEdges();
        if (graph.getVertices().isEmpty() || dfs(graph)) return result;
        for (Graph.Vertex v : graph.getVertices()) {
            x = v;
            if (graph.getNeighbors(v).size() % 2 == 1)
                return result;
        }
        stack.push(x);
        while (!stack.isEmpty()) {
            Graph.Vertex y = stack.peek();
            boolean mod = false;
                for (Graph.Vertex e : graph.getVertices()) {
                    if (setE.contains(graph.getConnection(y, e))) {
                        stack.push(e);
                        setE.remove(graph.getConnection(y, e));
                        mod = true;
                        break;
                    }
                }
            if (!mod) {
                stack.pop();
                listV.add(y);
            }
        }
        setE = graph.getEdges();
        for (int i = 0; i < listV.size() - 1; i++) {
            if (setE.contains(graph.getConnection(listV.get(i), listV.get(i + 1)))) {
                result.add(graph.getConnection(listV.get(i), listV.get(i + 1)));
                setE.remove(graph.getConnection(listV.get(i), listV.get(i + 1)));
            }
        }
        return result;
    }
    //Трудоемкость T = O(N*M)
    //Ресурсоемкость R = O(N)

    static boolean dfs(Graph graph) {
        ArrayDeque<Graph.Vertex> stack = new ArrayDeque<>();
        HashMap<Graph.Vertex, Boolean> map = new HashMap<>();
        Graph.Vertex x = null;
        for (Graph.Vertex v : graph.getVertices()) {
            map.put(v,false);
        }
        for (Graph.Vertex v : graph.getVertices()) {
            x = v;
            break;
        }
        stack.push(x);
        while (!stack.isEmpty()) {
            Graph.Vertex y = stack.pop();
            if (!map.get(y)) {
                map.remove(y);
                map.put(y, true);
                for (Graph.Vertex v : graph.getNeighbors(y)) {
                    if (!map.get(v)) stack.push(v);
                }
            }
        }
        return map.containsValue(false);
    }
    /**
     * Минимальное остовное дерево.
     * Средняя
     *
     * Дан связный граф (получатель). Найти по нему минимальное остовное дерево.
     * Если есть несколько минимальных остовных деревьев с одинаковым числом дуг,
     * вернуть любое из них. Веса дуг не учитывать.
     *
     * Пример:
     *
     *      G -- H
     *      |    |
     * A -- B -- C -- D
     * |    |    |    |
     * E    F -- I    |
     * |              |
     * J ------------ K
     *
     * Ответ:
     *
     *      G    H
     *      |    |
     * A -- B -- C -- D
     * |    |    |
     * E    F    I
     * |
     * J ------------ K
     */
    public static Graph minimumSpanningTree(Graph graph) {
        throw new NotImplementedError();
    }

    /**
     * Максимальное независимое множество вершин в графе без циклов.
     * Сложная
     *
     * Дан граф без циклов (получатель), например
     *
     *      G -- H -- J
     *      |
     * A -- B -- D
     * |         |
     * C -- F    I
     * |
     * E
     *
     * Найти в нём самое большое независимое множество вершин и вернуть его.
     * Никакая пара вершин в независимом множестве не должна быть связана ребром.
     *
     * Если самых больших множеств несколько, приоритет имеет то из них,
     * в котором вершины расположены раньше во множестве this.vertices (начиная с первых).
     *
     * В данном случае ответ (A, E, F, D, G, J)
     *
     * Если на входе граф с циклами, бросить IllegalArgumentException
     *
     * Эта задача может быть зачтена за пятый и шестой урок одновременно
     */
    public static Set<Graph.Vertex> largestIndependentVertexSet(Graph graph) {
        throw new NotImplementedError();
    }

    /**
     * Наидлиннейший простой путь.
     * Сложная
     *
     * Дан граф (получатель). Найти в нём простой путь, включающий максимальное количество рёбер.
     * Простым считается путь, вершины в котором не повторяются.
     * Если таких путей несколько, вернуть любой из них.
     *
     * Пример:
     *
     *      G -- H
     *      |    |
     * A -- B -- C -- D
     * |    |    |    |
     * E    F -- I    |
     * |              |
     * J ----------- -K
     *
     * Ответ: A, E, J, K, D, C, H, G, B, F, I
     */
    public static Path longestSimplePath(Graph graph) {
        Path result = new Path();
        Set<Graph.Vertex> setV = graph.getVertices();
        ArrayDeque<Path> stack = new ArrayDeque<>();
        int k = 0;
        if (setV.isEmpty()) return result;
        for (Graph.Vertex v : setV) {
            stack.push(new Path((v)));
        }
        while (!stack.isEmpty()) {
            Path p = stack.pop();
            if (p.getLength() > k) {
                result = p;
                k = result.getLength();
            }
            Set<Graph.Vertex> set = graph.getNeighbors(p.getVertices().get(p.getLength()));
            for (Graph.Vertex n : set) {
                if (!p.contains(n)) stack.push(new Path(p, graph, n));
            }
        }
        return result;
    }
    //Трудоемкость T = O(N!)
    //Ресурсоемкость R = O(N!)


    /**
     * Балда
     * Сложная
     *
     * Задача хоть и не использует граф напрямую, но решение базируется на тех же алгоритмах -
     * поэтому задача присутствует в этом разделе
     *
     * В файле с именем inputName задана матрица из букв в следующем формате
     * (отдельные буквы в ряду разделены пробелами):
     *
     * И Т Ы Н
     * К Р А Н
     * А К В А
     *
     * В аргументе words содержится множество слов для поиска, например,
     * ТРАВА, КРАН, АКВА, НАРТЫ, РАК.
     *
     * Попытаться найти каждое из слов в матрице букв, используя правила игры БАЛДА,
     * и вернуть множество найденных слов. В данном случае:
     * ТРАВА, КРАН, АКВА, НАРТЫ
     *
     * И т Ы Н     И т ы Н
     * К р а Н     К р а н
     * А К в а     А К В А
     *
     * Все слова и буквы -- русские или английские, прописные.
     * В файле буквы разделены пробелами, строки -- переносами строк.
     * Остальные символы ни в файле, ни в словах не допускаются.
     */
    static public Set<String> baldaSearcher(String inputName, Set<String> words) {
        throw new NotImplementedError();
    }
}
