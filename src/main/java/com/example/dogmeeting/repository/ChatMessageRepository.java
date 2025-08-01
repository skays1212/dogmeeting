package com.example.dogmeeting.repository;

import com.example.dogmeeting.entity.ChatMessage;
import com.example.dogmeeting.entity.ChatRoom;
import com.example.dogmeeting.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
    
    List<ChatMessage> findByChatRoomOrderBySentAtAsc(ChatRoom chatRoom);
    
    Page<ChatMessage> findByChatRoomOrderBySentAtDesc(ChatRoom chatRoom, Pageable pageable);
    
    List<ChatMessage> findByChatRoomIdOrderBySentAtAsc(Long chatRoomId);
    
    @Query("SELECT COUNT(cm) FROM ChatMessage cm WHERE cm.chatRoom.id = :chatRoomId AND cm.sender.id != :userId AND cm.read = false")
    long countUnreadMessages(@Param("chatRoomId") Long chatRoomId, @Param("userId") Long userId);
    
    List<ChatMessage> findByChatRoomAndSenderAndReadFalse(ChatRoom chatRoom, User sender);
} 