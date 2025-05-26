# Knapsack Problem in Java
Problemem do rozwiązania w ramach laboratorium był nieskończony problem plecakowy. 
# Definicja klas i generator instancji
W pierwszej części należało stworzyć klasy: Item oraz Problem. Pierwsza to klasa, która definiowała strukturę przedmiotów:
```Java
public class Item implements Comparable<Item> {
    private int id;
    private int value;
    private int weight;
    private double ratio;

    public Item(int id, int value, int weight) {
        this.id = id;
        this.value = value;
        this.weight = weight;
        this.ratio = (double) value / (double) weight;
    }

    public int getValue(){
        return value;
    }

    public int getWeight(){
        return weight;
    }

    @Override
    public int compareTo(Item o) {
        return Double.compare(o.ratio, this.ratio);
    }

    @Override
    public String toString() {
        return "Id: " + id + " Value: " + value + " Weight: " + weight;
    }
}
```
W tej klasie przeciążono 2 metody:
1. metoda compareTo - w celu sortowania listy przedmiotów po ratio w metodzie Solve
2. toString - w celu debugowania

Klasa problem - generowanie problemu
```Java
public class KnapsackProblem {
    private int n;
    private int seed;
    private int lowerBound;
    private int upperBound;
    private List<Item> items;

    public KnapsackProblem(int n, int seed, int lowerBound, int upperBound) {
        this.n = n;
        this.seed = seed;
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
        this.items = new ArrayList<Item>();
        Random random = new Random(seed);

        for (int i = 0; i < n; i++) {
            int weight = random.nextInt(upperBound - lowerBound + 1) + lowerBound;
            int value = random.nextInt(upperBound - lowerBound + 1) + lowerBound;
            items.add(new Item(i+1, weight, value));
        }
    }

    public KnapsackProblem(List<Item> items) {
        this.n = items.size();
        this.items = items;
        this.lowerBound = 0;
        this.upperBound = 0;
        this.seed = 0;
    }
}
```
klasa ma pola pozwalające wygenerować interesujący nas problem plecakowy:
1. n - ilość przedmiotów generowanych w instancji problemu
2. seed - ziarno losowania
3. lower i upperBound - przedział z którego losowane będą wartości i wagi przedmiotów
4. items - lista przedmiotów branych pod uwagę
Klasa ma 2 konstruktory:
1. Działający jako generator
2. Używany do testów

# Rozwiązanie
W drugim etapie zaimplementowano metodę solve, która rozwiązywała nieskończony problem plecakowy. Metoda zwracała wynik w postaci obiektu klasy Result.
Klasa result:
```Java
public class Result {
    private int totalWeight;
    private int totalValue;
    private List<Item> selectedItems;

    public Result(int totalWeight, int totalValue, List<Item> selectedItems) {
        this.totalWeight = totalWeight;
        this.totalValue = totalValue;
        this.selectedItems = selectedItems;
    }

    public List<Item> getSelectedItems() {
        return selectedItems;
    }

    public int getTotalWeight() {
        return totalWeight;
    }

    public int getTotalValue() {
        return totalValue;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("====RESULT=====").append("\n");
        builder.append("totalWeight: ").append(totalWeight).append("\n");
        builder.append("totalValue: ").append(totalValue).append("\n");
        builder.append("selectedItems: ").append(selectedItems).append("\n");

        return builder.toString();
    }

}
```
Klasa przechowuje informacje o całkowitej wartości i wadze "spakowanych" przedmiotów. Przeciążona została metoda toString w celu wyświetlania wyników.

Metoda Solve
```Java
public Result solve(int capacity) {
        int remainingCapacity = capacity;
        int totalWeight = 0;
        int totalValue = 0;
        List<Item> selectedItems = new ArrayList<>();

        Collections.sort(items);

        for(Item item : items) {
            if(item.getWeight() <= remainingCapacity) {
                int count = remainingCapacity/item.getWeight();
                remainingCapacity -= count * item.getWeight();
                totalWeight += count * item.getWeight();
                totalValue += count*item.getValue();
                for (int i = 0; i < count; i++) {
                    selectedItems.add(item);
                }
            }

        }

        return new Result(totalWeight, totalValue, selectedItems);
    }
```
Metoda solve:
1. Sortuje przedmoity w liście według ich ratio
2. Dla każdego przedmiotu sprawdza ile razy może go "spakować"
3. Aktualizuje zmienne totalValue i totalWeight
4. Pakuje przedmiot tyle razy ile może dodając go do listy "spakowanych" przedmiotów
5. Po przejściu wszystkich przedmiotów zwraca wynik w postaci obiektu klasy Result

# Testy
W trzecim etapie należało napisać testy sprawdzające działanie projektu. Napisane zostało 6 testów:
1. Sprawdzamy czy jeżeli chociaż jeden przedmiot "zmieści się do plecaka" to czy plecak nie jest pusty
2. Sprawdza czy Klasa solve tworzy listę przedmiotów o podanej liczbie przedmiotów
3. Sprawdzenie czy jeżeli żaden przedmiot nie zmieści się w plecaku to czy zwraca pustą listę
4. Sprawdzenie czy wartości i wagi przedmiotów mieszczą się w zadanych granicach
5. Sprawdzenie instancji rozwiązanej ręcznie
6. Sprawdzenie czy całkowita waga nie jest większa od capacity

   
