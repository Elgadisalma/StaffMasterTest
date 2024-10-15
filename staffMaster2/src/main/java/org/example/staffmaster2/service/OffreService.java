package org.example.staffmaster2.service;

import org.example.staffmaster2.dao.OffreDao;
import org.example.staffmaster2.entity.Offre;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class OffreService {
    private OffreDao offreDao;

    public OffreService() {
        this.offreDao = new OffreDao();
    }

    public List<Offre> getAllOffres() {
        return offreDao.getOffres();
    }

    public Offre getOffreById(long id) {
        return offreDao.getOffreById(id);
    }

    public void addOffre(Offre offre) {
        offreDao.addOffre(offre);
    }

    public Offre createOffreFromRequest(String title, String description, String dateValiditeStr, String dateFinStr) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dateValidite = dateFormat.parse(dateValiditeStr);
        Date dateFin = dateFormat.parse(dateFinStr);
        Boolean status = true;

        return new Offre(null, title, description, status, dateValidite, dateFin);
    }
}