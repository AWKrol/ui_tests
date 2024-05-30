package constants;

public enum CoursesType {

  ALL_TYPES("Все направления"),
  PROGRAMMING("Программирование"),
  ARCHITECTURE("Архитектура"),
  INFRASTRUCTURE("Инфраструктура"),
  SAFETY("Безопасность"),
  DATA_SCIENCE("Data Science"),
  GAME_DEV("GameDev"),
  MANAGEMENT("Управление"),
  ANALYTICS("Аналитика"),
  TESTING("Тестирование"),
  CORPORATE_COURSES("Корпоративные курсы"),
  IT_WITHOUT_PROGRAMMING("IT без программирования");

  private String name;

  CoursesType(String name) {
    this.name = name;
  }

  public String getName(){
    return name;
  }

}
