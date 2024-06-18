import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class BinaryTree {
    private RouteNode root;
    private int size;

    public BinaryTree() {
        this.root = null;
        this.size = 0;
    }

    public int size() {
        return size;
    }

    public void addRoute(Route route) {
        List<Route> routes = new ArrayList<>();
        getRoutes(routes);
        if (!routes.contains(route)) {
            root = addRouteToTree(root, route);
        }
    }

    private RouteNode addRouteToTree(RouteNode node, Route route) {
        if (node == null) {
            size++;
            return new RouteNode(route);
        }

        int compareResult = route.getId().compareTo(node.getRoute().getId());

        if (compareResult < 0) {
            node.setLeft(addRouteToTree(node.getLeft(), route));
        } else if (compareResult > 0) {
            node.setRight(addRouteToTree(node.getRight(), route));
        }

        return node;
    }

    public void removeRoute(String routeId) {
        root = removeRouteFromTree(root, routeId);
    }

    private RouteNode removeRouteFromTree(RouteNode node, String routeId) {
        if (node == null) {
            return null;
        }

        int compareResult = routeId.compareTo(node.getRoute().getId());

        if (compareResult < 0) {
            node.setLeft(removeRouteFromTree(node.getLeft(), routeId));
        } else if (compareResult > 0) {
            node.setRight(removeRouteFromTree(node.getRight(), routeId));
        } else {
            size--;
            if (node.getLeft() == null) {
                return node.getRight();
            } else if (node.getRight() == null) {
                return node.getLeft();
            }

            node.setRoute(findMin(node.getRight()).getRoute());
            node.setRight(removeMin(node.getRight()));
        }

        return node;
    }

    private RouteNode findMin(RouteNode node) {
        while (node.getLeft() != null) {
            node = node.getLeft();
        }
        return node;
    }

    private RouteNode removeMin(RouteNode node) {
        if (node.getLeft() == null) {
            return node.getRight();
        }
        node.setLeft(removeMin(node.getLeft()));
        return node;
    }

    public RouteNode getRoute(String routeId) {
        return getRoute(root, routeId);
    }

    private RouteNode getRoute(RouteNode node, String routeId) {
        if (node == null) {
            return null;
        }

        int compareResult = routeId.compareTo(node.getRoute().getId());

        if (compareResult < 0) {
            return getRoute(node.getLeft(), routeId);
        } else if (compareResult > 0) {
            return getRoute(node.getRight(), routeId);
        } else {
            return node;
        }
    }

    public List<Route> searchRoutes(String startPoint, String endPoint) {
        List<Route> routes = new ArrayList<>();
        searchRoutes(root, startPoint, endPoint, routes);
        return routes;
    }

    private void searchRoutes(RouteNode node, String startPoint, String endPoint, List<Route> routes) {
        if (node == null) {
            return;
        }

        Route route = node.getRoute();
        if (route.getLocationPoints().contains(startPoint) && route.getLocationPoints().contains(endPoint)) {
            routes.add(route);
        }

        searchRoutes(node.getLeft(), startPoint, endPoint, routes);
        searchRoutes(node.getRight(), startPoint, endPoint, routes);
    }

    public void getRoutes(List<Route> routes) {
        getRoutes(root, routes);
    }

    private void getRoutes(RouteNode node, List<Route> routes) {
        if (node == null) {
            return;
        }

        routes.add(node.getRoute());
        getRoutes(node.getLeft(), routes);
        getRoutes(node.getRight(), routes);
    }


    public List<Route> getFavoriteRoutes() {
        List<Route> routes = new ArrayList<>();
        getFavoriteRoutes(root, routes);
        return routes;
    }

    private void getFavoriteRoutes(RouteNode node, List<Route> routes) {
        if (node == null) {
            return;
        }

        Route route = node.getRoute();
        if (route.isFavorite()) {
            routes.add(route);
        }

        getFavoriteRoutes(node.getLeft(), routes);
        getFavoriteRoutes(node.getRight(), routes);
    }

}

