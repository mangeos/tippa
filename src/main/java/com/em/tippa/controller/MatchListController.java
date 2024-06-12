package com.em.tippa.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.em.tippa.dto.MatchListDto;
import com.em.tippa.models.MatchList;
import com.em.tippa.services.MatchListService;

@Controller
public class MatchListController {
    private MatchListService matchListService;

    @Autowired
    public MatchListController(MatchListService matchListService) {
        this.matchListService = matchListService;
    }

    @GetMapping("/match-list")
    public String listMatchList(Model model) {
        List<MatchListDto> matchList = matchListService.findAllMatches();
        // Set the current date as the match date if needed

        List<String> scores = new ArrayList<>();
        for (int i = 0; i <= 10; i++) {
            for (int j = 0; j <= 10; j++) {
                scores.add(i + "-" + j);
            }
        }
        model.addAttribute("scores", scores);

        // model.addAttribute("test", test);
        model.addAttribute("matchList", matchList);
        // skapar en tom matchlist som jag anvander till post request
        model.addAttribute("match", new MatchList());
        return "match-list";
    }

    @GetMapping("/home")
    public String home(Model model) {
        List<MatchListDto> matchList = matchListService.findAllMatches();
        // Set the current date as the match date if needed

        List<String> scores = new ArrayList<>();
        for (int i = 0; i <= 10; i++) {
            for (int j = 0; j <= 10; j++) {
                scores.add(i + "-" + j);
            }
        }
        model.addAttribute("scores", scores);

        // model.addAttribute("test", test);
        model.addAttribute("matchList", matchList);
        // skapar en tom matchlist som jag anvander till post request
        model.addAttribute("match", new MatchList());
        return "home";
    }

    @PostMapping("/match-list")
    public String saveNewMatch(@ModelAttribute("match") MatchList matchList) {
        // Hantera formulärdata här (ex. spara till databas)
        matchListService.saveMatch(matchList);
        return "redirect:/match-list";
    }

    @PostMapping("/result")
    @ResponseBody
    public Map<String, String> result(@RequestBody Map<String, String> json) {
        // Hantera formulärdata här (ex. spara till databas)
        MatchList tempMatchList = matchListService.findMatchById(Long.parseLong(json.get("matchId")));

        // temp matchlist
        // MatchList tempMatchList = new MatchList(tempMatchListDto.getTeam1());
        // matchListService.saveMatch(matchList);

        MatchList tempMatchList2 = new MatchList(tempMatchList.getMatchId(), tempMatchList.getTeam1(),
                tempMatchList.getTeam2(), tempMatchList.getTvChannel(), tempMatchList.getMatchTime(),
                tempMatchList.getMatchDate(),
                json.get("matchResult"));

        matchListService.saveMatch(tempMatchList2);
        System.out.println(tempMatchList2.getResult());
        // Create a response map
        Map<String, String> response = new HashMap<>();
        response.put("status", "success");
        response.put("message", "Data received and processed");
        return response;
    }

    @GetMapping("/match-list/{matchId}/delete")
    public String deleteMatch(@PathVariable("matchId") Long matchId) {
        matchListService.deleteMatchById(matchId);
        return "redirect:/match-list";
    }
}
