package nextstep.ladder.domain;

import java.util.Collections;
import java.util.List;

public class Line {

    private final List<Point> points;

    public static Line of(List<Point> points) {
        return new Line(points);
    }

    private Line(List<Point> points) {
        this.points = points;
    }

    public List<Point> get() {
        return Collections.unmodifiableList(this.points);
    }

    public int move(int position) {
        return currentPoint(position).getDirection().move(position);
    }

    private Point currentPoint(int position) {
        return this.points.get(position);
    }
}
