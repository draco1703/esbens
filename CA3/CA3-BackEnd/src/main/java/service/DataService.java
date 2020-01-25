/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import com.google.gson.Gson;
import entity.SWPersonDTO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Esben
 */
public class DataService {

    Gson gson = new Gson();
    ExternalData ext = new ExternalData();

    public SWPersonDTO getPerson(int id) {
        String str = "";
        try {
            str = ext.getSwappiDataSpecific("people", id);
        } catch (IOException ex) {
        }
        return gson.fromJson(str, SWPersonDTO.class);
    }

    public List<SWPersonDTO> getPeople(int pageNum) {
        String str = "";
        try {
            str = ext.getSwappiDataPage("people", pageNum);
        } catch (IOException ex) {
        }
        List<SWPersonDTO> ppl = new ArrayList();
        ppl.add(gson.fromJson(str, SWPersonDTO.class));
        
        return null;
    }
}
