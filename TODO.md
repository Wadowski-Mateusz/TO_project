Zaimplementować convertFromRecord(int id) klasom, które są przechowywane w bazie. Metoda powinna znajdować rekord na podstawie id.
    
Przerobić Order/Cart na budowniczego
    
Konwertowanie eventNotification na jsona (i vice versa).
    
    {
        "id" : id,
        "message" : "message"
    }

Tam gdzie jest freeId należy w konstruktorze:

        - sprawdzić czy freeId >= 0
        - jesli tak to przypisac je do obiekty i zikrementowac
        - jesli nie to odczytac je z pliku, przypisać do atrybutu i zrobić jak wyżej

    
Tam gdzie w klasach jest id innych klas można przerobić na ich obielry i w odpowiednich miejscach używać getId().
    
DataConnector
    
Jak zapisac produkt do pliku, jezeli ma 2 kontenery?

Produkt powinien być dodawany do bazy.
Jeśli jest potrzebny, powinien być z niej wczytywany.

Wzorce.

Na pewno coś o czym zapomniałem
