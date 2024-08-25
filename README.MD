### Build Application
```bash
./mnvw clean build
```
### Run Application
Run the application you need to pass 'seed' or 'finance' or 'seedAndFinance' to define if you want to first feed the DB and then run the finance algorithm or both.
```bash
java -jar target/challenge-0.0.1-SNAPSHOT.jar seedAndFinance
```
### Test Application
```bash
./mvn clean test
```
## Guides
### Performance Test
To run performance Test run class FinanceServicePerf, that it will seed application in memory DB with 2000000 financed invoices and 20000 not financed invoice.
Time to run the test is around 10 seconds on my machine, most of the time is spent on seeding the DB and retrieve the data from the DB.

### Further explanation
The algorithm is implemented in Invoice class that has a method matchPurchaser that will return Optional<Pair<Purchaser, Long>
that represent the purchaser with the lowest rate and the rate itself. The finance method will use this info for setting
the purchaser of the invoice and it will use the rate to calculate the early payment amount of the invoice
saved as well in the DB in the invoice table.
It is assumed a Purchaser has only one rate for one Creditor, so the rate is unique for a pair of Purchaser and Creditor.
In seeding the invoice and purchaser it was not tested data with a purchaser with a long list of creditor considering
the performance of th algorithm to check if a creditor is in the list of the purchaser is implemented as a Map with O(1) complexity.
It was decided to not use a DTOs and mapping between DTOs and Entities to keep the code simple and easy to understand,
considering that the application is small and the entities are simple.