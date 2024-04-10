package nextstep.ladder.domain;

import java.util.Collections;
import java.util.List;

public class Line {

    private final List<Point> points;

    public static <T> Line of(PointsGenerateStrategy<T> strategy, T pointParam) {
        return new Line(Collections.unmodifiableList(strategy.generate(pointParam)));
    }

    private Line(List<Point> points) {
        this.points = points;
    }

    public List<Point> get() {
        return Collections.unmodifiableList(this.points);
    }

    public int move(int position) {
        return position + currentPoint(position).move();
    }

    private Point currentPoint(int position) {
        return this.points.get(position);
    }
}
