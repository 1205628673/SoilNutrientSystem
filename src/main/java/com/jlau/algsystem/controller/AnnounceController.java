package com.jlau.algsystem.controller;

import com.jlau.algsystem.entity.Announce;
import com.jlau.algsystem.service.AnnounceService;
import com.jlau.algsystem.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by cxr1205628673 on 2020/5/9.
 */
@RestController
public class AnnounceController {
    @Autowired
    private AnnounceService announceService;

    @GetMapping("/user/announce/page/{page}")
    public Object findAnnounceByPage(@PathVariable int page) {
        Result result = announceService.getAllPyPage(page);
        return result;
    }
    @PutMapping("/admin/announce/save")
    public Object saveAnnounce(@RequestBody  Announce announce) {
        Result result = announceService.save(announce);
        return result;
    }
    @DeleteMapping("/admin/announce/delete/{id}")
    public Object deleteAnnounce(@PathVariable int id) {
        Result result = announceService.deleteById(id);
        return result;
    }
    @GetMapping("/user/announce/all")
    public Object findAllAnnounce() {
        Result result = announceService.getAll();
        return result;
    }
    @GetMapping("/user/announce/find/{id}")
    public Object findAnnounceById(@PathVariable int id){
        Result result = announceService.getOneById(id);
        return result;
    }
}
