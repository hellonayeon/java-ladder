package nextstep.ladder.domain;

import java.util.List;
import java.util.stream.Collectors;

public class Participants {

    private final List<Participant> participants;

    public static Participants of(List<String> names) {
        List<Participant> participants = names.stream()
                .map(Participant::of)
                .collect(Collectors.toUnmodifiableList());
        return new Participants(participants);
    }

    private Participants(List<Participant> participants) {
        this.participants = participants;
    }

    public int numberOf() {
        return this.participants.size();
    }

    public List<Participant> get() {
        return this.participants;
    }

    public int startPosition(Participant executeParticipant) {
        return participants.indexOf(executeParticipant);
    }

}
