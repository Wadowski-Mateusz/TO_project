Order:
    
    save to file when user confirm order (with shipping and payment)

Cart + CartBuilder

    Cart po zbudowaniu zamówienia powinien być wyczyszczony z produktów oraz ceny


 Product:

    Zmienić wszystko co tworzy Produkt by koszystało z metody wytwórczej
    Jak ma wyglądać sugerowanie produktów?
    convertToRecord()
    convertFromRecord() 
    produkty będą zapisywane jako .csv, ale w postaci .json
    skoro wywalenie klas, to trzeba będzie zmienić DBC (może?)

EventNoticication:

    Conversion to and from string

DatabaseConnector:

    Load all items from given category

    getFreeId na product
    przykładowy plik z produktem:
        microwave.csv
            {json string}
    Skoro inne niż csv, to może adapter uda sie wcisnąć?

    Zmieniono drzewo bazy produktów


User:

    orderHistory

Tworzenie zamówienia:
    
    Jeśli user nie ma uzupełnionego adresu w bazie, to przed złożeniem zamówienia musi je uzupełnić.


Wzorce projektowe:

a) kreacyjne\
&emsp;1) <del> singleton - kontakt/łącze z bazą danych lub/i configi użytkowników </del>\
&emsp;2) metoda wytwórcza - dodawanie produktów na stronę (przykładowo dodanie mikrofali wraz ze specyfikacją jako agd_kuchnia) i/lub sposób dostawy\
&emsp;3) <del> budowniczy - zbudowanie koszyka z zamówieniem </del>
    
b) strukturalne\
&emsp;1) Adapter - "przerzucenie" danych z bazy do kodu (Nie mam pomysłu gdzie go wrzucić)\
&emsp;2) Dekorator - opcjonalne dodatki do zamówień (np. przy zamówieniu książki zakładka)\
&emsp;3) Fasada - Interfejs użytkownika
    
c) czynościowe\
&emsp;1) Łańcuch zobowiązań - rozdzielenie użytkowników zwykłych od uprzywilejowanych\
&emsp;2) Obserwator - wysyłanie powiadomień email o promocjach, protuktach (wydarzeniach)\
&emsp;3) Mediator - zarządzanie informacjami na temat użytkownika

"volatile" a "synchronization"

Pożądane zmiany w danych można zapisać (zazwyczaj):\
&emsp;a) nowy rekord: bdc.saveToFile(Convertible convertible)\
&emsp;b) aktualizacja danych: dbc.updateRecord(Convertible convertible)
