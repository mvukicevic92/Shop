package Project.Shop.service.impl;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import Project.Shop.enumeration.KorisnickaUloga;
import Project.Shop.model.Korisnik;
import Project.Shop.repository.KorisnikRepository;
import Project.Shop.service.KorisnikService;
import Project.Shop.web.dto.KorisnikPromenaLozinkeDto;

@Service
public class JpaKorisnikService implements KorisnikService{

	@Autowired
	private KorisnikRepository korisnikRepository;

	@Override
	public Optional<Korisnik> findOne(Long id) {
		return korisnikRepository.findById(id);
	}

	@Override
	public List<Korisnik> findAll() {
		return korisnikRepository.findAll();
	}

	@Override
	public Page<Korisnik> findAll(int brojStranice) {
		// TODO Auto-generated method stub
		return korisnikRepository.findAll(PageRequest.of(brojStranice, 10));
	}

	@Override
	public Korisnik save(Korisnik korisnik) {
		korisnik.setUloga(KorisnickaUloga.KORISNIK);
		return korisnikRepository.save(korisnik);
	}

	@Override
	public void delete(Long id) {
		korisnikRepository.deleteById(id);
		
	}

	@Override
	public Optional<Korisnik> findbyKorisnickoIme(String korisnickoIme) {
		// TODO Auto-generated method stub
		return korisnikRepository.findFirstByKorisnickoIme(korisnickoIme);
	}

	@Override
	public boolean changePassword(Long id, KorisnikPromenaLozinkeDto korisnikPromenaLozinkeDto) {
        Optional<Korisnik> rezultat = korisnikRepository.findById(id);

        if(!rezultat.isPresent()) {
            throw new EntityNotFoundException();
        }

        Korisnik korisnik = rezultat.get();

        boolean passwordsMatch = BCrypt.checkpw(korisnikPromenaLozinkeDto.getStaraLozinka(), korisnik.getLozinka());
        if(!korisnik.getKorisnickoIme().equals(korisnikPromenaLozinkeDto.getKorisnickoIme()) || !passwordsMatch){
            return false;
        }

        korisnik.setLozinka(korisnikPromenaLozinkeDto.getLozinka());

        korisnikRepository.save(korisnik);
		return true;
	}
}
