package com.em.tippa.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.em.tippa.dto.CreateMatchDto;
import com.em.tippa.dto.GetMatchListDto;
import com.em.tippa.dto.GroupDto;
import com.em.tippa.models.MatchList;
import com.em.tippa.models.Match;
import com.em.tippa.models.UserEntity;
import com.em.tippa.repository.MatchListRepository;
import com.em.tippa.repository.MatchRepository;
import com.em.tippa.repository.UserRepository;
import com.em.tippa.services.MatchService;

import jakarta.validation.Valid;

@Controller
public class MatchListController {
    private MatchService matchListService;
    private UserRepository userRepository;
    private MatchListRepository matchListRepository;
    private MatchRepository matchRepository;

    @Autowired
    public MatchListController(MatchService matchListService, UserRepository userRepository,
            MatchListRepository matchListRepository, MatchRepository matchRepository) {
        this.matchListService = matchListService;
        this.userRepository = userRepository;
        this.matchListRepository = matchListRepository;
        this.matchRepository = matchRepository;
    }

    @GetMapping("/match-list")
    public String listMatchList(Model model) {
        // List<GetMatchListDto> matchList = matchListService.findAllMatches();
        List<GetMatchListDto> matches = this.matchListService.findAllMatches();
        // model.addAttribute("matchList", matchList);
        model.addAttribute("matches", matches);
        // skapar en tom matchlist som jag anvander till post request
        model.addAttribute("newmatch", new MatchList());
        return "match-list";
    }

    @PostMapping("/match-list")
    public String saveNewMatch(@RequestParam("selectedMatchIds") List<Long> selectedMatchIds,
            @RequestParam("matchListName") String matchListName) {
        // Hantera formulärdata här (ex. spara till databas)
        // Match match = matchListService.findMatchById(selectedMatchId);
        // matchListService.saveMatch(createMatchListDto);
        // Hämta matcher från deras ID:n
        List<Match> matches = new ArrayList<>();
        for (Long matchId : selectedMatchIds) {
            Match match = matchRepository.findByMatchId(matchId);
            matches.add(match);
        }

        // Skapa en ny MatchList
        MatchList matchList = new MatchList();
        matchList.setName(matchListName);
        matchList.setMatches(matches); // Tilldela matcher till MatchList

        // Spara MatchList (matcher sparas i samband med detta pga. bidirectional
        // relationship)
        matchListRepository.save(matchList);
        return "redirect:/match-list";
    }

    @GetMapping("/meny")
    public String meny(Model model) {

        return "meny";
    }

    @GetMapping("/match-list/{matchId}/delete")
    public String deleteMatch(@PathVariable("matchId") Long matchId) {
        matchListService.deleteMatchById(matchId);
        return "redirect:/match-list";
    }

    @GetMapping("/match")
    public String Match(@Valid @ModelAttribute("match") CreateMatchDto createMatchDto,
            BindingResult bindingResult) {

        // System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        // UserEntity test =
        // this.userRepository.findByEmail("mange.ostling@protonmail.com");
        // System.out.println(test);
        return "createMatch";
    }

    @PostMapping("/match")
    public String postCreateMatch(@Valid @ModelAttribute("match") CreateMatchDto createMatchDto,
            BindingResult bindingResult) {
        System.out.println(createMatchDto);
        if (bindingResult.hasErrors()) {
            return "redirect:/match";
        }
        matchListService.saveMatch(createMatchDto);
        return "redirect:/match";
    }

}
