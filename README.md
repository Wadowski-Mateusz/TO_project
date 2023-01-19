#### Na dole pliku znajdują się informacje, które klasy implementują poniższe wzorce.

    Język: JAVA 17

    Github: https://github.com/Wadowski-Mateusz/TO_project

    Temat: Sklep Internetowy

    Projekt ma używać co najmniej 8 wzorców projektowych 
    (co najmniej 6 różnych, co najmniej 2 z każdej kategorii: kreacyjne, strukturalne, czynnościowe)

    Wzorce projektowe:
        a) kreacyjne
            1) singleton - kontakt/łącze z bazą danych lub/i configi użytkowników +
            2) metoda wytwórcza - dodawanie produktów na stronę (przykładowo dodanie mikrofali wraz ze specyfikacją jako agd_kuchnia) i/lub sposób dostawy +
            3) budowniczy - zbudowanie koszyka z zamówieniem +

        b) strukturalne
            1) Adapter - "przerzucenie" danych z bazy do kodu +
            2) Dekorator - opcjonalne dodatki do zamówień (np. przy zamówieniu książki zakładka)
            3) Fasada - Interfejs użytkownika +

        c) czynościowe
            1) Łańcuch zobowiązań - rozdzielenie użytkowników zwykłych od uprzywilejowanych +
            2) Obserwator - wysyłanie powiadomień email o promocjach, protuktach (wydarzeniach) +
            3) Mediator - zarządzanie informacjami na temat użytkownika


    Co projekt będzie robił?
        Projekt będzie prostym sklepem internetowym.


#### Implementacja wzorców

1) Singleton 
   * DatabaseConnector - odpowiada za zapis, odczyt i aktualizację danych, jest singletonem 
   * Newsletter - wysyła maile (jest również obserwatorem)
2) Metoda wytwórcza 
    * ProductFactory - składa różne typy przedmiotów w całość, ale przedmioty mogą mieć różną charakterystyke w zależności od kategorii
3) Budowniczy
   * UserBuilder - podczas rejestracji pokolei usupełnia o kolejne informacje
   * CartBuilder - krokowo skłąda koszyk w całość - użytkownik powinien móc dodawać i uwusać produkty, a dopiero potem przy potwierdzeniu składa koszyk 
4) Adapter
   * DbcAdapter - interfejs
   * DbcAdapterRecordJSON - baza danych domyślnie działa na w formacie csv, ale klasa Przedmiot działą jako JSON, adapter konwertuje JSON na string i string na JSON 
   * DbcAdapterRecordString - reszta klas implementujących interfejs <i>Convertible</i> działa pod csv, ale gdyby była konieczność zmiany, adapter ten szybko umożliwi podmiankę
5) Fasada
   * ClientApp, ServerAppThread - ClientApp jedynie wysyła informacje jaką operacje chce wykonać, ale ServerAppThread odpowiada za całą logikę wykoniania operacji (tworzenie instancji, operacje na koszyku itp.). 
   Niestety, w obecnym stanie nie reprezentuje stanu sklepu i reszty kodu. Nie wszytkie dostępne opcje znalazły się w niej. Sklep jest bardziej rozbudowany niż fasada by na to wskazywała. 
6) Łańcuch zobowiązań
   * UserChecker, RegisteredUserChecker, RoleChecker -  sprawdza czy użytkownik istnieje oraz jego role (standard, admin)
7) Obserwator
   * Newsletter - <i>Product</i> informuje o zmianach cen, <i>User</i> zapisuje się. Kiedy liczba przedmiotów na obniżce przekroczy X (tutaj 3), wysyła mail do zapisanych użytkowników (do pluku <i>mail/user_mail</i>)  
   * próbowaliśmy również stworzyć obserwatorów bardziej skonkretyzowanych na przedmioty/userów, ale bez powodzenia

~~8) Dekorator~~\
~~9) Mediator~~