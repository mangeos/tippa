package com.em.tippa.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
import com.em.tippa.dto.RegistrationDto;
import com.em.tippa.models.Group;
import com.em.tippa.models.GroupMembership;
import com.em.tippa.models.Match;
import com.em.tippa.models.MatchList;
import com.em.tippa.models.Point;
import com.em.tippa.models.UserEntity;
import com.em.tippa.repository.GroupMembershipRepository;
import com.em.tippa.repository.GroupRepository;
import com.em.tippa.repository.MatchListRepository;
import com.em.tippa.repository.PointsRepository;
import com.em.tippa.repository.UserRepository;
import com.em.tippa.services.MatchService;

import jakarta.validation.Valid;

@Controller
public class GroupController {
    private MatchService matchListService;
    private MatchListRepository matchListRepository;
    private GroupRepository groupRepository;
    private UserRepository userRepository;
    private GroupMembershipRepository groupMembershipRepository;
    private GroupMembership groupMembership;
    private PointsRepository pointsRepository;

    @Autowired
    public GroupController(GroupRepository groupRepository, UserRepository userRepository,
            MatchService matchListService, GroupMembershipRepository groupMembershipRepository,
            MatchListRepository matchListRepository, PointsRepository pointsRepository) {
        this.matchListService = matchListService;
        this.matchListRepository = matchListRepository;
        this.userRepository = userRepository;
        this.groupRepository = groupRepository;
        this.groupMembershipRepository = groupMembershipRepository;
        this.pointsRepository = pointsRepository;
    }

    @GetMapping("/home")
    public String home(Model model) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername(); // Hämta användarnamn
        UserEntity user = userRepository.findByEmail(username); // Hämta UserEntity från databasen

        if (user != null) {
            System.out.println(user.getId());
            List<GroupMembership> allGroupByCreator = groupMembershipRepository.findByCreateID(user);
            System.out.println(allGroupByCreator);
            model.addAttribute("allGroupByCreator", allGroupByCreator);
            return "home";
        } else {
            System.out.println("User not found");
        }
        return "home";
    }

    @GetMapping("/create-group")
    public String createGroup(Model model) {
        int matchListIdInt = 1; // Exempel på ett int ID
        Long matchListId = Long.valueOf(matchListIdInt);
        List<Match> test = matchListRepository.findMatchesByMatchListId(matchListId);
        // Returnera matcherna som finns i setet
        System.out.println(test);
        model.addAttribute("group", new GroupDto());
        model.addAttribute("matchLists", matchListRepository.findAll());
        model.addAttribute("matchList", new MatchList());

        return "createGroup";
    }

    @PostMapping("/create")
    public String postCreateGroup(@ModelAttribute("group") GroupDto groupDto,
            @ModelAttribute("matchList") MatchList matchList, @RequestParam("username") String username,
            @RequestParam("maxMembers") Long maxMembers) {

        UserEntity creater = userRepository.findByEmail(username);
        groupDto.setCreator(creater);
        groupDto.setMatchList(matchList);
        System.out.println(groupDto);
        System.out.println(matchList);
        System.out.println("ssssssssssssssssssssssssssss");

        // mappa dto till entitet
        Group g = Group.builder()
                .creator(groupDto.getCreator()) // Mappa creator från DTO
                .groupName(groupDto.getGroupName()) // Mappa groupName från DTO
                .matchList(groupDto.getMatchList()) // Mappa matchList från DTO
                .build();

        g.setMaxMembers(maxMembers);
        groupRepository.save(g);
        GroupMembership groupM = GroupMembership.builder()
                .group(g)
                .user(creater)
                .roll("ADMIN")
                .build();
        groupMembershipRepository.save(groupM);
        System.out.println(g);
        Point point = Point.builder().user(creater).group(g).build();
        pointsRepository.save(point);
        // Redirect till en annan sida eller visa ett resultat
        return "redirect:/create-group";
    }

    @GetMapping("/all-groups")
    public String getAllGroups(Model model) {
        List<Group> test = groupRepository.findAll();
        // Returnera matcherna som finns i setet

        System.out.println(test);
        // System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        model.addAttribute("groups", test);
        return "allGroups";
    }

    @PostMapping("/join-group")
    public String joinGroup(@RequestParam("groupID") Long groupID) {

        Group group1 = groupRepository.findById(groupID).get();

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername(); // Hämta användarnamn
        UserEntity user = userRepository.findByEmail(username); // Hämta UserEntity från databasen

        GroupMembership groupM = GroupMembership.builder()
                .group(group1)
                .user(user)
                .roll("User").build();

        try {
            System.out.println("Funkar");
            groupMembershipRepository.save(groupM);
            Point point = Point.builder().user(user).group(group1).build();
            pointsRepository.save(point);
            System.out.println("Funkar");

        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("!!!!!!!!!!!!!error!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            System.out.println(e);
            System.out.println("!!!!!!!!!!!!!!error!!!!!!!!!!!!!!!!!!!!!!!!!!");
        }
        // add user to the group

        return "redirect:/group/" + groupID;
    }

    @GetMapping("/group/{groupID}")
    public String getGroup(@PathVariable("groupID") Long groupID, Model model) {
        // Returnera matcherna som finns i setet
        try {

            List<GroupMembership> group = groupMembershipRepository.findMembershipsByGroupId(groupID);
            GroupMembership membership = group.get(0); // Om objektet finns på index 0
            Long matchListId = membership.getGroup().getMatchList().getMatchListId();

            Optional<MatchList> matchListOptional = matchListRepository.findById(matchListId);

            MatchList matchList = matchListOptional.get();
            List<Match> matches = matchList.getMatches(); // Hämta alla matcher från MatchList
            List<Point> point = pointsRepository.findByGroup_GroupID(groupID);
            System.out.println("sssssssssssssssssssssss");

            point.forEach(p -> System.out.println(p));
            System.out.println("sssssssssssssssssss");
            model.addAttribute("matches", matches);
            model.addAttribute("groupM", group);
            model.addAttribute("point", point);
            return "group";
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e);
            return "redirect:/home";
        }
    }
}