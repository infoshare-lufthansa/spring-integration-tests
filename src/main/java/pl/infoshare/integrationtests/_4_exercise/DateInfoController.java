package pl.infoshare.integrationtests._4_exercise;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.infoshare.integrationtests._4_exercise.model.AddNamesRequest;
import pl.infoshare.integrationtests._4_exercise.model.DateInfo;
import pl.infoshare.integrationtests._4_exercise.names.NameDayCreateService;

@RestController
public class DateInfoController {

    private final DateInfoService dateInfoService;
    private final NameDayCreateService nameDayCreateService;

    public DateInfoController(DateInfoService dateInfoService, NameDayCreateService nameDayCreateService) {
        this.dateInfoService = dateInfoService;
        this.nameDayCreateService = nameDayCreateService;
    }

    @GetMapping("/date-info/{date}")
    public DateInfo getDateInfo(@PathVariable String date) {
        return dateInfoService.getDateInfo(date);
    }

    @PostMapping("/date-info/{date}/day-names")
    @ResponseStatus(HttpStatus.CREATED)
    public void addNames(@PathVariable String date, @RequestBody AddNamesRequest addNamesRequest) {
        nameDayCreateService.addNamesToDate(date, addNamesRequest);
    }
}
