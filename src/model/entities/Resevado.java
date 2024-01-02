package model.entities;

import model.exceptions.DomainException;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.concurrent.TimeUnit;


public class Resevado {
    private Integer qtNumero;
    private Date checkIn;
    private Date confira;

    public static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public Resevado(Integer qtNumero, Date checkIn, Date confira) {
        if (!confira.after(checkIn)) {
            throw new DomainException("Check-out date must be after check-in date");
        }
        this.qtNumero = qtNumero;
        this.checkIn = checkIn;
        this.confira = confira;
    }

    public Integer getQtNumero() {
        return qtNumero;
    }

    public void setQtNumero(Integer qtNumero) {
        this.qtNumero = qtNumero;
    }

    public Date getCheckIn() {
        return checkIn;
    }

    public Date getConfira() {
        return confira;
    }

    public long duration() {
        long diff = confira.getTime()- checkIn.getTime();
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }

    public void updateDates(Date checkIn, Date confira) {
        Date now = new Date();
        if (checkIn.before(now) | confira.before(now)) {
            throw new DomainException("Reservation dates for update must be future dates");
        }
        if (!confira.after(checkIn)) {
            throw new DomainException("Check-out date must be after check-in date");
        }
        this.checkIn = checkIn;
        this.confira = confira;
    }

    @Override
    public String toString() {
        return "Quarto" + qtNumero +
                ", Data Atual" + sdf.format(checkIn) +
                ", Data para agendamento" + sdf.format(confira) +
                ", " +
                duration() + "noites";
    }
}
