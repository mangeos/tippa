package com.em.tippa.repository;

import com.em.tippa.models.Group;
import com.em.tippa.models.GroupMembership;
import com.em.tippa.models.Match;
import com.em.tippa.models.UserEntity;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupMembershipRepository extends JpaRepository<GroupMembership, Long> {
    // Lägg till anpassade metoder här om du behöver ytterligare
    // dataåtkomstoperationer för Membership-modellen
    @Query("SELECT g FROM GroupMembership g WHERE g.user = :id")
    List<GroupMembership> findByCreateID(@Param("id") UserEntity id);

    @Query(value = "select * from group_memberships as gm join users as u on u.id = gm.userid where gm.groupid = :groupId", nativeQuery = true)
    List<GroupMembership> findMembershipsByGroupId(@Param("groupId") Long groupId);
}