package nextstep.ladder.ui;

import nextstep.ladder.domain.Participant;
import nextstep.ladder.exception.LadderHeightException;
import nextstep.ladder.exception.NotInParticipantsException;
import nextstep.ladder.exception.ParticipantNameLengthExceedException;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

import static nextstep.ladder.validator.LadderValidator.validateLadderMaxHeight;
import static nextstep.ladder.validator.ParticipantValidator.validateNameLength;
import static nextstep.ladder.validator.ParticipantValidator.validateNamesLength;
import static nextstep.ladder.validator.ParticipantsValidator.notMatchParticipantsSize;
import static nextstep.ladder.validator.ParticipantsValidator.validateInParticipants;

public class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);

    public static List<String> readParticipantNames() {
        System.out.println("참여할 사람 이름을 입력하세요. (이름은 쉽표(,)로 구분하세요)");

        List<String> names;
        try {
            names = parseStringList(nextLine());
            validateNamesLength(names);
        } catch (ParticipantNameLengthExceedException e) {
            ResultView.printException(e.getMessage());
            return readParticipantNames();
        }

        return names;
    }

    private static String nextLine() {
        return SCANNER.nextLine();
    }

    private static List<String> parseStringList(String string) {
        String[] strings = string.replace(" ", "").split(",");
        return Arrays.stream(strings).collect(Collectors.toUnmodifiableList());
    }

    public static int readLadderMaxHeight() {
        System.out.println("최대 사다리 높이는 몇 개인가요?");

        int height;
        try {
            height = nextInt();
            validateLadderMaxHeight(height);
        } catch (LadderHeightException e) {
            ResultView.printException(e.getMessage());
            return readLadderMaxHeight();
        }

        return height;
    }

    private static int nextInt() {
        try {
            return Integer.parseInt(SCANNER.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("사다리 높이는 숫자만 입력 가능합니다. 다시 입력해주세요.");
            return nextInt();
        }
    }

    public static List<String> readLadderGamePrizes(int numberOfParticipant) {
        System.out.println("실행 결과를 입력하세요. (결과는 쉼표(,)로 구분하세요.");

        List<String> prizes = parseStringList(nextLine());
        if (notMatchParticipantsSize(prizes, numberOfParticipant)) {
            System.out.println("참가자 수와 실행 결과 수가 일치하지 않습니다.");
            return readLadderGamePrizes(numberOfParticipant);
        }
        return prizes;
    }

    public static String readNameForGameResult(Set<Participant> participants) {
        System.out.println("결과를 보고 싶은 사람은?");

        String name;
        try {
            name = nextLine();
            validateNameLength(name);
            validateInParticipants(participants, name);
        } catch (ParticipantNameLengthExceedException | NotInParticipantsException e) {
            ResultView.printException(e.getMessage());
            return readNameForGameResult(participants);
        }

        return name;
    }

}
