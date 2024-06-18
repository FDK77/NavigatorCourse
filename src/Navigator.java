public interface Navigator {
            void addRoute(Route route); // добавляет маршрут в «Навигатор».
            void removeRoute(String routeId); // удаляет маршрут из «Навигатора».
            boolean contains(Route route); // возвращает значение true, если маршрут найден.
            int size();// возвращает общее количество маршрутов.
            Route getRoute(String routeId); // возвращает маршрут по id.
            void chooseRoute(String routeId);// увеличивает популярность данного маршрута на 1.
            Iterable<Route> searchRoutes(String startPoint, String endPoint); // возвращает все маршруты, имеющие логический смысл, содержащие начальную и конечную точки.
            Iterable<Route> getFavoriteRoutes(String destinationPoint); // возвращает все маршруты, которые являются избранными и содержат данную точку назначения в качестве не начальной точки.
    	    Iterable<Route> getTop5Routes(); // возвращает первые 5 маршрутов по популярности в порядке убывания, затем по расстоянию в порядке возрастания и затем по количеству точек местоположения в порядке возрастания.
}
