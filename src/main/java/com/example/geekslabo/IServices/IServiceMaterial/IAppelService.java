package com.example.geekslabo.IServices.IServiceMaterial;

import com.example.geekslabo.Entities.Appel;

import java.util.List;

public interface IAppelService {
    List<Appel> findAllAppels();
    Appel findAppelById(Integer id);
    Appel createAppel(Appel appel);
    Appel updateAppel(Integer id, Appel appelDetails);
    void deleteAppel(Integer id);
}
