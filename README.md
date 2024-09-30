# Expense-Tracker

Expense tracker ir vienkārša izdevumu reģistrēšanas sistēma.


Projekta apraksts

- Expense-Tracker vienkāršu izdevumu reģistrēšanas sistēma, Kuras realizēšanai tika izmantoti Java programmēšanas valoda,  MySQL datu bāze un Swing grafisko lietotāja saskarne. 

1. Programmas galvenās funkcijas:

- Izdevumu reģistrēšana (datums, summa, apraksts)
- Izdevumu apskatīšana 
- Izdevumu dzesēšana no datubāzes


2. Izdevumu apskatīšana

 Izdevumi tika integrētī Swing JTable, ar ko tos:

- Viegli var apskatīt visus reģistrētos izdevumus
- Nokārtot izdevumus pēc datuma vai summas vai pec apraksta.

3. Programma automatisķi saskaita visus ievadītūs sitēmā izdevumus 



Tehniskāis apraksts:

- Tika izmantota Java aprogrammēšanas valoda 
- Tika izmantot MySQL datu bāze datu glābašanai
- Tika izmantot Java MySQL Connector datu bāzes savienojumam
- Tika implementēts vienkārša grafisko lietotāja saskarne, izmantojot Swing
- Tika implementēti 3 klases (DatabaseManager, DataBaseFunct, TestGUI)

4. Programmas izmantošanai jāievada Databasemanager savu local adresi savienojumam ar DataBase, login un paroli:
   
   //data for DB connection
    private static final String DB_URL = "jdbc:mysql://localhost:3306/izdevumu_gramata";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "manaparole";



