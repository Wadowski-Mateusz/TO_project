Przerobić Order/Cart na budowniczego
  
??  
Konwertowanie eventNotification na jsona (i vice versa).
    
    {
        "id" : id,
        "message" : "message"
    }
Czy jednak csv? Wiadomosc moze zawierać przecinki

??

Tam gdzie jest freeId należy w konstruktorze:

        - sprawdzić czy freeId > -1
        - jesli tak to przypisac je do obiekty i zikrementowac
        - jesli nie to odczytac je z pliku, przypisać do atrybutu i zrobić jak wyżej

    
Tam gdzie w klasach jest id innych klas można przerobić na ich obieky i w odpowiednich miejscach używać getId().
    
DatabaseConnector

Przetestować DatabaseConnector.saveToFile()
    
Jak zapisac produkt do pliku, jezeli ma 2 kontenery?

Produkt powinien być dodawany do bazy.
Jeśli jest potrzebny, powinien być z niej wczytywany.

Wzorce.

"volatile" a "synchronization"


Na pewno coś o czym zapomniałem
