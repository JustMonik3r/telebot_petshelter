package pro.sky.telebotpetshelter.utils;
public enum CallbackDataRequest {
    CAT("Приют для котов", "CAT"),
    DOG("Приют для собак", "DOG"),
    TAKE_CAT("Хочу взять кошечку", "TAKE_CAT"),
    TAKE_DOG("Хочу взять собачку", "TAKE_DOG"),
    GENERAL_SHELTER_INFO("Информация о приюте", "GENERAL_SHELTER_INFO"),
    HOW_TO_TAKE_ANIMAL("Как взять животное из приюта", "HOW_TO_TAKE_ANIMAL"),
    REPORT_ANIMAL("Прислать отчет о питомце", "REPORT_ANIMAL"),
    VOLUNTEER("Позвать волонтера", "VOLUNTEER"),
    ABOUT_SHELTER("О нас", "ABOUT_SHELTER"),
    CONTACTS("Наши контакты", "CONTACTS"),
    SECURITY("Оформить пропуск на машину", "SECURITY"),
    SAFETY_IN_SHELTER_TERRITORY("Техника безопасности на территории приюта", "SAFETY_IN_SHELTER_TERRITORY"),
    GIVE_MY_CONTACT("Оставить контакт для связи", "GIVE_MY_CONTACT"),
    ROLLBACK("Вернуться назад", "ROLLBACK"),
    //Консультация клиента

    SHELTER_RULES_BEFORE_MEETING_ANIMAL("Правила знакомства с животным", "SHELTER_RULES_BEFORE_MEETING_ANIMAL"),
    DOCUMENTS_TO_TAKE_ANIMAL("Список документов, чтобы взять животное", "DOCUMENTS_TO_TAKE_ANIMAL"),
    TRANSPORTATION_ADVICE("Рекомендации по транспортировке животного", "TRANSPORTATION_ADVICE"),
    HOUSE_RULES_FOR_SMALL_ANIMAL("Рекомендации по обустройству дома для детенышей", "HOUSE_RULES_FOR_KITTY"),
    HOUSE_RULES_FOR_ADULT_ANIMAL("Рекомендации по обустройству дома для взрослого животного", "HOUSE_RULES_FOR_ADULT_ANIMAL"),
    HOUSE_RULES_FOR_ANIMAL_WITH_DISABILITY("Уход за животными с ограниченными возможностями", "HOUSE_RULES_FOR_ANIMAL_WITH_DISABILITY"),
    CYNOLOGIST_ADVICE("Рекомендации по уходу от кинологов", "CYNOLOGIST_ADVICE"),
    CYNOLOGISTS("Рекомендованные кинологи", "CYNOLOGISTS"),
    FELINOLOGIST_ADVICE("Рекомендации по уходу от фелинологов", "FELINOLOGIST_ADVICE"),
    FELINOLOGISTS("Рекомендованные фелинологи", "FELINOLOGISTS"),
    REFUSE_REASONS("Причины для отказа выдачи животного", "REFUSE_REASONS");

    private final String text;
    private final String callbackData;

    CallbackDataRequest(String text, String callbackData) {
        this.text = text;
        this.callbackData = callbackData;
    }

    public String getText() {
        return text;
    }

    public String getCallbackData() {
        return callbackData;
    }

    public static CallbackDataRequest getConstantByRequest(String data) {
        for (CallbackDataRequest value : CallbackDataRequest.values()) {
            if (value.getCallbackData().equals(data)) {
                return value;
            }
        }
        throw new IllegalArgumentException("Передан неизвестный аргумент");
    }
}