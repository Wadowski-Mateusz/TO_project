Order:
    
    save to file when user confirm order (with shipping and payment)

 Product:

    Jak ma wyglądać sugerowanie produktów?
    Jak je wczytywać do produktu? Bo jeśli to ma być lista produktów,\
        to one też będą generować swoje sugerowane i mamy nieskończoną pętlę

EventNoticication:

    Conversion to and from string

DatabaseConnector:

    Load all items from given category
    Korzystać z adaptera by przerabiać Producty json na csv
    Ładowanie pojedyńczego produktu z bazy (Adapter?)

Tworzenie zamówienia:
    
    Jeśli user nie ma uzupełnionego adresu w bazie, to przed złożeniem zamówienia musi je uzupełnić.


Wzorce projektowe:\
&ensp;b) strukturalne\
&emsp;1) Adapter - "przerzucenie" danych z bazy do kodu\
&emsp;2) Dekorator - opcjonalne dodatki do zamówień (np. przy zamówieniu książki zakładka)\
&emsp;3) Fasada - Interfejs użytkownika

&ensp;c) czynościowe\
&emsp;1) Łańcuch zobowiązań - rozdzielenie użytkowników zwykłych od uprzywilejowanych\
&emsp;2) Obserwator - wysyłanie powiadomień email o promocjach, protuktach (wydarzeniach)\
&emsp;3) Mediator - zarządzanie informacjami na temat użytkownika

"volatile" a "synchronization"

Pożądane zmiany w danych można zapisać (zazwyczaj):\
&emsp;a) nowy rekord: bdc.saveToFile(Convertible convertible)\
&emsp;b) aktualizacja danych: dbc.updateRecord(Convertible convertible)
