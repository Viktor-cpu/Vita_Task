package vitaSoft.testTask.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "vitaSoft", produces = "application/json")
@Slf4j
public class VitaController {

    Map<Integer, String> monthsMap = new HashMap<Integer, String>() {{
        put(1, "Я-Н-В-А-Р-Ь");
        put(2, "Ф-Е-В-Р-А-Л-Ь");
        put(3, "М-А-Р-Т");
        put(4, "А-П-Р-Е-Л-Ь");
        put(5, "М-А-Й");
        put(6, "И-Ю-Н-Ь");
        put(7, "И-Ю-Л-Ь");
        put(8, "А-В-Г-У-С-Т");
        put(9, "С-Е-Н-Т-Я-Б-Р-Ь");
        put(10, "О-К-Т-Я-Б-Р-Ь");
        put(11, "Н-О-Я-Б-Р-Ь");
        put(12, "Д-Е-К-А-Б-Р-Ь");
    }};

    @GetMapping("{id}")
    public String months(@PathVariable("id") Integer id) {
        log.info("@GetMapping");

        String month = monthsMap.get(id);

        if (Objects.isNull(month)) {
            log.warn("INCORRECT INPUT DATA");
            return "INCORRECT INPUT DATA";
        }

        return month;
    }

    @PostMapping(consumes = "application/json")
    public List<String> sortStrings(@RequestBody List<String> requestList) {
        log.info("@PostMapping");

        return requestList.stream()
                .sorted((s1, s2) -> (s1.length() == s2.length()) ? s1.compareTo(s2) : s1.length() - s2.length())
                .map(s -> "(" + s.length() + "):" + s)
                .collect(Collectors.toList());
    }

}
