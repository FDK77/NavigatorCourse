import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class NavigatorImpl implements Navigator {
    private BinaryTree binaryTree;

    public NavigatorImpl() {
        this.binaryTree = new BinaryTree();
    }

    public void printAllRoutes() {
        List<Route> routes = new ArrayList<>();
        binaryTree.getRoutes(routes);
        for (Route route : routes) {
            System.out.println(route);
        }
    }

    @Override
    public void addRoute(Route route) {
        binaryTree.addRoute(route);
    }

    @Override
    public void removeRoute(String routeId) {
        binaryTree.removeRoute(routeId);
    }

    @Override
    public boolean contains(Route route) {
        String id = route.getId();
        if(binaryTree.getRoute(id)!=null){
            return true;
        }
        else return false;
    }

    @Override
    public int size() {
        return binaryTree.size();
    }

    @Override
    public Route getRoute(String routeId) {
        return binaryTree.getRoute(routeId).getRoute();
    }

    @Override
    public void chooseRoute(String routeId) {
       int popularity = binaryTree.getRoute(routeId).getRoute().getPopularity();
       binaryTree.getRoute(routeId).getRoute().setPopularity(++popularity);
    }

    @Override
    public Iterable<Route> searchRoutes(String startPoint, String endPoint) {
        List<Route> routes = binaryTree.searchRoutes(startPoint, endPoint);
        routes.sort((r1, r2) -> {
            int r1Points = r1.getLocationPoints().subList(r1.getLocationPoints().indexOf(startPoint), r1.getLocationPoints().indexOf(endPoint)).size();
            int r2Points = r2.getLocationPoints().subList(r2.getLocationPoints().indexOf(startPoint), r2.getLocationPoints().indexOf(endPoint)).size();
            if (r1Points != r2Points) {
                return r1Points - r2Points; // сортировка по расстоянию в порядке возрастания
            } else if (r1.isFavorite() != r2.isFavorite()) {
                return r2.isFavorite() ? 1 : -1; // избранные маршруты на первом месте
            } else {
                return r2.getPopularity() - r1.getPopularity(); // сортировка по популярности в порядке убывания
            }
        });
        return routes;
    }


    @Override
    public Iterable<Route> getFavoriteRoutes(String destinationPoint) {
        List<Route> routes = binaryTree.getFavoriteRoutes().stream()
                .filter(route -> route.getLocationPoints().contains(destinationPoint) && !route.getLocationPoints().get(0).equals(destinationPoint))
                .sorted(Comparator.comparing(Route::getDistance)
                        .thenComparing(Route::getPopularity).reversed())
                .collect(Collectors.toList());
        return routes;
    }


    @Override
    public Iterable<Route> getTop5Routes() {
        List<Route> routes = new ArrayList<>();
        binaryTree.getRoutes(routes);
        routes.sort((r1, r2) -> {
            if (r1.getPopularity() != r2.getPopularity()) {
                return r2.getPopularity() - r1.getPopularity(); // сортировка по популярности в порядке убывания
            } else if (r1.getDistance() != r2.getDistance()) {
                return Double.compare(r1.getDistance(), r2.getDistance()); // сортировка по расстоянию в порядке возрастания
            } else {
                return r1.getLocationPoints().size() - r2.getLocationPoints().size(); // сортировка по количеству точек местоположения в порядке возрастания
            }
        });
        return routes.subList(0, Math.min(routes.size(), 5)); // возвращаем первые 5 маршрутов
    }
}

