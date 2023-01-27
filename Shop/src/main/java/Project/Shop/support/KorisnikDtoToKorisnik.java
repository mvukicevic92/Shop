package Project.Shop.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import Project.Shop.model.Korisnik;
import Project.Shop.service.KorisnikService;
import Project.Shop.web.dto.KorisnikDTO;

@Component
public class KorisnikDtoToKorisnik implements Converter<KorisnikDTO, Korisnik>{

	@Autowired
	private KorisnikService korisnikService;

	@Override
	public Korisnik convert(KorisnikDTO korisnikDTO) {
        Korisnik korisnik = null;
        if(korisnikDTO.getId() != null) {
            korisnik = korisnikService.findOne(korisnikDTO.getId()).get();
        }

        if(korisnik == null) {
            korisnik = new Korisnik();
        }

        korisnik.setKorisnickoIme(korisnikDTO.getKorisnickoIme());
        korisnik.seteMail(korisnikDTO.geteMail());
        korisnik.setIme(korisnikDTO.getIme());
        korisnik.setPrezime(korisnikDTO.getPrezime());

        return korisnik;
    }
}
