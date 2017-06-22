# Design Pattern Katas für Java #

## Worum handelt es sich hier? ##

### Design Patterns ###

Bei [**Design Patterns**](https://de.wikipedia.org/wiki/Entwurfsmuster) (deutsch _Entwurfsmuster_) handelt es sich laut 
Wikipedia um bewährte Lösungsschablonen für wiederkehrende Entwurfsprobleme in der Softwareentwicklung.

**Literaturempfehlungen**:
* Einen guten und leicht verständlichen Einstieg in das Thema Design Patterns (sowie objektorientierte 
Design-Prinzipien allgemein) gibt das Buch _"Entwurfsmuster von Kopf bis Fuß"_ von Eric Freeman und Elisabeth Robson, 
erschienen im O'Reilly-Verlag. 
* Eine abstraktere Behandlung findet sich im Klassiker der "Gang of Four",
durch den Design Patterns bekannt geworden sind: _"Entwurfsmuster: Elemente wiederverwendbarer objektorientierter 
Software"_ von Erich Gamma, Richard Helm, Ralph Johnson, John Vlissides (erschienen im Addison-Wesley-Verlag).

### Code Katas ###

Unter einem **Code Kata** versteht man eine kleine, abgeschlossene Übung, mit der Softwareentwickler ihre Fertigkeiten 
trainieren können. Der Begriff stammt aus der [asiatischen Kampfkunst](https://de.wikipedia.org/wiki/Form_(Kampfkunst)). 
Ausführliche Informationen zu Code Katas finden sich [hier](http://codekata.com/). 

### Katas für Design Patterns ###

Ganz nach dem Motto _"learning by doing"_ finden sich hier Code Katas für verschiedene gängige Design Patterns.
Damit kann die Implementierung der Design Patterns in Java ganz praktisch geübt werden.

## Wie geht das? ##

Ganz einfach: 
* Für jedes Design Pattern gibt es ein entsprechendes Package mit vorgefertigten Klassengerüsten, sowie einen JUnit-Test.
* Die TODOs in den Gerüst-Klassen müssen durch Implementierung des jeweiligen Design Patterns so erledigt werden, 
dass alle JUnit-Tests erfolgreich durchlaufen.

## Voraussetzungen ##

Die Katas werden als Maven-Projekt zur Verfügung gestellt, das mit der "Maven-Projekt importieren"-Funktion 
(oder ähnlich lautend) in die jeweils bevorzugte IDE importiert werden kann.

| Software    | Version                    |
|-------------|----------------------------| 
| Java        | Version 8 (1.8) oder höher |
| Maven       |3.x                         | 

## Die Katas ##

### Factory Method (Fabrikmethode) ###

**Typ**: Erzeugungsmuster (_creational pattern_)

Dieses Pattern wird verwendet, wenn eine Klasse den konkreten Typ der von ihr zu erzeugenden Objekte nicht kennen kann 
oder soll. Die Fabrikmethode liefert Objekte vom Typ eines Interfaces oder einer abstrakten Klasse zurück. Damit wird
eine lose Kopplung zwischen dem aufrufenden und den erzeugten Objekten erzielt.

### Factory Method Kata ###

**Package:** `de.doubleslash.kata.designpattern.factory` \
**JUnit-Test:** `LoggerFactoryTest`

In diesem Kata soll eine `LoggerFactory` implementiert werden, die je nach Konfiguration eine Unterschiedliche
Logger-Klasse instanziert und zurückgeliefert werden.

**UML**

![alt](doc/images/factory_method.png) 

| Logger-Klasse | Funktion                | Bemerkung |
|---------------|-------------------------|-----------|
| FileLogger    | Loggt in eine Datei     | |
| DbLogger      | Loggt in eine Datenbank | |
| SilentLogger  | Macht gar nichts        | |
