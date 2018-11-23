package com.sda.taskmanager;

import java.util.Optional;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String komenda;

        do {
            System.out.println("Podaj komendę: (logowanie/rejestracja):");
            komenda = scanner.next();

            if (komenda.equals("logowanie")) {
                if (zaloguj(scanner)) {
                    panelUzytkownika(scanner);
                }
            } else if (komenda.equals("rejestracja")) {
                rejestruj(scanner);
            }

        } while (!komenda.equals("exit"));
    }

    private static void rejestruj(Scanner scanner) {
        // REJESTRACJA
        String imie = scanner.next();
        String nazwisko = scanner.next();
        String nazwaUzytkownika = scanner.next();
        String haslo = scanner.next();

        User user = new User(null, imie, nazwisko, nazwaUzytkownika, haslo);

        UserDao dao = new UserDao();
        dao.create(user);
        // todo: dodać sprawdzenie w bazie czy użytkownik o tym samym username nie istnieje, jeśli tak nie pozwól na rejestrację
    }

    private static void panelUzytkownika(Scanner scanner) {

    }

    private static boolean zaloguj(Scanner scanner) {
        UserDao dao = new UserDao();

        System.out.println("Nazwa uzytkownika:");
        String nu = scanner.next();

        System.out.println("Haslo:");
        String haslo = scanner.next();

        Optional<User> userOptional = dao.zaloguj(nu, haslo);
        if (userOptional.isPresent()) {
            System.out.println("Zalogowany!");
            return true;
        }
        System.out.println("Zły login lub hasło.");
        return false;
    }
}
