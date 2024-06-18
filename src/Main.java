import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        NavigatorImpl navigator = new NavigatorImpl();

        // Создаем маршруты
        Route route1 = new Route( "6",1000, 10, true, Arrays.asList("Москва", "Тверь", "Санкт-Петербург"));
        Route route2 = new Route( "1",1500, 20, false, Arrays.asList("Москва", "Казань", "Екатеринбург"));
        Route route3 = new Route( "3",2000, 30, true, Arrays.asList("Москва", "Самара", "Кострома"));
        Route route4 = new Route( "7",2500, 40, true, Arrays.asList("Москва", "Ростов-на-Дону", "Сочи"));
        Route route5 = new Route( "10",3000, 50, true, Arrays.asList("Москва", "Владимир", "Нижний Новгород"));
        Route route6 = new Route("2",3500, 60, false, Arrays.asList("Москва", "Воронеж", "Ростов-на-Дону"));
        Route route7 = new Route("15",4000, 70, true, Arrays.asList("Москва", "Тула", "Курск"));
        Route route8 = new Route("23",4500, 80, false, Arrays.asList("Москва", "Рязань", "Воронеж"));
        Route route9 = new Route("11",5000, 90, true, Arrays.asList("Москва", "Тверь", "Великий Новгород"));
        Route route10 = new Route("74",5500, 100, false, Arrays.asList("Москва", "Ярославль", "Вологда"));
        Route route11 = new Route("5",6000, 110, true, Arrays.asList("Москва", "Иваново", "Кострома"));
        Route route12 = new Route("17",6500, 120, false, Arrays.asList("Москва", "Урюпинск", "Кострома"));
        Route route13 = new Route("21",7000, 130, true, Arrays.asList("Москва", "Смоленск", "Брянск"));
        Route route14 = new Route("53",7500, 140, false, Arrays.asList("Москва", "Тула", "Орел"));
        Route route15 = new Route("4",8000, 150, true, Arrays.asList("Москва", "Рязань", "Тамбов"));

        // Добавляем маршруты в навигатор
        navigator.addRoute(route1);
        navigator.addRoute(route2);
        navigator.addRoute(route3);
        navigator.addRoute(route4);
        navigator.addRoute(route5);
        navigator.addRoute(route6);
        navigator.addRoute(route7);
        navigator.addRoute(route8);
        navigator.addRoute(route9);
        navigator.addRoute(route10);
        navigator.addRoute(route11);
        navigator.addRoute(route12);
        navigator.addRoute(route13);
        navigator.addRoute(route14);
        navigator.addRoute(route15);

        // Проверяем методы навигатора

        System.out.println("Количество маршрутов = "+ navigator.size());


        System.out.println("\nВсе маршруты:");
        navigator.printAllRoutes();

        System.out.println("\nСодержит ли route1?");
        System.out.println(navigator.contains(route1));

        System.out.println("\nУдаляем route1 по Id");
        navigator.removeRoute(route1.getId());

        System.out.println("\nСодержит ли route1?");
        System.out.println(navigator.contains(route1));

        System.out.println("\nУвеличиваем популярность route2");
        System.out.println("Популярность до увелечения: "+ route2.getPopularity());
        navigator.chooseRoute(route2.getId());
        System.out.println("Популярность после увелечения: "+ route2.getPopularity());

        System.out.println("\nМаршруты из Москвы в Екатеринбург:");
        for (Route route : navigator.searchRoutes("Москва", "Екатеринбург")) {
            System.out.println(route);
        }

        System.out.println("\nИзбранные маршруты до Сочи:");
        for (Route route : navigator.getFavoriteRoutes("Сочи")) {
            System.out.println(route);
        }

        System.out.println("\nТоп-5 маршрутов:");
        for (Route route : navigator.getTop5Routes()) {
            System.out.println(route);
        }
    }

}