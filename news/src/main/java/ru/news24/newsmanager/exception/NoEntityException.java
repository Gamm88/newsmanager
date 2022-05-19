package ru.news24.newsmanager.exception;

public class NoEntityException extends Exception {
    public NoEntityException(long newsTypeId) {
        System.out.println("Тип новости с ID № " + newsTypeId + " не найден. Проверьте данные.");
    }
}