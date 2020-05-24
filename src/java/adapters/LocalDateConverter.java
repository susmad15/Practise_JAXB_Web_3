package adapters;

import java.sql.Date;
import java.time.LocalDate;
import javax.persistence.AttributeConverter;


public class LocalDateConverter implements AttributeConverter<LocalDate, Date>{

    @Override
    public Date convertToDatabaseColumn(LocalDate x) {
        return Date.valueOf(x);
    }

    @Override
    public LocalDate convertToEntityAttribute(Date y) {
        return y.toLocalDate();
    }
    
}
