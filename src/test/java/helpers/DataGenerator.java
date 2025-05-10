package helpers;

import net.datafaker.Faker;

public class DataGenerator {
    private static final Faker faker = new Faker();

    public static String generateData() {
        return faker.lorem().sentence(1);
    }
}
