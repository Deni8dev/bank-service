package com.slmndr.common;

import com.google.common.base.Splitter;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.StreamSupport;

public class Generator {

    public static String accountNumberGenerator() {
        final Random random = new Random();

        // Get random numbers
        final String staring =
            IntStream
                .range(0, 20)
                .mapToObj(i -> String.valueOf(random.nextInt(10000)))
                .collect(Collectors.joining());

        // Split numbers
        List<String> list = StreamSupport
            .stream(Splitter.fixedLength(4).split(staring).spliterator(), false)
            .filter(s -> s.length() == 4)
            .collect(Collectors.toList());

        // Disarray list of numbers
        Collections.shuffle(list);

        // Get Shuffled
        return IntStream
            .range(1, 5)
            // TODO: Review this shuffled range
            .mapToObj(i -> list.get(i + 5))
            .collect(Collectors.joining(" "));

    }
}
