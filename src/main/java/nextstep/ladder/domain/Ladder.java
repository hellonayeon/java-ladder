package nextstep.ladder.domain;

import nextstep.ladder.validator.LadderValidator;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Ladder {

    public static final int MIN_LADDER_HEIGHT = 1;
    private final List<Line> lines;

    public static Ladder of(int pointSize, int height) {
        List<Line> lines = Stream.generate(() -> Line.of(RandomPointsStrategy.get(), pointSize))
                .limit(height)
                .collect(Collectors.toUnmodifiableList());
        return new Ladder(lines);
    }

    private Ladder(List<Line> lines) {
        LadderValidator.validateLadderMaxHeight(lines.size());
        this.lines = lines;
    }

    public List<Line> getLines() {
        return this.lines;
    }

    public Map<Participant, String> move(Participants participants, List<String> prizes) {
        Map<Participant, String> prizeMap = new LinkedHashMap<>();

        for (Participant participant : participants.get()) {
            int position = move(participants.startPositionOf(participant));
            prizeMap.put(participant, prizes.get(position));
        }

        return Collections.unmodifiableMap(prizeMap);
    }

    private int move(int position) {
        for (Line line : this.lines) {
            position = line.move(position);
        }
        return position;
    }
}
