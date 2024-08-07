package ru.practicum.ewm.event.validation;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDateTime;

public class DateMustBeAfterValidator implements ConstraintValidator<DateMustBeAfter, LocalDateTime> {

    private LocalDateTime date;

    public void initialize(DateMustBeAfter annotation) {
        date = LocalDateTime.now().plusHours(annotation.hours());
    }

    public boolean isValid(LocalDateTime value, ConstraintValidatorContext context) {
        boolean valid = true;
        if (value != null) {
            if (!value.isAfter(date) && !value.isEqual(date)) {
                valid = false;
            }
        }
        return valid;
    }
}
