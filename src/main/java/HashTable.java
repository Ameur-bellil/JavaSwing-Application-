import java.util.ArrayList;
import java.util.Scanner;

public class HashTable {
    public int tableSize;
    public ArrayList<String>[] table;
    private int count;

    public HashTable(int size) {
        tableSize = size;
        this.table = new ArrayList[tableSize];
        for (int i = 0; i < tableSize; i++) {
            table[i] = new ArrayList<>();
        }
        this.count = 0;
    }

    public int hash(String key) {
        int hashValue = 0;
        int n = key.length();
        for (int i = 0; i < n; i++) {
            hashValue = (hashValue + key.charAt(i)) * 31;
        }
        return Math.abs(hashValue % tableSize);
    }

    public void add(String ch) {
        int index = hash(ch);
        if (table[index].contains(ch)) {
            System.out.println("Already added");
        } else {
            table[index].add(ch);
            count++;
            System.out.println("Added");
        }
    }

    public void remove(String ch) {
        int index = hash(ch);
        if (table[index].remove(ch)) {
            count--;
            System.out.println("Removed");
        } else {
            System.out.println(ch + " not found");
        }
    }

    public int size() {
        return count;
    }

    public boolean contains(String ch) {
        int index = hash(ch);
        if (table[index].contains(ch)) {
            System.out.println(ch + " found");
            return true;
        } else {
            System.out.println(ch + " not found");
            return false;
        }
    }

    public void display() {
        for (int i = 0; i < tableSize; i++) {
            System.out.println(i + ".- " + table[i]);
        }
    }

    public ArrayList<String>[] getTable() {
        return table;
    }

    public static void main(String[] args) {
        HashTable hashTable = new HashTable(10);
        Scanner scanner = new Scanner(System.in);
        label:
        while (true) {
            System.out.print(">");
            String input = scanner.nextLine();
            String command = input.split(" ")[0];
            switch (command) {
                case "add":
                    hashTable.add(input);
                    break;
                case "remove":
                    hashTable.remove(input);
                    break;
                case "size":
                    System.out.println(hashTable.size());
                    break;
                case "contains":
                    hashTable.contains(input);
                    break;
                case "display":
                    hashTable.display();
                    break;
                case "exit":
                    break label;
                default:
                    System.out.println("Invalid command");
                    break;
            }
        }
    }
}
