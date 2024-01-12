package fedeCapiz.VemerdiSp;

import com.github.javafaker.Faker;
import fedeCapiz.VemerdiSp.DAO.BaseService;
import fedeCapiz.VemerdiSp.DAO.PalazzoService;
import fedeCapiz.VemerdiSp.DAO.PersonaService;
import fedeCapiz.VemerdiSp.DAO.PrenotazioneService;
import fedeCapiz.VemerdiSp.Entities.Base;
import fedeCapiz.VemerdiSp.Entities.Palazzo;
import fedeCapiz.VemerdiSp.Entities.Persona;
import fedeCapiz.VemerdiSp.Entities.Prenotazione;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Component
public class Runner implements CommandLineRunner {
    @Autowired
    PalazzoService palazzoService;
    @Autowired
    BaseService baseService;
    @Autowired
    PrenotazioneService prenotazioneService;
    @Autowired
    PersonaService personaService;
    private final Faker faker = new Faker();
    @Override
    public void run(String... args) {
        System.out.println("ajeje saluta dal runner, CREIAMO OGGETI CON I FAKER E SALVIAMOLI");
        Palazzo palazzoCreato = new Palazzo(faker.lorem().word(), faker.address().streetName(), faker.address().city());
        palazzoService.save(palazzoCreato);

        Persona personaCreata = new Persona(faker.name().fullName(), faker.name().username(), faker.internet().emailAddress());
        personaService.save(personaCreata);

        Base baseCreata = new Base(faker.lorem().word(), EnumLOCAZIONE.OPENSPACE, faker.number().randomDigit(), palazzoCreato, true);
        baseService.save(baseCreata);

        Prenotazione prenotazione = new Prenotazione(baseCreata, personaCreata);
        prenotazioneService.save(prenotazione);

        System.out.println("STAMPIAMO TUTTI I DATI NEL DB");

        List<Persona> persone = personaService.findAll();
        System.out.println("ecco le persone nel database");
        for (Persona p : persone) {
            System.out.println("id " + p.getId() + ", nome " + p.getNomeVero() + ", username " + p.getUsername() + ", mail " + p.getMail());
        }

        List<Palazzo> palazzi = palazzoService.findAll();
        System.out.println("ecco i palazzi nel database");
        for (Palazzo palazzo : palazzi) {
            System.out.println("id" + palazzo.getId() + ",indirizzo: via " + palazzo.getIndirizzo()  + ",città " + palazzo.getCitta());
        }
        List<Base> basi = baseService.findAll();
        System.out.println("ecco le basi nel database:");
        for (Base base : basi) {
            System.out.println("id " + base.getId() + ", desc " + base.getDescrizione() + ", locazione " + base.getEnumLOCAZIONE() +  ", palazzo " + base.getPalazzo().getNome() + ", è disponibile " + base.isLibero());
        }

        List<Prenotazione> prenotazioni = prenotazioneService.findAll();
        System.out.println("ecco le prenotazioni nel database:");
        for (Prenotazione prenot : prenotazioni) {
            System.out.println("id " + prenot.getId() + ", base " + prenot.getBasePrenotazione() + ", prenotato da  " + prenot.getPersona());
        }

        final Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("scegli un'opzione , dovrai inserire l'id");
            System.out.println("A  trova prenotazione");
            System.out.println("B  trova base");
            System.out.println("C  trova persona");
            System.out.println("D  trova palazzo");
            System.out.println("F  esci dal ciclo");

            String scelta = scanner.nextLine();

            switch (scelta.toUpperCase()) {
                case "A":
                    System.out.println("inserisci l'ID della Prenotazione");
                    long prenotazioneId = Long.parseLong(scanner.nextLine());
                    Optional<Prenotazione> prenotazioneById = prenotazioneService.findById(prenotazioneId);
                    if (prenotazioneById.isPresent()) {
                        System.out.println("prenotazione trovata");
                    } else {
                        System.out.println("prenotazione non trovata");
                    }
                    break;
                case "B":
                    System.out.println("inserisci l'ID della Base");
                    long baseId = Long.parseLong(scanner.nextLine());
                    Optional<Base> base = baseService.findById(baseId);
                    if (base.isPresent()) {
                        System.out.println("base trovata");
                    } else {
                        System.out.println("base non trovata");
                    }
                    break;
                case "C":
                    System.out.println("inserisci l'ID della Persona");
                    long personaId = Long.parseLong(scanner.nextLine());
                    Optional<Persona> persona = personaService.findById(personaId);
                    if (persona.isPresent()) {
                        System.out.println("persona trovata");
                    } else {
                        System.out.println("persona non trovata");
                    }
                    break;
                case "D":
                    System.out.println("inserisci l'ID del Palazzo");
                    long palazzoId = Long.parseLong(scanner.nextLine());
                    Optional<Palazzo> palazzo = palazzoService.findById(palazzoId);
                    if (palazzo.isPresent()) {
                        System.out.println("palazzo trovato");
                    } else {
                        System.out.println("palazzo non trovato");
                    }
                    break;
                case "F":
                    System.out.println("uscita dal ciclo");
                    break;
                default:
                    System.out.println("non valida");
            }
        } while (true);
    }
}
