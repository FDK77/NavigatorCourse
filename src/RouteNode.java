public class RouteNode {
    private Route route;
    private RouteNode left;
    private RouteNode right;

    public RouteNode(Route route) {
        this.route = route;
        this.left = null;
        this.right = null;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public RouteNode getLeft() {
        return left;
    }

    public void setLeft(RouteNode left) {
        this.left = left;
    }

    public RouteNode getRight() {
        return right;
    }

    public void setRight(RouteNode right) {
        this.right = right;
    }
}