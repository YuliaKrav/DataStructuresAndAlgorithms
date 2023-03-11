import java.io.*;
import java.util.Arrays;

public class UnderGround {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            long[] firstLine = readArray(reader);

            long numberOfMonsters = firstLine[0];
            long heroStamina = firstLine[1];
            long[] monstersHealth = readArray(reader);

            if (heroStamina < numberOfMonsters) {
                writer.write("The game is unwinnable.");
                return;
            }
            writer.write("Min level: " + findMinLevelToWin(heroStamina, monstersHealth));
        }
    }

    private static long findMinLevelToWin(long heroStamina, long[] monstersHealth) {
        long minResultLevel = 0;
        Arrays.sort(monstersHealth);
        int firstAttemptKIllMaxIndexNext = 0;
        long totalLeftMonstersHealth = getTotalLeftMonstersHealth(firstAttemptKIllMaxIndexNext, monstersHealth);

        minResultLevel = totalLeftMonstersHealth % heroStamina == 0
                ? totalLeftMonstersHealth / heroStamina - 1
                : totalLeftMonstersHealth / heroStamina;


        while (totalLeftMonstersHealth != 0) {
            minResultLevel++;
            firstAttemptKIllMaxIndexNext = findNextIndexAfterTheFirstAttemptKill(minResultLevel, firstAttemptKIllMaxIndexNext, monstersHealth);
            long staminaLeft = heroStamina - firstAttemptKIllMaxIndexNext;
            int allAttemptKillMaxIndexNext =
                    findNextIndexAfterTheLastAttemptKill(minResultLevel, staminaLeft, firstAttemptKIllMaxIndexNext, monstersHealth);
            totalLeftMonstersHealth = getTotalLeftMonstersHealth(allAttemptKillMaxIndexNext, monstersHealth);
        }
        return minResultLevel;
    }

    private static int findNextIndexAfterTheLastAttemptKill(long damage, long stamina, int leftIndex, long[] monstersHealth) {
        while (leftIndex < monstersHealth.length) {
            stamina = monstersHealth[leftIndex] % damage == 0
                    ? stamina - monstersHealth[leftIndex] / damage
                    : stamina - monstersHealth[leftIndex] / damage - 1;

            if (stamina < 0) {
                break;
            }
            leftIndex++;
        }
        return leftIndex;
    }

    private static int findNextIndexAfterTheFirstAttemptKill(long damage, int leftIndex, long[] monstersHealth) {
        int rightIndex = monstersHealth.length - 1;

        if (monstersHealth[leftIndex] > damage) {
            return leftIndex;
        } else if (monstersHealth[rightIndex] <= damage) {
            return rightIndex + 1;
        }

        while (rightIndex - leftIndex > 1) {
            int mediumIndex = (leftIndex + rightIndex) / 2;
            if (monstersHealth[mediumIndex] <= damage) {
                leftIndex = mediumIndex;
            } else {
                rightIndex = mediumIndex;
            }
        }
        return monstersHealth[leftIndex] <= damage ? rightIndex : leftIndex;
    }

    private static long getTotalLeftMonstersHealth(int fromIndex, long[] array) {
        long totalSum = 0;

        for (int i = fromIndex; i < array.length; i++) {
            totalSum += array[i];
        }
        return totalSum;
    }

    private static long[] readArray(BufferedReader reader) throws IOException {
        return Arrays.stream(reader.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
    }
}
