## Приложения Spring Boot ##

### 1. Configuration ###
Класс JavaConfig:       
- Определяет бины для различных профилей (DevProfile, ProductionProfile) с использованием аннотации @Configuration.  
- Использует аннотации @ConditionalOnProperty для создания нужного профиля в зависимости от конфигурации.  

### 2. Model ###

Интерфейс SystemProfile: 
- Определяет контракт для профилей системы,предоставляя метод getProfile().  
Классы DevProfile и ProductionProfile:   
- Реализуют интерфейс SystemProfile   
- предоставляют конкретные реализации метода getProfile() для различных окружений.  

### 3. Controller Layer ###

Класс ProfileController:
- Обрабатывает HTTP-запросы, предоставляя REST API.  
- Получает экземпляр SystemProfile через инъекцию зависимостей и возвращает соответствующий профиль в ответ на запросы.  