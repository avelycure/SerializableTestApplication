 * Чтобы воспроизвести прикол:
 * 1. Запускаем приложение
 * 2. Создаем файл
 * 3. Сериализуем объект
 * 4. Десериализуем объект
 * 5. Видим в логах: NotificationMeta(pushType=SINGLE_MESSAGE, hasSmartReplies=true, isReminder=true)
 * 6. Убираем комментарии из файла PushMessageType, а в файле NotificationUpdater их наоборот добавляем
 * 7. Запускаем приложение
 * 8. Десериализуем объект
 * 9. Видим в логах: NotificationMeta(pushType=null, hasSmartReplies=true, isReminder=true)
