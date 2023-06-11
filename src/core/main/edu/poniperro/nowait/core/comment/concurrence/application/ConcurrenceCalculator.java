package edu.poniperro.nowait.core.comment.concurrence.application;

import edu.poniperro.nowait.core.comment.comment.domain.Comment;
import edu.poniperro.nowait.core.comment.comment.domain.CommentRepository;
import edu.poniperro.nowait.core.comment.concurrence.ConcurrencePerDayResponse;
import edu.poniperro.nowait.core.comment.concurrence.ConcurrenceResponse;
import edu.poniperro.nowait.shared.domain.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public final class ConcurrenceCalculator {

    private final CommentRepository repository;

    public ConcurrenceCalculator(CommentRepository repository) {
        this.repository = repository;
    }

    public ConcurrenceResponse calculate(String placeId) {
        int average = calculateConcurrenceLastHour(placeId);
        List<Integer> today = calculateConcurrenceToday(placeId);
        List<Integer> week = calculateConcurrenceWeek(placeId);
        return new ConcurrenceResponse(average, today, week);
    }

    public int calculateConcurrenceLastHour(String placeId) {
        List<Comment> commentsLastHour = new ArrayList<Comment>();
        LocalDateTime currentDateTime = LocalDateTime.now();

        for (Comment comment : repository.searchByPlaceId(placeId)) {
            LocalDateTime commentDateTime = LocalDateTime.parse(comment.getCreationDate());
            if (commentDateTime.isAfter(currentDateTime.minusHours(1))) {
                commentsLastHour.add(comment);
            }
        }
        int total = 0;
        int count = 0;
        for (Comment comment : commentsLastHour) {
            total += comment.getQuantifiableElement();
            count++;
        }
        return count > 0 ? total / count : 0;
    }

    public List<Integer> calculateConcurrenceToday(String placeId) {
        // comments last 24 hours
        List<Comment> commentsLastDay = new ArrayList<Comment>();
        LocalDateTime currentDateTime = LocalDateTime.now();
        for (Comment comment : repository.searchByPlaceId(placeId)) {
            LocalDateTime commentDateTime = LocalDateTime.parse(comment.getCreationDate());
            if (commentDateTime.isAfter(currentDateTime.minusHours(24))) { // Verifica si el comentario está dentro de las últimas 24 horas
                commentsLastDay.add(comment);
            }
        }

        List<Integer> hourlyAverages = new ArrayList<>(); // Almacena las medias por cada hora en las últimas 24 horas
        LocalDateTime hourIterator = currentDateTime.minusHours(1); // Inicia el iterador de horas en la hora actual menos una hora
        for (int i = 0; i < 24; i++) {
            int total = 0;
            int count = 0;
            for (Comment comment : commentsLastDay) {
                LocalDateTime commentDateTime = LocalDateTime.parse(comment.getCreationDate());
                if (commentDateTime.isAfter(hourIterator) && commentDateTime.isBefore(hourIterator.plusHours(1))) { // Verifica si el comentario está dentro de la hora actual
                    total += comment.getQuantifiableElement();
                    count++;
                }
            }
            int hourlyAverage = count > 0 ? total / count : 0; // Calcula la media para esa hora (entero aproximado)
            hourlyAverages.add(hourlyAverage);
            hourIterator = hourIterator.minusHours(1); // Retrocede una hora en el iterador
        }

        return hourlyAverages;
    }

    public List<Integer> calculateConcurrenceWeek(String placeId) {
        // comments last 7 days
        List<Comment> commentsLastWeek = new ArrayList<Comment>();
        LocalDateTime currentDateTime = LocalDateTime.now();
        for (Comment comment : repository.searchByPlaceId(placeId)) {
            LocalDateTime commentDateTime = LocalDateTime.parse(comment.getCreationDate());
            if (commentDateTime.isAfter(currentDateTime.minusDays(7))) {
                commentsLastWeek.add(comment);
            }
        }

        List<Integer> dailyAverages = new ArrayList<>();
        LocalDateTime dayIterator = currentDateTime.minusDays(1);
        for (int i = 0; i < 7; i++) {
            int total = 0;
            int count = 0;
            for (Comment comment : commentsLastWeek) {
                LocalDateTime commentDateTime = LocalDateTime.parse(comment.getCreationDate());
                if (commentDateTime.isAfter(dayIterator) && commentDateTime.isBefore(dayIterator.plusDays(1))) {
                    total += comment.getQuantifiableElement();
                    count++;
                }
            }
            int dailyAverage = count > 0 ? total / count : 0;
            dailyAverages.add(dailyAverage);
            dayIterator = dayIterator.minusDays(1);
        }

        return dailyAverages;
    }

    public ConcurrencePerDayResponse calculateConcurrencePerDay(String placeId, int day) {
        List<Comment> commentsPerDay = new ArrayList<>();
        LocalDateTime currentDateTime = LocalDateTime.now().minusDays(day);
        for (Comment comment : repository.searchByPlaceId(placeId)) {
            LocalDateTime commentDateTime = LocalDateTime.parse(comment.getCreationDate());
            if (commentDateTime.toLocalDate().isEqual(currentDateTime.toLocalDate())) {
                commentsPerDay.add(comment); // Agrega el comentario si es del día indicado
            }
        }

        List<Integer> hourlyAverages = new ArrayList<>();
        LocalDateTime hourIterator = currentDateTime.withHour(0).withMinute(0).withSecond(0); // Inicia el iterador de horas en la hora 00:00:00
        for (int i = 0; i < 24; i++) {
            int total = 0;
            int count = 0;
            for (Comment comment : commentsPerDay) {
                LocalDateTime commentDateTime = LocalDateTime.parse(comment.getCreationDate());
                if (commentDateTime.isAfter(hourIterator) && commentDateTime.isBefore(hourIterator.plusHours(1))) { // Verifica si el comentario está dentro de la hora
                    total += comment.getQuantifiableElement();
                    count++;
                }
            }
            int hourlyAverage = count > 0 ? total / count : 0;
            hourlyAverages.add(hourlyAverage);
            hourIterator = hourIterator.plusHours(1);
        }

        return new ConcurrencePerDayResponse(hourlyAverages);
    }


}
