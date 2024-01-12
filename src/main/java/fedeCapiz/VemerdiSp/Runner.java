package fedeCapiz.VemerdiSp;

import com.github.javafaker.Faker;
import fedeCapiz.VemerdiSp.DAO.*;
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
import java.util.UUID;

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
    @Autowired
    PersonaDAO personaDAO;
    @Autowired
    PrenotazioneDAO prenotazioneDAO;
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
            System.out.println("F  trova da email");
            System.out.println("G  trova da username");
            System.out.println("H  esci dal ciclo");

            String scelta = scanner.nextLine();

            switch (scelta.toUpperCase()) {
                case "A":
                    System.out.println("Inserisci l'ID della Prenotazione");
                    try {
                        UUID prenotazioneId = UUID.fromString(scanner.next());
                        Optional<Prenotazione> prenotazione1 = prenotazioneService.findById(prenotazioneId);
                        if (prenotazione1.isPresent()) {
                            System.out.println("Prenotazione trovata");
                        } else {
                            System.out.println("Prenotazione non trovata");
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println("Formato UUID non valido");
                    }
                    break;

                case "B":
                    System.out.println("Inserisci l'ID della Base");
                    try {
                        UUID baseId = UUID.fromString(scanner.next());
                        Optional<Base> base = baseService.findById(baseId);
                        if (base.isPresent()) {
                            System.out.println("Base trovata");
                        } else {
                            System.out.println("Base non trovata");
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println("Formato UUID non valido");
                    }
                    break;

                case "C":
                    System.out.println("Inserisci l'ID della Persona");
                    try {
                        UUID personaId = UUID.fromString(scanner.next());
                        Optional<Persona> persona = personaService.findById(personaId);
                        if (persona.isPresent()) {
                            System.out.println("Persona trovata");
                        } else {
                            System.out.println("Persona non trovata");
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println("Formato UUID non valido");
                    }
                    break;
                case "D":
                    System.out.println("Inserisci l'UUID del Palazzo");
                    String palazzoIdString = scanner.next();
                    try {
                        UUID palazzoId = UUID.fromString(palazzoIdString);
                        Optional<Palazzo> palazzo = palazzoService.findById(palazzoId);
                        if (palazzo.isPresent()) {
                            System.out.println("Palazzo trovato");
                        } else {
                            System.out.println("Palazzo non trovato");
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println("Formato UUID non valido");
                    }
                    break;

                case "F":
                    System.out.println("ecco la prima query cust , metti l'email COMPLETA della persona");
                    String personaEmail = scanner.nextLine();
                    Persona personaByEmail = personaDAO.findByEmail(personaEmail);
                    if (personaByEmail != null) {
                        System.out.println("email della persona trovata " + personaByEmail);
                    } else {
                        System.out.println("Persona non trovata");
                    }
                    break;
                case "G":
                    System.out.println("Ecco la SEC query cust, inserisci parte dello username");
                    String usernameCercato = scanner.nextLine().toLowerCase();
                    List<Persona> usersByQuery = personaDAO.findByPartialUsername(usernameCercato);
                    if (!usersByQuery.isEmpty()) {
                        System.out.println("Utenti trovati:");
                        for (Persona user : usersByQuery) {
                            System.out.println("id " + user.getId() + ", nome " + user.getNomeVero() + ", username " + user.getUsername() + ", mail " + user.getMail());
                        }
                    } else {
                        System.out.println("Nessun utente trovato");
                    }
                    break;

                case "H":
                    System.out.println("uscita dal ciclo");
                    break;
                default:
                    System.out.println("non valida");
            }
        } while (true);
    }
}
