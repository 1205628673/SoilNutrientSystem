package com.jlau.algsystem.service;

import com.jlau.algsystem.entity.Announce;
import com.jlau.algsystem.repository.AnnounceRepository;
import com.jlau.algsystem.utils.CodeUtil;
import com.jlau.algsystem.utils.Result;
import org.apache.poi.ss.formula.functions.Code;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by cxr1205628673 on 2020/5/9.
 */
@Service
@Transactional
public class AnnounceService {
    @Autowired
    AnnounceRepository announceRepository;

    public Result getAll() {
        List<Announce> announces = announceRepository.findAll();
        return new Result(CodeUtil.SUCESS.getCode(),CodeUtil.SUCESS.getMessage(),announces);
    }
    public Result getAllPyPage(int page) {
        Pageable pageable = PageRequest.of(page-1,20);
        Page announcePage = announceRepository.findAll(pageable);
        return new Result(CodeUtil.SUCESS.getCode(),CodeUtil.SUCESS.getMessage(),announcePage);
    }
    public Result getOneById(int id){
        Announce announce = announceRepository.findById(id).get();
        String[] lines = announce.getContent().split("\\n");
        List<String> newLines = new ArrayList<>();
        for (String line:lines) {
            newLines.add("<p>"+line+"</p>");
        }
        String content = String.join("",newLines);
        announce.setContent(content);
        return new Result(CodeUtil.SUCESS.getCode(),CodeUtil.SUCESS.getMessage(),announce);
    }
    public Result save(Announce announce) {
        announce.setTime(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        announceRepository.save(announce);
        return new Result(CodeUtil.SUCESS.getCode(),CodeUtil.SUCESS.getMessage(),"success");
    }
    public Result deleteById(int id){
        announceRepository.deleteById(id);
        return new Result(CodeUtil.SUCESS.getCode(),CodeUtil.SUCESS.getMessage(),"success");
    }
}
