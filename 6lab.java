import java.util.*;
// Базовий клас для цукерок
abstract class Candy implements Comparable<Candy> {
    private String name;
    private double weight; // Вага в грамах

    public Candy(String name, double weight) {
        this.name = name;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public double getWeight() {
        return weight;
    }

    // Абстрактний метод для отримання характеристик
    public abstract double getChocolateContent();

    @Override
    public String toString() {
        return "Candy{name='" + name + "', weight=" + weight + "}";
    }

    // Порівняння за вагою (можна змінити на інший критерій)
    @Override
    public int compareTo(Candy other) {
        return Double.compare(this.weight, other.weight); // Порівнюємо за вагою
    }
}

// Клас для шоколадних цукерок
class ChocolateCandy extends Candy {
    private double chocolateContent; // Вміст шоколаду (від 0 до 100%)

    public ChocolateCandy(String name, double weight, double chocolateContent) {
        super(name, weight);
        this.chocolateContent = chocolateContent;
    }

    @Override
    public double getChocolateContent() {
        return chocolateContent;
    }

    @Override
    public String toString() {
        return getName() + " (" + chocolateContent + "% chocolate)";
    }
}

// Клас для карамельних цукерок
class CaramelCandy extends Candy {
    private double caramelContent; // Вміст карамелі

    public CaramelCandy(String name, double weight, double caramelContent) {
        super(name, weight);
        this.caramelContent = caramelContent;
    }

    @Override
    public double getChocolateContent() {
        return 0; // Карамель не містить шоколаду
    }

    @Override
    public String toString() {
        return getName() + " (" + caramelContent + "% caramel)";
    }
}

// Клас для двозв'язного списку
class MyDoublyLinkedList<T> {
    private Node head;
    private Node tail;
    private int size;

    // Внутрішній клас для вузлів списку
    private class Node {
        T data;
        Node next;
        Node prev;

        Node(T data) {
            this.data = data;
        }
    }

    // Порожній конструктор
    public MyDoublyLinkedList() {
        head = tail = null;
        size = 0;
    }

    // Конструктор з переданим елементом
    public MyDoublyLinkedList(T item) {
        head = new Node(item);
        tail = head;
        size = 1;
    }

    // Конструктор, що приймає стандартну колекцію
    public MyDoublyLinkedList(Collection<? extends T> collection) {
        this();
        for (T item : collection) {
            add(item);
        }
    }

    // Додавання елемента
    public boolean add(T item) {
        Node newNode = new Node(item);
        if (tail == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
        return true;
    }

    // Виведення елементів списку
    public void print() {
        Node current = head;
        while (current != null) {
            System.out.println(current.data);
            current = current.next;
        }
    }

    // Сортування елементів
    public void sort() {
        List<T> list = new ArrayList<>();
        Node current = head;
        while (current != null) {
            list.add(current.data);
            current = current.next;
        }
        // Сортуємо лише якщо елементи реалізують Comparable
        if (list.get(0) instanceof Comparable) {
            list.sort(null); // сортуємо за допомогою Comparable
        } else {
            System.out.println("Elements are not Comparable");
        }
        current = head;
        for (T item : list) {
            current.data = item;
            current = current.next;
        }
    }

    // Повернення розміру
    public int size() {
        return size;
    }

    // Перевірка на порожність
    public boolean isEmpty() {
        return size == 0;
    }
}

// Головний клас для виконання
public class Main {
    public static void main(String[] args) {
        try {
            // Створення цукерок
            ChocolateCandy chocolateCandy1 = new ChocolateCandy("Milk Chocolate", 50, 60); // 60% шоколаду
            ChocolateCandy chocolateCandy2 = new ChocolateCandy("Dark Chocolate", 70, 80); // 80% шоколаду
            CaramelCandy caramelCandy1 = new CaramelCandy("Caramel Delight", 30, 90); // 90% карамелі
            CaramelCandy caramelCandy2 = new CaramelCandy("Caramel Dream", 40, 85); // 85% карамелі

            // Створення колекції цукерок
            MyDoublyLinkedList<Candy> candyCollection = new MyDoublyLinkedList<>();
            candyCollection.add(chocolateCandy1);
            candyCollection.add(chocolateCandy2);
            candyCollection.add(caramelCandy1);
            candyCollection.add(caramelCandy2);

            // Виведення цукерок у колекції
            System.out.println("Candies in collection:");
            candyCollection.print();

            // Сортування цукерок за вагою
            System.out.println("\nSorted candies:");
            candyCollection.sort();
            candyCollection.print();

        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }
}
