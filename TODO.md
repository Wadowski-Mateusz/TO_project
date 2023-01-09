Order:
    
    save to file when user confirm order (with shipping and payment)

Cart + CartBuilder

    Cart po zbudowaniu zamówienia powinien być wyczyszczony z produktów oraz ceny


 Product:

    convertToRecord() - How to convert, if product is using 2 containers
    convertFromRecord() 

EventNoticication:

    Conversion to and from string

DatabaseConnector:

    Load all items from given category

User:

    orderHistory

Tworzenie zamówienia:
    
    Jeśli user nie ma uzupełnionego adresu w bazie, to przed złożeniem zamówienia musi je uzupełnić.


Wzorce.

"volatile" a "synchronization"

Pożądane zmiany w danych można zapisać
    
    dbc.updateRecord(Convertible convertible)
