**Order**
* cart.createOrder() ma je zapisywać do pliku

**Product**
* Jak ma wyglądać sugerowanie produktów?
* Jak je wczytywać do produktu przy ConvertFromRecord? Bo jeśli to ma być lista produktów,
        to one też będą generować swoje sugerowane i mamy nieskończoną pętlę. Może same nazwy produktów?

**EventNoticication**
* Konwersja na i ze stringa

**DatabaseConnector**
* Korzystać z adaptera by przerabiać Producty json na csv (jeśli to wykonalne)

**Tworzenie zamówienia** 
* Jeśli user nie ma uzupełnionego adresu w bazie, to przed złożeniem zamówienia musi je uzupełnić.

**Wzorce projektowe**
* strukturalne
    * Adapter - "przerzucenie" danych z bazy do kodu
    * Dekorator - opcjonalne dodatki do zamówień (np. przy zamówieniu książki zakładka)
    * Fasada - Interfejs użytkownika

 * czynościowe
      * Łańcuch zobowiązań - rozdzielenie użytkowników zwykłych od uprzywilejowanych
      * Obserwator - wysyłanie powiadomień email o promocjach, protuktach (wydarzeniach)
      * Mediator - zarządzanie informacjami na temat użytkownika

**"volatile" a "synchronization"**'
