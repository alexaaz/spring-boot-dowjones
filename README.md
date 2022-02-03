
## 1. How to start
```bash
$ git clone [https://github.com/alexaaz/spring-boot-dowjones.git](https://github.com/alexaaz/spring-boot-dowjones.git)

Compile/run it in an IDE such as Eclipse, by running: StartDowJonesApplication

## ( or 
## $ mvn spring-boot:run
## )


## Test using curl, command line: 
	curl localhost:8080/dowjones
	
	curl localhost:8080/dowjones/stock/AA
	
	curl -F file=@"C:\alex\github\spring-boot-dowjones\dow_jones_index/dow_jones_index_frgm.data" http://localhost:8080/dowjones/upload/
	
	curl -H "Content-Type: application/json" -X POST -d {\"quarter\":\"1\",\"stock\":\"BAC\",\"date\":\"2011-02-18\",\"open\":\"14.77\",\"high\":\"14.95\",\"low\":\"14.69\",\"close\":\"14.75\",\"volume\":\"458423511\",\"percentChangePrice\":\"-0.13541\",\"percentChangeVolumeOverLastWeek\":\"-38.58744486\",\"previousWeeksVolume\":\"746465458\",\"nextWeeksOpen\":\"14.38\",\"nextWeeksClose\":\"14.20\",\"percentChangeNextWeeksPrice\":\"-1.25174\",\"daysToNextDividend\":\"12\",\"percentReturnNextDividend\":\"0.0677966\"} http://localhost:8080/dowjones/add/
	 
## Test using Postman:

	https://www.getpostman.com/collections/03add783979c75fa8384
	or 
	see dowjones.postman_collection.json

## TODO: swagger API
